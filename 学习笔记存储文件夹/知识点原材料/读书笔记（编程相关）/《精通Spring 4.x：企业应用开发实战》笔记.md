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

虽然MoAttack和LiuDeHua实现了解耦，MoAttack无须关注角色实现类的实例化工作，但这些工作在代码种依然存在，只是转移到Director类种而已。假设某一制片人想改变这一局面，在选择某个剧本之后，希望通过媒体“海选”或者第三方代理机构来选择导演、演员，让他们各司其职，那么剧本、导演、演员就都实现了解耦。

所谓媒体“海选”和第三方代理机构，在程序领域就是一个第三方的容器，它帮助完成类的初始化与装配工作，让开发者从这些底层实现类的实例化、依赖关系装配等工作种解脱出来，专注于更有意义的业务逻辑开发工作。这无疑是一件令人向往的事情。Spring就是这样一个容器，它通过配置文件或注解描述类与类之间的依赖关系，自动完成类的初始化和依赖注入工作。下面是Spring配置文件对以上实例进行配置的配置文件片段：

~~~xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w4.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans-4.0.xsd">
	<!--①实现类实例化-->
    <bean id="geli" class="LiuDeHua"/>
    <bean id="moAttack" class="com.smart.ioc.MoAttack" p:geli-ref="geli"/><!--②通过geli-ref建立依赖关系-->
</beans>
~~~

通过new XmlBeanFactory("bean.xml")等方式即可启动容器。在容器启动时，Spring根据配置文件的描述信息，自动实例化Bean并完成依赖关系的装配，从容器中即可返回准备就绪的Bean实例，后续可直接使用之。

Spring为什么会有这种“神奇”的力量，仅凭一个简单的配置文件，就能魔法般地实例化并装配好程序所用地Bean呢？这种“神奇”地力量归功于Java语言本身的类反射功能。下面我们独辟章节专门讲解Java语言的反射知识，为大家深刻理解Spring的技术内幕做好准备。

## 4.2 相关Java基础知识

Java语言允许通过程序化的方式间接对Class进行操作。Class文件由类装载器装载后，在JVM中将形成一份描述Class结构的元信息对象，通过该元信息对象可以获知Class的结构信息，如构造函数、属性和方法等。Java允许用户借由这个与Class相关的元信息对象间接调用Class对象的功能，这就为使用程序化方式操作Class对象开辟了途径。

### 4.2.1 简单实例

我们将从一个简单的例子开始探访Java反射机制的征程。下面的Car类拥有两个构造函数、一个方法及3个属性，如下：

~~~java
package com.smart.reflect;
public class Car{
    private String brand;
    private String color;
    private int maxSpeed;
    // ①默认构造函数
    public Car(){}
    // ②带参构造函数
    public Car(String brand, String color, int maxSpeed){
        this.brand = brand;
        this.color = color;
        this.maxSpeed = maxSpeed;
    }
    // ③未带参的方法
    public void introduce() {
        System.out.println("brand:" + brand + ";color:" + color + ";maxSpeed" + maxSpeed);
    }
    // 省略参数的getter/setter方法
    ...
}
~~~

一般情况下，我们会使用如下代码创建Car的实例：

~~~java
Car car = new Car();
car.setBrand("红旗CA72");
~~~

或者：

~~~java
Car car = new Car("红旗CA72", "黑色");
~~~

以上两种方法都采用传统方式直接调用目标类的方法。下面我们通过Java反射机制以一种间接的方式操控目标类，如下<span id="code4_10">代码清单4-10</span>：

~~~java
package com.smart.reflect;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
public class ReflectTest {
    public static Car initByDefaultConst() throws Throwable {
        // ①通过类装载器获取Car类对象
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("com.smart.reflect.Car");
        
        // ②获取类的默认构造器对象并通过它实例化Car
        Constructor cons = clazz.getDeclaredConstructor((Class[])null);
        Car car = (Car)cons.newInstance();
        
        // ③通过反射方法设置属性
        Method setBrand = clazz.getMethod("setBrand", String.class);
        serBrand.invoke(car, "红旗CA72");
        Method setColor = clazz.getMethod("setColor", String.class);
        setColor.invoke(car, "黑色");
        Method setMaxSpeed = clazz.getMethod("setMaxSpeed", int.class);
        setColor.invoke(car, 200);
        return car;
    }
    
    public static void main(String[] args) throws Throwable{
        Car car = initByDefaultConst();
        car.introduce();
    }
}
~~~

运行以上程序，在控制台上将打印出以下信息：

~~~
brand:红旗CA72;color:黑色;maxSpeed:200
~~~

这说明我们完全可以通过编程方式调用Class的各项功能，与通过构造函数和方法直接调用类功能的效果是一致的，只不过前者是间接调用，后者是直接调用罢了。

在ReflectTest中使用了几个重要的反射类，分别是ClassLoader、Class、Constructor和Method，通过这些反射类就可以间接调用目标Class的各项功能。

- 在①处，我们获取当前线程的ClassLoader，然后通过指定的全限定类名"com.smart.beans.Car"装载Car类对应的反射实例。
- 在②处，我们通过Car的反射类对象获取Car的构造函数对象cons，通过构造函数对象的newInstance()方法实例化Car对象，其效果等同于`new Car()`。
- 在③处，我们又通过Car的反射类对象的`getMethod(String method name, Class paramClass)`获取属性的Setter方法对象，其中第一个参数是目标Class的方法名；第二个参数是方法入参的对象类型。在获取方法反射对象后，即可通过`invoke(Object object, Object param)`方法调用目标类的方法，该方法的第一个参数是操作的目标类对象实例，第二个参数是目标方法的入参。

在代码清单4-10中，"com.smart.reflect.Car"、“setBrand”、“红旗CA72”之类的信息即通过反射方法操控目标类的元信息，如果我们将这些信息以一个配置文件的方法提供，就可以使用Java语言的反射功能编写一段通用的代码，对类似于Car的类进行实例化及功能调用操作。

### 4.2.2 类装载器ClassLoader

#### 1.类装载器的工作机制

类装载器就是寻找类的字节码文件并构造出类在JVM内部表示对象的组件。在Java中，类装载器把一个类装入JVM中，需要经过以下步骤：

1. 装载：查找和导入Class文件。
2. 链接：执行校验、准备和解析步骤，其中解析步骤是可以选择的。
   1. 校验：检查载入Class文件数据的正确性。
   2. 准备：给类的静态变量分配存储空间。
   3. 解析：将符号引用转换成直接引用。
3. 初始化：对类的静态变量、静态代码块执行初始化工作。

类装载工作由ClassLoader及其子类负责。ClassLoader是一个重要的Java运行时系统组件，它负责在运行时查找和装入Class字节码文件。JVM在运行时会产生3个ClassLoader：根装载器、ExtClassLoader（扩展类装载器）和AppClassLoader（应用类装载器）。其中，根装载器不是ClassLoader的子类，它使用C++语言编写，因而在Java中看不到它，根装载器负责装载JRE的核心类库，如JRE目标下的rt.jar、charsets.jar等。ExtClassLoader和AppClassLoader都是ClassLoader的子类，其中ExtClassLoader负责装载JRE扩展目录ext中的JAR类包；AppClassLoader负责装载Classpath路径下的类包。

这3个类装载器之间存在父子层级关系，即根装载器是ExtClassLoader的父装载器，ExtClassLoader是AppClassLoader的父装载器。在默认情况下，使用AppClassLoader装载应用程序的类。我们可以做一个实验，如下面代码清单4-11所示。

~~~java
public class ClassLoaderTest {
    public static void main(String[] args){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println("current loader:" + loader);
        System.out.println("parent loader:" + loader.getParent());
        System.out.println("grandparent loader:" + loader.getParent().getParent());
    }
}
~~~

运行以上代码，在控制台上将打印出以下信息：

~~~java
current loader: sum.misc.Launcher$AppClassLoader@131f71a
parent loader: sum.misc.Launcher$ExtClassLoader@15601ea
// ①根装载器在Java中访问不到，所以返回null
grandparent loader: null
~~~

通过以上输出信息，我们知道当前的ClassLoader是AppClassLoader，其父ClassLoader是ExtClassLoader，祖父ClassLoader是根装载器，因为在Java中无法获得它的句柄，所以仅返回null。

JVM装载类时使用“全盘负责委托机制”，“全盘负责”是指当一个ClassLoader装载一个类时，除非显式地使用另一个ClassLoader，该类所依赖及引用的类也由这个ClassLoader载入：“委托机制”是指先委托父装载器寻找目标类，只有在找不到的情况下才从自己的类路径中查找并装载目标类。这一点是从安全角度考虑的，试想，如果有人编写了一个恶意的基础类（如java.lang.String）并装载到JVM中，将会引起多么可怕的后果？但是由于有了“全盘负责委托机制”，java.lang.String永远是由根装载器来装载的，这样就避免了上述安全隐患的发生。

> Java的开发者想必都遇到过java.lang.NoSuchMethodError的错误信息吧。究其根源，这个错误基本上都是由JVM的“全盘负责委托机制”引发的问题。因为在类路径下放置了多个不同版本的类包，如commons-lang2.x.jar和commons-lang4.x.jar都位于类路径中，代码用到了commons-lang4.x类的某个方法，而这个方法在commons-lang2.x中并不存在，JVM加载器碰巧又从commons-lang2.x.jar中加载类，运行时就会抛出NoSuchMethodError错误。

#### 2.ClassLoader的重要方法

在Java中，ClassLoader是一个抽象类，位于java.lang包中。下面对该类的一些重要接口方法进行介绍。

- `Class loadClass(String name)`：
  name参数指定类装载器需要装载类的名字，必须使用全限定类名，如com.smart.beans.Car。该方法有一个重载方法loadClass(String name, boolean resolve)，resolve函数告诉类装载器是否需要解析该类。在初始化类之前，应考虑进行类解析的工作，但并不是所有的类都需要解析。如果JVM只需要知道该类是否存在或找出该类的超类，那么就不需要进行解析。
- `Class defineClass(String name, byte[] b, int off, int len)`：
  将类文件的字节数组转换成JVM内部的java.lang.Class对象。字节数组可以从本地文件系统、远程网络获取。参数name为字节数组对应的全限定类名。
- `Class findSystemClass(String name)`：
  从本地文件系统载入Class文件。如果本地文件系统不存在该Class文件，则将抛出ClassNotFoundException异常。该方法是JVM默认使用的装载机制。
- `Class findLoadedClass(String name)`：
  调用该方法来查看ClassLoader是否已装入某个类。如果已装入，那么返回java.lang.Class对象；否则返回null。如果强行装载已存在的类，那么将会抛出链接错误。
- `ClassLoader getParentO()`：
  获取类装载器的父装载器。除根装载器外，所有的类装载器都有且仅有一个父装载器。ExtClassLoader的父装载器是根装载器，因为根装载器非Java语言编写，所以无法获得，将返回null。

除JVM默认的3个ClassLoader外，用户可以编写自己的第三方类装载器，以实现一些特殊的需求。类文件被装载并解析后，在JVM内将拥有一个对应的java.lang.Class类描述对象，该类的实例都拥有指向这个类描述对象的引用，而类描述对象又拥有指向关联ClassLoader的引用。

每个类在JVM中都拥有一个对应的java.lang.Class对象，它提供了类结构信息的描述。数组、枚举、注解及基本Java类型（如int、double等），甚至void都拥有对应的Class对象。Class没有public的构造方法。Class对象是在装载类时由JVM通过调用类装载器中的defineClass()方法自动构造的。

### 4.2.3 Java反射机制

Class反射对象描述类语义结构，可以从Class对象中获取构造函数、成员变量、方法类等类元素的反射对象，并以编程的方式通过这些反射对象对目标类对象进行操作。这些反射对象类在java.reflect包中定义。下面介绍3个主要的反射类。

- Constructor：类的构造函数反射类，通过`Class#getConstructors()`方法可以获取类的所有构造函数反射对象数组。在Java 5.0中，还可以通过`getConstructor(Class... parameterTypes)`获取拥有特定入参的构造函数反射对象。Constructor的一个主要方法是`newInstance(Object[] initargs)`，通过该方法可以创建一个对象类的实例，相当于new关键字。在Java 5.0中，该方法演化为更为灵活的形式：`newInstance(Object... initargs)`。
- Method：类方法的反射类，通过`Class#getDeclaredMethods()`方法可以获取类的所有方法反射类对象数组Method[]。在Java 5.0中，可以通过`getDeclaredMethod(String name, Class... parameterTypes)`获取特定签名的方法，其中name为方法名；Class...为方法入参类型列表。Method最主要的方法是invoke(Object obj, Object[] args)，其中obj表示操作的目标对象；args为方法入参，[代码清单4-10](#code4_10) 中的③处演示了这个反射类的使用方法。在Java 5.0中，该方法的形式调整为invoke(Object obj, Object... args)。此外，Method还有很多用于获取类方法更多信息的方法。
  - `Class getReturnType()`：获取方法的返回值类型。
  - `Class[] getParameterTypes()`：获取方法的入参类型数组。
  - `Class[] getExceptionTypes()`：获取方法的异常类型数组。
  - `Annotation[][] getParameterAnnotations()`：获取方法的注解信息，是Java 5.0中的新方法。
- Field：类的成员变量的反射类，通过`Class#getDeclaredFields()`方法可以获取类的成员变量反射对象数组，通过`Class#getDeclaredFields(String name)`则可以获取某个特定名称的成员变量反射对象。Field类最主要的方法是`set(Object obj, Object value)`，其中obj表示操作的目标对象，通过value为目标对象的成员变量设置值。如果成员变量为基础类型，则用户可以使用Field类中提供的带类型名的值设置方法，如setBoolean(Object obj, boolean value)、setInt(Object obj, int value)等。

此外，Java还为包提供了Package反射类，在Java 5.0中还为注解提供了AnnotatedElement反射类。总之，Java的反射体系保证了可以通过程序化的方式访问目标类中所有的元素，对于private或protected成员变量和方法，只要JVM的安全机制允许，也可以通过反射进行调用，请看下面的例子代码清单4-12：

~~~java
package com.smart.reflect;
public class PrivateCar {
    // ①private成员变量：使用传统的类实例调用方式，只能在本类中访问
    private String color;
    
    // ②protected方法：使用传统的类实例调用方式，只能在子类和本包中访问
    protected void drive(){
        System.out.println("drive private car! the color is:" + color);
    }
}
~~~

color变量和drive()方法都是私有的，通过类实例变量无法在外部访问私有变量、调用私有方法，但通过反射机制则可以绕过这个限制，如下代码清单4-13：

~~~java
...
public class PrivateCarReflect {
    public static void main(String[] args) throws Throwable {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("com.smart.reflect.PrivateCar");
        PrivateCar pcar = (PrivateCar)clazz.newInstance();
        Field colorFld = clazz.getDeclaredField("color");
        
        // ①取消Java语言访问检查以访问private变量
        colorFld.setAccessible(true);
        colorFld.set(pcar, "红色");
        
        Method driveMtd = clazz.getDeclaredMethod("drive", (Class[])null);
        // Method driveMtd = clazz.getDeclaredMethod("drive"); JDK 5.0下使用
        
        // ②取消Java语言访问检查以访问protected方法
        driveMtd.setAccessible(true);
        driveMtd.invoke(pcar, (Object[])null);
    }
}
~~~

运行该类，打印出以下信息：

~~~
drive private car! the color is: 红色
~~~

在访问private或protected成员变量和方法时，必须通过setAccessible(boolean access)方法取消Java语言检查，否则将抛出IllegalAccessException。如果JVM的安全管理器设置了相应的安全机制，那么调用该方法将抛出SecurityException。

## 4.3 资源访问利器

### 4.3.1 资源抽象接口

JDK所提供的访问资源的类（如java.net.URL、File类）并不能很好地满足各种底层资源的访问需求，比如确少从类路径或者Web容器的上下文中获取资源的操作类。鉴于此，Spring设计了一个Resource接口，它为应用提供了更强的底层资源访问能力。该接口具有对应不同资源类型的实现类。先来了解一下Resource接口的主要方法。

- `boolean exists()`： 资源是否存在。
- `boolean isOpen()`： 资源是否打开。
- `URL getURL() throws IOException`：如果底层资源可以表示成URL，则该方法返回对应的URL对象。
- `File getFile() throws IOException`： 如果底层资源对应一个文件，则该方法返回对应的File对象。
- `InputStream getInputStream() throws IOException`： 返回资源对应的输入流。

Resource在Spring框架中起着不可或缺的作用，Spring框架使用Resource装载各种资源，包括配置文件资源、国际化属性文件资源等。下面我们来了解一下Resource的具体实现类。

~~~mermaid
classDiagram
	Resource<|..AbstractResource
	Resource<|--WritableResource
	AbstractResource<|--ByteArrayResource
	AbstractResource<|--InputStreamResource
	AbstractResource<|--ClassPathResource
	AbstractResource<|--PortletContextResource
	AbstractResource<|--ServletContextResource
	AbstractResource<|--DescriptiveResource
	AbstractResource<|--UrlResource
	AbstractResource<|--PathResource
	WritableResource<|..PathResource
	AbstractResource<|--FileSystemResource
	WritableResource<|..FileSystemResource
~~~

- WritableResource：可写资源接口，是Spring 3.1版本新加的接口，有两个实现类，即FileSystemResource和PathResource，其中PathResource是Spring 4.0提供的实现类。
- ByteArrayResource：二进制数组表示的资源，二进制数组资源可以在内存中通过程序构造。
- ClassPathResource：类路径下的资源，资源以相对于类路径的方式表示。
- FileSystemResource：文件系统资源，资源以文件系统路径的方式表示，如`D:/conf/bean.xml`等。
- InputStreamResource：以输入流返回表示的资源。
- ServletContextResource：为访问Web容器上下文中的资源而设计的类，负责以相对于Web应用根目录的路径加载资源。它支持以流和URL的方式访问，在WAR解包的情况下，也可以通过File方式访问。该类还可以直接从JAR包中访问资源。
- UrlResource：URL封装了java.net.URL，它使用户能够访问任何可以通过URL表示的资源，如文件系统的资源、HTTP资源、FTP资源等。
- PathResource：Spring 4.0提供的读取资源文件的新类。Path封装了java.net.URL、java.nio.file.Path（Java 7.0 提供）、文件系统资源，它使用户能够访问任何可以通过URL、Path、系统文件路径表示的资源，如文件系统的资源、HTTP资源、FTP资源等。

有了这个抽象的资源类后，就可以将Spring的配置信息放置在任何地方（如数据库、LDAP中），只要最终可以通过Resource接口返回配置信息即可。

> 提示：Spring的Resource接口及其实现类可以在脱离Spring框架的情况下使用，它比通过JDK访问资源的API更好用、更强大。

假设有一个文件位于Web应用的类路径下，用户可以通过以下方式对这个方式资源进行访问：

- 通过FileSymtemResource以文件系统绝对路径的方式进行访问。
- 通过ClassPathResource以类路径的方式进行访问。
- 通过ServletContextResource以相对于Web应用根目录的方式进行访问。

相比于通过JDK的File类访问文件资源的方式，Spring的Resource实现类无疑提供了更加灵活便捷的访问方式，用户可以根据实际情况选择适合的Resource实现类访问资源。

在获取资源后，用户就可以通过Resource接口定义的多个方法访问文件的数据和其他信息。如可以通过getFileName()方法获取文件名，通过getFile()方法获取资源对应的File对象，通过getInputStream()方法直接获取文件的输入流。通过WritableResource接口定义的多个方法向文件写数据，通过getOutputStream()方法直接获取文件的输出流。此外，还可以通过createRelative(String relativePath)在资源相对地址上创建新的文件。

在Web应用中，用户还可以通过ServletContextResource以相对于Web应用根目录的方式访问文件资源。

对于位于远程服务器（Web服务器或FTP服务器）的文件资源，用户可以方便地通过UrlResource进行访问。

资源加载时默认采用系统编码读取资源内容。如果资源文件采用特殊的编码格式，那么可以通过EncodedResource对资源进行编码，以保证资源内容操作的正确性。

### 4.3.2 资源加载

为了访问不同类型的资源，必须使用相应的Resource实现类，这是比较麻烦的。Spring提供了一个强大的加载资源的机制，不但能够通过“classpath:”、“file:”等资源地址前缀识别不同的资源类型，还支持Ant风格带通配符的资源地址。

#### 1、资源地址表达式

| 地址前缀   | 示例                                       | 对应的资源类型                                               |
| ---------- | ------------------------------------------ | ------------------------------------------------------------ |
| classpath: | classpath: com/smart/beanfactory/beans.xml | 从类路径中加载资源，classpath:和classpath:/是等价的，都是相对于类的根路径。资源文件可以在标准的文件系统中，也可以在JAR或ZIP的类包中。 |
| file:      | file:/conf/com/smart/beanfactory/beans.xml | 使用UrlResource从文件系统目录中装载资源，可采用绝对或相对路径 |
| http://    | http://www.smart.com/resource/bean.xml     | 使用UrlResource从Web服务器中装载资源                         |
| ftp://     | ftp://www.smart.com/resource/beans.xml     | 使用UrlResource从FTP服务器中装载资源                         |
| 没有前缀   | com/smart/beanfactory/beans.xml            | 根据 ApplicationContext的具体实现类采用对应类型的Resource    |

其中，和“classpath:”对应的还有另一种比较难理解的"`classpath*:`"前缀。假设有多个JAR包或文件系统类路径都拥有相同的包名（如com.smart）。“classpath:”只会在第一个加载的com.smart包的类路径下查找，而"`classpath*:`"会扫描所有这些JAR包及类路径下出现的com.smart类路径。

这对于分模块打包的应用非常有用。

Ant风格的资源地址支持3种匹配符。

- `?`：匹配文件名中的一个字符
- `*`：匹配文件名中的任意字符
- `**`：匹配多层路径

下面是几个Ant风格的资源路径的示例：

- `classpath:/t?st.xml`：匹配com类路径下的com/test.xml、com/tast.xml或者com/txst.xml文件
- `file:D:/conf/*.xml`：匹配文件系统D:/conf目录下所有以.xml为后缀的文件
- `classpath:com/**/test.xml`：匹配com类路径下（当前目录及其子孙目录）的test.xml文件。
- `classpath:org/springframework/**/*.xml`： 匹配类路径org/springframework下所有以.xml为后缀的文件。
- `classpath:org/**/servlet/bla.xml`：不仅匹配类路径org/springframework/servlet/bla.xml，也匹配org/springframework/testing/servlet/bla.xml，还匹配org/servlet/bla.xml。

#### 2、资源加载器

Spring定义了一套资源加载的接口，并提供了实现类，如下图所示

~~~mermaid
classDiagram
	class ResourcePatternResolver{
		+ getResources(String locationPattern): Resource[]
	}
	class ResourceLoader{
		+ getResources(String location): Resource
	}
	class Resource{
		+ getFile(): File
		+ getURI(): URI
	}
	ResourcePatternResolver<|..PathMatchingResourcePatternResolver
	ResourceLoader<|--ResourcePatternResolver
	ResourceLoader-->Resource
~~~

ResourceLoader接口仅有一个getResource(String location)方法，可以根据一个资源地址加载文件资源。不过，资源地址仅支持带资源类型前缀的表达式，不支持Ant风格的资源路径表达式。

ResourcePatternResolver扩展ResourceLoader接口，定义了一个新的接口方法getResources(String locationPattern)，该方法支持带资源类型前缀及Ant风格的资源路径表达式。

PathMatchingResourcePatternResolver是Spring提供的标准实现类。

> 实战经验： 用Resource操作文件时，如果资源配置文件在项目发布时会被打包到JAR中，那么不能使用Resource#getFile()方法，否则会抛出FileNotFoundException。但可以使用Resource#getInputStream()方法读取。
>
> 这个问题在实际项目开发过程中很容易被忽视，因为在项目开发时，资源配置文件一般是在文件夹下的，所以Resource#getFile()是可以正常工作的。但在发布时，如果资源配置文件被打包到JAR中，这时getFile()就无法读取了，从而造成部署实施的时候出现意想不到的问题。因此，我们建议尽量以流的方式读取，避免环境不同造成的问题。

## 4.4 BeanFactory和ApplicationContext

Spring通过一个配置文件描述Bean及Bean之间的依赖关系，利用Java语言的反射功能实例化Bean并建立Bean之间的依赖关系。Spring的IoC容器在完成这些底层工作的基础上，还提供了Bean实例缓存、生命周期管理、Bean实例代理、事件发布、资源装载等高级服务。

**Bean工厂**（com.springframework.beans.factory.BeanFactory）是Spring框架最核心的接口，它提供了高级IoC的配置机制。BeanFactory使管理不同类型的Java对象成为可能，**应用上下文**（com.springframework.context.ApplicationContext）建立在BeanFactory基础之上，提供了更多面向应用的功能，它提供了国际化支持和框架事件体系，更易于创建实际应用。我们一般称BeanFactory为IoC容器，而称ApplicationContext为应用上下文。但有时为了行文方便，我们也将ApplicationContext成为Spring容器。

对于二者的用途，我们可以进行简单的划分：BeanFactory是Spring框架的基础设施，面向Spring本身：ApplicationContext面向使用Spring框架的开发者，几乎所有的应用场合都可以直接使用ApplicationContext而非底层的BeanFactory。

### 4.4.1 BeanFactory介绍

诚如其名，BeanFactory是一个类工厂，但和传统的类工厂不同，传统的类工厂仅负责构造一个或几个类的实例：而BeanFactory是类的通用工厂，它可以创建并管理各种类的对象。这些可被创建和管理的对象本身没有什么特别之处，仅是一个POJO，Spring称这些被创建和管理的Java对象为Bean。我们知道JavaBean是要满足一定规范的，如必须提供一个默认不带参的构造函数、不依赖于某一特定的容器等，但Spring所说的Bean比JavaBean更宽泛一些，所有可以被Spring容器实例化并管理的Java类都可以成为Bean。

#### 1、BeanFactory的类体系结构

Spring为BeanFactory提供了多种实现，最常用的是XmlBeanFactory，但在Spring3.2中已被废弃，建议使用XmlBeanDefinationReader、DefaultListableBeanFactory替代。BeanFactory的类继承体系设计优雅，堪称经典。通过继承体系，我们可以很容易地了解到BeanFactory具有哪些功能，如下图所示。

~~~mermaid
classDiagram
class BeanFactory{
	<<interface>>
}
class ListableBeanFactory{
	<<interface>>
}
class HierachicalBeanFactory{
	<<interface>>
}
class ConfigurableBeanFactory{
	<<interface>>
}
class ConfigurableListableBeanFactory{
	<<interface>>
}
class BeanDefinationRegistry{
	<<interface>>
}
class SingletonBeanRegistry{
	<<interface>>
}
class AutowireCapableBeanFactory{
	<<interface>>
}
	BeanFactory<|--ListableBeanFactory
	BeanFactory<|--HierachicalBeanFactory
	HierachicalBeanFactory<|--ConfigurableBeanFactory
	ListableBeanFactory<|--ConfigurableListableBeanFactory
	ConfigurableBeanFactory<|--ConfigurableListableBeanFactory
	BeanDefinationRegistry<|..DefaultListableBeanFactory
	ConfigurableListableBeanFactory<|..DefaultListableBeanFactory
	ConfigurableBeanFactory<|..AbstractBeanFactory
	AbstractAutowireCapableBeanFactory<|--DefaultListableBeanFactory
	AbstractBeanFactory<|--AbstractAutowireCapableBeanFactory
	DefaultSingletonBeanRegistry<|--AbstractBeanFactory
	SingletonBeanRegistry<|..DefaultSingletonBeanRegistry
	AutowireCapableBeanFactory<|..AbstractAutowireCapableBeanFactory
~~~

BeanFactory接口位于类结构树的顶端，它最主要的方法就是getBean(String beanName)，该方法从容器中返回特定名称的Bean。BeanFactory的功能通过其他接口得到不断扩展。下面对图中其他接口分别进行说明。

- ListableBeanFactory：该接口定义了访问容器中Bean基本信息的若干方法，如查看Bean的个数、获取某一类型Bean的配置名、查看容器中是否包括某一Bean等。
- HierarchicalBeanFactory：父子级联IoC容器的接口，子容器可以通过接口方法访问父容器。
- ConfigurableBeanFactory：这是一个重要的接口，增强了IoC容器的可定制性。它定义了设置类装载器、属性编辑器、容器初始化后置处理器等方法。
- AutowireCapableBeanFactory：定义了将容器中的Bean按某种规则（如按名字匹配、按类型匹配等）进行自动装配的方法。
- SingletonBeanRegistry：定义了允许在运行期向容器注册单实例Bean的方法。
- BeanDefinitionRegistry：Spring配置文件中每一个`<bean>`节点元素在Spring容器里都通过一个BeanDefinition对象表示，它描述了Bean的配置信息。而BeanDefinitionRegistry接口提供了向容器手工注册BeanDefinition对象的方法。

#### 2、初始化BeanFactory

下面使用Spring配置文件为Car提供配置信息，然后通过BeanFactory装载配置文件，启动Spring IoC容器。Spring配置文件如下代码清单4-18所示：

~~~xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w4.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans-4.0.xsd">
    <bean id="car1" class="com.smart.Car" 
          p:brand="红旗CA72" 
          p:color="黑色" 
          p:maxSpeed="200" />
</beans>
~~~

下面通过XmlBeanDefinitionReader、DefaultListableBeanFactory实现类启动Spring IoC容器，如下代码清单4-19所示

~~~java
package com.smart.beanfactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import com.smart.Car;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class BeanFactoryTest{
    @Test
    public void getBean() throws Throwable{
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource res = resolver.getResource("classpath:com/smart/beanfactory/beans.xml");
        System.out.println(res.getURL());
        
        // 被废弃，不建议使用
        // BeanFactory bf = new XmlBeanFactory(res);
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(res);
        
        System.out.println("init BeanFactory.");
        
        Car car = factory.getBean("car", Car.class);
        System.out.println("car bean is ready for use!");
        car.introduce();
    }
}
~~~

XmlBeanDefinitionReader通过Resource装载Spring配置信息并启动IoC容器，然后就可以通过BeanFactory#getBean(beanName)方法从IoC容器中获取Bean。通过BeanFactory启动IoC容器时，并不会初始化配置文件中定义的Bean，初始化动作发生在第一个调用时。对于单实例（singleton）的Bean来说，BeanFactory会缓存Bean实例，所以第二次使用getBean()获取Bean时，将直接从IoC容器的缓存中获取Bean实例。

Spring在DefaultSingletonBeanRegistry类中提供了一个用于缓存单实例Bean的缓存器，它是一个用HashMap实现的缓存器，单实例的Bean以beanName为键保存在这个HashMap中。

值得一提的是，在初始化BeanFactory时，必须为其提供一种日志框架，我们使用Log4j，即在类路径下提供Log4j配置文件，这样启动Spring容器才不会报错。

### 4.4.2 ApplicationContext介绍

如果说BeanFactory是Spring的“心脏”，那么ApplicationContext就是完整的“身躯”了。ApplicationContext由BeanFactory派生而来，提供了更多面向实际应用的功能。在BeanFactory中，很多功能需要以编程的方式实现，而在ApplicationContext中则可以通过配置的方式实现。

#### 1、ApplicationContext类体系结构

ApplicationContext的主要实现类是ClassPathXmlApplicationContext和FileSystemXmlApplicationContext，前者默认从类路径加载配置文件，后者默认从文件系统中装载配置文件。下面了解一下ApplicationContext的类继承体系，如下图所示。

~~~mermaid
classDiagram
class ApplicationEventPublisher{
	<<interface>>
}
class MessageSource{
	<<interface>>
}
class ApplicationContext{
	<<interface>>
}
class ResourcePatternResolver{
	<<interface>>
}
class ResourceLoader{
	<<interface>>
}
class BeanFactory{
	<<interface>>
}
class HierachicalBeanFactory{
	<<interface>>
}
class ListableBeanFactory{
	<<interface>>
}
class LifeCycle{
	<<interface>>
}
class ConfigurableApplicationContext{
	<<interface>>
}
ApplicationEventPublisher<|--MessageSource
MessageSource<|--ApplicationContext
ApplicationEventPublisher<|--ApplicationContext
ResourceLoader<|--ResourcePatternResolver
ResourcePatternResolver<|--ApplicationContext
BeanFactory<|--HierachicalBeanFactory
BeanFactory<|--ListableBeanFactory
HierachicalBeanFactory<|--ApplicationContext
ListableBeanFactory<|--ApplicationContext
ApplicationContext<|--ConfigurableApplicationContext
LifeCycle<|--ConfigurableApplicationContext
ConfigurableApplicationContext<|..AbstractApplicationContext
AbstractApplicationContext<|--GenericApplicationContext
GenericApplicationContext<|--GenericGroovyApplicationContext
GenericApplicationContext<|--AnnotationConfigApplicationContext
AbstractApplicationContext<|--AbstractRefreshableApplicationContext
AbstractRefreshableApplicationContext<|--AbstractRefreshableConfigApplicationContext
AbstractRefreshableConfigApplicationContext<|--AbstractXmlApplicationContext
AbstractXmlApplicationContext<|--ClassPathXmlApplicationContext
AbstractXmlApplicationContext<|--FileSymstemXmlApplicationContext
~~~

ConfigurableApplicationContext扩展于ApplicationContext，它新增了两个主要的方法：refresh()和close()，让ApplicationContext具有启动、刷新和关闭上下文的能力。在应用上下文关闭的情况下调用refresh()即可启动应用上下文，在已经启动的状态下调用refresh()则可清除缓存并重新装载配置信息，而调用close()则可关闭应用上下文。这些接口方法为容器的控制管理带来了便利，但作为开发者，我们并不需要过多关心这些方法。

和BeanFactory初始化相似，ApplicationContext的初始化也很简单。如果配置文件放置在类路径下，则可以优先考虑使用ClassPathXmlApplicationContext实现类。

~~~java
ApplicationContext ctx = new ClassPathXmlApplicationContext("com/smart/context/beans.xml");
~~~

对于ClassPathXmlApplicationContext来说，“com/smart/context/beans.xml”等同于“classpath:com/smart/context/beans.xml”。

如果配置文件放置在文件系统的路径下，则可以优先考虑使用FileSystemXmlApplicationContext实现类。

~~~java
ApplicationContext ctx = new FileSystemXmlApplicationContext(
    new String[]{"conf/beans1.xml","conf/beans2.xml"});
~~~

当然，FileSystemXmlApplicationContext和ClassPathXmlApplicationContext都可以显式使用带资源类型前缀的路径，它们的区别在于如果不显式指定资源类型前缀，则分别将路径解析为文件系统路径和类路径。

在获取ApplicationContext实例后，就可以像BeanFactory一样调用getBean(beanName)返回Bean了。ApplicationContext的初始化和BeanFactory有一个重大的区别：BeanFactory在初始化容器时，并未实例化Bean，直到第一次访问某个Bean时才实例化目标Bean；

而ApplicationContext则在初始化应用上下文时就实例化所有单实例的Bean。因此，ApplicationContext的初始化时间会比BeanFactory稍长一些，不过稍后的调用则没有“第一次惩罚”的问题。

Spring支持基于类注解的配置方式，主要功能来自Spring的一个名叫JavaConfig的子项目。JavaConfig现已升级为Spring核心框架的一部分。一个标注@Configuration注解的POJO即可提供Spring所需的Bean配置信息，如下面代码清单4-20所示：

~~~java
package com.smart.context;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.smart.Car;

// ①表示是一个配置信息提供类
@Configuration
public class Beans {
    // ②定义一个Bean
    @Bean(name = "car")
    public Car buildCar(){
        Car car = new Car();
        car.setBrand("红旗CA72");
        car.setMaxSpeed(200);
        return car;
    }
}
~~~

和基于XML文件的配置方式相比，类注解的配置方式可以很容易地让开发者控制Bean地初始化过程，比基于XML文件地配置方式更加灵活。

Spring为基于注解类的配置提供了专门的ApplicationContext实现类：AnnotationConfigApplicationContext。来看一个使用AnnotationConfigApplicationContext启动Spring容器的示例，如代码清单4-21所示：

~~~java
package com.smart.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.smart.Car;
import static org.testng.Assert.*;
import org.testng.annotations.*;

public class AnnotationApplicationContextTest {
    @Test
    public void getBean(){
        // ①通过一个带@Configuration的POJO装载Bean配置
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
        Car car = ctx.getBean("car", Car.class);
        assertNotNull(car);
    }
}
~~~

AnnotationConfigApplicationContext将加载Bean.class中的Bean定义并调用Beans.class中的方法实例化Bean，启动容器并装配Bean。关于使用JavaConfig配置方式的详细内容，将在第5章详细介绍。

Spring 4.0支持使用Groovy DSL来进行Bean定义配置。其与基于XML文件的配置类似，只不过基于Groovy脚本语言，可以实现负责、灵活的Bean配置逻辑，来看一个例子，如代码清单4-22所示。

~~~java
package com.smart.context;
import com.smart.Car;

beans{
    car(Car){ // ①名字（类型）
        brand = "红旗CA72" //②注入属性
        maxSpeed = "200"
        color = "red"
    }
}
~~~

基于Groovy的配置方式可以很容易地让开发者配置复杂Bean的初始化过程，比基于XML文件、注解的配置方式更加灵活。

Spring为基于Groovy的配置提供了专门的ApplicationContext实现类：GenericGroovyApplicationContext。来看一个如何使用GenericGroovyApplicationContext启动Spring容器的示例，如代码清单4-23所示：

~~~java
package com.smart.context;

import com.smart.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.GenericGroovyApplicationContext;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class AnnotationApplicationContextTest {
    @Test
    public void getBean(){
        // ①通过一个带@Configuration的POJO装载Bean配置
        ApplicationContext ctx = new GenericGroovyApplicationContext("classpath:com/smart/context/groovy-beans.groovy");
        Car car = (Car) ctx.getBean("car");
        assertNotNull(car);
        assertEquals(car.getColor(), "red");
    }
}
~~~

#### 2、WebApplicationContext类体系结构

WebApplicationContext是专门为Web应用准备的，它允许从相对于Web根目录的路径中装载配置文件完成初始化工作。从WebApplicationContext中可以获得ServletContext的引用，整个Web应用上下文对象将作为属性放置到ServletContext中，以便Web应用环境可以访问Spring应用上下文。Spring专门为此提供了一个工具类WebApplicationContextUtils，通过该类的getWebApplicationContext(ServletContext sc)方法，可以从ServletContext中获取WebApplicationContext实例。

在非Web应用的环境下，Bean只有singleton和prototype两种作用域。WebApplicationContext为Bean添加了三个新的作用域：request、session和global session。

下面来看一下WebApplicationContext的类继承体系，如下图所示：

~~~mermaid
classDiagram
class ApplicationContext{
	<<interface>>
}
class ConfigurableApplicationContext{
	<<interface>>
}
class WebApplicationContext{
	<<interface>>
}
class ConfigurableWebApplicationContext{
	<<interface>>
}
ApplicationContext<|--ConfigurableApplicationContext
ApplicationContext<|--WebApplicationContext
ConfigurableApplicationContext<|..AbstractApplicationContext
AbstractApplicationContext<|--AbstractRefreshableApplicationContext
AbstractRefreshableApplicationContext<|--AbstractRefreshableConfigApplicationContext
AbstractRefreshableConfigApplicationContext<|--AbstractXmlApplicationContext
AbstractRefreshableConfigApplicationContext<|--AbstractRefreshableWebApplicationContext
WebApplicationContext<|--ConfigurableWebApplicationContext
ConfigurableWebApplicationContext<|..AbstractRefreshableWebApplicationContext
AbstractRefreshableWebApplicationContext<|--XmlWebApplicationContext
AbstractRefreshableWebApplicationContext<|--AnnotationConfigWebApplicationContext
AbstractRefreshableWebApplicationContext<|--GroovyWebApplicationContext
~~~

由于Web应用比一般的应用拥有更多的特性，因此WebApplicationContext扩展了ApplicationContext。WebApplicationContext定义了一个常量ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE，在上下文启动时，WebApplication实例即以此为键放置在ServletContext的属性列表中，可以通过以下语句中从Web容器中获取WebApplicationContext：

~~~java
WebApplicationContext wac = (WebApplicationContext)servletContext.getAttribute(
WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
~~~

这正是前面提到的WebApplicationContextUtils工具类getWebApplicationContext(ServletContext sc)方法的内部实现方式。这样，Spring的Web应用上下文和Web容器的上下文应用就可以实现互访，二者实现了融合。

ConfigurableWebApplicationContext扩展了WebApplicationContext，它允许通过配置的方式实例化WebApplicationContext，同时定义了两个重要的方法。

- `setServletContext(ServletContext servletContext)`：为Spring设置Web应用上下文，以便两者整合。
- `setConfigLocations(String[] configLocations)`：设置Spring配置文件地址，一般情况下，配置文件地址是相对于Web根目录的地址，如/WEB-INF/smart-dao.xml、/WEB-INF/smart-service.xml等。但用户也可以使用带资源类型前缀的地址，如classpath:com/smart/beans.xml等。

#### 3、WebApplicationContext初始化

WebApplicationContext的初始化方式和BeanFactory、ApplicationContext有所区别，因为WebApplicationContext需要ServletContext实例，也就是说，它必须在拥有Web容器的前提下才能完成启动工作。有过Web开发经验的读者都知道，可以在web.xml中配置自启动的Servlet或定义Web容器监听器(ServletContextListener)，借助二者中的任何一个，就可以完成启动Spring Web应用上下文的工作。

> 提示：所有版本的Web容器都可以定义自启动的Servlet，但只有Servlet 2.3及以上版本的Web容器才支持Web容器监听器。有些即使支持Servlet 2.3的Web服务器，也不能在Servlet初始化之前启动Web监听器，如Weblogic 8.1、WebSphere 5.x、Oracle OC4J 9.0。

Spring分别提供了用于启动WebApplicationContext的Servlet和Web容器监听器：

- org.springframework.web.context.ContextLoaderServlet。
- org.springframework.web.context.ContextLoaderListener。

二者的内部都实现了启动WebApplicationContext实例的逻辑，只要根据Web容器的具体情况选择二者之一，并在web.xml中完成配置即可。

代码清单4-24是使用ContextLoaderListener启动WebApplicationContext的具体配置。

~~~xml
...
<!--①指定配置文件-->
<context-param>
	<param-name>contextConfigLocation</param-name>
    <param-value>
        WEB-INF/smart-dao.xml, /WEB-INF/smart-service.xml
    </param-value>
</context-param>

<!--②声明Web容器监听器-->
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
~~~

ContextLoaderListener通过Web容器上下文参数contextConfigLocation获取Spring配置文件的位置。用户可以指定多个配置文件，用逗号、空格或冒号分隔均可。对于未带资源类型前缀的配置文件路径，WebApplicationContext默认这些路径相对于Web的部署根路径。当然，也可以采用带资源类型前缀的路径配置，如“`classpath*:/smart-*.xml`”和上面的配置是等效的。

如果在不支持容器监听器的低版本Web容器中，则可以采用ContextLoaderServlet完成相同的工作，如代码清单4-25所示：

~~~xml
...
<context-param>
	<param-name>contextConfigLocation</param-name>
    <param-value>
        WEB-INF/smart-dao.xml, /WEB-INF/smart-service.xml
    </param-value>
</context-param>
...
<!--①声明自启动的Servlet-->
<servlet>
	<servlet-name>springContextLoaderServlet</servlet-name>
    <servlet-class>org.springframework.web.context.ContextLoaderServlet</servlet-class>
    
    <!--②启动顺序-->
    <load-on-startup>1</load-on-startup>
</servlet>
~~~

由于WebApplicationContext需要使用日志功能，所以用户可以将Log4J的配置文件放置在类路径WEB-INF/classes下，这时Log4J引擎即可顺利启动。如果Log4J配置文件放置在其他位置，那么用户必须在web.xml中指定Log4J配置文件的位置。Spring为启动Log4J引擎提供了两个类似于启动WebApplicationContext的实现类：Log4jConfigServlet和Log4jConfigListener，不管采用哪种方式，都必须保证能够在装载Spring配置文件前先装载Log4J配置信息，如代码清单4-26所示。

~~~xml
<context-param>
	<param-name>contextConfigLocation</param-name>
    <param-value>
        WEB-INF/smart-dao.xml, /WEB-INF/smart-service.xml
    </param-value>
</context-param>
<!--①指定Log4J配置文件的位置-->
<context-param>
	<param-name>log4jConfigLocation</param-name>
    <param-value>WEB-INF/log4j.properties</param-value>
</context-param>
<!--②装载Log4J配置文件的自启动Servlet-->
<servlet>
	<servlet-name>log4jConfigServlet</servlet-name>
    <servlet-class>org.springframework.web.util.Log4jConfigServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet>
	<servlet-name>springContextLoaderServlet</servlet-name>
    <servlet-class>org.springframework.web.context.ContextLoaderServlet</servlet-class>
    <load-on-startup>2</load-on-startup>
</servlet>
~~~

注意上面将log4jConfigServlet的启动顺序号设置为1，而将springContextLoaderServlet的启动顺序号设置为2。这样，前者将先启动，完成装载Log4J配置文件并初始化Log4J引擎的工作，紧接着后者再启动。如果使用Web监听器，则必须将Log4jConfigListener放置在ContextLoaderListener前面。采用以上配置方式，Spring将自动使用XmlWebApplicationContext启动Spring容器，即通过XML文件为Spring容器提供Bean的配置信息。

如果使用标注@Configuration的Java类提供配置信息，则web.xml需要按以下方式配置，如代码清单4-27所示。

~~~xml
<web-app>
	<!--通过指定context参数，让Spring使用AnnotationConfigWebApplicationContext而非XmlWebApplicationContext启动容器-->
    <context-param>
    	<param-name>contextClass</param-name>
        <param-value>
        	org.springframework.web.context.support.AnnotationConfigWebApplicationContext
        </param-value>
    </context-param>
    
    <!--指定标注了@Configuration的配置类，多个可以使用逗号或空格分隔-->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>
        	com.smart.AppConfig1,com.smart.AppConfig2
        </param-value>
    </context-param>
    
    <!-- ContextLoaderListener监听器将根据上面的配置使用
		AnnotationConfigWebApplicationContext根据contextConfigLocation
		指定的配置类启动Spring容器-->
    <listener>
        <listener-class>
        	org.springframework.web.context.ContextLoaderListener
        </listener-class>
    </listener>
</web-app>
~~~

ContextLoaderListener如果发现配置了contextClass上下文参数，就会使用参数所指定的WebApplicationContext实现类（AnnotationConfigWebApplicationContext）初始化容器，该实现类会根据contextConfigLocation上下文参数指定的标注@Configuration的配置类所提供的Spring配置信息初始化容器。

如果使用Groovy DSL配置Bean信息，则web.xml需要按以下方式配置，如代码清单4-28所示。

~~~xml
<web-app>
    <!--通过指定context参数，让Spring使用GroovyWebApplicationContext而非XmlWebApplicationContext或AnnotationConfigWebApplicationContext启动容器-->
    <context-param>
    	<param-name>contextClass</param-name>
        <param-value>
        	org.springframework.web.context.support.GroovyWebApplicationContext
        </param-value>
    </context-param>
    
    <!--指定标注了Groovy的配置类-->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>
        	Classpath*:conf/spring-mvc.groovy
        </param-value>
    </context-param>
    
    <!-- ContextLoaderListener监听器将根据上面的配置使用
		GroovyWebApplicationContext根据contextConfigLocation
		指定的配置类启动Spring容器-->
    <listener>
        <listener-class>
        	org.springframework.web.context.ContextLoaderListener
        </listener-class>
    </listener>
</web-app>
~~~

GroovyWebApplicationContext实现类会根据contextConfigLocation上下文参数指定的conf/spring-mvc.groovy所提供的Spring配置信息初始化容器。

### 4.4.3 父子容器

通过HierarchicalBeanFactory接口，Spring的IoC容器可以建立父子层级关联的容器体系，子容器可以访问父容器中的Bean，但父容器不能访问子容器中的Bean。在容器内，Bean的id必须是唯一的，但子容器可以拥有一个和父容器id相同的Bean。父子容器层级体系增强了Spring容器架构的扩展性和灵活性，因为第三方可以通过编程的方式为一个已经存在的容器添加一个或多个特殊用途的子容器，以提供一些额外的功能。

Spring使用父子容器实现了很多功能，比如在SpringMVC中，展示层Bean位于一个子容器中，而业务层和持久层Bean位于父容器中。这样，展现层Bean就可以引用业务层和持久层Bean，而业务层和持久层Bean则看不到展现层Bean。

## 4.5 Bean的生命周期

我们知道Web容器的Servlet拥有明确的生命周期，Spring容器中的Bean也拥有相似的生命周期。Bean生命周期由多个特定的生命阶段组成，每个生命阶段都开出了一扇门，允许外界借由此门对Bean施加控制。

在Spring中，可以从两个层面定义Bean的生命周期：第一个层面是Bean的作用范围；第二个层面是实例化Bean时所经历的一系列阶段。下面分别对BeanFactory和ApplicationContext中Bean的生命周期进行分析。

### 4.5.1 BeanFactory中Bean的生命周期

#### 1、生命周期图解

由于Bean的生命周期所经历的阶段比较多，下面将通过图形化的方式进行描述。<span id="graph4-11">图4-11</span>描述了BeanFactory中Bean生命周期的完整过程

~~~mermaid
graph TB
start(( ))--通过getBean方法调用某一个Bean-->postProcessBeforeInstantiation(*调用InstantiationAwareBeanPostProcessor的postProcessBeforeInstantiation方法)
postProcessBeforeInstantiation-->实例化
实例化-->postProcessAfterInstantiation(*调用InstantiationAwareBeanPostProcessor的postProcessBeforeInstantiation方法)
postProcessAfterInstantiation-->postProcessPropertyValues(*调用InstantiationAwareBeanPostProcessor的postProcessPropertyValues方法)
postProcessPropertyValues-->设置属性值
设置属性值-->setBeanName(调用BeanNameAware的setBeanName方法)
setBeanName-->setBeanFactory(调用BeanFactoryAware的setBeanFactory方法)
setBeanFactory-->postProcessBeforeInitialization(*调用BeanPostProcessor的postProcessBeforeInitialization方法)
postProcessBeforeInitialization-->afterPropertiesSet(调用InitializingBean的afterPropertiesSet方法)
afterPropertiesSet-->init-method(通过init-method属性配置的初始化方法)
init-method--singleton-->缓冲池Bean((Spring缓存池中准备就绪的Bean))
init-method--prototype-->调用者Bean((将准备就绪的Bean交给调用者))
缓冲池Bean--容器销毁-->destroy(调用DisposableBean的destroy方法)
destroy-->destroy-method(通过destroy-method属性配置的销毁方法)
destroy-method-->stop(( ))
style 调用者Bean stroke:#f66,stroke-width:2px,stroke-dasharray: 5, 5
~~~

具体过程如下。

1. 当调用者通过getBean(beanName)向容器请求某一个Bean时，如果容器注册了org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor接口，则在实例化Bean之前，将调用接口的postProcessBeforeInstantiation()方法。
2. 根据配置情况调用Bean构造函数或工厂方法实例化Bean。
3. 如果容器注册了InstantiationAwareBeanPostProcessor接口，那么在实例化Bean之后，调用该接口的postProcessAfterInstantiation()方法，可在这里对已经实例化的对象进行一些“梳妆打扮”。
4. 如果Bean配置了属性信息，那么容器在这一步着手将配置值设置到Bean对应的属性中，不过在设置每个属性之前将先调用InstantiationAwareBeanPostProcessor接口的postProcessPropertyValues()方法。
5. 调用Bean的属性设置方法设置属性值。
6. 如果Bean实现了org.springframework.beans.factory.BeanNameAware接口，则将调用setBeanName()接口方法，将配置文件中该Bean对应的名称设置到Bean中。
7. 如果Bean实现了org.springframework.beans.factory.BeanFactoryAware接口，则将调用setBeanFactory()接口方法，将BeanFactory容器实例设置到Bean中。
8. 如果BeanFactory装配了org.springframework.beans.factory.config.BeanPostProcessor后处理器，则将调用BeanPostProcessor的Object postProcessBeforeInitialization(Object bean, String beanName)接口方法对Bean进行加工操作。其中，入参bean是当前正在处理的Bean，而beanName是当前Bean的配置名，返回的对象为加工处理后的Bean。用户可以使用该方法对某些Bean进行特殊的处理，甚至改变Bean的行为。BeanPostProcessor在Spring框架中占有重要的地位，为容器提供对Bean进行后续加工处理的切入点，Spring容器所提供的各种“神奇功能”（如AOP、动态代理等）都通过BeanPostProcessor实施。
9. 如果Bean实现了InitializingBean接口，则将调用接口的afterPropertiesSet()方法。
10. 如果在`<bean>`中通过init-method属性定义了初始化方法，则将执行这个方法。
11. BeanPostProcessor后处理器定义了两个方法：其一是postProcessorBeforeInitialization()，在第8步调用；其二是Object postProcessAfterInitialization(Object bean, String beanName)，这个方法在此时调用，容器再次获得对Bean进行加工处理的机会。
12. 如果在`<bean>`中指定Bean的作用范围为scope="prototype"，则将Bean返回给调用者，调用者负责Bean后续生命的管理，Spring不再管理这个Bean的生命周期。如果将作用范围设置为scope="singleton"，则将Bean放入Spring IoC容器的缓存池中，并将Bean引用返回给调用者，Spring继续对这些Bean进行后续的生命管理。
13. 对于scope="singleton"的Bean（默认情况），当容器关闭时，将触发Spring对Bean后续生命周期的管理工作。如果Bean实现了DisposableBean接口，则将调用接口的destroy()方法，可以在此编写释放资源、记录日志等操作。
14. 对于scope="singleton"的Bean，如果通过`<bean>`的destroy-method属性指定了Bean的销毁方法，那么Spring将执行Bean的这个方法，完成Bean资源的释放等操作。

Bean的完整生命周期从Spring容器着手实例化Bean开始，直到最终销毁Bean。其中经过了许多关键点，每个关键点都涉及特定的方法调用，可以将这些方法大致划分为4类。

- Bean自身的方法：如调用Bean构造函数实例化Bean、调用Setter设置Bean的属性值及通过`<bean>`的init-method和destroy-method所指定的方法。
- Bean级生命周期接口方法：如BeanNameAware、BeanFactoryAware、InitializingBean和DisposableBean，这些接口方法由Bean类直接实现。
- 容器级生命周期接口方法：在[图4-11](#graph4-11)中带"`*`"的步骤是由InstantiationAwareBeanPostProcessor和BeanPostProcessor这两个接口实现的，一般称它们的实现类为“后处理器”。后处理器接口一般不由Bean本身实现，它们独立于Bean，实现类以容器附加装置的形式注册到Spring容器中，并通过接口反射为Spring容器扫描识别。当Spring容器创建任何Bean的时候，这些后处理都会发生作用，所以这些后处理器的影响是全局性的。当然，用户可以通过合理地编写后处理器，让其仅对感兴趣的Bean进行加工处理。
- 工厂后处理器接口方法：包括AspectJWeavingEnabler、CustomAutowireConfigurer、ConfigurationClassPostProcessor等方法。工厂后处理器也是容器级的，在应用上下文装配配置文件后立即调用。

Bean级生命周期接口和容器级生命周期接口是个性和共性辩证统一思想的体现，前者解决Bean个性化处理的问题，而后者解决容器中某些Bean共性化处理的问题。

Spring容器中是否可以注册多个后处理器呢？答案是肯定的。只要它们同时实现org.springframework.core.Ordered接口，容器将按特定的顺序依次调用这些后处理器。所以[图4-11](#graph4-11)带"`*`"的步骤都可能调用多个后处理器进行一系列加工操作。

InstantiationAwareBeanPostProcessor其实是BeanPostProcessor接口的子接口，Spring为其提供了一个适配器类InstantiationAwareBeanPostProcessorAdapter，一般情况下，可以方便地扩展该适配器覆盖感兴趣的方法以定义实现类。下面将通过一个具体的实例来更好地理解Bean生命周期的各个步骤。

#### 2、窥探Bean生命周期的实例

依旧采用前面介绍的Car类，让它实现所有Bean级的生命周期接口。此外，还定义了初始化和销毁的方法，这两个方法将通过`<bean>`的init-method和destroy-method属性指定，如代码清单4-29所示。

~~~java
package com.smart;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// ①管理Bean生命周期的接口
public class Car implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {
    private String brand;
    private String color;
    private int maxSpeed;
    
    private BeanFactory beanFactory;
    private String beanName;
    
    public Car(){
        System.out.println("调用Car()构造函数。");
    }
    public void setBrand(String brand){
        System.out.println("调用setBrand()设置属性。");
        this.brand = brand;
    }
    public void introduce(){
        System.out.println("brand:" + brand + ";color:" + color + ";maxSpeed:" + maxSpeed);
    }
    
    // ②BeanFactoryAware接口方法
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("调用BeanFactoryAware.setBeanFactory()。");
        this.beanFactory = beanFactory;
    }
    
    // ③BeanNameAware接口方法
    public void setBeanName(String beanName) {
        System.out.println("调用BeanNameAware.setBeanName()。");
        this.beanName = beanName;
    }
    
    // ④InitializingBean接口方法
    public void afterPropertiesSet() throws Exception {
        System.out.println("调用InitializingBean.afterPropertiesSet()。");
    }
    
    // ⑤DisposableBean接口方法
    public void destroy() throws Exception {
        System.out.println("调用Disposable.destroy()。");
    }
    
    // ⑥通过<bean>的init-method属性指定的初始化方法
    public void myInit() {
        System.out.println("调用init-method所指定的myInit()，将maxSpeed设置为240。");
        this.maxSpeed = 240;
    }
    
    // ⑦通过<bean>的destroy-method属性指定的销毁方法
    public void myDestroy() {
        System.out.println("调用destroy-method所指定的myDestroy()。");
    }
}
~~~

Car类在②、③、④、⑤处实现了BeanFactoryAware、BeanNameAware、InitializingBean、DisposableBean这些Bean级的生命周期控制接口；在⑥和⑦处定义了myInit()和myDestroy()方法，以便在配置文件中通过init-method和destroy-method属性定义初始化和销毁方法。

MyInstantiationAwareBeanPostProcessor通过扩展InstantiationAwareBeanPostProcessor适配器InstantiationAwareBeanPostProcessorAdapter提供实现，如代码清单4-30所示。

~~~java
package com.smart.beanfactory;
import java.beans.PropertyDescriptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import com.smart.Car;

public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {
    // ①接口方法：在实例化Bean前调用
    public Object postProcessBeforeInstantiation(Class beanClass, String beanName) throws BeansException {
        // ①-1仅对容器中的car Bean处理
        if("car".equals(beanName)){
            System.out.println("InstantiationAware BeanPostProcessor.postProcessBeforeInstantiation");
        }
        return null;
    }
    
    // ②接口方法：在实例化Bean后调用
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        // ②-1仅对容器中的car Bean处理
        if("car".equals(beanName)){
            System.out.println("InstantiationAware BeanPostProcessor.postProcessAfterInstantiation");
        }
        return true;
    }
    
    // ③接口方法：在设置某个属性时调用
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeanException {
        // ③-1仅对容器中的car Bean进行处理，还可以通过pdst入参进行过滤，
        // 仅对car的某个特定属性值进行处理
        if("car".equals(beanName)){
            System.out.println("Instantiation AwareBeanPostProcessor.postProcessPropertyValues");
        }
        return pvs;
    }
}
~~~

在MyInstatiationAwareBeanPostProcessor中，通过过滤条件仅对car Bean进行处理，对其他的Bean一概视而不见。

此外，还提供了一个BeanPostProcessor实现类，在该实现类中仅对car Bean进行处理，对配置文件所提供的属性设置值进行判断，并执行相应的“补缺补漏”操作，如代码清单4-31所示：

~~~java
package com.smart.beanfactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import com.smart.Car;
public class MyBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("car")){
            Car car = (Car)bean;
            if(car.getColor()==null){
                System.out.println("调用BeanPostProcessor.postProcessBeforeInitialization(), color为空，设置为默认黑色。");
                car.setColor("黑色");
            }
        }
        return bean;
    }
    
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(beanName.equals("car")){
            Car car = (Car)bean;
            if(car.getMaxSpeed()) >= 200){
                System.out.println("调用BeanPostProcessor.postProcessAfterInitialization(),将maxSpeed调整为200。");
                car.setMaxSpeed(200);
            }
        }
        return bean;
    }
}
~~~

在MyBeanPostProcessor类的postProcessBeforeInitialization()方法中，首先判断所处理的Bean是否名为car，如果是，则进一步判断该Bean的color属性是否为空；如果为空，则将该属性设置为“黑色”。在postProcessAfterInitialization()方法中，仅对名为car的Bean进行处理，判断其maxSpeed是否超过最大速度200，如果超过，则将其设置为200。

至于如何将MyInstantiationAwareBeanPostProcessor和MyBeanPostProcessor这两个处理器注册到BeanFactory容器中，请参看代码清单4-32：

~~~xml
<bean id="car" class="com.smart.Car"
      init-method="myInit"
      destroy-method="myDestroy"
      p:brand="红旗CA72"
      p:maxSpeed="200"/>
~~~

通过init-method指定Car的初始化方法为myInit();通过destroy-method指定Car的销毁方法为myDestroy(); 同时通过scope定义了Car的作用范围（关于Bean作用范围的详细讨论，请参见5.8节）。

下面让容器装载配置文件，然后分别注册上面所提供的两个后处理器，如代码清单4-33所示：

~~~java
package com.smart.beanfactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.xml.xmlBeanFactory;
import org.springframework.beans.core.io.ClassPathResource;
import org.springframework.beans.core.io.Resource;
import com.smart.Car;

public class BeanLifeCycle{
    private static void LifeCycleInBeanFactory(){
        // ①下面两句装载配置文件并启动容器
        Resource res = new ClassPathResource("com/smart/beanfactory/beans.xml");
        
        BeanFactory bf = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((DefaultListableBeanFactory)bf);
        reader.loadBeanDefinitions(res);
        
        // ②向容器中注册MyBeanPostProcessor后处理器
        ((ConfigurableBeanFactory)bf).addBeanPostProcessor(new MyBeanPostProcessor());
        
        // ③向容器中注册MyInstantiationAwareBeanPostProcessor后处理器
        ((ConfigurableBeanFactory)bf).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        
        // ④第一次从容器中获取car，将触发容器实例化该Bean，这将引发Bean生命周期方法的调用
        Car car1 = (Car)bf.getBean("car");
        car1.introduce();
        car1.setColor("红色");
        
        // ⑤第二次从容器中获取car，直接从缓存池中获取
        Car car = (Car)bf.getBean("car");
        
        // ⑥查看car1和car2是否指向同一引用
        System.out.println("car1==car2:"+(car1==car2));
        
        // ⑦关闭容器
        ((DefaultListableBeanFactory)bf).destroySingletons();
    }
    
    public static void main(String[] args){
        LifeCycleInBeanFactory();
    }
}
~~~

在①处，装载了配置文件并启动容器。在②处，向容器中注册了MyBeanPostProcessor后处理器，注意对BeanFactory类型的bf变量进行了强制类型转换，因为用于注册后处理器的addBeanPostProcessor()方法是在ConfigurableBeanFactory接口中定义的。如果有多个后处理器，则可以按照相似的方式调用addBeanPostProcessor()方法进行注册。需要强调的是，后处理器的实际调用顺序和注册顺序是无关的，在具有多个后处理器的情况下，必须通过实现的org.springframework.core.Ordered接口来确定调用顺序。

在③处，按照注册MyBeanPostProcessor后处理器相同的方法注册MyInstantiationAwareBeanPostProcessor后处理器，Spring容器会自动检查后处理器是否实现了InstantiationAwareBeanPostProcessor接口，并据此判断后处理器的类型。

在④处，第一次从容器中获取car Bean，容器将按[图4-11](#graph4-11)中描述的Bean生命周期过程，实例化Car并将其放入缓存池中，然后再将这个Bean引用返回给调用者。在⑤处，再次从容器中获取car Bean，Bean将从容器缓存池中直接取出，不会引发生命周期相关方法的执行。如果Bean的作用范围定义为scope="prototype"，则第二次getBean()时，生命周期方法会再次被调用，因为prototype范围的Bean每次都返回新的实例。在⑥处，检验car1和car2是否指向相同的对象。

运行BeanLifeCycle，在控制台得到以下输出信息：

~~~
InstantiationAwareBeanPostProcessor.postProcessorBeforeInstantiation
调用Car()构造函数。
InstantiationAwareBeanPostProcessor.postProcessorAfterInstantiation
InstantiationAwareBeanPostProcessor.postProcessorPropertyValues
调用setBrand()设置属性。
调用BeanNameAware.setBeanName()。
调用BeanFactoryAware.setBeanFactory()。
调用BeanPostProcessor.postProcessBeforeInitialization(), color为空，设置为默认黑色。
调用InitializingBean.afterPropertiesSet()。
调用myInit()，将maxSpeed设置为240。
调用BeanPostProcessor.postProcessAfterInitialization(),将maxSpeed调整为200。
brand:奇瑞QQ;color:黑色;maxSpeed:200
brand:奇瑞QQ;color:红色;maxSpeed:200
2016-01-03 15:47:10,640 INFO [main] (DefaultSingletonBeanRegistry.java:272) - Destroying singletons in (org.springframework.beans.factory.xml.XmlBeanFactory defining beans [car]; root of BeanFactory hierarchy)
调用DisposableBean.destroy()。
调用myDestroy()。
~~~

仔细观察输出的信息，发现其验证了前面所介绍的Bean生命周期的完整过程。在⑦处，通过destroySingletons()方法关闭了容器，由于Car实现了销毁接口并指定了销毁方法，所以容器将触发调用这两个方法。

#### 3、关于Bean生命周期接口的探讨

通过实现Spring的Bean生命周期接口对Bean进行额外控制，虽然让Bean具有了更细致的生命周期阶段，但也带来了一个问题：Bean和Spring框架紧密地绑定在一起，这和Spring一直推崇的“不对应用程序类作任何限制”的理念是相悖的。因此，如果用户希望将业务类完全POJO化，则可以只实现自己的业务接口，不需要和某个特定框架（包括Spring框架）的接口关联。可以通过`<bean>`的init-method和destroy-method属性配置方式为Bean指定初始化和销毁的方法，采用这种方式对Bean生命周期的控制效果和通过实现InitializingBean和DisposableBean接口所达到的效果是完全不同的。采用前者的配置方式可以使Bean不需要和特定的Spring框架接口绑定，达到了框架解耦的目的。此外，Spring还拥有一个Bean后置处理器initDestroyAnnotationBeanPostProcessor，它负责对标注了@PostConstruct、@PreDestroy的Bean进行处理，在Bean初始化后及销毁前执行相应的逻辑。喜欢注解的读者，可以通过InitDestroyAnnotationBeanPostProcessor达到和以上两种方式相同的效果（如果在ApplicationContext中，则已经默认装配了该处理器）。

对于BeanFactoryAware和BeanNameAware接口，前者让Bean感知容器（BeanFactory实例），而后者让Bean获得配置文件中对应的配置名称。一般情况下，用户几乎不需要关心这两个接口。如果Bean希望获取容器中的其他Bean，则可以通过属性注入的方式引用这些Bean；如果Bean希望在运行期获知在配置文件中的Bean名称，则可以简单地将名称作为属性注入。

综上所述，我们认为，除非编写一个基于Spring之上的扩展插件或子项目之类的东西，否则用户完全可以抛开以上4个Bean生命周期的接口类，使用更好的方案替代之。

但BeanPostProcessor接口却不一样，它不要求Bean去继承它，可以完全像插件一样注册到Spring容器中，为容器提供额外的功能。Spring容器充分利用了BeanPostProcessor对Bean进行加工处理，当我们讲到Spring的AOP功能时，还会对此进行分析，了解BeanPostProcessor对Bean的影响，对于深入理解Spring核心功能的工作机理将会有很大的帮助。很多Spring扩展插件或Spring子项目都是使用这些后处理器完成激动人心的功能的。

### 4.5.2 ApplicationContext中Bean的生命周期

Bean在应用上下文中的生命周期和在BeanFactory中的生命周期类似，不同的是，如果Bean实现了org.springframework.context.ApplicationContextAware接口，则会增加一个调用该接口方法setApplicationContext()的步骤，如图4-12所示。

~~~mermaid
graph TB
start(( ))--启动容器-->postProcessBeanFatory(调用BeanFactoryPostProcessor的postProcessBeanFactory方法对工厂定义信息进行后处理)
postProcessBeanFatory--通过getBean调用某一个Bean-->postProcessBeforeInstantiation(*调用InstantiationAwareBeanPostProcessor的postProcessBeforeInstantiation方法)
postProcessBeforeInstantiation-->实例化
实例化-->postProcessAfterInstantiation(*调用InstantiationAwareBeanPostProcessor的postProcessBeforeInstantiation方法)
postProcessAfterInstantiation-->postProcessPropertyValues(*调用InstantiationAwareBeanPostProcessor的postProcessPropertyValues方法)
postProcessPropertyValues-->设置属性值
设置属性值-->setBeanName(调用BeanNameAware的setBeanName方法)
setBeanName-->setBeanFactory(调用BeanFactoryAware的setBeanFactory方法)
setBeanFactory-->setApplicationContext(调用ApplicationContextAware的setApplicationContext方法)
setApplicationContext-->postProcessBeforeInitialization(*调用BeanPostProcessor的postProcessBeforeInitialization方法)
postProcessBeforeInitialization-->afterPropertiesSet(调用InitializingBean的afterPropertiesSet方法)
afterPropertiesSet-->init-method(通过init-method属性配置的初始化方法)
init-method--singleton-->缓冲池Bean((Spring缓存池中准备就绪的Bean))
init-method--prototype-->调用者Bean((将准备就绪的Bean交给调用者))
缓冲池Bean--容器销毁-->destroy(调用DisposableBean的destroy方法)
destroy-->destroy-method(通过destroy-method属性配置的销毁方法)
destroy-method-->stop(( ))
style 调用者Bean stroke:#f66,stroke-width:2px,stroke-dasharray: 5, 5
~~~

此外，如果在配置文件中声明了工厂后处理器接口BeanFactoryPostProcessor的实现类，则应用上下文在装载配置文件之后、初始化Bean实例之前将调用这些BeanFactoryPostProcessor对配置信息进行加工处理。Spring框架提供了多个工厂后处理器，如CustomEditorConfigurer、PropertyPlaceholderConfigurer等，我们将在第5章中详细介绍它们的功用。如果在配置文件中定义了多个工厂后处理器，那么最好让它们实现org.springframework.core.Ordered接口，以便Spring以确定的顺序调用它们。工厂后处理器是容器级的，仅在应用上下文初始化时调用一次，其目的是完成一些配置文件的加工处理工作。

ApplicationContext和BeanFactory另一个最大的不同之处在于：前者会利用Java反射机制自动识别出配置文件中定义的BeanPostProcessor、InstantiationAwareBeanPostProcessor和BeanFactoryPostProcessor，并自动将它们注册到应用上下文中；而后者需要在代码中通过手工调用addBeanPostProcessor()方法进行注册。这也是为什么在应用开发时普遍使用ApplicationContext而很少使用BeanFactory的原因之一。

在ApplicationContext中，只需在配置文件中通过`<bean>`定义工厂后处理器和Bean后处理器，它们就会按预期的方式运行。

来看一个使用工厂后处理器的实例。假设我们希望对配置文件中car的brand配置属性进行调整，则可以编写一个如代码清单4-34所示的工厂后处理器。

~~~java
package com.smart.context;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import com.smart.Car;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    // ①对car <bean>的brand属性配置信息进行“偷梁换柱”的加工操作
    public void postProcessBeanFactory(ConfigurableListableBeanFactory bf) throws BeansException {
        BeanDefinition bd = bf.getBeanDefinition("car");
        
        bd.getPropertyValues().addPropertyValue("brand", "奇瑞QQ");
        System.out.println("调用BeanFactoryPostProcessor.postProcessBeanFactory()!");
    }
}
~~~

ApplicationContext在启动时，将首先为配置文件中的每个`<bean>`生成一个BeanDefinition对象，BeanDefinition是`<bean>`在Spring容器中的内部表示。当配置文件中所有的`<bean>`都被解析成BeanDefinition时，ApplicationContext将调用工厂后处理器的方法，因此，我们有机会通过程序的方式调整Bean的配置信息。在这里，我们将car对应的BeanDefinition进行调整，将brand属性设置为"奇瑞QQ"，具体配置如代码清单4-35所示

~~~xml
<!--①这个brand属性的值将被工厂后处理器更改掉-->
<bean id="car" class="com.smart.Car" init-method="myInit" destroy-method="myDestroy"
      p:brand="红旗CA72"
      p:maxSpeed="200"/>
<!--②工厂后处理器-->
<bean id="myBeanPostProcessor"
      class="com.smart.context.MyBeanPostProcessor"/>
<!--③注册Bean后处理器-->
<bean id="myBeanFactoryPostProcessor"
      class="com.smart.context.MyBeanFactoryPostProcessor"/>
~~~

在②和③处定义的BeanPostProcessor和BeanFactoryPostProcessor会自动被ApplicationContext识别并注册到容器中。在②处注册的工厂后处理器将会对在①处配置的属性值进行调整。在③处还声明了一个Bean后处理器，它也可以对Bean的属性进行调整。启动容器并查看car Bean的信息，将发现car Bean的brand属性成功被工厂后处理器更改了。

## 4.6 小结

# 第5章 在IoC容器中装配Bean

## 5.1 Spring配置概述

### 5.1.1 Spring容器高层视图

要使应用程序中的Spring容器成功启动，需要同时具备以下三方面的条件：

- Spring框架的类包都已经放到应用程序的类路径下。
- 应用程序为Spring提供了完备的Bean配置信息。
- Bean的类都已经放到应用程序的类路径下。

Spring启动时读取应用程序提供的Bean配置信息，并在Spring容器中生成一份响应的Spring配置注册表，然后根据这张注册表实例化Bean，装配好Bean之间的依赖关系，为上层应用提供准备就绪的运行环境。

Bean配置信息是Bean的元数据信息，它由以下4个方面组成：

- Bean的实现类。
- Bean的属性信息，如数据源的连接数、用户名、密码等。
- Bean的依赖关系，Spring根据依赖关系配置完成Bean之间的装配。
- Bean的行为配置，如生命周期范围及生命周期各过程的回调函数等。

Bean元数据信息在Spring容器中的内部对应物是由一个个BeanDefinition形成的Bean注册表，Spring实现了Bean元数据信息内部表示和外部定义的解耦。Spring支持多种形式的Bean配置方式。Spring1.0仅支持基于XML的配置，Spring2.0新增基于注解配置的支持，Spring3.0新增基于Java类配置的支持，而Spring4.0则新增基于Groovy动态语言配置的支持。

Bean配置信息首先定义了Bean的实现及依赖关系，Spring容器根据各种形式的Bean配置信息在容器内建立Bean定义注册表；然后根据注册表加载、实例化Bean，并建立Bean和Bean之间的依赖关系；最后将这些准备就绪的Bean放到Bean缓存池中，以供外层的应用程序进行调用。

### 5.1.2 基于XML的配置

对于基于XML的配置，Spring1.0的配置文件采用DTD格式，Spring2.0以后采用Schema格式，后者让不同类型的配置拥有了自己的命名空间，使得配置文件更具扩展性。此外，Spring基于Schema配置方案为许多领域的问题提供了简化的配置方法，配置工作因此得到了大幅简化。

## 5.2 Bean基本配置

### 5.2.1 装配一个Bean

### 5.2.2 Bean的命名

一般情况下，在配置一个Bean时，需要为其指定一个id属性作为Bean的名称。id在IoC容器中必须是唯一的，而且id的命名需要满足XML对id的命名规范（id是XML规定的特殊属性）：必须以字母开始，后面可以是字母、数字、连字符、下划线、句号、冒号等完整结束（full stops）的符号，逗号和空格这些非完整结束符是非法的。在实际情况下，id命名约束并不会给用户带来影响，但如果用户确实希望用一些特殊字符进行Bean命名，则可以使用`<bean>`的name属性。name属性没有字符上的限制，几乎可以使用任何字符，如`?ab`、`123`等。

id和name都可以指定多个名字，名字之间可用逗号、分号或者空格进行分隔。

Spring配置文件不允许出现两个相同id的`<bean>`，但却可以出现两个相同name的`<bean>`。如果有多个name相同的`<bean>`，那么通过getBean(beanName)获取Bean时，将返回后面声明的那个Bean，原因是后面的Bean覆盖了前面同名的Bean。所以为了避免无意间Bean覆盖的隐患，尽量使用id而非name命名Bean。

如果id和name两个属性都未指定，那么Spring自动将全限定类名作为Bean的名称，这时用户可以通过`getBean("com.smart.simple.Car")`获取car Bean。如果存在多个实现类相同的匿名`<bean>`,如下：

~~~xml
<bean class="com.smart.simple.Car"/>
<bean class="com.smart.simple.Car"/>
<bean class="com.smart.simple.Car"/>
~~~

第一个Bean通过`getBean("com.smart.simple.Car")`获得；第二个Bean通过`getBean("com.smart.simple.Car#1")`获得；第三个Bean通过`getBean("com.smart.simple.Car#3")`获得，以此类推。一般匿名`<bean>`在内部Bean为外层Bean提供注入值时使用，正如Java匿名类一样。

> 提示： 各种眼花缭乱、花拳绣腿式的命名方式着实让我们见识了Spring配置的灵活性和包容性，但在一般情况下，那些奇怪的命名大多是唬人的噱头，不值得在实际项目中使用，通过id为Bean指定唯一的名称才是“康庄大道”。

## 5.3 依赖注入

Spring支持两种依赖注入方式，分别是属性注入和构造函数注入。除此之外，Spring还支持工厂方法注入方式。

### 5.3.1 属性注入

属性注入指通过setXxx()方法注入Bean的属性值或依赖对象。由于属性注入方式具有可选择性和灵活性高的特点，因此属性注入是实际应用中最常采用的注入方式。

#### 1. 属性注入实例

属性注入要求Bean提供一个默认的构造函数，并为需要注入的属性提供对应的setter方法。Spring先调用Bean的默认构造函数实例化Bean对象，然后通过反射的方式调用Setter方法注入属性值。

> 提示：默认构造函数是不带参的构造函数。Java语言规定，如果类中没有定义任何构造函数，则JVM会自动为其生成一个默认的构造函数；反之，如果类中显式定义了构造函数，则JVM不会为其生成默认的构造函数。所以假设Car类中显式定义了一个带参的构造函数，如public Car(String brand)，则需要同时提供一个默认的构造函数public Car()，否则使用属性注入时将抛出异常。

需要指出的是，Spring只会检查Bean是否有对应的Setter方法，至于Bean中是否有对应的属性成员变更则不做要求。举个例子，配置文件中`<property name="brand">`的属性配置项仅要求Car类中拥有setBrand()方法，但Car类不一定要拥有brand成员变量。

虽然如此，但在一般情况下，仍然按照约定俗成的方式在Bean中提供同名的属性变量。

#### 2. JavaBean关于属性命名的特殊规范

Spring配置文件中`<property>`元素所指定的属性名和Bean实现类的Setter实现类的Setter方法满足Sun JavaBean的属性命名规范：xxx的属性对应setXxx()方法。

一般情况下，Java的属性变量名都以小写字母开头，如maxSpeed、brand等，但也存在特殊情况。考虑到一些特定意义的大写英文缩略词（如USA、XML等），JavaBean也允许以大写字母开头的属性变量名，不过必须满足“变量的前两个字母要么全部大写，要么全部小写”的要求，如brand、IDCode、IC、ICCard等属性变量名是合法的，而iC、iCcard、iDCode等属性变量名则是非法的。这个并不广为人知的JavaBean规范条款引发了让人困惑的配置问题。

为了更清楚地理解这个隐晦地问题，我们来看一个具体的实例。下面是一个“违反”了JavaBean属性命名规范的类：

~~~java
public class Foo {
    // ①非法的属性变量名，不过Java语言本身不会报错，因为它将iDCode看成普通的变量
    private String iDCode;
    
    // ②该Setter方法对应IDCode属性而非iDCode属性
    public void setIDCode(String iDcode){
        this.iDCode = iDcode;
    }
}
~~~

在Spring配置文件中，我们可能会想当然地为Foo提供以下配置：

~~~xml
<bean id="foo" class="com.smart.attr.Foo">
	<!--①这个属性变量名是非法的！！-->
    <property name="iDCode" value="070101"/>
</bean>
~~~

当我们试图启动Spring容器时，将会得到启动失败的结果，控制台输出如下错误信息：

~~~
Error setting property values; 
nested exception is org.springframework.beans.NotWritablePropertyException: Invalid property 'iDCode' of bean class [com.smart.attr.Foo]: Bean property 'iDCode' is not writable or has an invalid Setter method. Did you mean 'IDCode'?
Caused by: org.springframework.beans.NotWritablePropertyException: Invalid property 'iDCode' of bean class
~~~

虽然Spring给出了启动失败的原因，但错误信息具有很强的误导性，因为它“抱怨”Foo中没有提供对应于iDCode的Setter方法，但事实上Foo已经提供了setIDCode()方法。那真相到底是什么呢？其实真正的错误根源是我们在Spring配置文件中指定了一个非法的属性名iDcode，这个非法的属性名永远不可能有对应的Setter方法，因此错误就产生了。

纠正的办法是将配置文件中的属性名改为IDCode，如下：

~~~xml
<bean id="foo" class="com.smart.attr.Foo">
	<!--①IDCode对应setIDCode()属性设置方法-->
    <property name="IDCode" value="070101"/>
</bean>
~~~

Foo类中的iDCode属性变量名不一定要修改，因为我们说过，Spring配置文件的属性名仅对应于Bean实现类的get/setXxx()方法。但是如果进一步探讨引发这个配置错误的根源，我们就会归咎于Foo类中iDCode的变量名。原因很简单，因为我们在编写了Foo的iDCode变量名后，通过IDE的代码自动完成功能生成setIDCode()属性设置方法，然后想当然地在Spring配置文件中使用iDCode属性名进行配置，最终造成了Spring启动器的启动错误。

以大写字母开头的变量名总显得比较另类，为了避免这类诡异的错误，用户可以遵照以下的编程经验：像QQ、MSN、ID等正常情况下以大写字母出现的专业术语，在Java中一律将其调整为小写形式，如qq、msn、id等，以保证命名的统一性（变量名都以小写字母开头），减少出现错误的概率。

### 5.3.2 构造函数注入

构造函数注入是除属性注入外的另一种常用的注入方式，它保证一些必要的属性在Bean实例化时就得到设置，确保Bean在实例化后就可以使用。

#### 1. 按类型匹配入参

如果任何可用的Car对象都必须提供brand和price的值，若使用属性注入方式，则只能人为地在配置时提供保证而无法在语法级提供保证，这时通过构造函数注入就可以很好地满足这一要求。使用构造函数注入的前提是Bean必须提供带参的构造函数。下面为Car提供一个可设置brand和price属性的构造函数。

~~~java
package com.smart.ditype
public class Car {
    ...
    public Car(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }
}
~~~

构造函数注入的配置方式和属性注入的配置方式有所不同，下面在Spring配置文件中使用构造函数注入的配置方式装配这个car Bean，如代码清单5-3所示：

~~~xml
<bean id="car1" class="com.smart.ditype.Car">
	<constructor-arg type="java.lang.String"> <!--①-->
    	<value>红旗CA72</value>
    </constructor-arg>
    <constructor-arg type="double"> <!--②-->
    	<value>20000</value>
    </constructor-arg>
</bean>
~~~

在`<constructor-arg>`的元素中有一个type属性，它为Spring提供了判断配置项和构造函数入参对应关系的“信息”。细心的读者可能会提出以下疑问：配置文件中`<bean>`元素的`<constructor-arg>`声明顺序难道不能用于确定构造函数入参的顺序吗？在只有一个构造函数的情况下当然是可以的，但如果在Car中定义了多个具有相同入参的构造函数，这种顺序标识方法就失效了。此外，Spring的配置文件采用和元素标签顺序无关的策略，这种策略可以在一定程度上保证配置信息的确定性，避免一些似是而非的问题。因此，①和②处的`<constructor-arg>`位置并不会对最终配置效果产生影响。

#### 2. 按索引匹配入参