package com.siwuxie095.spring.boot.chapter2nd.example3rd;

/**
 * @author Jiajing Li
 * @date 2021-04-10 21:32:07
 */
public class Main {

    /**
     * 使用起步依赖
     *
     * 要理解 Spring Boot 起步依赖带来的好处，先来假设它们尚不存在。如果没用 Spring Boot 的话，你会向项目里添加
     * 哪些依赖呢？要用 Spring MVC 的话，你需要哪个 Spring 依赖？你还记得 Thymeleaf 的 Group 和 Artifact ID
     * 吗？你应该用哪个版本的 Spring Data JPA 呢？它们放在一起兼容吗？
     *
     * 看来如果没有 Spring Boot 起步依赖，你就有不少功课要做。而你想要做的只不过是开发一个 Spring Web 应用程序，
     * 使用 Thymeleaf 视图，通过 JPA 进行数据持久化。但在开始编写第一行代码之前，你得搞明白，要支持你的计划，需要
     * 往构建说明里加入哪些东西。
     *
     * 考虑再三之后（也许你还从其他有相似依赖的应用程序构建说明中复制粘贴了不少内容），你的 Maven 构建说明里大概会
     * 有下面这些东西：
     *
     *        <dependency>
     *             <groupId>org.springframework</groupId>
     *             <artifactId>spring-web</artifactId>
     *             <version>4.1.6.RELEASE</version>
     *        </dependency>
     *        <dependency>
     *             <groupId>org.thymeleaf</groupId>
     *             <artifactId>thymeleaf-spring4</artifactId>
     *             <version>2.1.4.RELEASE</version>
     *         </dependency>
     *        <dependency>
     *             <groupId>org.springframework.data</groupId>
     *             <artifactId>spring-data-jpa</artifactId>
     *             <version>1.8.0.RELEASE</version>
     *         </dependency>
     *        <dependency>
     *             <groupId>org.hibernate</groupId>
     *             <artifactId>hibernate-entitymanager</artifactId>
     *             <version>4.3.8.Final</version>
     *         </dependency>
     *        <dependency>
     *             <groupId>com.h2database</groupId>
     *             <artifactId>h2</artifactId>
     *             <version>1.4.187</version>
     *         </dependency>
     *
     * 这段依赖列表不错，应该能正常工作，但你是怎么知道的？你怎么保证你选的这些版本能相互兼容？也许可以，但构建并运
     * 行应用程序之前你是不知道的。再说了，你怎么知道这个列表是完整的？在一行代码都没写的情况下，你离开始构建还有很
     * 长的路要走。
     *
     * 不妨退一步再想想，要做什么。这里是要构建一个拥有如下功能的应用程序。
     * （1）这是一个 Web 应用程序。
     * （2）它用了 Thymeleaf。
     * （3）它通过 Spring Data JPA 在关系型数据库里持久化数据。
     *
     * 如果只在构建文件里指定这些功能，让构建过程自己搞明白这里要什么东西，岂不是更简单？这正是 Spring Boot 起步
     * 依赖的功能。
     *
     *
     *
     * 1、指定基于功能的依赖
     *
     * Spring Boot 通过提供众多起步依赖降低项目依赖的复杂度。起步依赖本质上是一个 Maven 项目对象模型（Project
     * Object Model，POM），定义了对其他库的传递依赖，这些东西加在一起即支持某项功能。很多起步依赖的命名都暗示了
     * 它们提供的某种或某类功能。
     *
     * 举例来说，你打算把这个阅读列表应用程序做成一个 Web 应用程序。与其向项目的构建文件里添加一堆单独的库依赖，
     * 还不如声明这是一个 Web 应用程序来得简单。你只要添加 Spring Boot 的 Web 起步依赖就好了。
     *
     * 这里还想以 Thymeleaf 为 Web 视图，用 JPA 来实现数据持久化，因此在构建文件里还需要 Thymeleaf 和 Spring
     * Data JPA 的起步依赖。
     *
     * 为了能进行测试，还需要能在 Spring Boot 上下文里运行集成测试的库，因此要添加 Spring Boot 的 test 起步依
     * 赖，这是一个测试时依赖。
     *
     * 统统放在一起，就有了这五个依赖，也就是 Initializr 在 Maven 的构建文件里提供的：
     *
     *         <dependency>
     *             <groupId>org.springframework.boot</groupId>
     *             <artifactId>spring-boot-starter-data-jpa</artifactId>
     *         </dependency>
     *         <dependency>
     *             <groupId>org.springframework.boot</groupId>
     *             <artifactId>spring-boot-starter-thymeleaf</artifactId>
     *         </dependency>
     *         <dependency>
     *             <groupId>org.springframework.boot</groupId>
     *             <artifactId>spring-boot-starter-web</artifactId>
     *         </dependency>
     *         <dependency>
     *             <groupId>com.h2database</groupId>
     *             <artifactId>h2</artifactId>
     *             <scope>runtime</scope>
     *         </dependency>
     *         <dependency>
     *             <groupId>org.springframework.boot</groupId>
     *             <artifactId>spring-boot-starter-test</artifactId>
     *             <scope>test</scope>
     *         </dependency>
     *
     * 正如先前所见，添加这些依赖的最简单方法就是在 Initializr 里选中 Web、Thymeleaf 和 JPA 复选框。但如果在初
     * 始化项目时没有这么做，当然也可以稍后再编辑生成的 pom.xml。
     *
     * 通过传递依赖，添加这四个依赖就等价于加了一大把独立的库。这些传递依赖涵盖了 Spring MVC、Spring Data JPA、
     * Thymeleaf 等内容，它们声明的依赖也会被传递依赖进来。
     *
     * 最值得注意的是，这四个起步依赖的具体程度恰到好处。这里并没有说想要 Spring MVC，只是说想要构建一个 Web 应用
     * 程序。这里并没有指定 JUnit 或其他测试工具，只是说想要测试自己的代码。Thymeleaf 和 Spring Data JPA 的起
     * 步依赖稍微具体一点，但这也只是由于没有更模糊的方法声明这种需要。
     *
     * 这四个起步依赖只是 Spring Boot 众多起步依赖中的沧海一粟。
     *
     * 这里并不需要指定版本号，起步依赖本身的版本是由正在使用的 Spring Boot 的版本来决定的，而起步依赖则会决定它们
     * 引入的传递依赖的版本。
     *
     * 不知道自己所用依赖的版本，你多少会有些不安。你要有信心，相信 Spring Boot 经过了足够的测试，确保引入的全部依
     * 赖都能相互兼容。这是一种解脱，只需指定起步依赖，不用担心自己需要维护哪些库，也不必担心它们的版本。
     *
     * 但如果你真想知道自己在用什么，在构建工具里总能找到你要的答案。在 Maven 里使用 dependency 插件的 tree 目标
     * 也能获得相似的依赖树。
     *
     * mvn dependency:tree
     *
     * 大部分情况下，你都无需关心每个 Spring Boot 起步依赖分别声明了些什么东西。Web 起步依赖能让你构建 Web 应用程
     * 序，Thymeleaf 起步依赖能让你用 Thymeleaf 模板，Spring Data JPA 起步依赖能让你用 Spring Data JPA 将数
     * 据持久化到数据库里，通常只要知道这些就足够了。
     *
     * 但是，即使经过了 Spring Boot 团队的测试，起步依赖里所选的库仍有问题该怎么办？如何覆盖起步依赖呢？
     *
     *
     *
     * 2、覆盖起步依赖引入的传递依赖
     *
     * 说到底，起步依赖和你项目里的其他依赖没什么区别。也就是说，你可以通过构建工具中的功能，选择性地覆盖它们引入的传
     * 递依赖的版本号，排除传递依赖，当然还可以为那些 Spring Boot 起步依赖没有涵盖的库指定依赖。
     *
     * 以 Spring Boot 的 Web 起步依赖为例，它传递依赖了 Jackson JSON 库。如果你正在构建一个生产或消费 JSON 资源
     * 表述的 REST 服务，那它会很有用。但是，要构建传统的面向人类用户的 Web 应用程序，你可能用不上 Jackson。虽然把
     * 它加进来也不会有什么坏处，但排除掉它的传递依赖，可以为你的项目瘦身。
     *
     * 在 Maven 里，可以用 <exclusions> 元素来排除传递依赖。下面这个引入 Spring Boot 的 pom.xml 的
     * <dependency> 增加了 <exclusions> 元素去除 Jackson：
     *
     * <dependency>
     *       <groupId>org.springframework.boot</groupId>
     *       <artifactId>spring-boot-starter-web</artifactId>
     *       <exclusions>
     *         <exclusion>
     *           <groupId>com.fasterxml.jackson.core</groupId>
     *         </exclusion>
     *       </exclusions>
     * </dependency>
     *
     * 另一方面，也许项目需要 Jackson，但你需要用另一个版本的 Jackson 来进行构建，而不是 Web 起步依赖里的那个。假
     * 设 Web 起步依赖引用了 Jackson 2.3.4，但你需要使用 2.4.3。在 Maven 里，你可以直接在 pom.xml 中表达诉求，
     * 就像这样：
     *
     *  <dependency>
     *       <groupId>com.fasterxml.jackson.core</groupId>
     *       <artifactId>jackson-databind</artifactId>
     *       <version>2.4.3</version>
     * </dependency>
     *
     * PS：此处提到的版本仅作演示之用，Spring Boot 的 Web 起步依赖所引用的实际 Jackson 版本由你使用的 Spring
     * Boot 版本决定。
     *
     * Maven 总是会用最近的依赖，也就是说，你在项目的构建说明文件里增加的这个依赖，会覆盖传递依赖引入的另一个依赖。
     *
     * 不管什么情况，在覆盖 Spring Boot 起步依赖引入的传递依赖时都要多加小心。虽然不同的版本放在一起也许没什么问题，
     * 但你要知道，起步依赖中各个依赖版本之间的兼容性都经过了精心的测试。应该只在特殊的情况下覆盖这些传递依赖（比如新
     * 版本修复了一个 bug）。
     */
    public static void main(String[] args) {

    }

}
