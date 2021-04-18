package com.siwuxie095.spring.boot.chapter5th.example3rd;

/**
 * @author Jiajing Li
 * @date 2021-04-18 21:20:20
 */
@SuppressWarnings("all")
public class Main {

    /**
     * 获取依赖
     *
     * 在 Spring MVC 和 JdbcTemplate 的例子中，为了获取必要的依赖并添加到 Classpath 里，Groovy 编译触发了
     * Spring Boot CLI。这是错误的。但如果需要一个依赖，而没有失败代码来触发自动依赖解析，又或者所需的依赖 CLI
     * 不知道，那该怎么办？
     *
     * 在阅读列表应用程序中，需要 Thymeleaf 库，这样才能编写使用了 Thymeleaf 模板的视图。还需要 H2 的库，这样
     * 才能拥有嵌入式的 H2 数据库。但因为没有 Groovy 代码会直接引用 Thymeleaf 或 H2 的类，所以不会有编译错误
     * 来触发自动依赖解析。因此，这里要帮一帮 CLI，在 Grabs 类上添加 @Grab 依赖。
     *
     * PS：该把 @Grab 注解放在哪里？并不需要像这里的做法，严格将 @Grab 注解放在一个单独的类上。它们在
     * ReadingListController 或 JdbcReadingListRepository 同样有效。不过，为了便于组织管理，最好
     * 创建一个空类，把所有 @Grab 注解放在一起。这样方便在一个地方看到所有显式声明的依赖。
     *
     * @Grab 注解源自 Groovy Grape（Groovy Adaptable Packaging Engine 或 Groovy Advanced Packaging
     * Engine）工具。从本质上来说，Grape 允许 Groovy 脚本在运行时下载依赖，无需 Maven 或 Gradle 这样的构建
     * 工具介入。除了支持 @Grab 注解，Spring Boot CLI 还用 Grape 来获取代码中推断出的依赖。
     *
     * 使用 @Grab 就和描述依赖一样简单。举例来说，假设你想往项目里添加 H2 数据库，可以往项目的一个 Groovy 脚本
     * 添加如下 @Grab 注解：
     *
     * @Grab(group="com.h2database", module="h2", version="1.4.190")
     *
     * 这样能明确地声明依赖的组、模块和版本号。或者，你也可以用更简洁的冒号分割表示依赖，这和 Gradle 构建说明里
     * 的表示方式类似。
     *
     * @Grab("com.h2database:h2:1.4.185")
     *
     * 这是两个教科书式的例子，但 Spring Boot CLI 对 @Grab 做了几处扩展，用起来更简单。
     *
     * 很多依赖不再要求指定版本号了。可以通过下面的方式，用 @Grab 添加 H2 数据库依赖：
     *
     * @Grab("com.h2database:h2")
     *
     * 确切的版本号是由你所使用的 CLI 的版本来决定的。如果用的是 Spring Boot CLI 1.3.0.RELEASE，那么 H2 依
     * 赖的版本会解析为 1.4.190。
     *
     * 这还不算完，很多常用依赖还可以省去 Group ID，直接在 @Grab 里写上模块的 ID。正是这个特性让之前的 @Grab
     * 注解成功加载了 H2。
     *
     * @Grab("h2")
     *
     * 那你该如何获知某个依赖是需要 Group ID 和版本号，还是只需要 Module ID 呢？通常，你可以先试一下只写 Module
     * ID，如果这样不行，再加上 Group ID 和版本号。
     *
     * 只用 Module ID 来表示依赖会很方便，但如果你并不认可 Spring Boot 选择的版本号怎么办？如果 Spring Boot
     * 的起步依赖传递引入了一个库的某个版本，但你想要使用修正了 bug 的新版本又该如何呢？
     *
     *
     *
     * 1、覆盖默认依赖版本
     *
     * Spring Boot 引入了新的 @GrabMetadata 注解，可以和 @Grab 搭配使用，用属性文件里的内容来覆盖默认的依赖
     * 版本。
     *
     * 要用 @GrabMetadata，可以把它加到某个 Groovy 脚本文件里，提供相应的属性文件来覆盖依赖元数据：
     *
     * @GrabMetadata("com.myorg:custom-versions:1.0.0")
     *
     * 这会从 Maven 仓库的 com/myorg 目录里加载一个名为 custom-versions.properties 的文件。文件里的每一行
     * 都应该有 Group ID 和 Module ID。以这两个东西为键名，属性则是值。例如，要把 H2 的默认版本覆盖为 1.4.186，
     * 可以把 @GrabMetadata 指向一个包含如下内容的属性文件：
     *
     * com.h2database:h2=1.4.186
     *
     *
     * PS：使用 Spring IO 平台：
     *
     * 你可能希望让 @GrabMetadata 使用 Spring IO 平台（https://spring.io/projects/platform）上定义的依
     * 赖版本。该平台提供了一套依赖和版本。明确哪个版本的 Spring 能和其他库的什么版本搭配使用。Spring IO 平台
     * 提供的依赖和版本是 Spring Boot 已知依赖库的一个超集，包含了很多 Spring 应用程序经常用到的第三方库。
     *
     * 如果你想在 Spring IO 平台上构建 Spring Boot CLI 应用程序，只需要在 Groovy 脚本中添加如下
     * @GrabMetadata 即可。
     *
     * @GrabMetadata('io.spring.platform:platform-versions:1.0.4.RELEASE')
     *
     * 这会覆盖 CLI 的默认依赖版本，使 Spring IO 平台定义的版本取而代之。
     *
     *
     * 你可能会有疑问，Grape 又是从哪里获取所有这些依赖的呢？这是可配置的吗？下面来看看你该如何管理 Grape 获取
     * 依赖的仓库集。
     *
     *
     *
     * 2、添加依赖仓库
     *
     * 默认情况下，@Grab 声明的依赖是从 Maven 中心仓库（http://repo1.maven.org/maven2/）拉取的。此外，
     * Spring Boot 还注册了 Spring 的里程碑及快照仓库，以便获取 Spring 项目的预发布版本依赖。对很多项目
     * 而言，这就足够了。但要是你的项目需要的库不在这两者之中该怎么办呢？或者你的工作环境在公司防火墙内，必
     * 须使用内部仓库又该如何？
     *
     * 没有问题。@GrabResolver 注解可以让你指定额外的仓库，用来获取依赖。
     *
     * 举个例子，假设你想使用最新的 Hibernate，而最新的 Hibernate 版本只能从 JBoss 的仓库里获取到。那么你需
     * 要通过 @GrabResolver 来添加仓库：
     *
     * @GrabResolver(name='jboss', root=
     *      'https://repository.jboss.org/nexus/content/groups/public-jboss')
     *
     * 这里通过 name 属性将该解析器命名为 jboss，通过 root 属性来指定仓库的 URL。
     *
     * 你已经了解了 Spring Boot CLI 是如何编译代码以及自动按需解析已知依赖库的。在 @Grab 的支持下，CLI 可以
     * 解析各种它无法自动解析的依赖。基于 CLI 的应用程序无需 Maven 或 Gradle 构建说明文件（传统方式开发的
     * Java 应用程序需要这个文件）。但解析依赖和编译代码并不是构建过程的全部，项目的构建通常还要执行自动化测试，
     * 要是没有构建说明文件，又该如何运行测试呢？后续将会对此进行介绍。
     */
    public static void main(String[] args) {

    }

}
