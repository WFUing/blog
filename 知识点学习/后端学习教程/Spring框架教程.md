#! https://zhuanlan.zhihu.com/p/657222015
# Spring框架教程

![image-20221025154424563](Spring%E6%A1%86%E6%9E%B6%E6%95%99%E7%A8%8B.assets/image-20221025154424563-16666838655491.png)

## 1. Spring 是什么？

Spring 是一款目前主流的 Java EE 轻量级开源框架 ，是 Java 世界最为成功的框架之一。Spring 由 `Spring 之父 Rod Johnson` 提出并创立，其目的是用于简化 Java 企业级应用的开发难度和开发周期。

Spring 框架**不局限于服务器端的开发**。从简单性、可测试性和松耦合的角度而言，任何 Java 应用都可以从 Spring 中受益。Spring 框架还是一个超级粘合平台，除了自己提供功能外，还提供粘合其他技术和框架的能力。

### 1.1 广义的 Spring：Spring 技术栈

广义上的 Spring 泛指以 Spring Framework 为核心的 Spring 技术栈。经过十多年的发展，Spring 已经不再是一个单纯的应用框架，而是逐渐发展成为一个由多个不同子项目（模块）组成的成熟技术，例如 Spring Framework、Spring MVC、SpringBoot、Spring Cloud、Spring Data、Spring Security 等，其中 Spring Framework 是其他子项目的基础。

这些子项目涵盖了从企业级应用开发到云计算等各方面的内容，能够帮助开发人员解决软件发展过程中不断产生的各种实际问题，给开发人员带来了更好的开发体验。

| 项目名称        | 描述                                                         |
| --------------- | ------------------------------------------------------------ |
| Spring Data     | Spring 提供的数据访问模块，对 JDBC 和 ORM 提供了很好的支持。通过它，开发人员可以使用一种相对统一的方式，来访问位于不同类型数据库中的数据。 |
| Spring Batch    | 一款专门针对企业级系统中的日常批处理任务的轻量级框架，能够帮助开发人员方便的开发出健壮、高效的批处理应用程序。 |
| Spring Security | 前身为 Acegi，是 Spring 中较成熟的子模块之一。它是一款可以定制化的身份验证和访问控制框架。 |
| Spring Mobile   | 是对 Spring MVC 的扩展，用来简化移动端 Web 应用的开发。      |
| Spring Boot     | 是 Spring 团队提供的全新框架，它为 Spring 以及第三方库一些开箱即用的配置，可以简化 Spring 应用的搭建及开发过程。 |
| Spring Cloud    | 一款基于 Spring Boot 实现的微服务框架。它并不是某一门技术，而是一系列微服务解决方案或框架的有序集合。它将市面上成熟的、经过验证的微服务框架整合起来，并通过 Spring Boot 的思想进行再封装，屏蔽调其中复杂的配置和实现原理，最终为开发人员提供了一套简单易懂、易部署和易维护的分布式系统开发工具包。 |

### 1.2 狭义的 Spring：Spring Framework

狭义的 Spring 特指 Spring Framework，通常我们将它称为 Spring 框架。

Spring 框架是一个分层的、面向切面的 Java 应用程序的一站式轻量级解决方案，它是 Spring 技术栈的核心和基础，是为了解决企业级应用开发的复杂性而创建的。

### 1.3 Spring 的两个核心部分

Spring 有两个核心部分： `IoC` 和 `AOP`。

| 核心 | 描述                                                         |
| ---- | ------------------------------------------------------------ |
| IOC  | Inverse of Control 的简写，译为 `控制反转` ，指把创建对象过程交给 Spring 进行管理。 |
| AOP  | Aspect Oriented Programming 的简写，译为 `面向切面编程` 。  AOP 用来封装多个类的公共行为，将那些与业务无关，却为业务模块所共同调用的逻辑封装起来，减少系统的重复代码，降低模块间的耦合度。另外，AOP 还解决一些系统层面上的问题，比如日志、事务、权限等。 |

### 1.4  Spring 基于 Bean 的编程技术

Spring 是一种基于 Bean 的编程技术，它深刻地改变着 Java 开发世界。Spring 使用简单、基本的 Java Bean 来完成以前只有 EJB 才能完成的工作，使得很多复杂的代码变得优雅和简洁，避免了 EJB 臃肿、低效的开发模式，极大的方便项目的后期维护、升级和扩展。

### 1.5 Spring 三层体系架构

在实际开发中，服务器端应用程序通常采用三层体系架构，分别为表现层（web）、业务逻辑层（service）、持久层（dao）。

Spring 致力于 Java EE 应用各层的解决方案，对每一层都提供了技术支持。

- 在表现层提供了对 Spring MVC、Struts2 等框架的整合；
- 在业务逻辑层提供了管理事务和记录日志的功能；
- 在持久层还可以整合 MyBatis、Hibernate 和 JdbcTemplate 等技术，对数据库进行访问。

### 1.6 Spring Framework 的特点

#### 1.6.1 方便解耦，简化开发

Spring 就是一个大工厂，可以将所有对象的创建和依赖关系的维护交给 Spring 管理。

#### 1.6.2 方便集成各种优秀框架

Spring 不排斥各种优秀的开源框架，其内部提供了对各种优秀框架（如 Struts2、Hibernate、MyBatis 等）的直接支持。

#### 1.6.3 降低 Java EE API 的使用难度

Spring 对 Java EE 开发中非常难用的一些 API（JDBC、JavaMail、远程调用等）都提供了封装，使这些 API 应用的难度大大降低。

#### 1.6.4 方便程序的测试

Spring 支持 JUnit4，可以通过注解方便地测试 Spring 程序。

#### 1.6.5 AOP 编程的支持

Spring 提供面向切面编程，可以方便地实现对程序进行权限拦截和运行监控等功能。

#### 1.6.6 声明式事务的支持

只需要通过配置就可以完成对事务的管理，而无须手动编程。

## 2. Spring体系结构

Spring 框架基本涵盖了企业级应用开发的各个方面，它包含了 20 多个不同的模块。

- spring-aop
- spring-context-indexer
- spring-instrument
- spring-orm
- spring-web
- spring-aspects
- spring-context-support
- spring-jcl
- spring-oxm
- spring-webflux
- spring-beans
- spring-core
- spring-jdbc
- spring-r2dbc
- spring-webmvc
- spring-context
- spring-expression
- spring-jms
- spring-test
- spring-websocket
- spring-messaging
- spring-tx

![image-20221106151214794](Spring%E6%A1%86%E6%9E%B6%E6%95%99%E7%A8%8B.assets/image-20221106151214794-16677187354041.png)

上图中包含了 Spring 框架的所有模块，这些模块可以满足一切企业级应用开发的需求，在开发过程中可以根据需求有选择性地使用所需要的模块。下面分别对这些模块的作用进行简单介绍。

### 2.1 Data Access/Integration（数据访问／集成）

数据访问／集成层包括 JDBC、ORM、OXM、JMS 和 Transactions 模块，具体介绍如下。

- JDBC 模块：提供了一个 JBDC 的样例模板，使用这些模板能消除传统冗长的 JDBC 编码还有必须的事务控制，而且能享受到 Spring 管理事务的好处。
- ORM 模块：提供与流行的“对象-关系”映射框架无缝集成的 API，包括 JPA、JDO、Hibernate 和 MyBatis 等。而且还可以使用 Spring 事务管理，无需额外控制事务。
- OXM 模块：提供了一个支持 Object /XML 映射的抽象层实现，如 JAXB、Castor、XMLBeans、JiBX 和 XStream。将 Java 对象映射成 XML 数据，或者将XML 数据映射成 Java 对象。
- JMS 模块：指 Java 消息服务，提供一套  `消息生产者、消息消费者` 模板用于更加简单的使用 JMS，JMS 用于用于在两个应用程序之间，或分布式系统中发送消息，进行异步通信。
- Transactions 事务模块：支持编程和声明式事务管理。

### 2.2 Web 模块

Spring 的 Web 层包括 Web、Servlet、WebSocket 和 Portlet 组件，具体介绍如下。

- Web 模块：提供了基本的 Web 开发集成特性，例如多文件上传功能、使用的 Servlet 监听器的 IOC 容器初始化以及 Web 应用上下文。
- Servlet 模块：提供了一个 Spring MVC Web 框架实现。Spring MVC 框架提供了基于注解的请求资源注入、更简单的数据绑定、数据验证等及一套非常易用的 JSP 标签，完全无缝与 Spring 其他技术协作。
- WebSocket 模块：提供了简单的接口，用户只要实现响应的接口就可以快速的搭建 WebSocket Server，从而实现双向通讯。
- Portlet 模块：提供了在 Portlet 环境中使用 MVC 实现，类似 Web-Servlet 模块的功能。

### 2.3 Core Container（Spring 的核心容器）

Spring 的核心容器是其他模块建立的基础，由 Beans 模块、Core 核心模块、Context 上下文模块和 SpEL 表达式语言模块组成，没有这些核心容器，也不可能有 AOP、Web 等上层的功能。具体介绍如下。

- Beans 模块：提供了框架的基础部分，包括控制反转和依赖注入。
- Core 核心模块：封装了 Spring 框架的底层部分，包括资源访问、类型转换及一些常用工具类。
- Context 上下文模块：建立在 Core 和 Beans 模块的基础之上，集成 Beans 模块功能并添加资源绑定、数据验证、国际化、Java EE 支持、容器生命周期、事件传播等。ApplicationContext 接口是上下文模块的焦点。
- SpEL 模块：提供了强大的表达式语言支持，支持访问和修改属性值，方法调用，支持访问及修改数组、容器和索引器，命名变量，支持算数和逻辑运算，支持从 Spring 容器获取 Bean，它也支持列表投影、选择和一般的列表聚合等。

### 2.4 AOP、Aspects、Instrumentation 和 Messaging

在 Core Container 之上是 AOP、Aspects 等模块，具体介绍如下：

- AOP 模块：提供了面向切面编程实现，提供比如日志记录、权限控制、性能统计等通用功能和业务逻辑分离的技术，并且能动态的把这些功能添加到需要的代码中，这样各司其职，降低业务逻辑和通用功能的耦合。
- Aspects 模块：提供与 AspectJ 的集成，是一个功能强大且成熟的面向切面编程（AOP）框架。
- Instrumentation 模块：提供了类工具的支持和类加载器的实现，可以在特定的应用服务器中使用。
- messaging 模块：Spring 4.0 以后新增了消息（Spring-messaging）模块，该模块提供了对消息传递体系结构和协议的支持。

### 2.5 Test 模块

Test 模块：Spring 支持 Junit 和 TestNG 测试框架，而且还额外提供了一些基于 Spring 的测试功能，比如在测试 Web 框架时，模拟 Http 请求的功能。

## 3. Spring IoC（控制反转）

IoC 是 Inversion of Control 的简写，译为“控制反转”，它不是一门技术，而是一种设计思想，是一个重要的面向对象编程法则，能够指导我们如何设计出松耦合、更优良的程序。

Spring 通过 IoC 容器来管理所有 Java 对象的实例化和初始化，控制对象与对象之间的依赖关系。我们将由 IoC 容器管理的 Java 对象称为 Spring Bean，它与使用关键字 new 创建的 Java 对象没有任何区别。

IoC 容器是 Spring 框架中最重要的核心组件之一，它贯穿了 Spring 从诞生到成长的整个过程。

### 3.1 控制反转（IoC）

在传统的 Java 应用中，一个类想要调用另一个类中的属性或方法，通常会先在其代码中通过 new Object() 的方式将后者的对象创建出来，然后才能实现属性或方法的调用。为了方便理解和描述，我们可以将前者称为 `调用者`，将后者称为 `被调用者`。也就是说，调用者掌握着被调用者对象创建的控制权。

但在 Spring 应用中，Java 对象创建的控制权是掌握在 IoC 容器手里的，其大致步骤如下。

1. 开发人员通过 XML 配置文件、注解、Java 配置类等方式，对 Java 对象进行定义，例如在 XML 配置文件中使用 `<bean>` 标签、在 Java 类上使用 `@Component` 注解等。
2. Spring 启动时，IoC 容器会自动根据对象定义，将这些对象创建并管理起来。这些被 IoC 容器创建并管理的对象被称为 Spring Bean。
3. 当我们想要使用某个 Bean 时，可以直接从 IoC 容器中获取（例如通过 `ApplicationContext` 的 `getBean()` 方法），而不需要手动通过代码（例如 `new Obejct()` 的方式）创建。

IoC 带来的最大改变不是代码层面的，而是从思想层面上发生了 `主从换位` 的改变。原本调用者是主动的一方，它想要使用什么资源就会主动出击，自己创建；但在 Spring 应用中，IoC 容器掌握着主动权，调用者则变成了被动的一方，被动的等待 IoC 容器创建它所需要的对象（Bean）。

这个过程在职责层面发生了控制权的反转，把原本调用者通过代码实现的对象的创建，反转给 IoC 容器来帮忙实现，因此我们将这个过程称为 Spring 的 `控制反转`。

### 3.2 依赖注入（DI）

在了解了 IoC 之后，我们还需要了解另外一个非常重要的概念：依赖注入。

依赖注入（`Denpendency Injection`，简写为 `DI`）是 `Martin Fowler` 在 2004 年在对`控制反转`进行解释时提出的。Martin Fowler 认为 `控制反转` 一词很晦涩，无法让人很直接的理解 `到底是哪里反转了` ，因此他建议使用 `依赖注入` 来代替 `控制反转`。

在面向对象中，对象和对象之间是存在一种叫做“依赖”的关系。简单来说，依赖关系就是在一个对象中需要用到另外一个对象，即对象中存在一个属性，该属性是另外一个类的对象。

#### 3.2.1 例子

有一个名为 B 的 Java 类，它的代码如下：

```java
public class B {
    String bid;
    A a;
}
```

从代码可以看出，B 中存在一个 A 类型的对象属性 a，此时我们就可以说 B 的对象依赖于对象 a。而依赖注入就是就是基于这种“依赖关系”而产生的。

我们知道，控制反转核心思想就是由 Spring 负责对象的创建。在对象创建过程中，Spring 会自动根据依赖关系，将它依赖的对象注入到当前对象中，这就是所谓的 `依赖注入` 。

依赖注入本质上是 `Spring Bean 属性注入` 的一种，只不过这个属性是一个对象属性而已。

#### 3.2.2 IoC 的工作原理

在 `Java` 软件开发过程中，系统中的各个对象之间、各个模块之间、软件系统和硬件系统之间，或多或少都存在一定的耦合关系。

若一个系统的耦合度过高，那么就会造成难以维护的问题，但完全没有耦合的代码几乎无法完成任何工作，这是由于几乎所有的功能都需要代码之间相互协作、相互依赖才能完成。因此我们在程序设计时，所秉承的思想一般都是在不影响系统功能的前提下，最大限度的降低耦合度。

`IoC` 底层通过工厂模式、`Java` 的反射机制、`XML` 解析等技术，将代码的耦合度降低到最低限度，其主要步骤如下。

1. 在配置文件（例如 `Bean.xml`）中，对各个对象以及它们之间的依赖关系进行配置；
2. 我们可以把 `IoC` 容器当做一个工厂，这个工厂的产品就是 `Spring Bean`；
3. 容器启动时会加载并解析这些配置文件，得到对象的基本信息以及它们之间的依赖关系；
4. `IoC` 利用 `Java` 的反射机制，根据类名生成相应的对象（即 `Spring Bean`），并根据依赖关系将这个对象注入到依赖它的对象中。

由于对象的基本信息、对象之间的依赖关系都是在配置文件中定义的，并没有在代码中紧密耦合，因此即使对象发生改变，我们也只需要在配置文件中进行修改即可，而无须对 `Java` 代码进行修改，这就是 `Spring IoC` 实现解耦的原理。

#### 3.2.3 IoC 容器的两种实现

`IoC` 思想基于 `IoC` 容器实现的，`IoC` 容器底层其实就是一个 `Bean` 工厂。`Spring` 框架为我们提供了两种不同类型 `IoC` 容器，它们分别是 `BeanFactory` 和 `ApplicationContext`。

##### 3.2.3.1 BeanFactory

`BeanFactory` 是 `IoC` 容器的基本实现，也是 `Spring` 提供的最简单的 `IoC` 容器，它提供了 `IoC` 容器最基本的功能，由 `org.springframework.beans.factory.BeanFactory` 接口定义。

`BeanFactory` 采用懒加载（`lazy-load`）机制，容器在加载配置文件时并不会立刻创建 `Java` 对象，只有程序中获取（使用）这个对对象时才会创建。

###### 3.2.3.1.1 例1

下面我们通过一个实例演示，来演示下 `BeanFactory` 的使用。

1. 在 `HelloSpring` 项目中，将 `MainApp` 的代码修改为使用 `BeanFactory` 获取 `HelloWorld` 的对象，具体代码如下。

```java
public static void main(String[] args) {
    BeanFactory context = new ClassPathXmlApplicationContext("Beans.xml");
    HelloWorld obj = context.getBean("helloWorld", HelloWorld.class);
    obj.getMessage();
}
```

2. 运行 MainApp.java，控制台输出如下。

```
message : Hello World!
```

注意：`BeanFactory` 是 `Spring` 内部使用接口，通常情况下不提供给开发人员使用。 

##### 3.2.3.2 ApplicationContext

`ApplicationContext` 是 `BeanFactory` 接口的子接口，是对 `BeanFactory` 的扩展。`ApplicationContext` 在 `BeanFactory` 的基础上增加了许多企业级的功能，例如 `AOP`（面向切面编程）、国际化、事务支持等。

ApplicationContext 接口有两个常用的实现类，具体如下表。

| 实现类                            | 描述                                                         | 示例代码                                                     |
| --------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| `ClassPathXmlApplicationContext`  | 加载类路径 ClassPath 下指定的 XML 配置文件，并完成 `ApplicationContext` 的实例化工作 | `ApplicationContext applicationContext = new ClassPathXmlApplicationContext(String configLocation);` |
| `FileSystemXmlApplicationContext` | 加载指定的文件系统路径中指定的 XML 配置文件，并完成 `ApplicationContext` 的实例化工作 | `ApplicationContext applicationContext = new FileSystemXmlApplicationContext(String configLocation);` |

## 4. Spring Bean定义

由 `Spring IoC` 容器管理的对象称为 Bean，Bean 根据 Spring 配置文件中的信息创建。

我们可以把 `Spring IoC` 容器看作是一个大工厂，`Bean` 相当于工厂的产品。如果希望这个大工厂生产和管理 `Bean`，就需要告诉容器需要哪些 `Bean`，以哪种方式装配。

`Spring` 配置文件支持两种格式，即 `XML` 文件格式和 `Properties` 文件格式。

- `Properties` 配置文件主要以 `key-value` 键值对的形式存在，只能赋值，不能进行其他操作，适用于简单的属性配置。
- `XML` 配置文件采用树形结构，结构清晰，相较于 `Properties` 文件更加灵活。但是 `XML` 配置比较繁琐，适用于大型的复杂的项目。

通常情况下，`Spring` 的配置文件都是使用 `XML` 格式的。`XML` 配置文件的根元素是 `<beans>`，该元素包含了多个子元素 `<bean>`。每一个 `<bean>` 元素都定义了一个 `Bean`，并描述了该 `Bean` 是如何被装配到 `Spring` 容器中的。

### 4.1 例子

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">
    <bean id="helloWorld" class="net.biancheng.c.HelloWorld">
        <property name="message" value="Hello World!"/>
    </bean>
</beans>
```

### 4.2 常用属性

在 `XML` 配置的 `<beans>` 元素中可以包含多个属性或子元素，常用的属性或子元素如下表所示。

| 属性名称        | 描述                                                         |
| --------------- | ------------------------------------------------------------ |
| id              | Bean 的唯一标识符，Spring IoC 容器对 Bean 的配置和管理都通过该属性完成。id 的值必须以字母开始，可以使用字母、数字、下划线等符号。 |
| name            | 该属性表示 Bean 的名称，我们可以通过 name 属性为同一个 Bean 同时指定多个名称，每个名称之间用逗号或分号隔开。Spring 容器可以通过 name 属性配置和管理容器中的 Bean。 |
| class           | 该属性指定了 Bean 的具体实现类，它必须是一个完整的类名，即类的全限定名。 |
| scope           | 表示 Bean 的作用域，属性值可以为 singleton（单例）、prototype（原型）、request、session 和 global Session。默认值是 singleton。 |
| constructor-arg | <bean> 元素的子元素，我们可以通过该元素，将构造参数传入，以实现 Bean 的实例化。该元素的 index 属性指定构造参数的序号（从 0 开始），type 属性指定构造参数的类型。 |
| property        | <bean>元素的子元素，用于调用 Bean 实例中的 setter 方法对属性进行赋值，从而完成属性的注入。该元素的 name 属性用于指定 Bean 实例中相应的属性名。 |
| ref             | <property> 和 <constructor-arg> 等元素的子元索，用于指定对某个 Bean 实例的引用，即 <bean> 元素中的 id 或 name 属性。 |
| value           | <property> 和 <constractor-arg> 等元素的子元素，用于直接指定一个常量值。 |
| list            | 用于封装 List 或数组类型的属性注入。                         |
| set             | 用于封装 Set 类型的属性注入。                                |
| map             | 用于封装 Map 类型的属性注入。                                |
| entry           | <map> 元素的子元素，用于设置一个键值对。其 key 属性指定字符串类型的键值，ref 或 value 子元素指定其值。 |
| init-method     | 容器加载 Bean 时调用该方法，类似于 Servlet 中的 init() 方法  |
| destroy-method  | 容器删除 Bean 时调用该方法，类似于 Servlet 中的 destroy() 方法。该方法只在 scope=singleton 时有效 |
| lazy-init       | 懒加载，值为 true，容器在首次请求时才会创建 Bean 实例；值为 false，容器在启动时创建 Bean 实例。该方法只在 scope=singleton 时有效 |
