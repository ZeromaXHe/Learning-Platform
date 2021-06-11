# 一、MLlib基本指南

http://spark.apache.org/docs/2.4.8/ml-guide.html

## 1.1 基本统计

### 1.1.1 相关性

计算两组数据之间的相关性是统计的常见操作。在`spark.ml`中，我们提供了计算多组数据之间成对相关性的灵活手段。支持的相关方法目前有Pearson和Spearman相关。

Correlation类会对输入的Vectors数据组使用特定方法计算相关矩阵。输出包含由向量列组成的相关矩阵的DataFrame对象

~~~java
import java.util.Arrays;
import java.util.List;

import org.apache.spark.ml.linalg.Vectors;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.stat.Correlation;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.types.*;

List<Row> data = Arrays.asList(
  RowFactory.create(Vectors.sparse(4, new int[]{0, 3}, new double[]{1.0, -2.0})),
  RowFactory.create(Vectors.dense(4.0, 5.0, 0.0, 3.0)),
  RowFactory.create(Vectors.dense(6.0, 7.0, 0.0, 8.0)),
  RowFactory.create(Vectors.sparse(4, new int[]{0, 3}, new double[]{9.0, 1.0}))
);

StructType schema = new StructType(new StructField[]{
  new StructField("features", new VectorUDT(), false, Metadata.empty()),
});

Dataset<Row> df = spark.createDataFrame(data, schema);
Row r1 = Correlation.corr(df, "features").head();
System.out.println("Pearson correlation matrix:\n" + r1.get(0).toString());

Row r2 = Correlation.corr(df, "features", "spearman").head();
System.out.println("Spearman correlation matrix:\n" + r2.get(0).toString());
~~~

### 1.1.2 假设检验

假设检验是统计中的强大工具，可以用来决定结果是否统计显著，或者说结果是否碰巧发生。`spark.ml`现在支持Pearson卡方检验独立性测试。

ChiSquareTest 针对标签对每个特征进行 Pearson 独立性测试。对于每个特征，（特征，标签）对被转换为权变矩阵，计算卡方统计量。所有标签和特征值必须是类别型的

~~~java
import java.util.Arrays;
import java.util.List;

import org.apache.spark.ml.linalg.Vectors;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.stat.ChiSquareTest;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.types.*;

List<Row> data = Arrays.asList(
  RowFactory.create(0.0, Vectors.dense(0.5, 10.0)),
  RowFactory.create(0.0, Vectors.dense(1.5, 20.0)),
  RowFactory.create(1.0, Vectors.dense(1.5, 30.0)),
  RowFactory.create(0.0, Vectors.dense(3.5, 30.0)),
  RowFactory.create(0.0, Vectors.dense(3.5, 40.0)),
  RowFactory.create(1.0, Vectors.dense(3.5, 40.0))
);

StructType schema = new StructType(new StructField[]{
  new StructField("label", DataTypes.DoubleType, false, Metadata.empty()),
  new StructField("features", new VectorUDT(), false, Metadata.empty()),
});

Dataset<Row> df = spark.createDataFrame(data, schema);
Row r = ChiSquareTest.test(df, "features", "label").head();
System.out.println("pValues: " + r.get(0).toString());
System.out.println("degreesOfFreedom: " + r.getList(1).toString());
System.out.println("statistics: " + r.get(2).toString());
~~~

### 1.1.3 Summarizer

我们通过Summarizer为Dataframe向量列提供汇总统计。支持的方法有列维度的最大值、最小值、平均值、方差、非零数量和总数。

下面是使用Summarizer计算输入dataframe向量列的平均值和方差。

~~~java
import java.util.Arrays;
import java.util.List;

import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.Vectors;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.stat.Summarizer;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

List<Row> data = Arrays.asList(
  RowFactory.create(Vectors.dense(2.0, 3.0, 5.0), 1.0),
  RowFactory.create(Vectors.dense(4.0, 6.0, 7.0), 2.0)
);

StructType schema = new StructType(new StructField[]{
  new StructField("features", new VectorUDT(), false, Metadata.empty()),
  new StructField("weight", DataTypes.DoubleType, false, Metadata.empty())
});

Dataset<Row> df = spark.createDataFrame(data, schema);

Row result1 = df.select(Summarizer.metrics("mean", "variance")
  .summary(new Column("features"), new Column("weight")).as("summary"))
  .select("summary.mean", "summary.variance").first();
System.out.println("with weight: mean = " + result1.<Vector>getAs(0).toString() +
  ", variance = " + result1.<Vector>getAs(1).toString());

Row result2 = df.select(
  Summarizer.mean(new Column("features")),
  Summarizer.variance(new Column("features"))
).first();
System.out.println("without weight: mean = " + result2.<Vector>getAs(0).toString() +
  ", variance = " + result2.<Vector>getAs(1).toString());
~~~

## 1.2 数据源

这一节，我们将介绍如何使用ML中的数据源来加载数据。除了一些常见的数据源例如Parquet、CSV、JSON和JDBC，我们也为ML提供一些特别的数据源

### 1.2.1 图片数据源

## 1.3 ML流水线

这一节，我们介绍ML流水线的概念。ML流水线提供一组统一的基于DataFrames构建的高级API，以帮助用户创建和调试实用的机器学习流水线。

### 1.3.1 流水线的主要概念

MLlib为机器学习算法提供了标准化的API，使得将组合多种算法成一个流水线或工作流的工作变得简单。这节包括了流水线API引入的关键概念，这些流水线概念大部分受到了scikit-learn项目的启发。

- DataFrame：ML API使用Spark SQL的DataFrame类作为一个ML的数据集，它可以存储多样化的数据类型。例如，一个DataFrame能够拥有不同的列分别存储文本，特征向量，真实标签和预测结果。
- Transformer：一个Transformer是能将一个DataFrame转换成另一个DataFrame的算法。例如，一个ML模型是一个将包含特征信息的DataFrame转换为包含预测信息的DataFrame的Transformer。
- Estimator：一个Estimator是能够匹配DataFrame来生成Transformer的算法。例如，一个学习算法是一个基于DataFrame训练并制造出模型的Estimator。
- Pipeline：Pipeline将多个Transformer和Estimator连接起来，来指定一个ML工作流。
- Parameter：所有的Transformer和Estimator现在共享了一套通用API来指定参数。

#### DataFrame

机器学习可以被应用到非常广范围的数据类型上，例如向量、文本、图片和结构化数据。这个API采用来自Spark SQL中的DataFrame来支持多样的数据类型。

DataFrame支持很多基础和结构化数据，可以参考[Spark SQL数据类型](http://spark.apache.org/docs/2.4.8/sql-reference.html#data-types)。除了在Spark SQL指南中列出的数据类型，DataFrame还可以使用ML的Vector类型。

DataFrame能显式或隐式地通过RDD创建。在Java API中，用户需要使用`Dataset<Row>`来表示一个DataFrame。参见[Spark SQL 编程指南](http://spark.apache.org/docs/2.4.8/sql-programming-guide.html)。

DataFrame中的列是被命名的。下面的代码用例中使用类似"text", "features"和"label"的名字。

#### Pipeline 组件

##### Transformers

Transformer是一种类如特征转换器或学习出的模型这些东西的抽象。技术层面而言，Transformer实现了transform()方法，这个方法将一个DataFrame转换为另一个，一般是增加一个或更多列。例如：

- 一个特征转换器可能将获取一个DataFrame，读一列（例如文本），将其映射到一个新的列（例如特征向量），然后输出一个包含了新增映射列的新DataFrame。
- 一个学习模型可能获取一个DataFrame，读取包含特征向量的列，为每个特征向量预测标签，然后输出一个包含预测标签新增列的新DataFrame。

##### Estimators

Estimator抽象了学习算法或任何基于数据匹配/训练算法的概念。技术层面而言，Estimator实现了fit()方法，这个方法接收一个DataFrame返回一个Model，Model即一个Transformer。例如，一个学习算法例如LogisticRegression，是一个Estimator。调用fit()方法训练一个LogisticRegressionModel，它是一个Model，因此也是一个Transformer。

##### 流水线组件的属性

Transformer.transform() 和 Estimator.fit() 都是无状态的。将来，有状态算法可能通过其他概念来支持。

每个Transformer或Estimator实例都有一个独一无二的ID，这在指定参数的时候非常有用。

#### Pipeline

在机器学习中，普遍需要跑一系列的算法来处理和学习数据。例如，简单的文本文件处理工作流可能包含以下步骤：

- 将每个文件的文本分割为词。
- 将每个文本中的词转换为数字特征向量。
- 使用特征向量和标签学习一个预测模型。

MLlib使用Pipeline来表示这样的工作流，包含了一系列的按指定顺序排列的要运行的PipelineStage（Transformer和Estimator）。本节中，我们将使用这个简单的工作流作为一个例子。

##### 运行原理

Pipeline是一系列的步骤组成的，每个步骤要么是Transformer要么是Estimator。这些步骤按顺序运行，输入的DataFrame传递过每一个步骤并被转换。对于Transformer步骤，在DataFrame上transform()方法被调用。对于Estimator步骤，为了生成一个Transformer（它成为PipelineModel或合适的Pipeline的一部分），fit()方法被调用。生成的Transformer的transform()方法在DataFrame上被调用。

我们为前面提到的简单文本文档工作流做下阐述。下图表现的是在训练阶段使用Pipeline。

~~~mermaid
graph LR
	subgraph Pipeline.fit
		rt(Raw text) --> w(Words)
		w --> fv(Feature vectors)
		fv --> lrm[LogisticRegressionModel]
	end
	
	subgraph Pipeline-Estimator
		t[Tokenizer] --> h[HashingTF] 
		h --> lr[LogisticRegression]
	end
	
	style t fill:#FFF,stroke:#00F,stroke-width:3px
	style h fill:#FFF,stroke:#00F,stroke-width:3px
	style lr fill:#FFF,stroke:#F00,stroke-width:3px
	style lrm fill:#FFF,stroke:#00F,stroke-width:3px
~~~

上面可以看到，上面一行表示一个三阶段的Pipeline。前两个（Tokenizer和HashingTF）是Transformer（蓝色的），第三个（LogisticRegression）是一个Estimator（红色）。下面一行表示流水线中的数据流，紫色圆角框代表的是DataFrame。Pipeline.fit()方法调用在最初的包含原始文本文件和标签的DataFrame上。Tokenizer.transform()方法将原始文本文档分割为词，为DataFrame添加一个新的词列。HashingTF.transform()方法将词列转换为特征向量，为DataFrame添加一个新的包含这些向量的列。现在，因为LogisticRegression是一个Estimator，Pipeline首先调用LogisticRegression.fit()来生成一个LogisticRegressionModel。如果Pipeline有更多Estimator，它将在传递DataFrame到下一个步骤前，在DataFrame上调用LogisticRegressionModel的transform()方法。

Pipeline是一个Estimator。因此，在Pipeline.fit()方法运行后，它将产生一个PipelineModel，这是一个Transformer。这个PipelineModel是在测试阶段使用，下图阐释了这个用法。

~~~mermaid
graph LR
	subgraph PipelineModel.transform
		rt(Raw text) --> w(Words)
		w --> fv(Feature vectors)
		fv --> p(Predictions)
	end
	
	subgraph PipelineModel-Transformer
		t[Tokenizer] --> h[HashingTF] 
		h --> lrm[LogisticRegression]
	end
	
	style t fill:#FFF,stroke:#00F,stroke-width:3px
	style h fill:#FFF,stroke:#00F,stroke-width:3px
	style lrm fill:#FFF,stroke:#00F,stroke-width:3px
~~~

上图中，PipelineModel和最初的Pipeline拥有相同数量的步骤，但在原来Pipeline中所有Estimator已经被转换为Transformer。当对测试数据集调用PipelineModel的tranform()方法时，数据将按顺序在合适的流水线间传递。每个阶段的transform()方法更新了数据集，并将其传递给下一个阶段。

Pipeline和PipelineModel有助于确保训练和测试数据经过相同的特征处理步骤。

##### 细节

DAG Pipeline：一个Pipeline的多个阶段是按有序数组指定的。这里给出的示例是针对线性Pipeline的，也就是说，每个阶段使用上一个阶段生成数据的Pipeline。只要数据流图构造的是一个有向无环图（DAG），那么就可以创建一个非线性的Pipeline。该图目前是根据每个阶段的输入和输出列名称隐式指定的（一般指定为参数）。如果流水线形成 DAG，则必须按拓扑顺序指定阶段。

运行时检查：由于Pipeline可以对具有不同类型的 DataFrame 进行操作，因此它们不能使用编译时类型检查。因此，Pipeline和PipelineModel在实际运行流水线之前进行运行时检查。 这种类型检查是使用 DataFrame 模式完成的，这是对 DataFrame 中列的数据类型的描述。

独特的Pipeline阶段：Pipeline的阶段应该是唯一的实例。 例如，同一个实例 myHashingTF 不应两次插入Pipeline，因为Pipeline阶段必须具有唯一的 ID。 但是，不同的实例 myHashingTF1 和 myHashingTF2（都是 HashingTF 类型）可以放入同一个管道中，因为不同的实例将使用不同的 ID 创建。

#### Parameters

MLlib Estimator 和 Transformer 使用统一的 API 来指定参数。

Param 是具有独立文档的命名参数。 ParamMap 是一组（参数，值）对。

向算法传递参数有两种主要方式：

1. 为实例设置参数。 例如，如果 lr 是 LogisticRegression 的一个实例，则可以调用 lr.setMaxIter(10) 使 lr.fit() 最多使用 10 次迭代。 此 API 类似于 spark.mllib 包中使用的 API。
2. 将 ParamMap 传递给 fit() 或 transform()。 ParamMap 中的任何参数都将覆盖先前通过 setter 方法指定的参数。

参数属于 Estimators 和 Transformers 的特定实例。 例如，如果我们有两个 LogisticRegression 实例 lr1 和 lr2，那么我们可以构建一个 ParamMap，同时指定两个 maxIter 参数：ParamMap(lr1.maxIter -> 10, lr2.maxIter -> 20)。 如果流水线中有两种带有 maxIter 参数的算法，这将很有用。

### 1.3.2 ML持久化：保存和加载流水线

通常，将模型或流水线保存到磁盘以备后用是值得的。 在 Spark 1.6 中，Pipeline API 中添加了模型导入/导出功能。 从 Spark 2.3 开始，spark.ml 和 pyspark.ml 中基于 DataFrame 的 API 已完全覆盖。

ML 持久性适用于 Scala、Java 和 Python。 但是，R 目前使用的是修改过的格式，因此保存在 R 中的模型只能在 R 中加载回来。

#### ML 持久性的向后兼容性

通常，MLlib 维护 ML 持久性的向后兼容性。即，如果您将 ML 模型或 Pipeline 保存在一个版本的 Spark 中，那么您应该能够重新加载它并在未来版本的 Spark 中使用它。但是，也有少数例外，如下所述。

模型持久性：在 Spark 版本 X 中使用 Apache Spark ML 持久性保存的模型或 Pipeline 是否可由 Spark 版本 Y 加载？

- 主要版本：没有保证，但尽力而为。
- 次要版本和补丁版本：是；这些是向后兼容的。
- 关于格式的注意事项：无法保证稳定的持久性格式，但模型加载本身旨在向后兼容。

模型行为：Spark 版本 X 中的模型或 Pipeline 在 Spark 版本 Y 中的行为是否相同？

- 主要版本：没有保证，但尽力而为。
- 次要版本和补丁版本：相同的行为，除了错误修复。

对于模型持久性和模型行为，小版本或补丁版本中的任何重大更改都会在 Spark 版本发行说明中报告。如果发行说明中未报告损坏，则应将其视为要修复的bug。

### 1.3.3 代码示例

这一节的代码示例展示了上面讨论过的功能。

#### 示例：Estimator，Transformer和Param

~~~java
import java.util.Arrays;
import java.util.List;

import org.apache.spark.ml.classification.LogisticRegression;
import org.apache.spark.ml.classification.LogisticRegressionModel;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.linalg.Vectors;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

// Prepare training data.
List<Row> dataTraining = Arrays.asList(
    RowFactory.create(1.0, Vectors.dense(0.0, 1.1, 0.1)),
    RowFactory.create(0.0, Vectors.dense(2.0, 1.0, -1.0)),
    RowFactory.create(0.0, Vectors.dense(2.0, 1.3, 1.0)),
    RowFactory.create(1.0, Vectors.dense(0.0, 1.2, -0.5))
);
StructType schema = new StructType(new StructField[]{
    new StructField("label", DataTypes.DoubleType, false, Metadata.empty()),
    new StructField("features", new VectorUDT(), false, Metadata.empty())
});
Dataset<Row> training = spark.createDataFrame(dataTraining, schema);

// Create a LogisticRegression instance. This instance is an Estimator.
LogisticRegression lr = new LogisticRegression();
// Print out the parameters, documentation, and any default values.
System.out.println("LogisticRegression parameters:\n" + lr.explainParams() + "\n");

// We may set parameters using setter methods.
lr.setMaxIter(10).setRegParam(0.01);

// Learn a LogisticRegression model. This uses the parameters stored in lr.
LogisticRegressionModel model1 = lr.fit(training);
// Since model1 is a Model (i.e., a Transformer produced by an Estimator),
// we can view the parameters it used during fit().
// This prints the parameter (name: value) pairs, where names are unique IDs for this
// LogisticRegression instance.
System.out.println("Model 1 was fit using parameters: " + model1.parent().extractParamMap());

// We may alternatively specify parameters using a ParamMap.
ParamMap paramMap = new ParamMap()
    .put(lr.maxIter().w(20))  // Specify 1 Param.
    .put(lr.maxIter(), 30)  // This overwrites the original maxIter.
    .put(lr.regParam().w(0.1), lr.threshold().w(0.55));  // Specify multiple Params.

// One can also combine ParamMaps.
ParamMap paramMap2 = new ParamMap()
    .put(lr.probabilityCol().w("myProbability"));  // Change output column name
ParamMap paramMapCombined = paramMap.$plus$plus(paramMap2);

// Now learn a new model using the paramMapCombined parameters.
// paramMapCombined overrides all parameters set earlier via lr.set* methods.
LogisticRegressionModel model2 = lr.fit(training, paramMapCombined);
System.out.println("Model 2 was fit using parameters: " + model2.parent().extractParamMap());

// Prepare test documents.
List<Row> dataTest = Arrays.asList(
    RowFactory.create(1.0, Vectors.dense(-1.0, 1.5, 1.3)),
    RowFactory.create(0.0, Vectors.dense(3.0, 2.0, -0.1)),
    RowFactory.create(1.0, Vectors.dense(0.0, 2.2, -1.5))
);
Dataset<Row> test = spark.createDataFrame(dataTest, schema);

// Make predictions on test documents using the Transformer.transform() method.
// LogisticRegression.transform will only use the 'features' column.
// Note that model2.transform() outputs a 'myProbability' column instead of the usual
// 'probability' column since we renamed the lr.probabilityCol parameter previously.
Dataset<Row> results = model2.transform(test);
Dataset<Row> rows = results.select("features", "label", "myProbability", "prediction");
for (Row r: rows.collectAsList()) {
    System.out.println("(" + r.get(0) + ", " + r.get(1) + ") -> prob=" + r.get(2)
                       + ", prediction=" + r.get(3));
}
~~~

#### 示例：Pipeline

~~~java
import java.util.Arrays;

import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.classification.LogisticRegression;
import org.apache.spark.ml.feature.HashingTF;
import org.apache.spark.ml.feature.Tokenizer;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

// Prepare training documents, which are labeled.
Dataset<Row> training = spark.createDataFrame(Arrays.asList(
    new JavaLabeledDocument(0L, "a b c d e spark", 1.0),
    new JavaLabeledDocument(1L, "b d", 0.0),
    new JavaLabeledDocument(2L, "spark f g h", 1.0),
    new JavaLabeledDocument(3L, "hadoop mapreduce", 0.0)
), JavaLabeledDocument.class);

// Configure an ML pipeline, which consists of three stages: tokenizer, hashingTF, and lr.
Tokenizer tokenizer = new Tokenizer()
    .setInputCol("text")
    .setOutputCol("words");
HashingTF hashingTF = new HashingTF()
    .setNumFeatures(1000)
    .setInputCol(tokenizer.getOutputCol())
    .setOutputCol("features");
LogisticRegression lr = new LogisticRegression()
    .setMaxIter(10)
    .setRegParam(0.001);
Pipeline pipeline = new Pipeline()
    .setStages(new PipelineStage[] {tokenizer, hashingTF, lr});

// Fit the pipeline to training documents.
PipelineModel model = pipeline.fit(training);

// Prepare test documents, which are unlabeled.
Dataset<Row> test = spark.createDataFrame(Arrays.asList(
    new JavaDocument(4L, "spark i j k"),
    new JavaDocument(5L, "l m n"),
    new JavaDocument(6L, "spark hadoop spark"),
    new JavaDocument(7L, "apache hadoop")
), JavaDocument.class);

// Make predictions on test documents.
Dataset<Row> predictions = model.transform(test);
for (Row r : predictions.select("id", "text", "probability", "prediction").collectAsList()) {
    System.out.println("(" + r.get(0) + ", " + r.get(1) + ") --> prob=" + r.get(2)
                       + ", prediction=" + r.get(3));
}
~~~

### 1.3.4 模型选择（超参数调试）

使用ML流水线的一大好处是超参数优化。

## 1.4 提取，转换和选择特征

