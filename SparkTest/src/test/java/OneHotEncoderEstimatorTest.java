import java.util.Arrays;
import java.util.List;

import org.apache.spark.ml.feature.OneHotEncoderEstimator;
import org.apache.spark.ml.feature.OneHotEncoderModel;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

/**
 * @Author: zhuxi
 * @Time: 2021/8/12 11:11
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class OneHotEncoderEstimatorTest {
    public static void main(String[] args) {
        SparkSession spark = SparkJavaTest.getSparkSession();

        List<Row> data = Arrays.asList(
                RowFactory.create(0.0, 1.0),
                RowFactory.create(1.0, 0.0),
                RowFactory.create(2.0, 1.0),
                RowFactory.create(0.0, 2.0),
                RowFactory.create(0.0, 1.0),
                RowFactory.create(2.0, 0.0)
        );

        StructType schema = new StructType(new StructField[]{
                new StructField("categoryIndex1", DataTypes.DoubleType, false, Metadata.empty()),
                new StructField("categoryIndex2", DataTypes.DoubleType, false, Metadata.empty())
        });

        Dataset<Row> df = spark.createDataFrame(data, schema);

        OneHotEncoderEstimator encoder = new OneHotEncoderEstimator()
                // 加上这里任意一行，输出列的维度变为3
//                .setHandleInvalid("keep")
//                .setDropLast(false)
                .setInputCols(new String[]{"categoryIndex1", "categoryIndex2"})
                .setOutputCols(new String[]{"categoryVec1", "categoryVec2"});

        System.out.println("encoder.getDropLast():" + encoder.getDropLast());

        OneHotEncoderModel model = encoder.fit(df);
        Dataset<Row> encoded = model.transform(df);
        encoded.show();
        // +--------------+--------------+-------------+-------------+
        //|categoryIndex1|categoryIndex2| categoryVec1| categoryVec2|
        //+--------------+--------------+-------------+-------------+
        //|           0.0|           1.0|(2,[0],[1.0])|(2,[1],[1.0])|
        //|           1.0|           0.0|(2,[1],[1.0])|(2,[0],[1.0])|
        //|           2.0|           1.0|    (2,[],[])|(2,[1],[1.0])|
        //|           0.0|           2.0|(2,[0],[1.0])|    (2,[],[])|
        //|           0.0|           1.0|(2,[0],[1.0])|(2,[1],[1.0])|
        //|           2.0|           0.0|    (2,[],[])|(2,[0],[1.0])|
        //+--------------+--------------+-------------+-------------+
        // 其中输出列类型为VectorUDT，打印出数据的意思应该是第一位表示维数，第二个是索引位置，第三个表示对应的值

        System.out.println(Arrays.toString(encoded.dtypes()));
//        Column categoryVec1 = encoded.col("categoryVec1");
    }
}
