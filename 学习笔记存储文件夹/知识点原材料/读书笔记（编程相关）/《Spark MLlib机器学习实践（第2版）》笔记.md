# 第1章 星星之火

## 1.1 大数据时代

飞速产生的数据构建了大数据， 海量数据的时代我们称为大数据时代。 但是， 简单地认为那些掌握了海量存储数据资料的人是大数据强者显然是不对的。 真正的强者是那些能够挖掘出隐藏在海量数据背后获取其中所包含的巨量数据信息与内容的人， 是那些掌握专门技能懂得怎样对数据进行有目的、 有方向地处理的人。   

## 1.2 大数据分析时代

一般来说， 大数据分析需要涉及以下5个方面

**1． 有效的数据质量**

任何数据分析都来自于真实的数据基础， 而一个真实数据是采用标准化的流程和工具对数据进行处理得到的， 可以保证一个预先定义好的高质量的分析结果。

**2． 优秀的分析引擎**

对于大数据来说， 数据的来源多种多样， 特别是非结构化数据来源的多样性给大数据分析带来了新的挑战。 因此， 我们需要一系列的工具去解析、 提取、 分析数据。 大数据分析引擎就是用于从数据中提取我们所需要的信息。

**3． 合适的分析算法**

采用合适的大数据分析算法能让我们深入数据内部挖掘价值。 在算法的具体选择上， 不仅仅要考虑能够处理的大数据的数量， 还要考虑到对大数据处理的速度。

**4． 对未来的合理预测**

数据分析的目的是对已有数据体现出来的规律进行总结， 并且将现象与其他情况紧密连接在一起， 从而获得对未来发展趋势的预测。 大数据分析也是如此。 不同的是， 在大数据分析中， 数据来源的基础更为广泛， 需要处理的方面更多。

**5． 数据结果的可视化**

大数据的分析结果更多是为决策者和普通用户提供决策支持和意见提示， 其对较为深奥的数学含义不会太了解。 因此必然要求数据的可视化能够直观地反映出经过分析后得到的信息与内容， 能够较为容易地被使用者所理解和接受。

因此可以说， 大数据分析是数据分析最前沿的技术。 这种新的数据分析是目标导向的， 不用关心数据的来源和具体格式， 能够根据我们的需求去处理各种结构化、 半结构化和非结构化的数据， 配合使用合适的分析引擎， 能够输出有效结果， 提供一定的对未来趋势的预测分析服务， 能够面向更广泛的用户快速部署数据分析应用。  

## 1.3 简单、优雅、有效——这就是Spark

Apache Spark是加州大学伯克利分校的AMPLabs开发的开源分布式轻量级通用计算框架。 与传统的数据分析框架相比， Spark在设计之初就是基于内存而设计， 因此其比一般的数据分析框架有着更高的处理性能， 并且对多种编程语言， 例如Java、 Scala及Python等提供编译支持，使得用户在使用传统的编程语言即可对其进行程序设计， 从而使得用户的学习和维护能力大大提高。

简单、 优雅、 有效——这就是Spark！

Spark是一个简单的大数据处理框架， 可以使程序设计人员和数据分析人员在不了解分布式底层细节的情况下， 就像编写一个简单的数据处理程序一样对大数据进行分析计算。

Spark是一个优雅的数据处理程序， 借助于Scala函数式编程语言，以前往往几百上千行的程序， 这里只需短短几十行即可完成。 Spark创新了数据获取和处理的理念， 简化了编程过程， 不再需要使用以往的建立索引来对数据分类， 通过相应的表链接将需要的数据匹配成我们需要的格式。 Spark没有臃肿， 只有优雅。

Spark是一款有效的数据处理工具程序， 充分利用集群的能力对数据进行处理， 其核心就是MapReduce数据处理。 通过对数据的输入、 分拆与组合， 可以有效地提高数据管理的安全性， 同时能够很好地访问管理的数据。

Spark是建立在JVM上的开源数据处理框架， 开创性地使用了一种从最底层结构上就与现有技术完全不同， 但是更加具有先进性的数据存储和处理技术， 这样使用Spark时无须掌握系统的底层细节， 更不需要购买价格不菲的软硬件平台， 借助于架设在普通商用机上的HDFS存储系统， 可以无限制地在价格低廉的商用PC上搭建所需要规模的评选数据分析平台。 即使从只有一台商用PC的集群平台开始， 也可以在后期任意扩充其规模。

Spark是基于MapReduce并行算法实现的分布式计算， 其拥有MapReduce的优点， 对数据分析细致而准确。 更进一步， Spark数据分析的结果可以保持在分布式框架的内存中， 从而使得下一步的计算不再频繁地读写HDFS， 使得数据分析更加快速和方便。  

> **提示**
>
> 需要注意的是， Spark并不是“仅”使用内存作为分析和处理的存储空间， 而是和HDFS交互使用， 首先尽可能地采用内存空间， 当内存使用达到一定阈值时， 仍会将数据存储在HDFS上。  

除此之外， Spark通过HDFS使用自带的和自定义的特定数据格式（RDD） ， Spark基本上可以按照程序设计人员的要求处理任何数据，不论这个数据类型是什么样的， 数据可以是音乐、 电影、 文本文件、Log记录等。 通过编写相应的Spark处理程序， 帮助用户获得任何想要的答案。

有了Spark后， 再没有数据被认为是过于庞大而不好处理或存储的了， 从而解决了之前无法解决的、 对海量数据进行分析的问题， 便于发现海量数据中潜在的价值。  

## 1.4 核心——MLlib

如果将Spark比作一个闪亮的星星的话， 那么其中最明亮最核心的部分就是MLlib。 MLlib是一个构建在Spark上的、 专门针对大数据处理的并发式高速机器学习库， 其特点是采用较为先进的迭代式、 内存存储的分析计算， 使得数据的计算处理速度大大高于普通的数据处理引擎。

MLlib机器学习库还在不停地更新中， Apache的相关研究人员仍在不停地为其中添加更多的机器学习算法。 目前MLlib中已经有通用的学习算法和工具类， 包括统计、 分类、 回归、 聚类、 降维等，如图1-2所示。

~~~
分类&回归：
	线性模型（线性支持向量机SVMs（分类）、逻辑回归（分类）、线性回归）
	朴素贝叶斯
	决策树
	RF&GBDT
推荐：
	ALS
关联规则：
	Fp-growth
聚类：
	K-means
	LDA
降维：
	SVD
	PCA
优化：
	随机梯度下降
	L-BFGS
特征抽取：
	TF-IDF
	StandardScaler
	Word2Vec
	Normalizer
	ChiSqSelector
统计：
	相关性
	分层抽样
	假设检验
算法评测：
	AUC
	准确率
	召回率
	F-measure
~~~

对预处理后的数据进行分析， 从而获得包含着数据内容的结果是MLlib的最终目的。 MLlib作为Spark的核心处理引擎， 在诞生之初就为了处理大数据而采用了“分治式”的数据处理模式， 将数据分散到各个节点中进行相应的处理。 通过数据处理的“依赖”关系从而使得处理过程层层递进。 这个过程可以依据要求具体编写， 好处是避免了大数据处理框架所要求进行的大规模数据传输， 从而节省了时间， 提高了处理效率。

同时， MLlib借助于函数式程序设计思想， 程序设计人员在编写程序的过程中只需要关注其数据， 而不必考虑函数调用顺序， 不用谨慎地设置外部状态。 所有要做的就是传递代表了边际情况的参数。

MLlib采用Scala语言编写， Scala语言是运行在JVM上的一种函数式编程语言， 特点就是可移植性强， “一次编写， 到处运行”是其最重要的特点。 借助于RDD数据统一输入格式， 让用户可以在不同的IDE上编写数据处理程序， 通过本地化测试后可以在略微修改运行参数后直接在集群上运行。 对结果的获取更为可视化和直观， 不会因为运行系统底层的不同而造成结果的差异与改变。

MLlib是Spark的核心内容， 也是其中最闪耀的部分。 对数据的分析和处理是Spark的精髓， 也是挖掘大数据这座宝山的金锄头， 本书的内容也是围绕MLlib进行的。  

## 1.5 星星之火，可以燎原



# 第2章 Spark安装和开发环境配置

本章将介绍Spark的单机版安装方法和开发环境配置。 MLlib是Spark数据处理框架的一个主要组件， 因此其运行必须要有Spark的支持。 本书以讲解和演示MLlib原理和示例为主， 因此在安装上将详细介绍基于Intellij IDEA的在Windows操作系统上的单机运行环境， 这也是MLlib学习和调试的最常见形式， 以便更好地帮助读者学习和掌握MLlib编写精髓。  

## 2.1 Windows单机模式Spark安装和配置

### 2.1.1 Windows 7安装Java

### 2.1.2 Windows 7安装Scala

### 2.1.3 Intellij IDEA下载和安装

### 2.1.4 Intellij IDEA中Scala插件的安装

Scala是一种把面向对象和函数式编程理念加入到静态类型语言中的语言， 可以把Scala应用在很大范围的编程任务上， 无论是小脚本或是大系统都可以用Scala实现。 Scala运行在标准的Java平台上（JVM） ， 可以与所有的Java库实现无缝交互。

而Spark MLlib是基于Java平台的大数据处理框架， 因此在语言的选择上， 可以自由选择最方便的语言进行编译处理。 而Scala天生具有的简洁性和性能上的优势， 以及可以在JVM上直接使用的特点， 使其成为Spark官方推荐的首选程序语言， 因此本书笔者也推荐使用Scala语言作为Spark MLlib学习的首选语言。

Intellij IDEA本身并没有安装Scala编译插件， 因此在使用Intellij IDEA编译Scala语言编写的Spark MLlib语言之前， 需要安装Scala编译插件  

### 2.1.5 HelloJava——使用Intellij IDEA创建Java程序

### 2.1.6 HelloScala——使用Intellij IDEA创建Scala程序

~~~scala
class helloScala { 
    def main(args: Array[String]): Unit = { 
        print("helloScala") 
    } 
}
~~~

### 2.1.7 最后一脚——Spark单机版安装

## 2.2 经典的WordCount

### 2.2.1 Spark实现WordCount

~~~scala
import org.apache.spark.{SparkContext, SparkConf}
object wordCount {
    def main(args: Array[String]) { 
        val conf = new SparkConf()
        				.setMaster("local")
        				.setAppName("wordCount") //创建环境变量 
        val sc = new SparkContext(conf) //创建环境变量实例 
        val data = sc.textFile("c://wc.txt") //读取文件 
        data.flatMap(_.split(" "))
            .map((_,1))
            .reduceByKey(_+_)
            .collect()
            .foreach(println) //word 计数 
    } 
}
~~~

下面是对程序进行分析。

（1） 首先笔者new了一个SparkConf()， 目的是创建了一个环境变量实例， 告诉系统开始Spark计算。 之后的setMaster("local")启动了本地化运算， setAppName("wordCount")是设置本程序名称。

（2） new SparkContext(conf)的作用是创建环境变量实例， 准备开始任务。

（3） sc.textFile("c://wc.txt")的作用是读取文件， 这里的文件是在C盘上， 因此路径目录即为c: //wc.txt。 顺便提一下， 此时的文件读取是按正常的顺序读取， 本书后面章节会介绍如何读取特定格式的文件。

（4） 第4行是对word进行计数。 flatMap()是Scala中提取相关数据按行处理的一个方法， _.split(" ")中， 下划线_是一个占位符， 代表传送进来的任意一个数据， 对其进行按" "分割。 map((_, 1))是对每个字符开始计数， 在这个过程中， 并不涉及合并和计算， 只是单纯地将每个数据行中单词加1。 最后的reduceByKey()方法是对传递进来的数据按key值相加， 最终形成wordCount计算结果。  

（5） collect()是对程序的启动， 因Spark编程的优化， 很多方法在计算过程中属于lazy模式， 因此需要一个显性启动支持。 foreach(println)是打印的一个调用方法， 打印出数据内容。  

### 2.2.2 MapReduce实现WordCount

与Spark对比的是MapReduce中wordCount程序的设计， 如程序2-4所示， 在这里笔者只是为了做对比， 如果有读者想深入学习MapReduce程序设计， 请参考相关的专业书籍。  

~~~java
import java.io.IOException; 
import java.util.Iterator;
import java.util.StringTokenizer;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat; 

public class WordCount { 
    //创建固定 Map 格式
    public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> { 
        //创建数据1格式
        private final static IntWritable one = new IntWritable(1); 
        //设定输入格式 
        private Text word = new Text(); 
        //开始 map 程序 
        public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException { 
            //将传入值定义为 line
            String line = value.toString(); 
            //格式化传入值
            StringTokenizer tokenizer = new StringTokenizer(line); 
            //开始迭代计算 
            while(tokenizer.hasMoreTokens()) { 
                //设置输入值
                word.set(tokenizer.nextToken()); 
                //写入输出值
                output.collect(word, one); 
            } 
        } 
    } 
    //创建固定 Reduce 格式
    public static class Reduce extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> { 
        //开始 Reduce 程序 
        public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter
                           reporter) throws IOException { 
            //初始化计算器 
            int sum = 0; 
            //开始迭代计算输入值 
            while (values.hasNext()) {
                sum += values.next().get(); 
                //计数器计算 
            } 
            //创建输出结果 
            output.collect(key, new IntWritable(sum)); 
        } 
    }
    //开始主程序 
    public static void main(String[] args) throws Exception { 
        //设置主程序 
        JobConf conf = new JobConf(WordCount.class); 
        //设置主程序名
        conf.setJobName("wordcount"); 
        //设置输出 Key 格式
        conf.setOutputKeyClass(Text.class); 
        //设置输出 Vlaue 格式
        conf.setOutputValueClass(IntWritable.class); 
        //设置主 
        Map conf.setMapperClass(Map.class); 
        //设置第一次 Reduce 方法
        conf.setCombinerClass(Reduce.class);
        //设置主 Reduce 方法
        conf.setReducerClass(Reduce.class); 
        //设置输入格式
        conf.setInputFormat(TextInputFormat.class); 
        //设置输出格式
        conf.setOutputFormat(TextOutputFormat.class);
        //设置输入文件路径
        FileInputFormat.setInputPaths(conf, new Path(args[0])); 
        //设置输出路径
        FileOutputFormat.setOutputPath(conf, new Path(args[1])); 
        //开始主程序 
        JobClient.runJob(conf);
    } 
}
~~~

从程序2-3和程序2-4的对比可以看到， 采用了Scala的Spark程序设计能够简化程序编写的过程与步骤， 同时在后端， Scala对编译后的文件有较好的优化性， 这些都是目前使用Java语言所欠缺的。

这里顺便提一下， 可能有部分使用者在使用Scala时感觉较为困难，但实际上， Scala在使用中主要将其进行整体化考虑， 而非Java的面向对象的思考方法， 这点请读者注意。  

# 第3章 RDD详解

本章将着重介绍Spark最重要的核心部分RDD， 整个Spark的运行和计算都是围绕RDD进行的。 RDD可以看成一个简单的“数组”， 对其进行操作也只需要调用有限的数组中的方法即可。 它与一般数组的区别在于： RDD是分布式存储， 可以更好地利用现有的云数据平台， 并在内存中运行。

本章笔者将详细介绍RDD的基本原理， 讲原理的时候总是感觉很沉闷， 笔者尽量使用图形方式向读者展示RDD的基本原理。 本章也向读者详细介绍RDD的常用方法， 介绍这些方法时与编程实战结合起来， 为后续的各种编程实战操作奠定基础。  

## 3.1 RDD是什么

RDD是Resilient Distributed Datasets的简称， 翻译成中文为“弹性分布式数据集”， 这个语义揭示了RDD实质上是存储在不同节点计算机中的数据集。 分布式存储最大的好处是可以让数据在不同的工作节点上并行存储， 以便在需要数据的时候并行运算， 从而获得最迅捷的运行效率。  

### 3.1.1 RDD名称的秘密

Resilient是弹性的意思。 在Spark中， 弹性指的是数据的存储方式，即数据在节点中进行存储时候， 既可以使用内存也可以使用磁盘。 这为使用者提供了很大的自由， 提供了不同的持久化和运行方法， 有关这点， 我们会在后面详细介绍。

除此之外， 弹性还有一个意思， 即RDD具有很强的容错性。 这里容错性指的是Spark在运行计算的过程中， 不会因为某个节点错误而使得整个任务失败。 不同节点中并发运行的数据， 如果在某一个节点发生错误时， RDD会自动将其在不同的节点中重试。 关于RDD的容错性， 这里尽量避免理论化探讨， 尽量讲解得深入一些， 毕竟这本书是以实战为主。

关于分布式数据的容错性处理是涉及面较广的问题， 较为常用的方法主要是两种：  

- 检查节点
- 更新记录

检查节点的方法是对每个数据节点逐个进行检测， 随时查询每个节点的运行情况。 这样做的好处是便于操作主节点随时了解任务的真实数据运行情况， 而坏处在于由于系统进行的是分布式存储和运算， 节点检测的资源耗费非常大， 而且一旦出现问题， 需要将数据在不同节点中搬运， 反而更加耗费时间从而极大地拉低了执行效率。

更新记录指的是运行的主节点并不总是查询每个分节点的运行状态， 而是将相同的数据在不同的节点（一般情况下是3个） 中进行保存， 各个工作节点按固定的周期更新在主节点中运行的记录， 如果在一定时间内主节点查询到数据的更新状态超时或者有异常， 则在存储相同数据的不同节点上重新启动数据计算工作。 其缺点在于如果数据量过大， 更新数据和重新启动运行任务的资源耗费也相当大。  

### 3.1.2 RDD特性

前面已经介绍， RDD是一种分布式弹性数据集， 将数据分布存储在不同节点的计算机内存中进行存储和处理。 每次RDD对数据处理的最终结果， 都分布存放在不同的节点中。 这样的话， 在进行到下一步数据处理工作时， 数据可以直接从内存中提取， 从而省去了大量的IO操作， 这对于传统的MapReduce操作来说， 更便于使用迭代运算提升效率。

RDD的另外一大特性是延迟计算， 即一个完整的RDD运行任务被分成两部分： Transformation和Action。  

**1. Transformation**

Transformation 用于对RDD的创建。 在Spark中， RDD只能使 Transformation 来创建， 同时Transformation还提供了大量的操作方法，例如map、 filter、 groupBy、 join等操作来对RDD进行处理。 除此之外 RDD可以利用Transformation来生成新的RDD， 这样可以在有限的内存空间中生成尽可能多的数据对象。 但是有一点请读者牢记， 无论发生了多少次Transformation， 在RDD中真正数据计算运行的操作Action都不可能真正运行。

**2． Action**

Action是数据的执行部分， 其通过执行count、 reduce、 collect等方法去真正执行数据的计算部分。 实际上， RDD中所有的操作都是使用的Lazy模式进行， Lazy是一种程序优化的特殊形式。 运行在编译的过程中不会立刻得到计算的最终结果， 而是记住所有的操作步骤和方法， 只有显式地遇到启动命令才进行计算。这样做的好处在于大部分的优化和前期工作在Transformation中已经执行完毕， 当Action进行工作时， 只需要利用全部自由完成业务的核心工作。   

### 3.1.3 与其他分布式共享内存的区别

可能有读者在以前的学习或工作中了解到， 分布式共享内存（Distributed Shared Memory， 简称DSM） 系统是一种较为常用的分布式框架。 在架构完成的DSM系统中， 用户可以向框架内节点的任意位置进行读写操作。 这样做有非常大的便捷性， 可以使得数据脱离本地单节点束缚， 但是在进行大规模计算时， 对容错性容忍程度不够， 常常因为一个节点产生错误而使得整个任务失败。

RDD与一般DSM有很大的区别， 首先RDD在框架内限制了批量读写数据的操作， 有利于整体的容错性提高。 此外， RDD并不单独等待某个节点任务完成， 而是使用“更新记录”的方式去主动性维护任务的运行， 在某一个节点中任务失败， 而只需要在存储数据的不同节点上重新运行即可。  

> **提示**
> 建议读者将RDD与DSM异同点列出做个对比查阅。 因为不是本章重点， 请读者自行完成。  

### 3.1.4 RDD缺陷

在前面已经说过， RDD相对于一般的DSM， 更加注重与批量数据的读写， 并且将优化和执行进行分类。 通过Transformation生成多个RDD， 当其在执行Action时， 主节点通过“记录查询”的方式去确保任务的政策执行。

但是由于这些原因， 使得RDD并不适合作为一个数据的存储和抓取框架， 因为RDD主要执行在多个节点中的批量操作， 即一个简单的写操作也会分成两个步骤进行， 这样反而会降低运行效率。 例如一般网站中的日志文件存储， 更加适合使用一些传统的MySQL数据库进行存储，而不适合采用RDD。  

## 3.2 RDD工作原理

RDD是一个开创性的数据处理模式， 其脱离了单纯的MapReduce的分布设定、 整合、 处理的模式， 而采用了一个新颖的、 类似一般数组或集合的处理模式， 对存储在分布式存储空间上的数据进行操作。 

### 3.2.1 RDD工作原理图 

前面笔者已经说了很多次， RDD可以将其看成一个分布在不同节点
中的分布式数据集， 并将数据以数据块（Block） 的形式存储在各个节
点的计算机中， 整体布局如图3-2所示。  

~~~mermaid
graph RL
    BlockSlave1 --- BlockMaster
    BlockSlave2 --- BlockMaster
    BlockSlave3 --- BlockMaster
	
	BlockNode1 --> BlockSlave1
	BlockNode2 --> BlockSlave1
	BlockNode3 --> BlockSlave2
	BlockNode4 --> BlockSlave2
	BlockNode5 --> BlockSlave3
	BlockNode6 --> BlockSlave3
~~~

从图上可以看到， 每个BlockMaster管理着若干个BlockSlave， 而每个BlockSlave又管理着若干个BlockNode。 当BlockSlave获得了每个Node节点的地址， 会又反向BlockMaster注册每个Node的基本信息， 这样形成分层管理。

而对于某个节点中存储的数据， 如果使用频率较多， 则BlockMaster会将其缓存在自己的内存中， 这样如果以后需要调用这些数据， 则可以直接从BlockMaster中读取。 对于不再使用的数据， BlcokMaster会向BlockSlave发送一组命令予以销毁。

### 3.2.2 RDD的相互依赖

