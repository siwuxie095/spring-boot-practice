package com.siwuxie095.spring.boot.chapter2nd.example2nd;

/**
 * @author Jiajing Li
 * @date 2021-04-07 22:11:55
 */
@SuppressWarnings("all")
public class Main {

    /**
     * 运用 Spring Boot
     *
     * 在这里，会构建一个简单的阅读列表应用程序。在这个程序里，用户可以输入想读的图书信息，查看列表，删除已经读过的书。
     * 这里将使用 Spring Boot 来辅助快速开发，各种繁文缛节越少越好。
     *
     * 开始前，需要先初始化一个项目。之前看到了好几种从 Spring Initializr 开始 Spring Boot 开发的方法。因为选择
     * 哪种方法都行，所以要选个最合适的，着手用 Spring Boot 开发就好了。
     *
     * 从技术角度来看，要用 Spring MVC 来处理 Web 请求，用 Thymeleaf 来定义 Web 视图，用 Spring Data JPA 来
     * 把阅读列表持久化到数据库里，姑且先用嵌入式的 H2 数据库。虽然也可以用 Groovy，但是这里还是先用 Java 来开发
     * 这个应用程序吧。此外，这里使用 Maven 作为构建工具。
     *
     * 无论是用 Web 界面、Spring Tool Suite 还是 IntelliJ IDEA，只要用了 Initializr，你就要确保勾选了 Web、
     * Thymeleaf 和 JPA 这几个复选框。还要记得勾上 H2 复选框，这样才能在开发应用程序时使用这个内嵌式数据库。
     *
     * 至于项目元信息，就随便你写了。以阅读列表为例，我创建项目时使用如下的信息。
     *
     *     <groupId>com.siwuxie095.spring.boot</groupId>
     *     <artifactId>chapter2nd-demo1st</artifactId>
     *     <version>0.0.1-SNAPSHOT</version>
     *     <name>Reading List</name>
     *     <description>Reading List Demo</description>
     *
     * 如果用 Spring Boot CLI 来初始化应用程序，可以在命令行里键入以下内容：
     *
     * spring init -dweb,data-jpa,h2,thymeleaf chapter2nd-demo1st
     *
     * 请记住，CLI 的 init 命令是不能指定项目根包名和项目名的。包名默认是 demo，项目名默认是 Demo。在项目创建完毕
     * 之后，你可以打开项目，把包名 demo 改为 chapter2nd-demo1st，把 DemoApplication.java 改名为
     * ReadingListApplication.java。
     *
     * 下面仔细看看初始化的项目里都有什么东西。
     *
     *
     *
     * 1、查看初始化的 Spring Boot 新项目
     *
     * 值得注意的是，整个项目结构遵循传统 Maven 或 Gradle 项目的布局，即主要应用程序代码位于 src/main/java 目录
     * 里，资源都在 src/main/resources 目录里，测试代码则在 src/test/java 目录里。此刻还没有测试资源，但如果有
     * 的话，要放在 src/test/resources 里。
     *
     * 再进一步，你会看到项目里还有不少文件。
     * （1）pom.xml：Maven 项目的核心配置文件。
     * （2）ReadingListApplication.java：应用程序的启动引导类（bootstrap class），也是主要的 Spring 配置类。
     * （3）application.properties：用于配置应用程序和 Spring Boot 的属性。
     * （4）ReadingListApplicationTests.java：一个基本的集成测试类。
     *
     * 下面先来看看 ReadingListApplication.java。
     *
     *
     * 1.1、启动引导 Spring
     *
     * ReadingListApplication 在 Spring Boot 应用程序里有两个作用：配置和启动引导。首先，这是主要的 Spring 配
     * 置类。虽然 Spring Boot 的自动配置免除了很多 Spring 配置，但你还需要进行少量配置来启用自动配置。如下所示，
     * 这里只有一行配置代码。
     *
     * @SpringBootApplication
     * public class ReadingListApplication {
     *
     *     public static void main(String[] args) {
     *         SpringApplication.run(ReadingListApplication.class, args);
     *     }
     *
     * }
     *
     * @SpringBootApplication 开启了 Spring 的组件扫描和 Spring Boot 的自动配置功能。实际上，
     * @SpringBootApplication 将三个有用的注解组合在了一起。
     * （1）Spring 的 @Configuration：标明该类使用 Spring 基于 Java 的配置。虽然这里不会写太多配置，但会更倾向
     * 于使用基于 Java 而不是 XML 的配置。
     * （2）Spring 的 @ComponentScan：启用组件扫描，这样你写的 Web 控制器类和其他组件才能被自动发现并注册为
     * Spring 应用程序上下文里的 Bean。
     * （3）Spring Boot 的 @EnableAutoConfiguration：这个不起眼的小注解也可以称为 @Abracadabra，就是这一行配
     * 置开启了 Spring Boot 自动配置的魔力，让你不用再写成篇的配置了。
     *
     * PS：Abracadabra 即 咒语。
     *
     * 在 Spring Boot 的早期版本中，你需要在 ReadingListApplication 类上同时标上这三个注解，但从 Spring Boot
     * 1.2.0 开始，有 @SpringBootApplication 就行了。
     *
     * ReadingListApplication 还是一个启动引导类。要运行 Spring Boot 应用程序有几种方式，其中包含传统的
     * WAR 文件部署。但这里的 main() 方法让你可以在命令行里把该应用程序当作一个可执行 JAR 文件来运行。这里
     * 向 SpringApplication.run() 传递了一个 ReadingListApplication 类的引用，还有命令行参数，通过这
     * 些东西启动应用程序。
     *
     * 实际上，就算一行代码也没写，此时你仍然可以构建应用程序尝尝鲜。要构建并运行应用程序，最简单的方法就是用
     * Maven 的 Spring Boot 插件，然后运行：
     *
     * spring-boot:run
     *
     * 应用程序应该能正常运行，启动一个监听 8080 端口的 Tomcat 服务器。要是愿意，你可以用浏览器访问
     * http://localhost:8080，但由于还没写控制器类，你只会收到一个 HTTP 404（NOT FOUND）错误，
     * 看到错误页面。
     *
     * 你几乎不需要修改 ReadingListApplication.java。如果你的应用程序需要 Spring Boot 自动配置以外的其他
     * Spring 配置，一般来说，最好把它写到一个单独的 @Configuration 标注的类里（组件扫描会发现并使用这些类的）。
     * 极度简单的情况下，可以把自定义配置加入 ReadingListApplication.java。
     *
     *
     * 1.2、测试 Spring Boot 应用程序
     *
     * Initializr 还提供了一个测试类的骨架，可以基于它为你的应用程序编写测试。但 ReadingListApplicationTests
     * 不止是个用于测试的占位符，它还是一个例子，告诉你如何为 Spring Boot 应用程序编写测试。
     *
     * @RunWith(SpringJUnit4ClassRunner.class)
     * @SpringApplicationConfiguration(
     *         classes = ReadingListApplication.class)
     * @WebAppConfiguration
     * public class ReadingListApplicationTests {
     *
     *     @Test
     *     public void contextLoads() {
     *     }
     *
     * }
     *
     * 一个典型的 Spring 集成测试会用 @ContextConfiguration 注解标识如何加载 Spring 的应用程序上下文。但是，为
     * 了充分发挥 Spring Boot 的魔力，这里应该用 @SpringApplicationConfiguration 注解。
     *
     * 所以 ReadingListApplicationTests 使用 @SpringApplicationConfiguration 注解从 ReadingListApplication
     * 配置类里加载 Spring 应用程序上下文。
     *
     * ReadingListApplicationTests 里还有一个简单的测试方法，即 contextLoads()。实际上它就是个空方法。但这个空
     * 方法足以证明应用程序上下文的加载没有问题。如果 ReadingListApplication 里定义的配置是好的，就能通过测试。如
     * 果有问题，测试就会失败。
     *
     * 当然，现在这只是一个新的应用程序，你还会添加自己的测试。但 contextLoads() 方法是个良好的开端，此刻可以验证应
     * 用程序提供的各种功能。
     *
     *
     * 1.3、配置应用程序属性
     *
     * Initializr 为你生成的 application.properties 文件是一个空文件。实际上，这个文件完全是可选的，你大可以删掉
     * 它，这不会对应用程序有任何影响，但留着也没什么问题。
     *
     * 稍后，肯定有机会向 application.properties 里添加几个条目。但现在，如果你想小试牛刀，可以加一行看看：
     *
     * server.port=8000
     *
     * 加上这一行，嵌入式 Tomcat 的监听端口就变成了 8000，而不是默认的 8080。你可以重新运行应用程序，看看是不是这样。
     *
     * 这说明 application.properties 文件可以很方便地帮你细粒度地调整 Spring Boot 的自动配置。你还可以用它来指定
     * 应用程序代码所需的配置项。
     *
     * 要注意的是，你完全不用告诉 Spring Boot 为你加载 application.properties，只要它存在就会被加载，Spring 和
     * 应用程序代码都能获取其中的属性。
     *
     *
     *
     * 2、Spring Boot 项目构建过程解析
     *
     * Spring Boot 应用程序的大部分内容都与其他 Spring 应用程序没有什么区别，与其他 Java 应用程序也没什么两样，因
     * 此构建一个 Spring Boot 应用程序和构建其他 Java 应用程序的过程类似。你可以选择 Gradle 或 Maven 作为构建工
     * 具，描述构建说明文件的方法和描述非 Spring Boot 应用程序的方法相似。但是，Spring Boot 在构建过程中耍了些小把
     * 戏，在此需要做个小小的说明。
     *
     * Spring Boot 为 Gradle 和 Maven 提供了构建插件，以便辅助构建 Spring Boot 项目。
     *
     * 如下是 Maven 的构建插件：
     *
     *     <build>
     *         <plugins>
     *             <plugin>
     *                 <groupId>org.springframework.boot</groupId>
     *                 <artifactId>spring-boot-maven-plugin</artifactId>
     *             </plugin>
     *         </plugins>
     *     </build>
     *
     * 无论你选择 Gradle 还是 Maven，Spring Boot 的构建插件都对构建过程有所帮助。
     *
     * 构建插件的主要功能是把项目打包成一个可执行的超级 JAR（uber-JAR），包括把应用程序的所有依赖打入 JAR 文件内，
     * 并为 JAR 添加一个描述文件，其中的内容能让你用 java -jar 来运行应用程序。
     *
     * 除了构建插件，Maven 构建说明中还将 spring-boot-starter-parent 作为上一级，这样一来就能利用 Maven 的依
     * 赖管理功能，继承很多常用库的依赖版本，在你声明依赖时就不用再去指定版本号了。请注意，这个 chapter2nd-demo1st
     * 的 pom.xml 里的 <dependency> 都没有指定版本。
     *
     * 说起依赖，无论哪个构建说明文件，都只有五个依赖，除了你手工添加的 H2 之外，其他的 Artifact ID 都有 spring
     * -boot-starter- 前缀。这些都是 Spring Boot 起步依赖，它们都有助于 Spring Boot 应用程序的构建。
     */
    public static void main(String[] args) {

    }

}
