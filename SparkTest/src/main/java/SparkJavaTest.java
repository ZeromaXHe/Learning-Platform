import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.ml.classification.LogisticRegression;
import org.apache.spark.ml.classification.LogisticRegressionModel;
import org.apache.spark.ml.feature.FeatureHasher;
import org.apache.spark.ml.feature.StringIndexer;
import org.apache.spark.ml.feature.StringIndexerModel;
import org.apache.spark.mllib.evaluation.MulticlassMetrics;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.io.File;


/**
 * @Author: zhuxi
 * @Time: 2021/8/10 14:26
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class SparkJavaTest {
    public static void main(String[] args) {
        // 不加这个的话，中间INFO日志太多了
        Logger.getLogger("org").setLevel(Level.WARN);
        Logger.getLogger("akka").setLevel(Level.WARN);

        SparkSession spark = SparkSession.builder()
                .appName("SparkCtrLrTest")
                .master("local[2]")
                .getOrCreate();

        File inputFile = new File("./SparkTest/src/main/resources/ctr_data.csv");
        System.out.println(inputFile.exists());

        Dataset<Row> data = spark.read().csv("file:///" + inputFile.getAbsolutePath()).toDF(
                "id", "click", "hour", "C1", "banner_pos", "site_id", "site_domain",
                "site_category", "app_id", "app_domain", "app_category", "device_id", "device_ip",
                "device_model", "device_type", "device_conn_type", "C14", "C15", "C16", "C17", "C18",
                "C19", "C20", "C21");

        data.show(5, false);

        Dataset<Row>[] split = data.randomSplit(new double[]{0.7, 0.3}, 2L);
        String[] catalog_features = new String[]{"click", "site_id", "site_domain", "site_category", "app_id", "app_domain", "app_category", "device_id", "device_ip", "device_model"};
        Dataset<Row> train_index = split[0];
        Dataset<Row> test_index = split[1];
        for (String catalog_feature : catalog_features) {
            // 对于分类特征可以使用StringIndexer将标签的字符串列编码为标签索引列，将字符串特征转化为数值特征，便于下游管道组件处理。
            StringIndexer indexer = new StringIndexer()
                    .setInputCol(catalog_feature)
                    .setOutputCol(catalog_feature.concat("_index"));
            StringIndexerModel train_index_model = indexer.fit(train_index);
            Dataset<Row> train_indexed = train_index_model.transform(train_index);
            Dataset<Row> test_indexed = indexer.fit(test_index).transform(test_index, train_index_model.extractParamMap());
            train_index = train_indexed;
            test_index = test_indexed;
        }
        System.out.println("字符串编码下标标签：");
        train_index.show(5, false);
        test_index.show(5, false);
        //    特征Hasher
        // 特征哈希将一组分类或数值特征投射到指定维的特征向量(通常比原始特征空间小很多)。这是使用哈希技巧将特征映射到特征向量中的索引。
        FeatureHasher hasher = new FeatureHasher()
                .setInputCols(new String[]{"site_id_index", "site_domain_index", "site_category_index", "app_id_index", "app_domain_index", "app_category_index", "device_id_index", "device_ip_index", "device_model_index", "device_type", "device_conn_type", "C14", "C15", "C16", "C17", "C18", "C19", "C20", "C21"})
                .setOutputCol("feature");
        System.out.println("特征Hasher编码：");
        Dataset<Row> train_hs = hasher.transform(train_index);
        Dataset<Row> test_hs = hasher.transform(test_index);

        /**
         * LR建模
         * setMaxIter设置最大迭代次数(默认100),具体迭代次数可能在不足最大迭代次数停止(见下一条)
         * setTol设置容错(默认1e-6),每次迭代会计算一个误差,误差值随着迭代次数增加而减小,当误差小于设置容错,则停止迭代
         * setRegParam设置正则化项系数(默认0),正则化主要用于防止过拟合现象,如果数据集较小,特征维数又多,易出现过拟合,考虑增大正则化系数
         * setElasticNetParam正则化范式比(默认0),正则化有两种方式:L1(Lasso)和L2(Ridge),L1用于特征的稀疏化,L2用于防止过拟合
         * setLabelCol设置标签列
         * setFeaturesCol设置特征列
         * setPredictionCol设置预测列
         * setThreshold设置二分类阈值
         */
        LogisticRegression lr = new LogisticRegression()
                .setMaxIter(10)
                .setRegParam(0.3)
                .setElasticNetParam(0)
                .setFeaturesCol("feature")
                .setLabelCol("click_index")
                .setPredictionCol("click_predict");
        LogisticRegressionModel model_lr = lr.fit(train_hs);

        // 使用 model_lr.coefficients() 的话，报错org.apache.spark.SparkException:
        // Multinomial models contain a matrix of coefficients, use coefficientMatrix instead.
        // 使用 model_lr.intercept() 的话，报错org.apache.spark.SparkException:
        // Multinomial models contain a vector of intercepts, use interceptVector instead.
        System.out.println("每个特征对应系数: " + model_lr.coefficientMatrix() + " 截距: " + model_lr.interceptVector());

        Dataset<Row> predictions = model_lr.transform(test_hs);
        predictions.select("click_index", "click_predict", "probability").show(100, false);
        // scala 版本的 case语句感觉没有处理什么东西，而且不知道怎么翻译成java的，所以直接合并了。
        // 根据运行结果，应该是没有影响metrics
        MulticlassMetrics metrics = new MulticlassMetrics(predictions.select("click_predict", "click_index"));
        double accuracy = metrics.accuracy();
        double weightedPrecision = metrics.weightedPrecision();
        double weightedRecall = metrics.weightedRecall();
        double f1 = metrics.weightedFMeasure();
        System.out.println("LR评估结果：\n" +
                "分类正确率：" + accuracy + "\n" +
                "加权正确率：" + weightedPrecision + "\n" +
                "加权召回率：" + weightedRecall + "\n" +
                "F1值：" + f1);
    }
}
