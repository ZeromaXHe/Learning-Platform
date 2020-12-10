# 第一篇 基础篇

# 第1章 Spring概述

## 1.1 认识Spring

Spring是分层的Java SE/EE应用一站式的轻量级开源框架，以IoC(Inverse of Control，控制反转)和AOP（Aspect Oriented Programming，切面编程）为内核，提供了展现层Spring MVC、持久层Spring JDBC及业务层事务管理等一站式的企业级应用技术。此外，Spring以海纳百川的胸怀整合了开源世界里众多著名的第三方框架和类库，逐渐成为使用最多的轻量级JavaEE企业应用开源框架。

Spring的缔造者Rod Johnson

## 1.2 关于SpringSource

当Spring1.0发布时，Rod就和他的骨干团队成立了SpringSource公司，以商业化的方式对开源的Spring进行运作。以Spring应用的开源框架为依托，成功开展了很多代表不同技术领域的子项目，将Spring的触角延伸到应用安全、云计算、批量数据处理等技术领域。

## 1.3 Spring带给我们什么

也许有很多开发者曾经被EJB的过度宣传所迷惑，成为EJB的拥趸者，并因此拥有一段痛苦的开发经历。EJB的复杂源于它对所有的企业应用采用统一的标准，它认为所有的企业应用都需要分布式对象、远程事务，因此造就了EJB框架的极度复杂。这种复杂不仅造成了陡峭的学习曲线，而且给开发、测试、部署工作造成了很多额外的要求和工作量。其中最大的诟病就是难于测试，因为这种测试不能脱离EJB容器，每次测试都需要进行应用部署并启动EJB容器，而部署和启动EJB容器是一项费时费力的重型操作，其结果是测试工作往往成为开发工作的瓶颈。

但EJB并非一无是处，它提供了很多可圈可点的服务，如声明事务、透明持久化等。Spring承认EJB中存在优秀的东西，只是它的实现太复杂、要求过严过高，所以Spring在努力提供类似服务的同时尽量简化开发，Spring认为Java EE的开发应该更容易、更简单。在实现这一目标时，Spring一直贯彻并遵守”好的设计优于具体实现，代码应易于测试“这一理念，最终带给我们一个易于开发、便于测试且功能齐全的开发框架。概括起来，Spring给我们带来以下好处。

- 方便解耦，简化开发。通过Spring提供的IoC容器，用户可以将对象之间的依赖关系交由Spring进行控制，避免硬编码所造成的过度程序耦合。有了Spring，用户不必再为单实例模式类、属性文件解析等这些底层的需求编写代码，可以更专注于上层的应用。

- AOP编程的支持。通过Spring提供的AOP功能，方便进行面向切面的编程，很多不容易用传统OOP实现的功能可以通过AOP轻松应对。

- 声明式事务的支持。在Spring中，用户可以从单调烦闷的事务管理代码中解脱出来，通过声明的方式灵活地进行事务管理，提高开发效率和质量。

- 方便程序地测试。可以用非容器依赖的编程方式进行几乎所有的测试工作。在Spring里，测试不再是昂贵的操作，而是随手可做的事情。

- 方便集成各种优秀框架。Spring不排斥各种优秀的开源框架，相反，Spring可以降低各种框架的使用难度。Spring提供了对各种优秀框架（如Struts、Hibernate、Hessian、Quartz等）的直接支持。

- 降低Java EE API的使用难度。Spring对很多难用的Java EE API（如JDBC、JavaMail、远程调用等）提供了一个薄层封装，通过Spring的简易封装，这些Java EE API的使用难度大大降低。

- Java源码是经典的学习范例。Spring的源码设计精妙、结构清晰、匠心独运，处处体现着大师对Java设计模式的灵活运用及对Java技术的高深造诣。Spring框架源码无疑是Java技术的最佳实践范例。如果想在短时间内迅速提高自己的Java技术水平和应用开发水平，学习和研究Spring源码将会收到意想不到的效果。

## 1.4 Spring体系结构

Spring核心框架由4000多个类组成，整个框架按其所属功能可以划分为5个主要模块。

~~~
IoC:            Bean, Context, 表达式语言
AOP:            Spring AOP, Aspects, Instrument
数据访问和集成:    JDBC, ORM, OXM, JMS, 事务管理
Web及远程操作:   MVC, Portlet, Web Service, WebSocket
测试框架
~~~

从整体来看，这5个主要模块几乎为企业应用提供了所需的一切，从持久层、业务层到展现层都拥有相应的支持。IoC和AOP是Spring所依赖的根本。在此基础上，Spring整合了各种企业应用开源框架和许多优秀的第三方类库，成为Java企业应用full-stack的开发框架。

1.IoC

Spring核心模块实现了IoC的功能，它将类与类之间的依赖从代码中脱离出来，用配置的方式进行依赖关系描述，由IoC容器负责依赖类之间的创建、拼接、管理、获取等工作。BeanFactory接口是Spring框架的核心接口，它实现了容器许多核心的功能。

Context模块构建于核心模块之上，扩展了BeanFactory的功能，添加了i18n国际化、Bean生命周期控制、框架事件体系、资源加载透明化等多项功能。此外，该模块还提供了许多企业级服务的支持，如邮件服务、任务调度、JNDI获取、EJB集成、远程访问等。ApplicationContext是Context模块的核心接口。

表达式语言模块是统一表达式语言（Unified EL)的一个扩展，该表达式语言用于查询和管理运行期的对象，支持设置/获取对象属性，调用对象方法，操作数组、集合等。此外，该模块还提供了逻辑表达式运算、变量定义等功能，可以方便地通过表达式串和Spring IoC容器进行交互。

2.AOP

AOP是继OOP之后，对编程设计思想影响极大的技术之一。AOP是进行横切逻辑编程的思想，它开拓了考虑问题的思路。在AOP模块里，Spring提供了满足AOP Alliance规范的实现，还整合了AspectJ这种AOP语言级的框架。在Spring里实现AOP编程有众多选择。Java5.0引入java.lang.instrument，允许在JVM启动时启用一个代理类，通过该代理类在运行期修改类的字节码，改变一个类的功能，从而实现AOP的功能。

3.数据访问和集成

任何应用程序的核心问题是对数据的访问和操作。数据有多种表现形式，如数据表、XML、消息等，而每种数据形式又拥有不同的数据访问技术（如数据表的访问即可以直接通过JDBC，也可以通过Hibernate或MyBatis）。

首先，Spring站在DAO的抽象层面，建立了一套面向DAO层的统一的异常体系，同时将各种访问数据的检查型异常转换为非检查型异常，为整合各种持久层框架提供基础。其次，Spring通过模板化技术对各种数据访问技术进行了薄层封装，将模式化的代码隐藏起来，使数据访问的程序得到大幅简化。这样，Spring就建立起了和数据形式及访问技术无关的统一的DAO层，借助AOP技术，Spring提供了声明式事务的功能。

4.Web及远程操作

该模块建立在Application Context模块之上，提供了Web应用的各种工具类，如通过Listener或Servlet初始化Spring容器，将Spring容器注册到Web容器中。该模块还提供了多项面向Web的功能，如透明化文件上传、Velocity、FreeMarker、XSLT的支持。此外，Spring可以整合Struts、WebWork等MVC框架。

5.Web及远程访问

Spring自己提供了一个完整的类似于Struts的MVC框架，称为Spring MVC。据说Spring之所以也提供了一个MVC框架，是因为Rod Johnson想证明实现MVC其实是一项简单的工作。当然，如果你不希望使用Spring MVC，那么Spring对Struts、WebWork等MVC框架的整合，一定也可以给你带来方便。相对于Servlet的MVC，Spring在简化Portlet的开发上也做了很多工作，开发者可以从中受益。

6.WebSocket

WebSocket提供了一个在Web应用中高效、双向的通信，需要考虑到客户端（浏览器）和服务器之间的高频和低时延信息交换。一般的应用场景有在线交易、游戏、协作、数据可视化等。

此外，Spring在远程访问及Web Service上提供了对很多著名框架的整合。由于Spring框架的扩展性，特别是随着Spring框架影响性的扩大，越来越多的框架主动支持Spring框架，使得Spring框架应用的涵盖面越来越宽广。

## 1.5 Spring对Java版本的要求

Spring 4.0基于Java 6.0，全面支持Java 8.0。运行Spring 4.0必须使用Java 6.0以上版本，推荐使用Java 8.0及以上版本，如果要编译Spring 4.0，则必须使用Java 8.0。此外，Spring保持和Java EE 6.0的兼容，同时也对Java EE 7.0提供一些早期的支持。

## 1.6 Spring 4.0 新特性

### 1.6.1 全面支持Java 8.0

1.Java 8.0的Lambda表达式

2.Java 8.0的时间与日期API

3.重复注解支持

4.空指针终结者：Optional<>

### 1.6.2 核心容器的增强

Spring 4.0对核心容器进行了增强，支持泛型依赖注入，对CgLib类代理不再要求必须有空参构造器（这个特性带来很大便利）；在基于Java的配置里添加了@Description；提供@Conditional注解来实现Bean的条件过滤；提供@Lazy注解解决Bean延时依赖注入；支持Bean被注入List或者Array时可以通过@Order注解或基于Ordered接口进行排序。如果使用Spring的注解支持，则可以使用自定义注解来组合多个注解，方便对外公开特定的属性。

- 泛型依赖注入：Spring 4.0可以为子类的成员变量注入泛型类型
~~~java
public abstract class BaseService<M extends Serializable> {
    @Autowired
    protected BaseDao<M> dao;
    ...
}
@Service
public class UserService extends BaseService<User> {
}
@Service
public class ViewSpaceService extends BaseService<ViewSpace>{
}
~~~

- Map依赖注入。
~~~java
@Autowired
private Map<String, BaseService> map;
~~~
上述写法将BaseService类型注入map中。其中，key是Bean的名字；value是所有实现了BaseService的Bean。

- @Lazy延迟依赖注入。
~~~java
@Lazy
@Service
public class UserService extends BaseService<User>{
}
~~~
也可以把@Lazy放在@Autowired之上，即依赖注入也是延时的，当调用userService时才会注入。同样适用于@Bean。

- List注入。
~~~java
@Autowired
private List<BaseService> list;
~~~
这样就会注入所有实现了BaseService的Bean,但顺序是不确定的。在Spring 4.0中可以使用@Order或Ordered接口来实现排序，例如：
~~~java
@Order(value=1)
@Service
public class UserService extends BaseService<User>{
}
~~~

- @Conditional注解：@Conditional类似于@Profile，一般用于在多个环境（开发环境、测试环境、正式机环境）中进行配置切换，即通过某个配置来开启某个环境。@Conditional注解的优点是允许自己定义规则。可以指定在如@Component、@Bean、@Configuration等注解的类上，以决定是否创建Bean等。

- CGLIB代理类增强：在Spring4.0中，基于CGLIB的代理类不再要求类必须有空参构造器，这是一个很好的特性。使用构造器注入有很多好处，比如，可以确保只在创建Bean时注入依赖，以保证Bean不可更改；又如，如果对UserService类进行事务增强，此时要求UserService类必须有空参构造器，就会造成很多不便。

### 1.6.3 支持用Groovy定义Bean

### 1.6.4 Web的增强

从Spring 4.0开始，Spring MVC基于Servlet3.0开发，如需使用Spring MVC测试框架，则要依赖Servlet 3.0相关.jar包（因为Mock的对象都是基于Servlet 3.0的）。另外，为了方便REST开发，引入新的@RestController控制器注解，这样就不需要在每个@RequestMapping方法上加@ResponceBody了。同时添加了一个AsyncRestTemplate，支持REST客户端的异步无阻塞请求。

### 1.6.5 支持WebSocket

### 1.6.6 测试的增强

### 1.6.7 其他

## 1.7 Spring子项目

## 1.8 如何获取Spring

## 1.9 小结

# 第2章 快速入门

## 2.1 实例概述

### 2.1.1 比Hello World更适用的实例

为了让Spring的功能轮廓更加清晰，笔者试图通过一个功能涵盖面更广的论坛登录模块替换经典的Hello World实例。

# 第3章 Spring Boot

## 3.1 Spring Boot概览

Spring Boot是由Pivotal团队设计的全新框架，其目的是用来简化Spring应用开发过程。该框架使用了特定的方式来进行配置，从而使得开发人员不再需要定义一系列样板化的配置文件，而专注于核心业务开发，项目涉及的一些基础设施则交由Spring Boot来解决。

### 3.1.1 Spring Boot发展背景

多年来，Spring配置复杂性一直为人所诟病，Spring IO子项目试图化解这一问题，但由于其主要侧重于解决集成方面的问题，因此Spring配置复杂性并没有得到本质的改观，如何实现简化Spring配置的呼声依旧高亢，直到Spring Boot的出现。Spring Boot可让开发人员不再需要编写复杂的XML配置文件，仅仅通过几行代码就能实现一个可运行的Web应用。

Spring Boot不是去再造一个“轮子”，它的“革命宣言”是为Spring项目开发带来一种全新的体验，从而大大降低Spring框架的使用门槛。

Spring Boot革新Spring项目开发体验之道，其实是借助强大的Groovy动态语言实现的，如借助Groovy强大的MetaObject协议、可插拔的AST转换器及内置的依赖解决方案引擎等。在其核心的编译模型中，Spring Boot使用Groovy来构建工程文件，所以它可以轻松地利用导入模板及方法模板对类所生成地字节码进行改造，从而让开发者仅用很简单的代码就可以完成很复杂的操作。

### 3.1.2 Spring Boot特点

Spring Boot包含如下特性：

- 为开发者提供Spring快速入门体验
- 内嵌Tomcat和Jetty容器，不需要部署WAR文件到Web容器就可独立运行应用。
- 提供许多基于Maven的pom配置模板来简化工程配置。
- 提供实现自动化配置的基础设施。
- 提供可以直接在生产环境中使用的功能，如性能指标、应用信息和应用健康检查。
- 开箱即用，没有代码生成，也无须XML配置文件，支持修改默认值来满足特定需求。

通过Spring Boot，创建一个新的Spring应用变得非常简单，只需几步即可完成。

### 3.1.3 Spring Boot启动器

| 启动器名称                             | 启动器说明                                                   |
| -------------------------------------- | ------------------------------------------------------------ |
| spring-boot-starter                    | 核心模块，包含自动配置支持、日志库和对YAML配置文件的支持     |
| spring-boot-starter-amqp               | 支持AMQP，包含spring-rabbit                                  |
| spring-boot-starter-aop                | 支持面向切面编程（AOP），包含spring-aop和AspectJ             |
| spring-boot-starter-artemis            | 通过Apache Artemis支持JMS的API（Java Message Service API）   |
| spring-boot-starter-batch              | 支持Spring Batch，包含HSQLDB                                 |
| spring-boot-starter-cache              | 支持Spring的Cache抽象                                        |
| spring-boot-starter-cloud-connectors   | 支持Spring Cloud Connectors, 简化了在像Cloud Foundry或Heroku这样的云平台上连接服务 |
| spring-boot-starter-data-gemfire       | 支持GemFire分布式数据存储，包含spring-data-gemfire           |
| spring-boot-starter-data-jpa           | 支持JPA，包含spring-data-jpa、spring-orm和Hibernate          |
| spring-boot-starter-data-elasticsearch | 支持ElasticSearch搜索和分析引擎，包含spring-data-elasticsearch |
| spring-boot-starter-data-solr          | 支持Apache Solr搜索平台，包含spring-data-solr                |
| spring-boot-starter-data-mongodb       | 支持MongoDB，包含spring-data-mongodb                         |
| spring-boot-starter-data-rest          | 支持以REST方式暴露Spring Data仓库，包含spring-data-rest-webmvc |
| spring-boot-starter-redis              | 支持Redis键值存储数据库，包含spring-redis                    |
| spring-boot-starter-jdbc               | 支持使用JDBC访问数据库                                       |
| spring-boot-starter-jta-atomikos       | 通过Atomikos支持JTA分布式事务处理                            |
| spring-boot-starter-jta-bitronix       | 通过Bitronix支持JTA分布式事务处理                            |
| spring-boot-starter-security           | 包含spring-security                                          |
| spring-boot-starter-test               | 包含常用的测试所需的依赖，如TestNG、Hamcrest、Mockito和spring-test等 |
| spring-boot-starter-velocity           | 支持使用Velocity作为模板引擎                                 |
| spring-boot-starter-freemarker         | 支持FreeMarker模板引擎                                       |
| spring-boot-starter-thymeleaf          | 支持Thymeleaf模板引擎，包括与Spring的集成                    |
| spring-boot-starter-mustache           | 支持Mustache模板引擎                                         |
| spring-boot-starter-web                | 支持Web应用开发，包含tomcat、spring-mvc、spring-webmvc和jackson |
| spring-boot-starter-websocket          | 支持使用Tomcat开发WebSocket应用                              |
| spring-boot-starter-ws                 | 支持Spring Web Service                                       |
| spring-boot-starter-groovy-templates   | 支持Groovy模板引擎                                           |
| spring-boot-starter-hateoas            | 通过spring-hateoas支持基于Hateoas的RESTful Web服务           |
| spring-boot-starter-hornetq            | 通过HornetQ支持JMS                                           |
| spring-boot-starter-log4j              | 添加Log4j的支持                                              |
| spring-boot-starter-logging            | 使用Spring Boot默认的日志框架Logback                         |
| spring-boot-starter-integration        | 支持通用的spring-integaration模块                            |
| spring-boot-starter-jersey             | 支持Jersey RESTful Web服务框架                               |
| spring-boot-starter-mail               | 支持javax.mail模块                                           |
| spring-boot-starter-mobile             | 支持spring-mobile                                            |
| spring-boot-starter-social-facebook    | 支持spring-social-facebook                                   |
| spring-boot-starter-social-linkedin    | 支持spring-social-linkedin                                   |
| spring-boot-starter-social-twitter     | 支持spring-social-twitter                                    |
| spring-boot-starter-actuator           | 添加适用于生产环境的功能，如性能指标和监测等功能             |
| spring-boot-starter-remote-shell       | 支持远程SSH命令操作                                          |
| spring-boot-starter-tomcat             | 使用Spring Boot默认的Tomcat作为应用服务器                    |
| spring-boot-starter-jetty              | 引入了Jetty HTTP引擎（用于替换Tomcat）                       |
| spring-boot-starter-undertow           | 引入了Undertow HTTP引擎（用于替换Tomcat）                    |

>计算机引导启动的英文单词是boot，可是，boot原意是靴子，“启动”与“靴子”有何关系？原来，这里的boot是bootstrap（鞋带）的缩写，它来自西方一句“拉鞋带”的谚语“pull oneself up by one's bootstraps”，译为“拽着鞋带把自己拉起来”，这就相当于项羽坐在椅子上要把自己举起来的典故一样，当然是不可能的。计算机启动本身就是一个很矛盾的过程：必须先运行程序，然后计算机才能启动，但是计算机不启动就无法运行程序——就像鸡生蛋、蛋生鸡一样！所以，工程师把这个启动过程叫做“拉鞋带”，久而久之就简称为boot了。

## 3.2 快速入门

首先需要在pom.xml文件中引入Spring Boot依赖

~~~xml
<dependecies>
	<dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <version>1.3.3.RELEASE</version>
    </dependency>
</dependecies>
~~~

当使用Maven"mvn dependency:tree"命令或IDEA依赖查看视图功能（在pom.xml文件视图中单击鼠标右键选择Diagrams->Show Dependencies）时，会发现spring-boot-starter-web内部已经封装了spring-web、spring-webmvc、jackson-databind等模块依赖。

配置好Spring Boot相关依赖之后，接下来就可以通过几行代码，快速创建一个Web应用

~~~java
@RestController
@EnableAutoConfiguration
public class BbsDaemon{
    @RequestMapping("/")
    public String index(){
        return "欢迎光临小春论坛！";
    }
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(BbsDaemon.class, args);
    }
}
~~~

其中，@EnableAutoConfiguration注解是由Boot提供的，用于对Spring框架进行自动配置，减少了开发人员的工作量；@RestController和@RequestMapping注解是由SpringMVC提供的，用于创建Rest服务。

直接运行BbsDaemon类会启动一个运行于8080端口的内嵌Tomcat服务，在浏览器中访问"http://localhost:8080"，即可看到页面上显示“欢迎光临小春论坛！”。也就是说，只需简单的两个步骤就完成了一个可独立启动运行的Web应用。既没有配置安装Tomcat或Jetty这样的应用服务器，也没有打包成WAR文件——与传统开发方式相比，Spring Boot堪称犀利！

## 3.3 安装配置

## 3.4 持久层

Spring框架提供了几种可选的操作数据库方式，可以直接使用Spring内置轻量级JdbcTemplate，也可以使用第三方持久化框架Hibernate或MyBatis。Spring Boot为这两种操作数据库方式分别提供了相应的启动器spring-boot-starter-jdbc和spring-boot-starter-jpa。应用Spring Boot启动器使数据库持久化操作变得更加简单，因为Spring Boot会自动配置访问数据库相关设施。只需在工程模块pom.xml文件中添加spring-boot-starter-data-jdbc或spring-boot-starter-data-jpa依赖即可。

## 3.5 业务层

在编写业务层代码时有两个重要的步骤：一是编写正确的业务逻辑；二是对业务事务的管控。在Spring Boot中，使用事务非常简单，首先在主类Application上标注@EnableTransactionManagerment注解（开启事务支持，相当于XML中的`<tx:annotation-driven/>`配置方式），然后在访问Service方法上标注@Transactional注解即可。如果将@Transactional注解标注在Service类级别上，那么当前Service类的所有方法都将被事务增强，建议不要在类级别上标注@Transactional注解

~~~java
@SpringBootApplication
@EnableTransactionManagement //启动注解事务管理
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
~~~

通过@EnableTransactionManagement注解，Boot为应用自己装配了事务支持。这对用户并不透明，用户如果想自己定义事务管理器，则在Application类中添加一个即可。

~~~java
@SpringBootApplication
@EnableTransactionManagement // 启用注解事务管理
public class Application{
    @Bean
    public PlatformTransactionManager txManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
    ...
}
~~~

在Application中添加自定义事务管理器方法txManager(),并在方法上标注@Bean注解，此时SpringBoot会加载自定义的事务管理器，不会重新实例化其他事务管理器。

## 3.6 展示层

> 基于Spring Boot应用，由于当前应用包含了一个可直接运行的Application类，所以在开发过程中，大家很容易在IDE（如IDEA工具）中单击鼠标右键运行当前类。虽然可以启动当前应用，在非Web应用中可能不会有什么问题，但在Web应用中，如果采用上述方法直接运行应用，那么在访问有视图的页面时（如JSP），会一直报404错误。
>
> 因为直接运行当前启动类，Spring Boot无法找到当前页面资源。因此，基于Spring Boot的应用在开发调试的时候，一定要基于Spring Boot提供的spring-boot-maven-plugin插件命令来运行应用或通过Spring Boot命令行来运行应用。

## 3.7 运维支持

Spring Boot对运维监控相关的类库进行了整合，形成了一个功能完备和可定制的启动器，称为Actuator。

基于Spring Boot应用，添加监控功能非常简单，只需在应用的pom.xml文件中添加spring-boot-starter-actuator依赖即可。

Spring Boot默认提供了对应用本身、关系数据库连接、MongoDB、Redis、Solr、ElasticSearch、JMS和RabbitMQ等服务的健康状态的检测功能。这些服务都可以在application.properties的management.health.*选项中进行配置

~~~properties
#数据库监控配置
management.health.db.enabled=true
management.health.defaults.enabled=true
#应用磁盘空间检查配置
management.health.diskspace.enabled=true
management.health.diskspace.path=D:/masterSpring/code
management.health.diskspace.threshold=0
#ElaticSearch服务健康检查配置
management.health.elasticsearch.enabled=true
management.health.elasticsearch.indices=index1,index2
management.health.elasticsearch.response-timeout=100
#Solr服务健康检查配置
management.health.solr.enabled=true
#JMS服务健康检查配置
management.health.jms.enabled=true
#Mail服务健康检查配置
management.health.mail.enabled=true
#MongoDB服务健康检查配置
management.health.mongo.enabled=true
#Rabbit MQ服务健康检查配置
management.health.rabbit.enabled=true
#Redis服务健康检查配置
management.health.redis.enabled=true
management.health.status.order=DOWN, OUT_OF_SERVICE, UNKNOWN, UP
~~~

配置好Actuator相关依赖及服务健康检查属性配置，重新启动应用，就可以在控制台上看到很多服务映射，如"/health"、"/env"、"/info"等。

在浏览器地址栏中输入其中的一个服务地址”http://localhost:8080/health“，就可以在浏览器中看到服务信息。

| 服务名称     | 服务说明                                                |
| ------------ | ------------------------------------------------------- |
| /health      | 显示应用的健康状态信息                                  |
| /configprops | 显示应用中的配置参数的实际值                            |
| /beans       | 显示应用中包含的Spring Bean的信息                       |
| /env         | 显示从ConfigurableEnvironment得到的环境配置信息         |
| /metrics     | 显示应用的性能指标                                      |
| /trace       | 显示应用相关的跟踪（trace）信息                         |
| /dump        | 生成一个线程dump                                        |
| /autoconfig  | 显示Spring Boot自动配置的信息                           |
| /mappings    | 显示Spring MVC应用中通过“@RequestMapping”添加的路径映射 |
| /info        | 显示应用的基本信息                                      |
| /shutdown    | 关闭应用                                                |

## 3.8 小结

# 第二篇 核心篇

# 第4章 IoC容器

## 4.1 IoC概述

IoC(Inverse of Control, 控制反转)是Spring容器的内核，AOP、声明式事务等功能在此基础上开花结果。IoC确实包括很多内涵，它涉及代码解耦、设计模式、代码优化等问题的考量，我们试图通过一个小例子来说明这个概念。

### 4.1.1 通过实例理解IoC的概念

贺岁大片《墨攻》中有一个场景，当刘德华所饰演的墨者革离到达梁国都城下时，城上梁国守军问道：“来者何人？”刘德华回答：“墨者革离！”我们不妨通过Java语言为这个“城门叩问”的场景编写剧本，并借此理解IoC的概念

~~~java
public class MoAttack{
    public void cityGateAsk(){
        //①演员直接侵入剧本
        LiuDeHua ldh = new LiuDeHua();
        ldh.responseAsk("墨者革离！");
    }
}
~~~

我们会发现，以上剧本在①处，作为具体角色饰演者的刘德华直接侵入剧本，使剧本和演员直接耦合在一起

~~~mermaid
classDiagram
  class MoAttack{
  	+cityGateAsk(): void
  }
  class LiuDeHua{
  	+responseAsk(): void
  }
  MoAttack..>LiuDeHua
~~~

一个明智的编剧在剧情创作时应围绕故事的角色进行，而不应考虑角色的具体饰演者，这样才可能在剧本投拍时自由地遴选任何适合的演员，而非绑定在某一人身上。通过以上分析，我们知道需要为该剧本的主人公革离定义一个接口

~~~java
public class MoAttack{
    public void cityGateAsk(){
        //①引入革离角色接口
        GeLi geli = new LiuDeHua();
        //②通过接口展开剧情
        geli.responseAsk("墨者革离！");
    }
}
~~~

在①处引入了剧本的角色——革离，剧本的情节通过角色展开，在拍摄时角色由演员饰演，如②处所示。因此，墨攻、革离、刘德华三者的类图关系如下：

~~~mermaid
classDiagram
  class MoAttack{
  	+cityGateAsk(): void
  }
  class LiuDeHua{
  	+responseAsk(): void
  }
  class GeLi{
  	<<interface>>
  	+responseAsk(): void
  }
  MoAttack ..> LiuDeHua : <<create>>
  MoAttack ..> GeLi
  LiuDeHua ..|> GeLi
~~~

MoAttack同时依赖于GeLi接口和LiuDeHua类，并没有达到我们所期望的剧本仅依赖于角色的目的。但是角色最终必须通过具体的演员才能完成拍摄，如何让LiuDeHua和剧本无关而又能完成GeLi的具体动作呢？当然是在影片投拍时，导演将LiuDeHua安排在GeLi的角色上，导演负责剧本、角色、饰演者三者的协调控制。

~~~mermaid
classDiagram
  class MoAttack{
  	+cityGateAsk(): void
  }
  class LiuDeHua{
  	+responseAsk(): void
  }
  class GeLi{
  	<<interface>>
  	+responseAsk(): void
  }
  class Director{
  	+directMovie(): void
  }
  Director ..> MoAttack
  
  Director ..> GeLi
  MoAttack ..> LiuDeHua
  Director ..> LiuDeHua : <<create>>
  MoAttack ..> GeLi
  LiuDeHua ..|> GeLi
~~~

通过引入导演，使得剧本和具体饰演者解耦。对应到软件中，导演就像一台装配器，安排演员表演具体的角色。



现在我们可以反过来讲解IoC的概念了。IoC（Inverse of Control）的字面意思是控制反转，它包括两方面的内容：

- 其一是控制
- 其二是反转

那到底是什么东西的“控制”被“反转”了呢？对应到前面的例子，“控制”是指选择GeLi角色扮演者的控制权；“反转”是指这种控制权从《墨攻》剧本中移除，转交到导演手中。对于软件来说，即某一接口具体实现类的选择控制权从调用类中移除，转交给第三方决定，即由Spring容器借由Bean配置来进行控制。

因为IoC确实不够开门见山，因此业界曾进行了广泛的讨论，最终软件界的泰斗级人物Martin Fowler提出了DI(Dependecy Injection, 依赖注入)的概念用来代替IoC，即让调用类对某一接口实现类的依赖关系由第三方（容器或协作类）注入，以移除调用类对某一接口实现类的依赖。“依赖注入”这个名词显然比“控制反转”直接明了、易于理解。

### 4.1.2 IoC的类型

从注入方法上看，IoC主要可以划分为3种类型：构造函数注入、属性注入和接口注入。Spring支持构造函数注入和属性注入。下面我们继续使用以上的例子说明这3种注入方式的区别。

#### 1.构造函数注入

在构造函数注入中，通过调用类的构造函数，将接口实现类通过构造函数变量传入

~~~java
public class MoAttack{
    private GeLi geli;
    
    //①注入革离的具体饰演者
    public MoAttack(GeLi geli){
        this.geli = geli;
    }
    public void cityGateAsk(){
        geli.responseAsk("墨者革离！");
    }
}
~~~

MoAttack的构造函数不关心具体由谁来饰演革离这个角色，只要在①处传入的饰演者按剧本要求完成相应的表演即可，角色的具体饰演者由导演来安排

~~~java
public class Director{
    public void direct(){
        //①指定角色的饰演者
        GeLi geli = new LiuDeHua();
        
        //②注入具体饰演者到剧本中
        MoAttack moAttack = new MoAttack(geli);
        moAttack.cityGateAsk();
    }
}
~~~

在①处导演安排刘德华饰演革离，并在②处将刘德华“注入”到《墨攻》剧本中，然后开始“城门叩问”剧情的演出工作。

#### 2.属性注入

有时，导演会发现，虽然革离是影片《墨攻》的第一主角，但并非每个场景都需要革离的出现，在这种情况下通过构造函数注入并不妥当，这时可以考虑使用属性注入。属性注入可以有选择地通过Setter方法完成调用类所需依赖的注入，更加灵活方便。

~~~java
public class MoAttack{
    private GeLi geli;
    
    //①属性注入方法
    public void setGeli(GeLi geli){
        this.geli = geli;
    }
    public void cityGateAsk(){
        geli.responseAsk("墨者革离！");
    }
}
~~~

MoAttack在①处为geli属性提供了一个Setter方法，以便让导演在需要时注入geli的具体饰演者

~~~java
public class Director{
    public void direct(){
        MoAttack moAttack = new MoAttack();
        //①调用属性Setter方法注入
        GeLi geli = new LiuDeHua();
        moAttack.setGeli(geli);
        moAttack.cityGateAsk();
    }
}
~~~

和通过构造函数注入革离饰演者不同，在实例化MoAttack剧本时，并未指定任何饰演者，而是在实例化MoAttack后，在需要革离出场时，才调用其setGeli()方法注入饰演者。按照类似的方式，还可以分别为剧本中的其他诸如梁王、巷淹中等角色提供输入的Setter方法，这样，导演就可以根据所拍剧段的不同，按需注入相应的角色。

#### 3.接口注入

将调用类所有依赖注入的方法抽取到一个接口中，调用类通过实现该接口提供相应的注入方法。为了采取接口注入的方式，必须先声明一个ActorArrangable接口，如下：

~~~java
public interface ActorArrangable{
    void injectGeli(Geli geli);
}
~~~

然后，MoAttack通过ActorArrangable接口提供具体的实现

~~~java
public class MoAttack implements ActorArrangable{
    private GeLi geli;
    
    //①实现接口方法
    public void injectGeli(GeLi geli){
        this.geli = geli;
    }
    public void cityGateAsk(){
        geli.responseAsk("墨者革离！");
    }
}
~~~

Director通过ActorArrangable的injectGeli()方法完成饰演者的注入工作

~~~java
public class Director{
    public void direct(){
        MoAttack moAttack = new MoAttack();
        GeLi geli = new LiuDeHua();
        moAttack.injectGeli(geli);
        moAttack.cityGateAsk();
    }
}
~~~

由于通过接口注入需要额外声明一个接口，增加了类的数目，而且它的效果和属性注入并无本质区别，因此我们不提倡采用这种注入方式。

### 4.1.3 通过容器完成依赖关系的注入





