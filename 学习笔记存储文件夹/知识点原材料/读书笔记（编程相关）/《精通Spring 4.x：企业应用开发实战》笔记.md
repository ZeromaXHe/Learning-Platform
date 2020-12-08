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