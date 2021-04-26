package com.siwuxie095.spring.boot.chapter7th.example2nd;

/**
 * @author Jiajing Li
 * @date 2021-04-26 21:00:39
 */
@SuppressWarnings("all")
public class Main {

    /**
     * 揭秘 Actuator 的端点
     *
     * Spring Boot Actuator 的关键特性是在应用程序里提供众多 Web 端点，通过它们了解应用程序运行时的内部状况。有了
     * Actuator，你可以知道 Bean 在 Spring 应用程序上下文里是如何组装在一起的，掌握应用程序可以获取的环境属性信息，
     * 获取运行时度量信息的快照 ......
     *
     * Actuator 提供了 13 个端点，具体如下所示。
     * （1）
     * HTTP 方法：GET
     * 路径：/autoconfig
     * 描述：提供了一份自动配置报告，记录哪些自动配置条件通过了，哪些没通过
     * （2）
     * HTTP 方法：GET
     * 路径：/configprops
     * 描述：描述配置属性（包含默认值）如何注入 Bean
     * （3）
     * HTTP 方法：GET
     * 路径：/beans
     * 描述：描述应用程序上下文里全部的 Bean，以及它们的关系
     * （4）
     * HTTP 方法：GET
     * 路径：/dump
     * 描述：获取线程活动的快照
     * （5）
     * HTTP 方法：GET
     * 路径：/env
     * 描述：获取全部环境属性
     * （6）
     * HTTP 方法：GET
     * 路径：/env/{name}
     * 描述：根据名称获取特定的环境属性值
     * （7）
     * HTTP 方法：GET
     * 路径：/health
     * 描述：报告应用程序的健康指标，这些值由 HealthIndicator 的实现类提供
     * （8）
     * HTTP 方法：GET
     * 路径：/info
     * 描述：获取应用程序的定制信息，这些信息由 info 打头的属性提供
     * （9）
     * HTTP 方法：GET
     * 路径：/mappings
     * 描述：描述全部的 URI 路径，以及它们和控制器（包含 Actuator 端点）的映射关系
     * （10）
     * HTTP 方法：GET
     * 路径：/metrics
     * 描述：报告各种应用程序度量信息，比如内存用量和 HTTP 请求计数
     * （11）
     * HTTP 方法：GET
     * 路径：/metrics/{name}
     * 描述：报告指定名称的应用程序度量值
     * （12）
     * HTTP 方法：GET
     * 路径：/shutdown
     * 描述：关闭应用程序，要求 endpoints.shutdown.enabled 设置为 true
     * （13）
     * HTTP 方法：GET
     * 路径：/trace
     * 描述：提供基本的 HTTP 请求跟踪信息（时间戳、HTTP 头等）
     *
     * 要启用 Actuator 的端点，只需在项目中引入 Actuator 的起步依赖即可。
     *
     * 对于 Maven 项目，引入的依赖是这样的：
     *
     * <dependency>
     *     <groupId>org.springframework.boot</groupId>
     *     <artifactId>spring-boot-starter-actuator</artifactId>
     * </dependency>
     *
     * 亦或你使用 Spring Boot CLI，可以使用如下 @Grab 注解：
     *
     * @Grab('spring-boot-starter-actuator')
     *
     * 无论 Actuator 是如何添加的，在应用程序运行时自动配置都会生效。Actuator 会开启。
     *
     * 以上所有的端点可以分为三大类：配置端点、度量端点和其他端点。下面分别了解一下这些端点，从提供应用程序配置信息的
     * 端点看起。
     *
     *
     *
     * 1、查看配置明细
     *
     * 关于 Spring 组件扫描和自动织入，最常遭人抱怨的问题之一就是很难看到应用程序中的组件是如何装配起来的。Spring
     * Boot 自动配置让这个问题变得更严重，因为 Spring 的配置更少了。在有显式配置的情况下，你至少还能看到 XML 文件
     * 或者配置类，对 Spring 应用程序上下文里的 Bean 关系有个大概的了解。
     *
     * 其实不必担心这个问题。因为在 Spring 出现之前，根本就没有应用程序组件的映射关系。
     *
     * 但是，如果你担心自动配置隐藏了 Spring 应用程序上下文中 Bean 的装配细节，那么要这里告诉你一个好消息！Actuator
     * 有一些端点不仅可以显示组件映射关系，还可以告诉你自动配置在配置 Spring 应用程序上下文时做了哪些决策。
     *
     *
     * 1.1、获得 Bean 装配报告
     *
     * 要了解应用程序中 Spring 上下文的情况，最重要的端点就是 /beans。它会返回一个 JSON 文档，描述上下文里每个 Bean
     * 的情况，包括其 Java 类型以及注入的其他 Bean。向 /beans（在本地运行时 是 http://localhost:8080/beans）发
     * 起 GET 请求后，你会看到类似如下的信息。
     *
     * [
     *  {
     *      "beans": [
     *        {
     *          "bean": "application",
     *          "dependencies": [],
     *          "resource": "null",
     *          "scope": "singleton",
     *          "type": "readinglist.Application$$EnhancerBySpringCGLIB$$f363c202"
     *        },
     *        {
     *          "bean": "amazonProperties",
     *          "dependencies": [],
     *          "resource": "URL [jar:file:/../readinglist-0.0.1-SNAPSHOT.jar!/readinglist/AmazonProperties.class]"
     *          "scope": "singleton",
     *          "type": "readinglist.AmazonProperties"
     *        },
     *        {
     *          "bean": "readingListController",
     *          "dependencies": [
     *              "readingListRepository",
     *              "amazonProperties"
     *          ],
     *          "resource": "URL [jar:file:/../readinglist-0.0.1-SNAPSHOT.jar! /readinglist/ReadingListController.class]",
     *          "scope": "singleton",
     *          "type": "readinglist.ReadingListController"
     *        },
     *        {
     *          "bean": "readerRepository",
     *          "dependencies": [
     *              "(inner bean)#219df4f5",
     *              "(inner bean)#2c0e7419",
     *              "(inner bean)#7d86037b",
     *              "jpaMappingContext"
     *          ],
     *          "resource": "null",
     *          "scope": "singleton",
     *          "type": "readinglist.ReaderRepository"
     *        },
     *        {
     *          "bean": "readingListRepository",
     *          "dependencies": [
     *              "(inner bean)#98ce66",
     *              "(inner bean)#1fd7add0",
     *              "(inner bean)#59faabb2",
     *              "jpaMappingContext"
     *          ],
     *          "resource": "null",
     *          "scope": "singleton",
     *          "type": "readinglist.ReadingListRepository"
     *        },
     * ...
     *      ],
     *      "context": "application",
     *      "parent": null
     *  }
     * ]
     *
     * 这段代码是阅读列表应用程序 Bean 信息的一个片段。如你所见，所有的 Bean 条目都有五类信息。
     * （1）bean：Spring 应用程序上下文中的 Bean 名称或 ID。
     * （2）resource：.class 文件的物理位置，通常是一个 URL，指向构建出的 JAR 文件。这会随着应用程序的构建和运行
     * 方式发生变化。
     * （3）dependencies：当前 Bean 注入的 Bean ID 列表。
     * （4）scope：Bean 的作用域（通常是单例，这也是默认作用域）。
     * （4）type：Bean 的 Java 类型。
     *
     * 虽然 Bean 报告不用具体绘图告诉你 Bean 是如何装配的（例如，通过属性或构造方法），但它帮你直观地了解了应用程序
     * 上下文中 Bean 的关系。实际上，写出一个工具，把 Bean 报告处理一下，用图形化的方式来展现 Bean 关系，这并不难。
     * 请注意，完整的 Bean 报告会包含很多 Bean，还有很多自动配置的 Bean，画出来的图会非常复杂。
     *
     *
     * 1.2、详解自动配置
     *
     * /beans 端点产生的报告能告诉你 Spring 应用程序上下文里都有哪些 Bean。/autoconfig 端点能告诉你为什么会有这
     * 个 Bean，或者为什么没有这个 Bean。
     *
     * Spring Boot 自动配置构建于 Spring 的条件化配置之上。它提供了众多带有 @Conditional 注解的配置类，根据条件
     * 决定是否要自动配置这些 Bean。/autoconfig 端点提供了一个报告，列出了计算过的所有条件，根据条件是否通过进行分
     * 组。
     *
     * 如下代码是阅读列表应用程序自动配置报告里的一个片段，里面有一个通过的条件，还有一个没通过的条件。
     *
     * {
     *  "positiveMatches": {
     *  ...
     *      "DataSourceAutoConfiguration.JdbcTemplateConfiguration#jdbcTemplate": [
     *          {
     *              "condition": "OnBeanCondition",
     *              "message": "@ConditionalOnMissingBean (types:
     *              org.springframework.jdbc.core.JdbcOperations;
     *              SearchStrategy: all) found no beans"
     *          }
     *      ],
     *  ...
     *  },
     *  "negativeMatches": {
     *      "ActiveMQAutoConfiguration": [
     *          {
     *              "condition": "OnClassCondition",
     *              "message": "required @ConditionalOnClass classes not found:
     *              javax.jms.ConnectionFactory,org.apache.activemq
     *              .ActiveMQConnectionFactory"
     *          }
     *      ],
     *  ...
     *  }
     * }
     *
     * 在 positiveMatches 里，你会看到一个条件，决定 Spring Boot 是否自动配置 JdbcTemplate Bean。匹配到的名字
     * 是 DataSourceAutoConfiguration.JdbcTemplateConfiguration#jdbcTemplate，这是运用了条件的具体配置类。
     * 条件类型是 OnBeanCondition，意味着条件的输出是由某个 Bean 的存在与否来决定的。在本例中，message 属性已经
     * 清晰地表明了该条件是检查是否有 JdbcOperations 类型（JbdcTemplate 实现了该接口）的 Bean 存在。如果没有配置
     * 这种 Bean，则条件成立，创建一个 JdbcTemplate Bean。
     *
     * 与之类似，在 negativeMatches 里，有一个条件决定了是否要配置 ActiveMQ。这是一个 OnClassCondition，会检查
     * Classpath 里是否存在 ActiveMQConnectionFactory。因为 Classpath 里没有这个类，条件不成立，所以不会自动配
     * 置 ActiveMQ。
     *
     *
     * 1.3、查看配置属性
     *
     * 除了要知道应用程序的 Bean 是如何装配的，你可能还对能获取哪些环境属性，哪些配置属性注入了 Bean 里感兴趣。
     *
     * /env 端点会生成应用程序可用的所有环境属性的列表，无论这些属性是否用到。这其中包括环境变量、JVM 属性、命令行参
     * 数，以及 applicaition.properties 或 application.yml 文件提供的属性。
     *
     * 如下代码是 /env 端点获取信息的一个片段。
     *
     * {
     *       "applicationConfig: [classpath:/application.yml]": {
     *         "amazon.associate_id": "habuma-20",
     *         "error.whitelabel.enabled": false,
     *         "logging.level.root": "INFO"
     *      },
     *      "profiles": [],
     *      "servletContextInitParams": {},
     *      "systemEnvironment": {
     *          "BOOK_HOME": "/Users/habuma/Projects/BookProjects/walls6",
     *          "GRADLE_HOME": "/Users/habuma/.sdkman/gradle/current",
     *          "GRAILS_HOME": "/Users/habuma/.sdkman/grails/current",
     *          "GROOVY_HOME": "/Users/habuma/.sdkman/groovy/current",
     *          ...
     *      },
     *      "systemProperties": {
     *          "PID": "682",
     *          "file.encoding": "UTF-8",
     *          "file.encoding.pkg": "sun.io",
     *          "file.separator": "/",
     *      ...
     *      }
     * }
     *
     * 基本上，任何能给 Spring Boot 应用程序提供属性的属性源都会列在 /env 的结果里，同时会显示具体的属性。
     *
     * 这段代码中的属性来源有很多，包括应用程序配置（application.yml）、Spring Profile、Servlet 上下文初始化参
     * 数、系统环境变量和 JVM 系统属性（本例中没有 Profile 和 Servlet 上下文初始化参数）。
     *
     * 属性常用来提供诸如数据库或 API 密码之类的敏感信息。为了避免此类信息暴露到 /env 里，所有名为 password、
     * secret、key（或者名字中最后一段是这些）的属性在 /env 里都会加上 "*"。 举个例子，如果有一个属性名字是
     * database.password，那么它在 /env 中的显示效果是这样的：
     *
     * "database.password":"******"
     *
     * /env 端点还能用来获取单个属性的值，只需要在请求时在 /env 后加上属性名即可。举例来说，对阅读列表应用程序发起
     * /env/amazon.associate_id 请求，获得的结果是 habuma-20（纯文本形式）。
     *
     * 这些环境属性可以通过 @ConfigurationProperties 注解很方便地使用。这些环境属性会注入带有
     * @ConfigurationProperties 注解的 Bean 的实例属性。/configprops 端点会生成一个报告，
     * 说明如何进行设置（注入或其他方式）。如下代码是阅读列表应用程序的配置属性报告片段。
     *
     * {
     *       "amazonProperties": {
     *         "prefix": "amazon",
     *         "properties": {
     *           "associateId": "habuma-20"
     *         }
     *       },
     *       ...
     *       "serverProperties": {
     *         "prefix": "server",
     *         "properties": {
     *           "address": null,
     *           "contextPath": null,
     *           "port": null,
     *           "servletPath": "/",
     *           "sessionTimeout": null,
     *           "ssl": null,
     *           "tomcat": {
     *              "accessLogEnabled": false,
     *              "accessLogPattern": null,
     *              "backgroundProcessorDelay": 30,
     *              "basedir": null,
     *              "compressableMimeTypes": "text/html,text/xml,text/plain",
     *              "compression": "off",
     *              "maxHttpHeaderSize": 0,
     *              "maxThreads": 0,
     *              "portHeader": null,
     *              "protocolHeader": null,
     *              "remoteIpHeader": null,
     *              "uriEncoding": null
     *           },
     *           ...
     *         }
     *       },
     *       ...
     * }
     *
     * 片段中的第一个内容是 amazonProperties Bean。报告显示它添加了 @ConfigurationProperties 注解，前缀为
     * amazon。associateId 属性设置为 habuma-20。这是因为在 application.yml 里，把 amazon.associateId
     * 属性设置成了 habuma-20。
     *
     * 你还会看到一个 serverProperties 条目（前缀是 server），还有一些属性。它们都有默认值，你也可以通过设置
     * server 前缀的属性来改变这些值。举例来说，你可以通过设置 server.port 属性来修改服务器监听的端口。
     *
     * 除了展现运行中应用程序的配置属性如何设置，这个报告也能作为一个快速参考指南，告诉你有哪些属性可以设置。例如，
     * 如果你不清楚怎么设置嵌入式 Tomcat 服务器的最大线程数，可以看一下配置属性报告，里面会有一条 server.tomcat
     * .maxThreads，这就是你要找的属性。
     *
     *
     * 1.4、生成端点到控制器的映射
     *
     * 在应用程序相对较小的时候，很容易搞清楚控制器都映射到了哪些端点上。如果 Web 界面的控制器和请求处理方法数量
     * 多，那最好能有一个列表，罗列出应用程序发布的全部端点。
     *
     * /mappings 端点就提供了这么一个列表。如下代码是阅读列表应用程序的映射报告片段。
     *
     * {
     *    ...
     *    "{[/],methods=[GET],params=[],headers=[],consumes=[],produces=[], custom=[]}": {
     *      "bean": "requestMappingHandlerMapping",
     *      "method": "public java.lang.String readinglist.ReadingListController.ReadingListController
     *              .readersBooks(readinglist.Reader,org.springframework.ui.Model)"
     *    },
     *    "{[/],methods=[POST],params=[],headers=[],consumes=[],produces=[], custom=[]}": {
     *      "bean": "requestMappingHandlerMapping",
     *      "method": "public java.lang.String readinglist.ReadingListController
     *                           .addToReadingList(readinglist.Reader,readinglist.Book)"
     *    },
     *    "{[/autoconfig],methods=[GET],params=[],headers=[],consumes=[] ,produces=[],custom=[]}": {
     *      "bean": "endpointHandlerMapping",
     *      "method": "public java.lang.Object org.springframework.boot.actuate.endpoint.mvc
     *              .EndpointMvcAdapter.invoke()"
     *     },
     *     ...
     * }
     *
     * 这里可以看到不少端点的映射。每个映射的键都是一个字符串，其内容就是 Spring MVC 的 @RequestMapping 注解
     * 上设置的属性。实际上，这个字符串能让你清晰地了解控制器是如何映射的，哪怕不看源代码。每个映射的值都有两个属
     * 性：bean 和 method。bean 属性标识了 Spring Bean 的名字，映射源自这个 Bean。method 属性是映射对应方
     * 法的全限定方法签名。
     *
     * 头两个映射关乎应用程序中 ReadingListController 的请求如何处理。第一个表明 readersBooks() 方法处理根
     * 路径（/）的 HTTP GET 请求。第二个表明 POST 请求映射到 addToReadingList() 方法上。
     *
     * 接下来的映射是 Actuator 提供的端点。/autoconfig 端点的 HTTP GET 请求由 Spring Boot 的
     * EndpointMvcAdapter 类的 invoke() 方法来处理。当然，还有很多其他 Actuator 的端点没有列
     * 出来，这种省略完全是为了简化代码示例。
     *
     * Actuator 的配置端点能很方便地让你了解应用程序是如何配置的。能看到应用程序在运行时究竟发生了什么，这很有趣、
     * 很实用。度量端点能展示应用程序运行时内部状况的快照。
     *
     *
     *
     * 2、运行时度量
     *
     * 你到医生那里体检时，会做一系列检查来了解身体状况。有一些重要的项目永远不会变，比如血型。这类测试能让医生了
     * 解你身体的一贯情况。其他测试让医生掌握你接受检查时的身体状况。你的心律、血压和胆固醇水平有助于医生评估你的
     * 健康。这些指标都是临时的，很可能随时间发生变化，但它们同样是很有帮助的运行时指标。
     *
     * 与之类似，对运行时度量情况做一个快照，这对评估应用程序的健康情况很有帮助。Actuator 提供了一系列端点，让你
     * 能在运行时快速检查应用程序。下面来了解一下这些端点，从 /metrics 开始。
     *
     *
     * 2.1、查看应用程序的度量值
     *
     * 关于运行中的应用程序，有很多有趣而且有用的信息。举个例子，了解应用程序的内存情况（可用或空闲）有助于决定给
     * JVM 分配多少内存。对 Web 应用程序而言，不用查看 Web 服务器日志，如果请求失败或者是耗时太长，就可以大概知
     * 道内存的情况了。
     *
     * 运行中的应用程序有诸多计数器和度量器，/metrics 端点提供了这些东西的快照。如下代码是 /metrics 端点输出内
     * 容的示例。
     *
     * {
     *     mem: 198144,
     *     mem.free: 144029,
     *     processors: 8,
     *     uptime: 1887794,
     *     instance.uptime: 1871237,
     *     systemload.average: 1.33251953125,
     *     heap.committed: 198144,
     *     heap.init: 131072,
     *     heap.used: 54114,
     *     heap: 1864192,
     *     threads.peak: 21,
     *     threads.daemon: 19,
     *     threads: 21,
     *     classes: 9749,
     *     classes.loaded: 9749,
     *     classes.unloaded: 0,
     *     gc.ps_scavenge.count: 22,
     *     gc.ps_scavenge.time: 122,
     *     gc.ps_marksweep.count: 2,
     *     gc.ps_marksweep.time: 156,
     *     httpsessions.max: -1,
     *     httpsessions.active: 1,
     *     datasource.primary.active: 0,
     *     datasource.primary.usage: 0,
     *     counter.status.200.beans: 1,
     *     counter.status.200.env: 1,
     *     counter.status.200.login: 3,
     *     counter.status.200.metrics: 2,
     *     counter.status.200.root: 6,
     *     counter.status.200.star-star: 9,
     *     counter.status.302.login: 3,
     *     counter.status.302.logout: 1,
     *     counter.status.302.root: 5,
     *     gauge.response.beans: 169,
     *     gauge.response.env: 165,
     *     gauge.response.login: 3,
     *     gauge.response.logout: 0,
     *     gauge.response.metrics: 2,
     *     gauge.response.root: 11,
     *     gauge.response.star-star: 2
     * }
     *
     * 如你所见，/metrics 端点提供了很多信息，逐行查看这些度量值太麻烦。下表根据所提供信息的类型对它们做了个分类。
     * （1）
     * 分类：垃圾收集器
     * 前缀：gc.*
     * 报告内容：已经发生过的垃圾收集次数，以及垃圾收集所耗费的时间，适用于标记-清理垃圾收集器和并行垃圾收集器
     * （数据源自java.lang.management. GarbageCollectorMXBean）
     * （2）
     * 分类：内存
     * 前缀：mem.*
     * 报告内容：分配给应用程序的内存数量和空闲的内存数量（数据源自 java.lang.Runtime）
     * （3）
     * 分类：堆
     * 前缀：heap.*
     * 报告内容：当前内存用量（数据源自 java.lang.management.MemoryUsage）
     * （4）
     * 分类：类加载器
     * 前缀：classes.*
     * 报告内容：JVM 类加载器加载与卸载的类的数量（数据源自java.lang.management.ClassLoadingMXBean）
     * （5）
     * 分类：系统
     * 前缀：processors、uptimeinstance.uptime、systemload.average
     * 报告内容：系统信息，例如处理器数量（数据源自java.lang.Runtime）、运行时间（数据源自 java.lang
     * .management.RuntimeMXBean）、平均负载（数据源自 java.lang.management.OperatingSystemMXBean）
     * （6）
     * 分类：线程池
     * 前缀：threads.*
     * 报告内容：线程、守护线程的数量，以及 JVM 启动后的线程数量峰值（数据源自 java.lang.management
     * .ThreadMXBean）
     * （7）
     * 分类：数据源
     * 前缀：datasource.*
     * 报告内容：数据源连接的数量（源自数据源的元数据，仅当 Spring 应用程序上下文里存在 DataSource Bean
     * 的时候才会有这个信息）
     * （8）
     * 分类：Tomcat 会话
     * 前缀：httpsessions.*
     * 报告内容：Tomcat 的活跃会话数和最大会话数（数据源自嵌入式 Tomcat 的 Bean，仅在使用嵌入式 Tomcat
     * 服务器运行应用程序时才有这个信息）
     * （9）
     * 分类：HTTP
     * 前缀：counter.status.*、gauge.response.*
     * 报告内容：多种应用程序服务 HTTP 请求的度量值与计数器
     *
     * 请注意，这里的一些度量值，比如数据源和 Tomcat 会话，仅在应用程序中运行特定组件时才有数据。你还可以注册自己
     * 的度量信息。
     *
     * HTTP 的计数器和度量值需要做一点说明。counter.status 后的值是 HTTP 状态码，随后是所请求的路径。举个例子，
     * counter.status.200.metrics 表明 /metrics 端点返回200（OK）状态码 5 的次数。
     *
     * HTTP 的度量信息在结构上也差不多，却在报告另一类信息。它们全部以 gauge.response 开头，表明这是 HTTP 响应
     * 的度量信息。前缀后是对应的路径。度量值是以毫秒为单位的时间，反映了最近处理该路径请求的耗时。举个例子，上述代
     * 码里的 gauge.response.beans 说明上一次请求耗时 169 毫秒。
     *
     * 这里还有几个特殊的值需要注意。root 路径指向的是根路径或 /。star-star 代表了那些 Spring 认为是静态资源的
     * 路径，包括图片、JavaScript 和样式表，其中还包含了那些找不到的资源。这就是为什么你经常会看到 counter.status
     * .404.star-star，这是返回了 HTTP 404（NOT FOUND）状态的请求数。
     *
     * /metrics 端点会返回所有的可用度量值，但你也可能只对某个值感兴趣。要获取单个值，请求时可以在 URL 后加上对应
     * 的键名。例如，要查看空闲内存大小，可以向 /metrics/mem.free 发一个 GET 请求：
     *
     * curl localhost:8080/metrics/mem.free
     *
     * 要知道，虽然响应里的 Content-Type 头设置为 application/json;charset=UTF-8，但实际 /metrics/{name}
     * 的结果是文本格式的。因此，如果需要的话，你也可以把它视为 JSON 来处理。
     *
     *
     * 2.2、追踪 Web 请求
     *
     * 尽管 /metrics 端点提供了一些针对 Web 请求的基本计数器和计时器，但那些度量值缺少详细信息。知道所处理请求的
     * 更多信息是很有帮助的，尤其是在调试时，所以就有了 /trace 这个端点。
     *
     * /trace 端点能报告所有 Web 请求的详细信息，包括请求方法、路径、时间戳以及请求和响应的头信息。如下代码是
     * /trace 输出的一个片段，其中包含了整个请求跟踪项。
     *
     * [
     * ...
     *   {
     *         "timestamp": 1426378239775,
     *         "info": {
     *           "method": "GET",
     *           "path": "/metrics",
     *           "headers": {
     *             "request": {
     *               "accept": "星号/星号",
     *               "host": "localhost:8080",
     *               "user-agent": "curl/7.37.1"
     *             },
     *             "response": {
     *               "X-Content-Type-Options": "nosniff",
     *               "X-XSS-Protection": "1; mode=block",
     *               "Cache-Control":
     *                         "no-cache, no-store, max-age=0, must-revalidate",
     *               "Pragma": "no-cache",
     *               "Expires": "0",
     *               "X-Frame-Options": "DENY",
     *               "X-Application-Context": "application",
     *               "Content-Type": "application/json;charset=UTF-8",
     *               "Transfer-Encoding": "chunked",
     *               "Date": "Sun, 15 Mar 2015 00:10:39 GMT",
     *               "status": "200"
     *             }
     *           }
     *         }
     *   }
     * ]
     *
     * 正如 method 和 path 属性所示，你可以看到这个跟踪项是一个针对 /metrics 的请求。timestamp 属性（以及响应
     * 中的 Date 头）告诉了你请求的处理时间。headers 属性的内容是请求和响应中所携带的头信息。
     *
     * 虽然这段代码里只显示了一条跟踪项，但 /trace 端点实际能显示最近 100 个请求的信息，包含对 /trace 自己的请求。
     * 它在内存里维护了一个跟踪库。后续你会看到如何创建一个自定义的跟踪库实现，以便将请求的跟踪持久化。
     *
     *
     * 2.3、导出线程活动
     *
     * 在确认应用程序运行情况时，除了跟踪请求，了解线程活动也会很有帮助。/dump 端点会生成当前线程活动的快照。
     *
     * 完整的线程导出报告里会包含应用程序的每个线程。为了节省空间，如下代码里只放了一个线程的内容片段。如你所见，其
     * 中包含很多线程的特定信息，还有线程相关的阻塞和锁状态。本例中，还有一个跟踪栈（stack trace），表明这是一个
     * Tomcat 容器线程。
     *
     * [
     *   {
     *     "threadName": "container-0",
     *     "threadId": 19,
     *     "blockedTime": -1,
     *     "blockedCount": 0,
     *     "waitedTime": -1,
     *     "waitedCount": 64,
     *     "lockName": null,
     *     "lockOwnerId": -1,
     *     "lockOwnerName": null,
     *     "inNative": false,
     *     "suspended": false,
     *     "threadState": "TIMED_WAITING",
     *     "stackTrace": [
     *       {
     *         "className": "java.lang.Thread",
     *         "fileName": "Thread.java",
     *         "lineNumber": -2,
     *         "methodName": "sleep",
     *         "nativeMethod": true
     *       },
     *       {
     *         "className": "org.springframework.boot.context.embedded. tomcat.TomcatEmbeddedServletContainer$1",
     *         "fileName": "TomcatEmbeddedServletContainer.java",
     *         "lineNumber": 139,
     *         "methodName": "run",
     *         "nativeMethod": false
     *       }
     *     ],
     *     "lockedMonitors": [],
     *     "lockedSynchronizers": [],
     *     "lockInfo": null
     *   },
     *   ...
     * ]
     *
     *
     * 2.4、监控应用程序健康情况
     *
     * 如果你想知道自己的应用程序是否在运行，可以直接访问 /health 端点。在最简单的情况下，该端点会显示一个简单的
     * JSON，内容如下：
     *
     * {"status":"UP"}
     *
     * status 属性显示了应用程序在运行中。当然，它的确在运行，此处的响应无关紧要，任何输出都说明这个应用程序在运
     * 行。但 /health 端点可以输出的信息远远不止简单的 UP 状态。
     *
     * /health 端点输出的某些信息可能涉及内容，因此对未经授权的请求只能提供简单的健康状态。如果经过身份验证（比
     * 如你已经登录了），则可以提供更多信息。下面是阅读列表应用程序一些健康信息的示例：
     *
     * {
     *       "status":"UP",
     *       "diskSpace": {
     *         "status":"UP",
     *         "free":377423302656,
     *         "threshold":10485760
     *       },
     *       "db":{
     *         "status":"UP",
     *         "database":"H2",
     *         "hello":1
     *       }
     * }
     *
     * 除了基本的健康状态，可用的磁盘空间以及应用程序正在使用的数据库状态也可以看到。
     *
     * /health 端点所提供的所有信息都是由一个或多个健康指示器提供的。下表列出了 Spring Boot 自带的健康指示器。
     * （1）
     * 健康指示器：ApplicationHealthIndicator
     * 键：none
     * 报告内容：永远为 UP
     * （2）
     * 健康指示器：DataSourceHealthIndicator
     * 键：db
     * 报告内容：如果数据库能连上，则内容是 UP 和数据库类型；否则为 DOWN
     * （3）
     * 健康指示器：DiskSpaceHealthIndicator
     * 键：diskSpace
     * 报告内容：如果可用空间大于阈值，则内容为 UP 和可用磁盘空间；如果空间不足则为 DOWN
     * （4）
     * 健康指示器：JmsHealthIndicator
     * 键：jms
     * 报告内容：如果能连上消息代理，则内容为 UP 和 JMS 提供方的名称；否则为 DOWN
     * （5）
     * 健康指示器：MailHealthIndicator
     * 键：mail
     * 报告内容：如果能连上邮件服务器，则内容为 UP 和邮件服务器主机和端口；否则为 DOWN
     * （6）
     * 健康指示器：MongoHealthIndicator
     * 键：mongo
     * 报告内容：如果能连上 MongoDB 服务器，则内容为 UP 和 MongoDB 服务器版本；否则为 DOWN
     * （7）
     * 健康指示器：RabbitHealthIndicator
     * 键：rabbit
     * 报告内容：如果能连上 RabbitMQ 服务器，则内容为 UP 和版本号；否则为 DOWN
     * （8）
     * 健康指示器：RedisHealthIndicator
     * 键：redis
     * 报告内容：如果能连上服务器，则内容为 UP 和 Redis 服务器版本；否则为 DOWN
     * （9）
     * 健康指示器：SolrHealthIndicator
     * 键：solr
     * 报告内容：如果能连上 Solr 服务器，则内容为 UP；否则为 DOWN
     *
     * 这些健康指示器会按需自动配置。举例来说，如果 Classpath 里有 javax.sql.DataSource，则会自动配置
     * DataSourceHealthIndicator。 ApplicationHealthIndicator 和 DiskSpaceHealthIndicator 则
     * 会一直配置着。后续你还会看到如何创建自定义健康指示器。
     *
     *
     *
     * 3、关闭应用程序
     *
     * 假设你要关闭运行中的应用程序。比方说，在微服务架构中，你有多个微服务应用的实例运行在云上，其中某个实
     * 例有问题了，你决定关闭该实例并让云服务提供商为你重启这个有问题的应用程序。在这个场景中，Actuator 的
     * /shutdown 端点就很有用了。
     *
     * 为了关闭应用程序，你要往 /shutdown 发送一个 POST 请求。例如，可以用命令行工具 curl 来关闭应用程序：
     *
     * curl -X POST http://localhost:8080/shutdown
     *
     * 很显然，关闭运行中的应用程序是件危险的事情，因此这个端点默认是关闭的。如果没有显式地开启这个功能，那
     * 么 POST 请求的结果是这样的：
     *
     * {"message":"This endpoint is disabled"}
     *
     * 要开启该端点，可以将 endpoints.shutdown.enabled 设置为 true。举例来说，可以把如下内容加入
     * application.yml，借此开启 /shutdown 端点：
     *
     * endpoints:
     *       shutdown:
     *         enabled: true
     *
     * 打开 /shutdown 端点后，你要确保并非任何人都能关闭应用程序。这时应该保护 /shutdown 端点，只有经过
     * 授权的用户能关闭应用程序。后续你将看到如何保护 Actuator 端点。
     *
     *
     *
     * 4、获取应用信息
     *
     * Spring Boot Actuator 还有一个有用的端点。/info 端点能展示各种你希望发布的应用信息。针对该端点的
     * GET 请求的默认响应是这样的：
     *
     * {}
     *
     * 很显然，一个空的 JSON 对象没什么用。但你可以通过配置带有 info 前缀的属性向 /info 端点的响应添加内
     * 容。例如，你希望在响应中添加联系邮箱。可以在 application.yml 里设置名为 info.contactEmail 的属
     * 性：
     *
     *  info:
     *       contactEmail: support@myreadinglist.com
     *
     * 现在再访问 /info 端点，就能得到如下响应：
     *
     * {
     *    "contactEmail":"support@myreadinglist.com"
     * }
     *
     * 这里的属性也可以是嵌套的。例如，假设你希望提供联系邮箱和电话。在 application.yml 里可以配置如下属
     * 性：
     *
     * info:
     *   contact:
     *     email: support@myreadinglist.com
     *     phone: 1-888-555-1971
     *
     * /info 端点返回的 JSON 会包含一个 contact 属性，其中有 email 和 phone 属性：
     *
     * {
     *       "contact":{
     *         "email":"support@myreadinglist.com",
     *         "phone":"1-888-555-1971"
     *       }
     * }
     *
     * 向 /info 端点添加属性只是定制 Actuator 行为的众多方式之一。后续还会看到其他配置与扩展 Actuator
     * 的方式。
     */
    public static void main(String[] args) {

    }

}
