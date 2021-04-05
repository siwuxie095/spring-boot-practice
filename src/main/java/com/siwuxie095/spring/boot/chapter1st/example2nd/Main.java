package com.siwuxie095.spring.boot.chapter1st.example2nd;

/**
 * @author Jiajing Li
 * @date 2021-04-05 22:15:45
 */
@SuppressWarnings("all")
public class Main {

    /**
     * Spring 风云再起
     *
     * Spring 诞生时是 Java 企业版（Java Enterprise Edition，JEE，也称 J2EE）的轻量级代替品。无需开发重量级的
     * Enterprise JavaBean（EJB），Spring 为企业级 Java 开发提供了一种相对简单的方法，通过依赖注入和面向切面编
     * 程，用简单的 Java 对象（Plain Old Java Object，POJO）实现了 EJB 的功能。
     *
     * 虽然 Spring 的组件代码是轻量级的，但它的配置却是重量级的。一开始，Spring 用 XML 配置，而且是很多 XML 配置。
     * Spring 2.5 引入了基于注解的组件扫描，这消除了大量针对应用程序自身组件的显式 XML 配置。Spring 3.0 引入了基
     * 于 Java 的配置，这是一种类型安全的可重构配置方式，可以代替 XML。
     *
     * 尽管如此，依旧没能逃脱配置的魔爪。开启某些 Spring 特性时，比如事务管理和 Spring MVC，还是需要用 XML 或
     * Java 进行显式配置。启用第三方库时也需要显式配置，比如基于 Thymeleaf 的 Web 视图。配置 Servlet 和过滤器
     * （比如 Spring 的 DispatcherServlet）同样需要在 web.xml 或 Servlet 初始化代码里进行显式配置。组件扫描
     * 减少了配置量，Java 配置让它看上去简洁不少，但 Spring 还是需要不少配置。
     *
     * 所有这些配置都代表了开发时的损耗。因为在思考 Spring 特性配置和解决业务问题之间需要进行思维切换，所以写配置挤
     * 占了写应用程序逻辑的时间。和所有框架一样，Spring 实用，但与此同时它要求的回报也不少。
     *
     * 除此之外，项目的依赖管理也是件吃力不讨好的事情。决定项目里要用哪些库就已经够让人头痛的了，你还要知道这些库的哪
     * 个版本和其他库不会有冲突，这难题实在太棘手。
     *
     * 并且，依赖管理也是一种损耗，添加依赖不是写应用程序代码。一旦选错了依赖的版本，随之而来的不兼容问题毫无疑问会是
     * 生产力杀手。
     *
     * Spring Boot 让这一切成为了过去。
     *
     *
     *
     * 1、重新认识 Spring
     *
     * 假设你受命用 Spring 开发一个简单的 Hello World Web 应用程序。你该做什么？下面是能想到的一些基本的需要。
     * （1）一个项目结构，其中有一个包含必要依赖的 Maven 或者 Gradle 构建文件，最起码要有 Spring MVC
     * 和 Servlet API 这些依赖。
     * （2）一个 web.xml 文件（或者一个 WebApplicationInitializer 实现），其中声明了 Spring 的
     * DispatcherServlet。
     * （3）一个启用了 Spring MVC 的 Spring 配置。
     * （4）一个控制器类，以 "Hello World" 响应 HTTP 请求。
     * （5）一个用于部署应用程序的 Web 应用服务器，比如 Tomcat。
     *
     * 最让人难以接受的是，这份清单里只有一个东西是和 Hello World 功能相关的，即控制器，剩下的都是 Spring 开发的
     * Web 应用程序必需的通用样板。既然所有 Spring Web 应用程序都要用到它们，那为什么还要你来提供这些东西呢？
     *
     * 假设这里只需要控制器。如下代码所示基于 Groovy 的控制器类就是一个简单而完整的 Spring 应用程序。
     *
     * @RestController
     * class HelloController {
     *
     *      @RequestMapping("/")
     *      def hello() {
     *          return "Hello World"
     *      }
     *
     * }
     *
     * 这里没有配置，没有 web.xml，没有构建说明，甚至没有应用服务器，但这就是整个应用程序了。Spring Boot 会搞定执
     * 行应用程序所需的各种后勤工作，你只要搞定应用程序的代码就好。
     *
     * 假设你已经装好了 Spring Boot 的命令行界面（Command Line Interface，CLI），可以像下面这样在命令行里运行
     * HelloController：
     *
     * spring run HelloController.groovy
     *
     * 想必你已经注意到了，这里甚至没有编译代码，Spring Boot CLI 可以运行未经编译的代码。
     *
     * 之所以选择用 Groovy 来写这个控制器示例，是因为 Groovy 语言的简洁与 Spring Boot 的简洁有异曲同工之妙。但
     * Spring Boot 并不强制要求使用 Groovy。
     *
     *
     *
     * 2、Spring Boot 精要
     *
     * Spring Boot 将很多魔法带入了 Spring 应用程序的开发之中，其中最重要的是以下四个核心。
     * （1）自动配置：针对很多 Spring 应用程序常见的应用功能，Spring Boot 能自动提供相关配置。
     * （2）起步依赖：告诉 Spring Boot 需要什么功能，它就能引入需要的库。
     * （3）命令行界面：这是 Spring Boot 的可选特性，借此你只需写代码就能完成完整的应用程序，无需传统项目构建。
     * （4）Actuator：让你能够深入运行中的 Spring Boot 应用程序，一探究竟。
     *
     * 每一个特性都在通过自己的方式简化 Spring 应用程序的开发。下面先简单看看它们都提供了哪些功能吧。
     *
     *
     * 2.1、自动配置
     *
     * 在任何 Spring 应用程序的源代码里，你都会找到 Java 配置或 XML 配置（抑或两者皆有），它们为应用程序开启了特定
     * 的特性和功能。举个例子，如果你写过用 JDBC 访问关系型数据库的应用程序，那你一定在 Spring 应用程序上下文里配置
     * 过 JdbcTemplate 这个 Bean。那段配置看起来是这样的：
     *
     * @Bean
     * public JdbcTemplate jdbcTemplate(DataSource dataSource) {
     *     return new JdbcTemplate(dataSource);
     * }
     *
     * 这段非常简单的 Bean 声明创建了一个 JdbcTemplate 的实例，注入了一个 DataSource 依赖。当然，这意味着你还需要
     * 配置一个 DataSource 的 Bean，这样才能满足依赖。假设你将配置一个嵌入式 H2 数据库作为 DataSource Bean，完成
     * 这个配置场景的代码大概是这样的：
     *
     *     @Bean
     *     public DataSource dataSource() {
     *       return new EmbeddedDatabaseBuilder()
     *               .setType(EmbeddedDatabaseType.H2)
     *               .addScripts('schema.sql', 'data.sql')
     *               .build();
     *     }
     *
     * 这个 Bean 配置方法创建了一个嵌入式数据库，并指定在该数据库上执行两段 SQL 脚本。build() 方法返回了一个指向该
     * 数据库的引用。
     *
     * 这两个 Bean 配置方法都不复杂，也不是很长，但它们只是典型 Spring 应用程序配置的一小部分。除此之外，还有无数
     * Spring 应用程序有着完全相同的方法。所有需要用到嵌入式数据库和 JdbcTemplate 的应用程序都会用到那些方法。简
     * 而言之，这就是一个样板配置。
     *
     * 既然它如此常见，那为什么还要你去写呢？
     *
     * Spring Boot 会为这些常见配置场景进行自动配置。如果 Spring Boot 在应用程序的 Classpath 里发现 H2 数据库的
     * 库，那么它就自动配置一个嵌入式 H2 数据库。如果在 Classpath 里发现 JdbcTemplate，那么它还会为你配置一个
     * JdbcTemplate 的 Bean。你无需操心那些 Bean 的配置，Spring Boot 会做好准备，随时都能将其注入到你的 Bean 里。
     *
     * Spring Boot 的自动配置远不止嵌入式数据库和 JdbcTemplate，它有大把的办法帮你减轻配置负担，这些自动配置涉及
     * Java 持久化 API（Java Persistence API，JPA）、Thymeleaf 模板、安全和 Spring MVC。
     *
     *
     * 2.2、起步依赖
     *
     * 向项目中添加依赖是件富有挑战的事。你需要什么库？它的 Group 和 Artifact 是什么？你需要哪个版本？哪个版本不会和
     * 项目中的其他依赖发生冲突？
     *
     * Spring Boot 通过起步依赖为项目的依赖管理提供帮助。起步依赖其实就是特殊的 Maven 依赖和 Gradle 依赖，利用了传
     * 递依赖解析，把常用库聚合在一起，组成了几个为特定功能而定制的依赖。
     *
     * 举个例子，假设你正在用 Spring MVC 构造一个 REST API，并将 JSON（JavaScript Object Notation）作为资源表
     * 述。此外，你还想运用遵循 JSR-303 规范的声明式校验，并使用嵌入式的 Tomcat 服务器来提供服务。要实现以上目标，
     * 你在 Maven 或 Gradle 里至少需要以下 8 个依赖：
     * （1）org.springframework:spring-core
     * （2）org.springframework:spring-web
     * （3）org.springframework:spring-webmvc
     * （4）com.fasterxml.jackson.core:jackson-databind
     * （5）org.hibernate:hibernate-validator
     * （6）org.apache.tomcat.embed:tomcat-embed-core
     * （7）org.apache.tomcat.embed:tomcat-embed-el
     * （8）org.apache.tomcat.embed:tomcat-embed-logging-juli
     *
     * 不过，如果打算利用 Spring Boot 的起步依赖，你只需添加 Spring Boot 的 Web 起步依赖（org.springframework.
     * boot:spring-boot-starter-web），仅此一个。它会根据依赖传递把其他所需依赖引入项目里，你都不用考虑它们。
     *
     * PS：Spring Boot 起步依赖基本都以 spring-boot-starter 打头，随后是直接代表其功能的名字，比如 web、test，
     * 后续出现起步依赖的名字时，可能就直接用其前缀后的单词来表示了。
     *
     * 比起减少依赖数量，起步依赖还引入了一些微妙的变化。向项目中添加了 Web 起步依赖，实际上指定了应用程序所需的一类功
     * 能。因为应用是个 Web 应用程序，所以加入了 web 起步依赖。与之类似，如果应用程序要用到 JPA 持久化，那么就可以加
     * 入 jpa 起步依赖。如果需要安全功能，那就加入 security 起步依赖。简而言之，你不再需要考虑支持某种功能要用什么库
     * 了，引入相关起步依赖就行。
     *
     * 此外，Spring Boot 的起步依赖还把你从 "需要这些库的哪些版本" 这个问题里解放了出来。起步依赖引入的库的版本都是经
     * 过测试的，因此你可以完全放心，它们之间不会出现不兼容的情况。
     *
     *
     * 2.3、命令行界面
     *
     * 除了自动配置和起步依赖，Spring Boot 还提供了一种很有意思的新方法，可以快速开发 Spring 应用程序。Spring Boot
     * CLI 让只写代码即可实现应用程序成为可能。
     *
     * Spring Boot CLI 利用了起步依赖和自动配置，让你专注于代码本身。不仅如此，在上面的 HelloController 代码里没有
     * import 相应的包？CLI 如何知道 RequestMapping 和 RestController 来自哪个包呢？说到这个问题，那些类最终又是
     * 怎么跑到 Classpath 里的呢？
     *
     * 说得简单一点，CLI 能检测到你使用了哪些类，它知道要向 Classpath 中添加哪些起步依赖才能让它运转起来。一旦那些依
     * 赖出现在 Classpath 中，一系列自动配置就会接踵而来，确保启用 DispatcherServlet 和 Spring MVC，这样控制器就
     * 能响应 HTTP 请求了。
     *
     * Spring Boot CLI 是 Spring Boot 的非必要组成部分。虽然它为 Spring 带来了惊人的力量，大大简化了开发，但也引
     * 入了一套不太常规的开发模型。要是这种开发模型与你的口味相去甚远，那也没关系，抛开 CLI，你还是可以利用 Spring
     * Boot 提供的其他东西。
     *
     *
     * 2.4、Actuator
     *
     * Spring Boot 的最后一块 "拼图" 是 Actuator，其他几个部分旨在简化 Spring 开发，而 Actuator 则要提供在运行时
     * 检视应用程序内部情况的能力。安装了 Actuator 就能窥探应用程序的内部情况了，包括如下细节：
     * （1）Spring 应用程序上下文里配置的 Bean
     * （2）Spring Boot 的自动配置做的决策
     * （3）应用程序取到的环境变量、系统属性、配置属性和命令行参数
     * （4）应用程序里线程的当前状态
     * （5）应用程序最近处理过的 HTTP 请求的追踪情况
     * （6）各种和内存用量、垃圾回收、Web 请求以及数据源用量相关的指标
     *
     * Actuator 通过 Web 端点和 shell 界面向外界提供信息。如果要借助 shell 界面，你可以打开SSH（Secure Shell），
     * 登入运行中的应用程序，发送指令查看它的情况。
     *
     *
     *
     * 3、Spring Boot 不是什么
     *
     * 因为 Spring Boot 实在是太惊艳了，所以过去一段时间里有不少和它相关的言论。原先听到或看到的东西可能给你造成了一些
     * 误解，这里应该先澄清这些误会。
     *
     * 首先，Spring Boot 不是应用服务器。这个误解是这样产生的：Spring Boot 可以把 Web 应用程序变为可自执行的 JAR 文
     * 件，不用部署到传统 Java 应用服务器里就能在命令行里运行。Spring Boot 在应用程序里嵌入了一个 Servlet 容器
     * （Tomcat、Jetty或Undertow），以此实现这一功能。但这是内嵌的 Servlet 容器提供的功能，不是 Spring Boot 实现的。
     *
     * 与之类似，Spring Boot 也没有实现诸如 JPA 或 JMS（Java Message Service，Java 消息服务）之类的企业级 Java
     * 规范。它的确支持不少企业级 Java 规范，但是要在 Spring 里自动配置支持那些特性的 Bean。例如，Spring Boot 没有
     * 实现 JPA，不过它自动配置了某个 JPA 实现（比如 Hibernate）的 Bean，以此支持 JPA。
     *
     * 最后，Spring Boot 没有引入任何形式的代码生成，而是利用了 Spring 4 的条件化配置特性，以及 Maven 和 Gradle 提
     * 供的传递依赖解析，以此实现 Spring 应用程序上下文里的自动配置。
     *
     * 简而言之，从本质上来说，Spring Boot 就是 Spring，它做了那些没有它你自己也会去做的 Spring Bean 配置。谢天谢地，
     * 幸好有 Spring，你不用再写这些样板配置了，可以专注于应用程序的逻辑，这些才是应用程序独一无二的东西。
     */
    public static void main(String[] args) {

    }

}
