# 阿里巴巴Java开发手册 1.7.0（嵩山版）

## 一、编程规约

### （一）命名风格

1、【强制】代码中的命名均不能以下划线或美元符号开始，也不能以下划线或美元符号结束。

> 反例：`_name/__name/$name/name_/name$/name__`

2、【强制】所有编程相关的命名严禁使用拼音与英文混合的方式，更不允许直接使用中文的方式。

> 说明：正确的英文拼写和语法可以让阅读者易于理解，避免歧义。注意，纯拼音命名方式更要避免采用。

> 正例：ali/alibaba/taobao/cainiao/aliyun/youku/hangzhou等国际通用名称，可视同英文。

> 反例：DaZhePromotion[打折]/getPingfenByName()[评分]/String fw[福娃]/int 某变量 = 3

3、【强制】代码和注释中都要避免使用任何语言的种族歧视性词语。

> 正例：日本人/印度人/blockList/allowList/secondary

> 反例：RIBENGUIZI/Asan/blackList/whiteList/slave

4、【强制】类名使用UpperCamelCase风格，但以下情形例外：DO/BO/DTO/VO/AO/PO/UID等。

> 正例：ForceCode / UserDO / HtmlDTO / XmlService / TcpUdpDeal / TaPromotion

> 反例：forcecode / UserDo / HTMLDto / XMLService / TCPUDPDeal / TAPromotion

5、【强制】方法名、参数名、成员变量、局部变量都统一使用lowerCamelCase风格。

> 正例： localValue / getHttpMessage() / inputUserId

6、【强制】 常量命名全部大写，单词间用下划线隔开，力求语义表达完整清楚，不要嫌名字长。

> 正例： MAX_STOCK_COUNT / CACHE_EXPIRED_TIME

> 反例： MAX_COUNT / EXPIRED_TIME

7、【强制】 抽象类命名使用 Abstract 或 Base 开头；异常类命名使用 Exception 结尾； 测试类命名以它要测试的类的名称开始，以 Test 结尾。

8、【强制】 类型与中括号紧挨相连来表示数组。

> 正例： 定义整形数组 int[] arrayDemo。

> 反例： 在 main 参数中，使用 String args[]来定义。

9、【强制】 POJO 类中的任何布尔类型的变量， 都不要加 is 前缀，否则部分框架解析会引起序列化错误 。

> 说明： 在本文 MySQL 规约中的建表约定第一条，表达是与否的变量采用 is_xxx 的命名方式，所以，需要在`<resultMap>`设置从 is_xxx 到 xxx 的映射关系。

> 反例： 定义为基本数据类型 Boolean isDeleted 的属性，它的方法也是 isDeleted()，框架在反向解析的时候， “误以为” 对应的属性名称是 deleted，导致属性获取不到，进而抛出异常。

10.【强制】 包名统一使用小写，点分隔符之间有且仅有一个自然语义的英语单词。包名统一使用单数形式，但是类名如果有复数含义，类名可以使用复数形式。

> 正例： 应用工具类包名为 com.alibaba.ei.kunlun.aap.util、类名为 MessageUtils（此规则参考 spring 的框架结构）

11.【强制】 避免在子父类的成员变量之间、或者不同代码块的局部变量之间采用完全相同的命名，使可理解性降低。

> 说明： 子类、父类成员变量名相同，即使是 public 类型的变量也能够通过编译，另外， 局部变量在同一方法内的不同代码块中同名也是合法的， 这些情况都要避免。对于非setter/getter 的参数名称也要避免与成员变量名称相同。  

> 反例：
>
> ~~~java
> public class ConfusingName {
> 	public int stock;
> 	// 非 setter/getter 的参数名称，不允许与本类成员变量同名
> 	public void get(String alibaba) {
> 		if (condition) {
> 			final int money = 666;
> 			// ...
> 		}
> 		for (int i = 0; i < 10; i++) {
> 			// 在同一方法体中，不允许与其它代码块中的 money 命名相同
> 			final int money = 15978;
> 			// ...
> 		}
> 	}
> }
> class Son extends ConfusingName {
> 	// 不允许与父类的成员变量名称相同
> 	public int stock;
> }  
> ~~~

12、【强制】 杜绝完全不规范的缩写， 避免望文不知义。

> 反例： AbstractClass“缩写” 成 AbsClass； condition“缩写” 成 condi； Function 缩写” 成 Fu， 此类随意缩写严重降低了代码的可阅读性。

13、【推荐】 为了达到代码自解释的目标，任何自定义编程元素在命名时，使用尽量完整的单词组合来表达。  

> 正例： 对某个对象引用的 volatile 字段进行原子更新的类名为 AtomicReferenceFieldUpdater。

> 反例： 常见的方法内变量为 `int a;`的定义方式。  

14、【推荐】 在常量与变量的命名时，表示类型的名词放在词尾，以提升辨识度。

> 正例： startTime / workQueue / nameList / TERMINATED_THREAD_COUNT

> 反例： startedAt / QueueOfWork / listName / COUNT_TERMINATED_THREAD

15、【推荐】 如果模块、 接口、类、方法使用了设计模式，在命名时需体现出具体模式。

> 说明： 将设计模式体现在名字中，有利于阅读者快速理解架构设计理念。

> 正例： 
> public class OrderFactory;
> public class LoginProxy;
> public class ResourceObserver;

16、【推荐】 接口类中的方法和属性不要加任何修饰符号（ public 也不要加），保持代码的简洁性，并加上有效的 Javadoc 注释。尽量不要在接口里定义变量，如果一定要定义变量， 确定与接口方法相关，并且是整个应用的基础常量。

> 正例： 接口方法签名 void commit();
> 接口基础常量 String COMPANY = "alibaba";

> 反例： 接口方法定义 public abstract void f();

> 说明： JDK8 中接口允许有默认实现，那么这个 default 方法，是对所有实现类都有价值的默认实现。

17、接口和实现类的命名有两套规则：

1） 【强制】 对于 Service 和 DAO 类，基于 SOA 的理念，暴露出来的服务一定是接口，内部的实现类用Impl 的后缀与接口区别。

> 正例： CacheServiceImpl 实现 CacheService 接口。

2） 【推荐】 如果是形容能力的接口名称，取对应的形容词为接口名（通常是–able 的形容词）。

> 正例： AbstractTranslator 实现 Translatable 接口。

18、【参考】 枚举类名带上 Enum 后缀，枚举成员名称需要全大写，单词间用下划线隔开。

> 说明： 枚举其实就是特殊的常量类，且构造方法被默认强制是私有。

> 正例： 枚举名字为 ProcessStatusEnum 的成员名称： SUCCESS / UNKNOWN_REASON。

19、【参考】 各层命名规约：

A) Service/DAO 层方法命名规约

​	1） 获取单个对象的方法用 get 做前缀。

​	2） 获取多个对象的方法用 list 做前缀，复数结尾，如： listObjects。

​	3） 获取统计值的方法用 count 做前缀。

​	4） 插入的方法用 save/insert 做前缀。

​	5） 删除的方法用 remove/delete 做前缀。

​	6） 修改的方法用 update 做前缀。

B) 领域模型命名规约  

​	1） 数据对象： xxxDO， xxx 即为数据表名。

​	2） 数据传输对象： xxxDTO， xxx 为业务领域相关的名称。

​	3） 展示对象： xxxVO， xxx 一般为网页名称。

​	4） POJO 是 DO/DTO/BO/VO 的统称，禁止命名成 xxxPOJO。

### （二）常量定义

1、【强制】 不允许任何魔法值（ 即未经预先定义的常量） 直接出现在代码中。

> 反例：
>
> ~~~java
> // 本例中，开发者 A 定义了缓存的 key， 然后开发者 B 使用缓存时少了下划线，即 key 是"Id#taobao"+tradeId，导致出现故障
> String key = "Id#taobao_" + tradeId;
> cache.put(key, value);
> ~~~

2、【强制】 在 long 或者 Long 赋值时， 数值后使用大写字母 L，不能是小写字母 l，小写容易跟数字混淆，造成误解。

> 说明： Long a = 2l; 写的是数字的 21，还是 Long 型的 2？

3、【推荐】 不要使用一个常量类维护所有常量， 要按常量功能进行归类，分开维护。

> 说明： 大而全的常量类， 杂乱无章， 使用查找功能才能定位到修改的常量，不利于理解，也不利于维护。

> 正例： 缓存相关常量放在类 CacheConsts 下；系统配置相关常量放在类 SystemConfigConsts 下。

4、【推荐】 常量的复用层次有五层：跨应用共享常量、应用内共享常量、子工程内共享常量、包内共享常量、类内共享常量。

1） 跨应用共享常量：放置在二方库中，通常是 client.jar 中的 constant 目录下。

2） 应用内共享常量：放置在一方库中， 通常是子模块中的 constant 目录下。

> 反例： 易懂变量也要统一定义成应用内共享常量， 两位工程师在两个类中分别定义了“YES” 的变量：
> 类 A 中： public static final String YES = "yes";
> 类 B 中： public static final String YES = "y";
> A.YES.equals(B.YES)，预期是 true，但实际返回为 false，导致线上问题。

3） 子工程内部共享常量：即在当前子工程的 constant 目录下。

4） 包内共享常量：即在当前包下单独的 constant 目录下。

5） 类内共享常量：直接在类内部 private static final 定义。

5、【推荐】 如果变量值仅在一个固定范围内变化用 enum 类型来定义。

> 说明： 如果存在名称之外的延伸属性应使用 enum 类型，下面正例中的数字就是延伸信息，表示一年中的第几个季节。  

> 正例：
>
> ~~~java
> public enum SeasonEnum {
> 	SPRING(1), SUMMER(2), AUTUMN(3), WINTER(4);
> 	private int seq;
> 	SeasonEnum(int seq) {
>     	this.seq = seq;
> 	}
> 	public int getSeq() {
> 		return seq;
> 	}
> }
> ~~~

### （三）代码格式

1、【强制】 如果是大括号内为空，则简洁地写成{}即可，大括号中间无需换行和空格；如果是非空代码块则：

1） 左大括号前不换行。

2） 左大括号后换行。

3） 右大括号前换行。

4） 右大括号后还有 else 等代码则不换行；表示终止的右大括号后必须换行。

2、【强制】 左小括号和右边相邻字符之间不出现空格； 右小括号和左边相邻字符之间也不出现空格；而左大括号前需要加空格。详见第 5 条下方正例提示。

> 反例： `if (空格 a == b 空格)`

3、【强制】 if/for/while/switch/do 等保留字与括号之间都必须加空格。

4、【强制】 任何二目、 三目运算符的左右两边都需要加一个空格。

> 说明： 包括赋值运算符=、逻辑运算符&&、加减乘除符号等。

5、【强制】 采用 4 个空格缩进，禁止使用 Tab 字符。

> 说明： 如果使用 Tab 缩进，必须设置 1 个 Tab 为 4 个空格。 IDEA 设置 Tab 为 4 个空格时，请勿勾选 `Use tab character`；而在 Eclipse 中，必须勾选 `insert spaces for tabs`。

> 正例： （涉及 1-5 点）
> ~~~java
> public static void main(String[] args) {
> 	// 缩进 4 个空格
> 	String say = "hello";
> 	// 运算符的左右必须有一个空格
> 	int flag = 0;
> 	// 关键词 if 与括号之间必须有一个空格，括号内的 f 与左括号， 0 与右括号不需要空格
> 	if (flag == 0) {
> 		System.out.println(say);
> 	}
> 	// 左大括号前加空格且不换行；左大括号后换行
> 	if (flag == 1) {
> 		System.out.println("world");
> 	// 右大括号前换行，右大括号后有 else，不用换行
> 	} else {
> 		System.out.println("ok");
> 	// 在右大括号后直接结束，则必须换行
> 	}
> }  
> ~~~

6、【强制】 注释的双斜线与注释内容之间有且仅有一个空格。

> 正例：  
>
> ~~~java
> // 这是示例注释，请注意在双斜线之后有一个空格
> String commentString = new String();
> ~~~

7、【强制】 在进行类型强制转换时，右括号与强制转换值之间不需要任何空格隔开。  

> 正例：
>
> ~~~java
> double first = 3.2d;
> int second = (int)first + 2;
> ~~~

8、【强制】 单行字符数限制不超过 120 个，超出需要换行，换行时遵循如下原则：

1） 第二行相对第一行缩进 4 个空格，从第三行开始，不再继续缩进，参考示例。

2） 运算符与下文一起换行。

3） 方法调用的点符号与下文一起换行。

4） 方法调用中的多个参数需要换行时， 在逗号后进行。

5）在括号前不要换行，见反例。

> 正例：  
>
> ~~~java
> StringBuilder sb = new StringBuilder();
> // 超过 120 个字符的情况下，换行缩进 4 个空格，并且方法前的点号一起换行
> sb.append("yang").append("hao")...
> 	.append("chen")...
> 	.append("chen")...
> 	.append("chen");
> ~~~

> 反例：
>
> ~~~java
> StringBuilder sb = new StringBuilder();
> // 超过 120 个字符的情况下，不要在括号前换行
> sb.append("you").append("are")...append
> 	("lucky");
> // 参数很多的方法调用可能超过 120 个字符，逗号后才是换行处
> method(args1, args2, args3, ...
> 	, argsX);
> ~~~

9、【强制】 方法参数在定义和传入时，多个参数逗号后面必须加空格。

> 正例： 下例中实参的 args1， 后边必须要有一个空格。
> method(args1, args2, args3);

10、【强制】 IDE 的 text file encoding 设置为 UTF-8; IDE 中文件的换行符使用 Unix 格式，不要使用 Windows 格式。

11、【推荐】 单个方法的总行数不超过 80 行。

> 说明： 除注释之外的方法签名、 左右大括号、方法内代码、空行、回车及任何不可见字符的总行数不超过80 行。

> 正例： 代码逻辑分清红花和绿叶，个性和共性，绿叶逻辑单独出来成为额外方法，使主干代码更加清晰；共
> 性逻辑抽取成为共性方法，便于复用和维护。  

12.【推荐】 没有必要增加若干空格来使变量的赋值等号与上一行对应位置的等号对齐。

> 正例：
>
> ~~~java
> int one = 1;
> long two = 2L;
> float three = 3F;
> StringBuilder sb = new StringBuilder();
> ~~~

> 说明： 增加 sb 这个变量，如果需要对齐，则给 one、 two、 three 都要增加几个空格，在变量比较多的情
> 况下，是非常累赘的事情。

13.【推荐】 不同逻辑、不同语义、不同业务的代码之间插入一个空行分隔开来以提升可读性。

> 说明： 任何情形， 没有必要插入多个空行进行隔开。

### （四）OOP规约

1、【强制】 避免通过一个类的对象引用访问此类的静态变量或静态方法，无谓增加编译器解析成本，直接用类名来访问即可。

2、【强制】 所有的覆写方法，必须加@Override 注解。

> 说明： getObject()与 get0bject()的问题。一个是字母的 O，一个是数字的 0，加@Override 可以准确判断是否覆盖成功。另外，如果在抽象类中对方法签名进行修改，其实现类会马上编译报错。

3、【强制】 相同参数类型，相同业务含义，才可以使用 Java 的可变参数，避免使用 Object。

> 说明： 可变参数必须放置在参数列表的最后。（ 建议开发者尽量不用可变参数编程）

> 正例： `public List<User> listUsers(String type, Long... ids) {...}`

4、【强制】 外部正在调用或者二方库依赖的接口，不允许修改方法签名，避免对接口调用方产生影响。接口过时必须加@Deprecated 注解，并清晰地说明采用的新接口或者新服务是什么。

5、【强制】 不能使用过时的类或方法。

> 说明： java.net.URLDecoder 中的方法 decode(String encodeStr) 这个方法已经过时，应该使用双参数 decode(String source, String encode)。接口提供方既然明确是过时接口，那么有义务同时提供新的接口；作为调用方来说，有义务去考证过时方法的新实现是什么。

6、【强制】Object 的 equals 方法容易抛空指针异常，应使用常量或确定有值的对象来调用 equals。

> 正例：` "test".equals(object);`

> 反例：` object.equals("test");`

> 说明： 推荐使用 JDK7 引入的工具类 java.util.Objects#equals(Object a, Object b)

7、【强制】 所有整型包装类对象之间值的比较， 全部使用 equals 方法比较。

> 说明： 对于 Integer var = ? 在-128 至 127 之间的赋值， Integer 对象是在 IntegerCache.cache 产生，会复用已有对象，这个区间内的 Integer 值可以直接使用==进行判断，但是这个区间之外的所有数据，都会在堆上产生，并不会复用已有对象，这是一个大坑，推荐使用 equals 方法进行判断。  

8、【强制】 任何货币金额，均以最小货币单位且整型类型来进行存储。

9、【强制】 浮点数之间的等值判断，基本数据类型不能用==来比较，包装数据类型不能用 equals 来判断。

> 说明： 浮点数采用“尾数+阶码” 的编码方式，类似于科学计数法的“有效数字+指数” 的表示方式。二进制无法精确表示大部分的十进制小数，具体原理参考《码出高效》 。

> 反例：  
>
> ~~~java
> float a = 1.0F - 0.9F;
> float b = 0.9F - 0.8F;
> if (a == b) {
>     // 预期进入此代码块，执行其它业务逻辑
>     // 但事实上 a==b 的结果为 false
> }
> Float x = Float.valueOf(a);
> Float y = Float.valueOf(b);
> if (x.equals(y)) {
>     // 预期进入此代码块，执行其它业务逻辑
>     // 但事实上 equals 的结果为 false
> }
> ~~~

> 正例：
> (1) 指定一个误差范围，两个浮点数的差值在此范围之内，则认为是相等的。
>
> ~~~java
> float a = 1.0F - 0.9F;
> float b = 0.9F - 0.8F;
> float diff = 1e-6F;
> if (Math.abs(a - b) < diff) {
> 	System.out.println("true");
> }
> ~~~
>
> (2) 使用 BigDecimal 来定义值，再进行浮点数的运算操作。
>
> ~~~java
> BigDecimal a = new BigDecimal("1.0");
> BigDecimal b = new BigDecimal("0.9");
> BigDecimal c = new BigDecimal("0.8");
> BigDecimal x = a.subtract(b);
> BigDecimal y = b.subtract(c);
> if (x.compareTo(y) == 0) {
> 	System.out.println("true");
> }  
> ~~~

10、【强制】 如上所示 BigDecimal 的等值比较应使用 compareTo()方法，而不是 equals()方法。

> 说明： equals()方法会比较值和精度（ 1.0 与 1.00 返回结果为 false） ， 而 compareTo()则会忽略精度。

11、【强制】 定义数据对象 DO 类时，属性类型要与数据库字段类型相匹配。

> 正例： 数据库字段的 bigint 必须与类属性的 Long 类型相对应。

> 反例： 某个案例的数据库表 id 字段定义类型 bigint unsigned，实际类对象属性为 Integer，随着 id 越来
> 越大，超过 Integer 的表示范围而溢出成为负数。  

12、【强制】 禁止使用构造方法 BigDecimal(double)的方式把 double 值转化为 BigDecimal 对象。

> 说明： BigDecimal(double)存在精度损失风险，在精确计算或值比较的场景中可能会导致业务逻辑异常。
>
> 如： BigDecimal g = new BigDecimal(0.1F); 实际的存储值为： 0.10000000149

> 正例： 优先推荐入参为 String 的构造方法，或使用 BigDecimal 的 valueOf 方法，此方法内部其实执行了
> Double 的 toString，而 Double 的 toString 按 double 的实际能表达的精度对尾数进行了截断。
>
> ~~~java
> BigDecimal recommend1 = new BigDecimal("0.1");
> BigDecimal recommend2 = BigDecimal.valueOf(0.1);
> ~~~

13、关于基本数据类型与包装数据类型的使用标准如下：

1） 【强制】 所有的 POJO 类属性必须使用包装数据类型。

2） 【强制】 RPC 方法的返回值和参数必须使用包装数据类型。

3） 【推荐】 所有的局部变量使用基本数据类型。

> 说明： POJO 类属性没有初值是提醒使用者在需要使用时，必须自己显式地进行赋值，任何 NPE 问题，或者入库检查，都由使用者来保证。

> 正例： 数据库的查询结果可能是 null，因为自动拆箱，用基本数据类型接收有 NPE 风险。

> 反例： 某业务的交易报表上显示成交总额涨跌情况，即正负 x%， x 为基本数据类型，调用的 RPC 服务，调用不成功时，返回的是默认值，页面显示为 0%，这是不合理的，应该显示成中划线-。所以包装数据类型的 null 值，能够表示额外的信息，如：远程调用失败，异常退出。

14、【强制】 定义 DO/DTO/VO 等 POJO 类时，不要设定任何属性默认值。

> 反例： POJO 类的 createTime 默认值为 new Date()， 但是这个属性在数据提取时并没有置入具体值，在更新其它字段时又附带更新了此字段，导致创建时间被修改成当前时间。

15、【强制】 序列化类新增属性时，请不要修改 serialVersionUID 字段，避免反序列失败；如果完全不兼容升级，避免反序列化混乱，那么请修改 serialVersionUID 值。

> 说明： 注意 serialVersionUID 不一致会抛出序列化运行时异常。

16、【强制】 构造方法里面禁止加入任何业务逻辑，如果有初始化逻辑，请放在 init 方法中。

17、【强制】 POJO 类必须写 toString 方法。使用 IDE 中的工具： source> generate toString 时，如果继承了另一个 POJO 类，注意在前面加一下 super.toString。

> 说明： 在方法执行抛出异常时，可以直接调用 POJO 的 toString()方法打印其属性值，便于排查问题。

18、【强制】 禁止在 POJO 类中，同时存在对应属性 xxx 的 isXxx()和 getXxx()方法。

> 说明： 框架在调用属性 xxx 的提取方法时，并不能确定哪个方法一定是被优先调用到的。

19、【推荐】 使用索引访问用 String 的 split 方法得到的数组时，需做最后一个分隔符后有无内容的检查，否则会有抛 IndexOutOfBoundsException 的风险。

> 说明：
>
> ~~~java
> String str = "a,b,c,,";
> String[] ary = str.split(",");
> // 预期大于 3，结果是 3
> System.out.println(ary.length);  
> ~~~

20、【推荐】 当一个类有多个构造方法，或者多个同名方法，这些方法应该按顺序放置在一起，便于阅读，此条规则优先于下一条。

21、【推荐】 类内方法定义的顺序依次是：公有方法或保护方法 > 私有方法 > getter / setter
方法。

> 说明： 公有方法是类的调用者和维护者最关心的方法，首屏展示最好；保护方法虽然只是子类关心，也可能是“模板设计模式” 下的核心方法；而私有方法外部一般不需要特别关心，是一个黑盒实现； 因为承载的信息价值较低，所有 Service 和 DAO 的 getter/setter 方法放在类体最后。

22、【推荐】 setter 方法中，参数名称与类成员变量名称一致， this.成员名 = 参数名。在 getter/setter 方法中， 不要增加业务逻辑，增加排查问题的难度。

> 反例：
>
> ~~~java
> public Integer getData () {
> 	if (condition) {
> 		return this.data + 100;
> 	} else {
> 		return this.data - 100;
> 	}
> }
> ~~~

23、【推荐】 循环体内，字符串的连接方式，使用 StringBuilder 的 append 方法进行扩展。

> 说明：下例中，反编译出的字节码文件显示每次循环都会 new 出一个 StringBuilder 对象，然后进行 append 操作，最后通过 toString 方法返回 String 对象，造成内存资源浪费。

> 反例：
>
> ~~~java
> String str = "start";
> for (int i = 0; i < 100; i++) {
> 	str = str + "hello";
> }
> ~~~

24、【推荐】 final 可以声明类、成员变量、方法、以及本地变量，下列情况使用 final 关键字：

1） 不允许被继承的类，如： String 类。

2） 不允许修改引用的域对象，如： POJO 类的域变量。

3） 不允许被覆写的方法，如： POJO 类的 setter 方法。

4） 不允许运行过程中重新赋值的局部变量。

5） 避免上下文重复使用一个变量，使用 final 关键字可以强制重新定义一个变量，方便更好地进行重构。

25、【推荐】 慎用 Object 的 clone 方法来拷贝对象。

> 说明： 对象 clone 方法默认是浅拷贝，若想实现深拷贝， 需覆写 clone 方法实现域对象的深度遍历式拷贝。

26、【推荐】 类成员与方法访问控制从严：

1） 如果不允许外部直接通过 new 来创建对象，那么构造方法必须是 private。

2） 工具类不允许有 public 或 default 构造方法。

3） 类非 static 成员变量并且与子类共享，必须是 protected。

4） 类非 static 成员变量并且仅在本类使用，必须是 private。

5） 类 static 成员变量如果仅在本类使用，必须是 private。

6） 若是 static 成员变量， 考虑是否为 final。

7） 类成员方法只供类内部调用，必须是 private。

8） 类成员方法只对继承类公开，那么限制为 protected。

> 说明： 任何类、方法、参数、变量，严控访问范围。过于宽泛的访问范围，不利于模块解耦。思考：如果是一个 private 的方法，想删除就删除，可是一个 public 的 service 成员方法或成员变量，删除一下，不得手心冒点汗吗？变量像自己的小孩，尽量在自己的视线内，变量作用域太大， 无限制的到处跑，那么你会担心的。    




# 阿里巴巴Java开发手册 1.0.0

## 一、编程规约

### （三）格式规约

9、【强制】 IDE 的 text file encoding 设置为 UTF-8; IDE 中文件的换行符使用 Unix 格式，不要使用 windows 格式。

### （四）OOP规约

10、【强制】序列化类新增属性时，请不要修改 serialVersionUID 字段，避免反序列失败；如果完全不兼容升级，避免反序列化混乱，那么请修改 serialVersionUID 值。

说明： 注意 serialVersionUID 不一致会抛出序列化运行时异常。

13、【推荐】使用索引访问用 String 的 split 方法得到的数组时，需做最后一个分隔符后有无内容的检查，否则会有抛 IndexOutOfBoundsException 的风险。

### （五）集合处理

1、【强制】 Map/Set 的 key 为自定义对象时，必须重写 hashCode 和 equals。

正例： String 重写了 hashCode 和 equals 方法，所以我们可以非常愉快地使用 String 对象作为 key 来使用。

2、【强制】 ArrayList 的 subList 结果不可强转成 ArrayList，否则会抛出 ClassCastException 异常： java.util.RandomAccessSubList cannot be cast to java.util.ArrayList ;

说明： subList 返回的是 ArrayList 的内部类 SubList，并不是 ArrayList ，而是 ArrayList的一个视图，对于 SubList 子列表的所有操作最终会反映到原列表上。

4、【强制】使用集合转数组的方法，必须使用集合的 toArray(T[] array)，传入的是类型完全一样的数组，大小就是 list.size()。

反例： 直接使用 toArray 无参方法存在问题，此方法返回值只能是 Object[]类，若强转其它类型数组将出现 ClassCastException 错误。

正例：
​~~~java
List<String> list = new ArrayList<String>(2);
list.add("guan");
list.add("bao");
String[] array = new String[list.size()];
array = list.toArray(array);
~~~

说明： 使用 toArray 带参方法，入参分配的数组空间不够大时， toArray 方法内部将重新分配内存空间，并返回新数组地址；如果数组元素大于实际所需，下标为[ list.size() ]的数组元素将被置为 null，其它数组元素保持原值，因此最好将方法入参数组大小定义与集合元素个数一致。

5、【强制】使用工具类 Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方法，它的 add/remove/clear 方法会抛出 UnsupportedOperationException 异常。

说明： asList 的返回对象是一个 Arrays 内部类，并没有实现集合的修改方法。 Arrays.asList 体现的是适配器模式，只是转换接口，后台的数据仍是数组。
​~~~java
String[] str = new String[] { "a", "b" };
List list = Arrays.asList(str);
~~~

第一种情况： list.add("c"); 运行时异常。
第二种情况： str[0]= "gujin"; 那么 list.get(0)也会随之修改。

6、【强制】泛型通配符<? extends T>来接收返回的数据，此写法的泛型集合不能使用 add 方法。

说明： 苹果装箱后返回一个<? extends Fruits>对象，此对象就不能往里加任何水果，包括苹果。

7、【强制】不要在 foreach 循环里进行元素的 remove/add 操作。 remove 元素请使用 Iterator方式，如果并发操作，需要对 Iterator 对象加锁。

反例：
~~~java
List<String> a = new ArrayList<String>();
a.add("1");
a.add("2");
for (String temp : a) {
    if("1".equals(temp)){
        a.remove(temp);
    }
}
~~~

说明：这个例子的执行结果会出乎大家的意料，那么试一下把“1” 换成“2” ，会是同样的结果吗？

总结：如果我们我们用foreach删除的元素刚好是最后一个，删除完成前cursor刚好等于size的大小。但是，删除完成后size的数量减1，但是cursor并没有变化。导致下一次循环不相等继续向下执行，导致检查数组不通过，抛出java.util.ConcurrentModificationException

正例：
~~~java
Iterator<String> it = a.iterator();
while(it.hasNext()){
    String temp = it.next();
    if(删除元素的条件){
        it.remove();
    }
}
~~~

10、【推荐】使用 entrySet 遍历 Map 类集合 KV，而不是 keySet 方式进行遍历。

说明： keySet 其实是遍历了 2 次，一次是转为 Iterator 对象，另一次是从 hashMap 中取出 key所对应的 value。而 entrySet 只是遍历了一次就把 key 和 value 都放到了 entry 中，效率更高。如果是 JDK8，使用 Map.foreach 方法。

正例： values()返回的是 V 值集合，是一个 list 集合对象； keySet()返回的是 K 值集合，是一个 Set 集合对象； entrySet()返回的是 K-V 值组合集合。

11、【推荐】高度注意 Map 类集合 K/V 能不能存储 null 值的情况，如下表格：

|集合类             |Key         |Value       |Super      |说明      |
|------------------|------------|------------|-----------|----------|
|Hashtable         |不允许为 null|不允许为 null|Dictionary |线程安全   |
|ConcurrentHashMap |不允许为 null|不允许为 null|AbstractMap|线程局部安全|
|TreeMap           |不允许为 null|允许为 null  |AbstractMap|线程不安全 |
|HashMap           |允许为 null  |允许为 null  |AbstractMap|线程不安全 |

反例： 很多同学认为 ConcurrentHashMap 是可以置入 null 值。 在批量翻译场景中，子线程分发时，出现置入 null 值的情况，但主线程没有捕获到此异常，导致排查困难。

### （六）并发处理

3、【强制】 SimpleDateFormat 是线程不安全的类，一般不要定义为 static 变量，如果定义为static，必须加锁，或者使用 DateUtils 工具类。

正例： 注意线程安全，使用 DateUtils。亦推荐如下处理：
~~~java
private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
    @Override
    protected DateFormat initialValue() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }
};
~~~
说明： 如果是 JDK8 的应用，可以使用 instant 代替 Date， Localdatetime 代替 Calendar，Datetimeformatter 代替 Simpledateformatter，官方给出的解释： simple beautiful strong immutable thread-safe。

6、【强制】并发修改同一记录时，避免更新丢失，要么在应用层加锁，要么在缓存加锁，要么在数据库层使用乐观锁，使用 version 作为更新依据。

说明： 如果每次访问冲突概率小于 20%，推荐使用乐观锁，否则使用悲观锁。乐观锁的重试次数不得小于 3 次。

7、【强制】多线程并行处理定时任务时， Timer 运行多个 TimeTask 时，只要其中之一没有捕获抛出的异常，其它任务便会自动终止运行，使用 ScheduledExecutorService 则没有这个问题。

8、【强制】线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。

说明： Executors 各个方法的弊端：

1） newFixedThreadPool 和 newSingleThreadExecutor:
主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至 OOM。

2） newCachedThreadPool 和 newScheduledThreadPool:
主要问题是线程数最大数是 Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至 OOM。

13.【参考】 volatile 解决多线程内存不可见问题。对于一写多读，是可以解决变量同步问题，但是如果多写，同样无法解决线程安全问题。如果想取回 count++数据，使用如下类实现：
~~~java
AtomicInteger count = new AtomicInteger(); 
count.addAndGet(1);
~~~
count++操作如果是JDK8，推荐使用 LongAdder 对象，比 AtomicLong 性能更好（减少乐观锁的重试次数）。

14.【参考】注意 HashMap 的扩容死链，导致 CPU 飙升的问题。

15.【参考】ThreadLocal 无法解决共享对象的更新问题， ThreadLocal 对象建议使用 static 修饰。这个变量是针对一个线程内所有操作共有的，所以设置为静态变量，所有此类实例共享此静态变量 ，也就是说在类第一次被使用时装载，只分配一块存储空间，所有此类的对象(只要是这个线程内定义的)都可以操控这个变量。

### （七）控制语句

3、【推荐】推荐尽量少用 else， if-else 的方式可以改写成：
~~~java
if(condition){
    …
    return obj;
}
// 接着写 else 的业务逻辑代码;
~~~
说明： 如果使用要 if-else if-else 方式表达逻辑，【强制】请勿超过 3 层，超过请使用状态设计模式。

### （九）其他

2、【强制】避免用 Apache Beanutils 进行属性的 copy。

说明： Apache BeanUtils 性能较差，可以使用其他方案比如 Spring BeanUtils, Cglib BeanCopier。

## 二、异常日志

### （一）异常处理

1、【强制】不要捕获 Java 类库中定义的继承自 RuntimeException 的运行时异常类，如：IndexOutOfBoundsException / NullPointerException，这类异常由程序员预检查来规避，保证程序健壮性。

正例： if(obj != null) {...}

反例： try { obj.method() } catch(NullPointerException e){…}

7、【强制】不能在 finally 块中使用 return， finally 块中的 return 返回后方法结束执行，不会再执行 try 块中的 return 语句。

9、【推荐】方法的返回值可以为 null，不强制返回空集合，或者空对象等，必须添加注释充分说明什么情况下会返回 null 值。调用方需要进行 null 判断防止 NPE 问题。

说明： 本规约明确防止 NPE 是调用者的责任。即使被调用方法返回空集合或者空对象，对调用者来说，也并非高枕无忧，必须考虑到远程调用失败，运行时异常等场景返回 null 的情况。

10、【推荐】防止 NPE，是程序员的基本修养，注意 NPE 产生的场景：

1） 返回类型为包装数据类型，有可能是 null，返回 int 值时注意判空。
反例： public int f(){ return Integer 对象}，如果为 null，自动解箱抛 NPE。
2） 数据库的查询结果可能为 null。
3） 集合里的元素即使 isNotEmpty，取出的数据元素也可能为 null。
4） 远程调用返回对象，一律要求进行 NPE 判断。
5） 对于 Session 中获取的数据，建议 NPE 检查，避免空指针。
6） 级联调用 obj.getA().getB().getC()；一连串调用，易产生 NPE。

12、【推荐】定义时区分 unchecked / checked 异常，避免直接使用 RuntimeException 抛出，更不允许抛出 Exception 或者 Throwable，应使用有业务含义的自定义异常。推荐业界已定义过的自定义异常，如： DaoException / ServiceException 等。

### （二）日志规约

4、
【强制】对 trace/debug/info 级别的日志输出，必须使用条件输出形式或者使用占位符的方式。

说明： logger.debug("Processing trade with id: " + id + " symbol: " + symbol); 如果日志级别是 warn，上述日志不会打印，但是会执行字符串拼接操作，如果 symbol 是对象，会执行 toString()方法，浪费了系统资源，执行了上述操作，最终日志却没有打印。

正例： （条件）
~~~java
if (logger.isDebugEnabled()) {
    logger.debug("Processing trade with id: " + id + " symbol: " + symbol);
}
~~~
正例： （占位符）
~~~java
logger.debug("Processing trade with id: {} and symbol : {} ", id, symbol);
~~~

5、【强制】避免重复打印日志，浪费磁盘空间，务必在 log4j.xml 中设置 additivity=false。
正例： `<logger name="com.taobao.ecrm.member.config" additivity="false">`

6、【强制】异常信息应该包括两类信息：案发现场信息和异常堆栈信息。如果不处理，那么往上抛。
正例： `logger.error(各类参数或者对象 toString + "_" + e.getMessage(), e);`

9、【推荐】谨慎地记录日志。生产环境禁止输出 debug 日志；有选择地输出 info 日志；如果使用 warn 来记录刚上线时的业务行为信息，一定要注意日志输出量的问题，避免把服务器磁盘撑爆，并记得及时删除这些观察日志。

说明： 大量地输出无效日志，不利于系统性能提升，也不利于快速定位错误点。纪录日志时请思考：这些日志真的有人看吗？看到这条日志你能做什么？能不能给问题排查带来好处？

## 三、MySQL规约

### （一）建表规约

1、【强制】表达是与否概念的字段，必须使用 is_xxx 的方式命名，数据类型是 unsigned tinyint（ 1 表示是， 0 表示否），此规则同样适用于 odps 建表。

说明： 任何字段如果为非负数，必须是 unsigned。

2、 【强制】表名、字段名必须使用小写字母或数字；禁止出现数字开头，禁止两个下划线中间只出现数字。数据库字段名的修改代价很大，因为无法进行预发布，所以字段名称需要慎重考虑。

正例： getter_admin， task_config， level3_name

反例： GetterAdmin， taskConfig， level_3_name

3、 【强制】表名不使用复数名词。

说明： 表名应该仅仅表示表里面的实体内容，不应该表示实体数量，对应于 DO 类名也是单数形式，符合表达习惯。

4、 【强制】禁用保留字，如 desc、 range、 match、 delayed 等， 参考官方保留字。

5、 【强制】唯一索引名为 uk_字段名；普通索引名则为 idx_字段名。

说明： uk_ 即 unique key； idx_ 即 index 的简称。

6、 【强制】小数类型为 decimal，禁止使用 float 和 double。

说明： float 和 double 在存储的时候，存在精度损失的问题，很可能在值的比较时，得到不正确的结果。如果存储的数据范围超过 decimal 的范围，建议将数据拆成整数和小数分开存储。

7、 【强制】如果存储的字符串长度几乎相等，使用 CHAR 定长字符串类型。

8、【强制】 varchar 是可变长字符串，不预先分配存储空间，长度不要超过 5000，如果存储长度大于此值，定义字段类型为 TEXT，独立出来一张表，用主键来对应，避免影响其它字段索引效率。

9、【强制】表必备三字段： id, gmt_create, gmt_modified。

说明： 其中 id 必为主键，类型为 unsigned bigint、单表时自增、步长为 1；分表时改为从TDDL Sequence 取值，确保分表之间的全局唯一。 gmt_create, gmt_modified 的类型均为date_time 类型。

14、【推荐】单表行数超过 500 万行或者单表容量超过 2GB，才推荐进行分库分表。

说明： 如果预计三年后的数据量根本达不到这个级别，请不要在创建表时就分库分表。

反例： 某业务三年总数据量才 2 万行，却分成 1024 张表，问：你为什么这么设计？答：分 1024 张表，不是标配吗？

### （二）索引规约

1、【强制】业务上具有唯一特性的字段，即使是组合字段，也必须建成唯一索引。

说明： 不要以为唯一索引影响了 insert 速度，这个速度损耗可以忽略，但提高查找速度是明显的；另外，即使在应用层做了非常完善的校验和控制，只要没有唯一索引，根据墨菲定律，必然有脏数据产生。

2、【强制】超过三个表禁止 join。需要 join 的字段，数据类型保持绝对一致；多表关联查询时，保证被关联的字段需要有索引。

说明： 即使双表 join 也要注意表索引、 SQL 性能。

3、 【强制】在 varchar 字段上建立索引时，必须指定索引长度，没必要对全字段建立索引，根据实际文本区分度决定索引长度。

说明： 索引的长度与区分度是一对矛盾体，一般对字符串类型数据，长度为 20 的索引，区分度会高达 90%以上，可以使用 count(distinct left(列名, 索引长度))/count(*)的区分度来确定。

4、【强制】页面搜索严禁左模糊或者全模糊，如果需要请走搜索引擎来解决。

说明： 索引文件具有 B-Tree 的最左前缀匹配特性，如果左边的值未确定，那么无法使用此索引。

5、【推荐】如果有 order by 的场景，请注意利用索引的有序性。 order by 最后的字段是组合索引的一部分，并且放在索引组合顺序的最后，避免出现 file_sort 的情况，影响查询性能。

正例： where a=? and b=? order by c; 索引： a_b_c

反例： 索引中有范围查找，那么索引有序性无法利用，如： WHERE a>10 ORDER BY b; 索引 a_b无法排序。

6、【推荐】利用覆盖索引来进行查询操作，来避免回表操作。

说明： 如果一本书需要知道第 11 章是什么标题，会翻开第 11 章对应的那一页吗？目录浏览一下就好，这个目录就是起到覆盖索引的作用。

正例： IDB 能够建立索引的种类：主键索引、唯一索引、普通索引，而覆盖索引是一种查询的一种效果，用 explain 的结果， extra 列会出现： using index

7、【推荐】利用延迟关联或者子查询优化超多分页场景。

说明： MySQL 并不是跳过 offset 行，而是取 offset+N 行，然后返回放弃前 offset 行，返回 N 行，那当 offset 特别大的时候，效率就非常的低下，要么控制返回的总页数，要么对超过特定阈值的页数进行 SQL 改写。

正例： 先快速定位需要获取的 id 段，然后再关联：
SELECT a.* FROM 表 1 a, (select id from 表 1 where 条件 LIMIT 100000,20 ) b where a.id=b.id

8、【推荐】 SQL 性能优化的目标：至少要达到 range 级别， 要求是 ref 级别， 如果可以是 consts最好。

说明：
1） consts 单表中最多只有一个匹配行（主键或者唯一索引），在优化阶段即可读取到数据。
2） ref 指的是使用普通的索引。（ normal index）
3） range 对索引进范围检索。

反例： explain 表的结果， type=index，索引物理文件全扫描，速度非常慢，这个 index 级别比较 range 还低，与全表扫描是小巫见大巫。

10、【参考】创建索引时避免有如下极端误解：
1）误认为一个查询就需要建一个索引。
2）误认为索引会消耗空间、严重拖慢更新和新增速度。
3）误认为唯一索引一律需要在应用层通过“ 先查后插” 方式解决。

### （三）SQL规约

1、【强制】不要使用 count(列名)或 count(常量)来替代 count(*)， count(*)就是 SQL92 定义的标准统计行数的语法，跟数据库无关，跟 NULL 和非 NULL 无关。

说明： count(*)会统计值为 NULL 的行，而 count(列名)不会统计此列为 NULL 值的行。

2、【强制】 count(distinct col) 计算该列除 NULL 之外的不重复数量。注意 count(distinct col1, col2) 如果其中一列全为 NULL，那么即使另一列有不同的值，也返回为 0。

3、【强制】当某一列的值全是 NULL 时， count(col)的返回结果为 0，但 sum(col)的返回结果为NULL，因此使用 sum()时需注意 NPE 问题。

正例： 可以使用如下方式来避免 sum 的 NPE 问题： SELECT IF(ISNULL(SUM(g)),0,SUM(g)) FROM table;

4、【强制】使用 ISNULL()来判断是否为 NULL 值。注意： NULL 与任何值的直接比较都为 NULL。

说明：
1） NULL<>NULL 的返回结果是 NULL，不是 false。
2） NULL=NULL 的返回结果是 NULL，不是 true。
3） NULL<>1 的返回结果是 NULL，而不是 true。

5、 【强制】在代码中写分页查询逻辑时，若 count 为 0 应直接返回，避免执行后面的分页语句。

6、 【强制】不得使用外键与级联，一切外键概念必须在应用层解决。

说明： （概念解释）学生表中的 student_id 是主键，那么成绩表中的 student_id 则为外键。如果更新学生表中的 student_id，同时触发成绩表中的 student_id 更新，则为级联更新。外键与级联更新适用于单机低并发，不适合分布式、高并发集群；级联更新是强阻塞，存在数据库更新风暴的风险；外键影响数据库的插入速度。

7、【强制】禁止使用存储过程，存储过程难以调试和扩展，更没有移植性。

8、【强制】 IDB 数据订正时，删除和修改记录时，要先 select，避免出现误删除，确认无误才能提交执行。

9、 【推荐】 in 操作能避免则避免，若实在避免不了，需要仔细评估 in 后边的集合元素数量，控制在 1000 个之内。

10、【参考】因阿里巴巴全球化需要，所有的字符存储与表示，均以 utf-8 编码，那么字符计数方法注意：

说明：
SELECT LENGTH("阿里巴巴")； 返回为 12
SELECT CHARACTER_LENGTH("阿里巴巴")； 返回为 4
如果要使用表情，那么使用 utfmb4 来进行存储，注意它与 utf-8 编码。

11、【参考】 TRUNCATE TABLE 比 DELETE 速度快，且使用的系统和事务日志资源少，但 TRUNCATE无事务且不触发 trigger，有可能造成事故，故不建议在开发代码中使用此语句。

说明： TRUNCATE TABLE 在功能上与不带 WHERE 子句的 DELETE 语句相同

### （四）ORM规约

1、 【强制】在表查询中，一律不要使用 * 作为查询的字段列表，需要哪些字段必须明确写明。

说明： 1）增加查询分析器解析成本。 2）增减字段容易与 resultMap 配置不一致。

2、【强制】 POJO 类的 boolean 属性不能加 is，而数据库字段必须加 is_，要求在 resultMap 中进行字段与属性之间的映射。

说明： 参见定义 POJO 类以及数据库字段定义规定，在 sql.xml 增加映射，是必须的。

3、【强制】不要用 resultClass 当返回参数，即使所有类属性名与数据库字段一一对应，也需要定义；反过来，每一个表也必然有一个与之对应。

说明： 配置映射关系，使字段与 DO 类解耦，方便维护。

4、 【强制】 xml 配置中参数注意使用： #{}， #param# 不要使用${} 此种方式容易出现 SQL 注入。

5、 【强制】 iBATIS 自带的 queryForList(String statementName,int start,int size)不推荐使用。

说明： 其实现方式是在数据库取到 statementName 对应的 SQL 语句的所有记录，再通过 subList 取 start,size 的子集合，线上因为这个原因曾经出现过 OOM。

正例： 在 sqlmap.xml 中引入 #start#, #size#
~~~java
Map<String, Object> map = new HashMap<String, Object>();
map.put("start", start);
map.put("size", size);
~~~

6、【强制】不允许直接拿 HashMap 与 HashTable 作为查询结果集的输出。

反例： 某同学为避免写一个<resultMap>，直接使用 HashTable 来接收数据库返回结果，结果出现日常是把 bigint 转成 Long 值，而线上由于数据库版本不一样，解析成 BigInteger，导致线上问题。

7、 【强制】更新数据表记录时，必须同时更新记录对应的 gmt_modified 字段值为当前时间。

8、 【推荐】不要写一个大而全的数据更新接口，传入为 POJO 类，不管是不是自己的目标更新字段，都进行 update table set c1=value1,c2=value2,c3=value3; 这是不对的。执行 SQL 时，尽量不要更新无改动的字段，一是易出错；二是效率低；三是 binlog 增加存储。

9、 【参考】 @Transactional 事务不要滥用。事务会影响数据库的 QPS，另外使用事务的地方需要考虑各方面的回滚方案，包括缓存回滚、搜索引擎回滚、消息补偿、统计修正等。

10、【参考】 <isEqual>中的 compareValue 是与属性值对比的常量，一般是数字，表示相等时带上此条件； <isNotEmpty>表示不为空且不为 null 时执行； <isNotNull>表示不为 null 值时执行。

## 四、工程规约

### （一）应用分层

1、【推荐】图中默认上层依赖于下层，箭头关系表示可直接依赖，如：开放接口层可以依赖于Web 层，也可以直接依赖于 Service 层，依此类推：

 开放接口层：可直接封装 Service 接口暴露成 RPC 接口；通过 Web 封装成 http 接口；网关控制层等。

 终端显示层：各个端的模板渲染并执行显示层。 当前主要是 velocity 渲染， JS 渲染， JSP 渲染，移动端展示层等。

 Web 层：主要是对访问控制进行转发，各类基本参数校验，或者不复用的业务简单处理等。

 Service 层：相对具体的业务逻辑服务层。

 Manager 层：通用业务处理层，它有如下特征：
1） 对第三方平台封装的层，预处理返回结果及转化异常信息；
2） 对 Service 层通用能力的下沉，如缓存方案、 中间件通用处理；
3） 与 DAO 层交互，对 DAO 的业务通用能力的封装。

 DAO 层：数据访问层，与底层 Mysql、 Oracle、 Hbase、 OB 进行数据交互。

 外部接口或第三方平台：包括其它部门 RPC 开放接口，基础平台，其它公司的 HTTP 接口。

2、 【参考】（分层异常处理规约）在 DAO 层，产生的异常类型有很多，无法用细粒度异常进行catch，使用 catch(Exception e)方式，并 throw new DaoException(e)，不需要打印日志，因为日志在 Manager/Service 层一定需要捕获并打到日志文件中去，如果同台服务器再打日志，浪费性能和存储。 在 Service 层出现异常时，必须记录日志信息到磁盘，尽可能带上参数信息，相当于保护案发现场。如果 Manager 层与 Service 同机部署，日志方式与 DAO 层处理一致，如果是单独部署，则采用与 Service 一致的处理方式。 Web 层绝不应该继续往上抛异常，因为已经处于顶层，无继续处理异常的方式，如果意识到这个异常将导致页面无法正常渲染，那么就应该直接跳转到友好错误页面，尽量加上友好的错误提示信息。开放接口层要将异常处理成错误码和错误信息方式返回。

3、 【参考】分层领域模型规约：
 DO（ Data Object）：与数据库表结构一一对应，通过 DAO 层向上传输数据源对象。
 DTO（ Data Transfer Object）：数据传输对象， Service 和 Manager 向外传输的对象。
 BO（ Business Object）：业务对象。 可以由 Service 层输出的封装业务逻辑的对象。
 QUERY：数据查询对象，各层接收上层的查询请求。 注：超过 2 个参数的查询封装，禁止使用 Map 类来传输。
 VO（ View Object）：显示层对象，通常是 Web 向模板渲染引擎层传输的对象。

### （二）二方库规约

1、 【强制】定义 GAV 遵从以下规则：

1） GroupID 格式： com.{公司/BU }.业务线.[子业务线]，最多 4 级。

说明： {公司/BU} 例如： alibaba/taobao/tmall/aliexpress 等 BU 一级；子业务线可选。

正例： com.taobao.tddl 或 com.alibaba.sourcing.multilang

2） ArtifactID 格式：产品线名-模块名。语义不重复不遗漏，先到仓库中心去查证一下。

正例： tc-client / uic-api / tair-tool

3） Version：详细规定参考下方。

2、 【强制】二方库版本号命名方式：主版本号.次版本号.修订号

1） 主版本号：当做了不兼容的 API 修改，或者增加了能改变产品方向的新功能。
2） 次版本号：当做了向下兼容的功能性新增（新增类、接口等）。
3） 修订号：修复 bug，没有修改方法签名的功能加强，保持 API 兼容性。

3、 【强制】线上应用不要依赖 SNAPSHOT 版本（安全包除外）；正式发布的类库必须使用 RELEASE版本号升级+1 的方式，且版本号不允许覆盖升级，必须去中央仓库进行查证。

说明： 不依赖 SNAPSHOT 版本是保证应用发布的幂等性。另外，也可以加快编译时的打包构建。

4、 【强制】二方库的新增或升级，保持除功能点之外的其它 jar 包仲裁结果不变。如果有改变，必须明确评估和验证， 建议进行 dependency:resolve 前后信息比对，如果仲裁结果完全不一致，那么通过 dependency:tree 命令，找出差异点，进行<excludes>排除 jar 包。

5、 【强制】二方库里可以定义枚举类型，参数可以使用枚举类型，但是接口返回值不允许使用枚举类型或者包含枚举类型的 POJO 对象。

6、 【强制】依赖于一个二方库群时，必须定义一个统一版本变量，避免版本号不一致。

说明： 依赖 springframework-core,-context,-beans，它们都是同一个版本，可以定义一个变量来保存版本： ${spring.version}，定义依赖的时候，引用该版本。

7、 【强制】禁止在子项目的 pom 依赖中出现相同的 GroupId，相同的 ArtifactId，但是不同的Version。

说明： 在本地调试时会使用各子项目指定的版本号，但是合并成一个 war，只能有一个版本号出现在最后的 lib 目录中。曾经出现过线下调试是正确的，发布到线上出故障的先例。

8、 【推荐】工具类二方库已经提供的，尽量不要在本应用中编程实现。
 json 操作： fastjson
 md5 操作： commons-codec
 工具集合： Guava 包
 数组操作： ArrayUtils（ org.apache.commons.lang3.ArrayUtils）
 集合操作： CollectionUtils(org.apache.commons.collections4.CollectionUtils)
 除上面以外还有 NumberUtils、 DateFormatUtils、 DateUtils 等优先使用org.apache.commons.lang3 这个包下的，不要使用 org.apache.commons.lang 包下面的。 原因是 commons.lang 这个包是从 JDK1.2 开始支持的所以很多 1.5/1.6 的特性是不支持的，例如：泛型。

9、 【推荐】所有 pom 文件中的依赖声明放在\<dependencies>语句块中，所有版本仲裁放在\<dependencyManagement>语句块中。

说明： \<dependencyManagement>里只是声明版本，并不实现引入，因此子项目需要显式的声明依赖， version 和 scope 都读取自父 pom。而<dependencies>所有声明在主 pom 的<dependencies >里的依赖都会自动引入，并默认被所有的子项目继承。

10、【推荐】二方库尽量不要有配置项，最低限度不要再增加配置项。

11、【参考】为避免应用二方库的依赖冲突问题，二方库发布者应当遵循以下原则：
1） 精简可控原则。移除一切不必要的 API 和依赖，只包含 Service API、必要的领域模型对象、 Utils 类、常量、枚举等。如果依赖其它二方库，尽量是 provided 引入，让二方库使用者去依赖具体版本号； 无 log 具体实现，只依赖日志框架。
2） 稳定可追溯原则。每个版本的变化应该被记录，二方库由谁维护，源码在哪里，都需要能方便查到。除非用户主动升级版本， 否则公共二方库的行为不应该发生变化。

### （三）服务器规约

1、 【推荐】 高并发服务器建议调小 TCP 协议的 time_wait 超时时间。

说明： 操作系统默认 240 秒后，才会关闭处于 time_wait 状态的连接，在高并发访问下，服务器端会因为处于 time_wait 的连接数太多，可能无法建立新的连接，所以需要在服务器上调小此等待值。

正例： 在 linux 服务器上请通过变更/etc/sysctl.conf 文件去修改该缺省值（秒）：
net.ipv4.tcp_fin_timeout = 30

2、 【推荐】 调大服务器所支持的最大文件句柄数（ File Descriptor，简写为 fd） 。

说明： 主流操作系统的设计是将 TCP/UDP 连接采用与文件一样的方式去管理，即一个连接对应于一个 fd。 主流的 linux 服务器默认所支持最大 fd 数量为 1024，当并发连接数很大时很容易因为 fd 不足而出现“open too many files” 错误，导致新的连接无法建立。 建议将 linux 服务器所支持的最大句柄数调高数倍（与服务器的内存数量相关） 。

3、 【推荐】给 JVM 设置-XX:+HeapDumpOnOutOfMemoryError 参数，让 JVM 碰到 OOM 场景时输出 dump 信息。

说明： OOM 的发生是有概率的，甚至有规律地相隔数月才出现一例，出现时的现场信息对查错非常有价值。

4、 【参考】服务器内部重定向必须使用 forward；外部重定向地址必须使用 URL Broker 生成，否则因线上采用 HTTPS 协议而导致浏览器提示“ 不安全” 。此外，还会带来 URL 维护不一致的问题。

## 五、安全规约

1、 【强制】可被用户直接访问的功能必须进行权限控制校验。

说明： 防止没有做权限控制就可随意访问、操作别人的数据，比如查看、修改别人的订单。

2、 【强制】用户敏感数据禁止直接展示，必须对展示数据脱敏。

说明： 支付宝中查看个人手机号码会显示成:158****9119，隐藏中间 4 位，防止隐私泄露。

3、 【强制】用户输入的 SQL 参数严格使用参数绑定或者 METADATA 字段值限定，防止 SQL 注入，禁止字符串拼接 SQL 访问数据库。

4、 【强制】用户请求传入的任何参数必须做有效性验证。

说明： 忽略参数校验可能导致：
 page size 过大导致内存溢出
 恶意 order by 导致数据库慢查询
 正则输入源串拒绝服务 ReDOS
 任意重定向
 SQL 注入
 Shell 注入
 反序列化注入

5、 【强制】禁止向 HTML 页面输出未经安全过滤或未正确转义的用户数据。

6、 【强制】表单、 AJAX 提交必须执行 CSRF 安全过滤。

说明： CSRF(Cross-site request forgery)跨站请求伪造是一类常见编程漏洞。对于存在 CSRF漏洞的应用/网站，攻击者可以事先构造好 URL，只要受害者用户一访问，后台便在用户不知情情况下对数据库中用户参数进行相应修改。

7、 【强制】 URL 外部重定向传入的目标地址必须执行白名单过滤。

正例：
~~~java
try {
    if (com.alibaba.fasttext.sec.url.CheckSafeUrl
        .getDefaultInstance().inWhiteList(targetUrl)){
            response.sendRedirect(targetUrl);
    }
} catch (IOException e) {
    logger.error("Check returnURL error! targetURL=" + targetURL, e);
    throw e;
}
~~~

8、 【强制】 Web 应用必须正确配置 Robots 文件，非 SEO URL 必须配置为禁止爬虫访问。
User-agent: * Disallow: /

9、 【强制】在使用平台资源，譬如短信、邮件、电话、下单、支付，必须实现正确的防重放限制，如数量限制、疲劳度控制、验证码校验，避免被滥刷、资损。

说明： 如注册时发送验证码到手机，如果没有限制次数和频率，那么可以利用此功能骚扰到其它用户，并造成短信平台资源浪费。

10、【推荐】发贴、评论、发送即时消息等用户生成内容的场景必须实现防刷、文本内容违禁词过滤等风控策略
