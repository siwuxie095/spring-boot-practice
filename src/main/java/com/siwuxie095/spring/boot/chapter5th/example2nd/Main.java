package com.siwuxie095.spring.boot.chapter5th.example2nd;

/**
 * @author Jiajing Li
 * @date 2021-04-18 17:14:57
 */
@SuppressWarnings("all")
public class Main {

    /**
     * 开发 Spring Boot CLI 应用程序
     *
     * 大部分针对 JVM 平台的项目都用 Java 语言开发，引入了诸如 Maven 或 Gradle 这样的构建系统，以生成可部署的产物。
     * 实际上，之前开发的阅读列表应用程序就遵循这套模型。
     *
     * 最近版本的 Java 语言有不少改进。然而，即便如此，Java 还是有一些严格的规则为代码增加了不少噪声。行尾分号、类和
     * 方法的修饰符（比如 public 和 private）、getter 和 setter 方法，还有 import 语句在 Java 中都有自己的作用，
     * 但它们同代码的本质无关，因而造成了干扰。从开发者的角度来看，代码噪声是阻力 —— 编写代码时是阻力，试图阅读代码时
     * 更是阻力。如果能消除一部分代码噪声，代码的开发和阅读可以更加方便。
     *
     * 同理，Maven 和 Gradle 这样的构建系统在项目中也有自己的作用，但你还得为此开发和维护构建说明。如果能直接构建，
     * 项目也会更加简单。
     *
     * 在使用 Spring Boot CLI 时，没有构建说明文件。代码本身就是构建说明，提供线索指引 CLI 解析依赖，并生成用于部
     * 署的产物。此外，配合 Groovy，Spring Boot CLI 提供了一种开发模型，消除了几乎所有代码噪声，带来了畅通无阻的
     * 开发体验。
     *
     * 在最简单的情况下，编写基于 CLI 的应用程序就和编写 Groovy 脚本一样简单。不过， 要用 CLI 编写更完整的应用程序，
     * 就需要设置一个基本的项目结构来容纳项目代码。下面马上用它重写阅读列表应用程序。
     *
     *
     *
     * 1、设置 CLI 项目
     *
     * 这里要做的第一件事是创建目录结构，容纳项目。与基于 Maven 或 Gradle 的项目不同，Spring Boot CLI 项目并没有
     * 严格的项目结构要求。实际上，最简单的 Spring Boot CLI 应用程序就是一个 Groovy 脚本，可以放在文件系统的任意
     * 目录里。对阅读列表应用程序而言，你应该创建一个干净的新目录来存放代码，把它们和你电脑上的其他东西分开。
     *
     * mkdir readinglist
     *
     * 此处将目录命名为 readinglist，但你可以随意命名。比起找个地方放置代码，名字并不重要。
     *
     * 还需要两个额外的目录存放静态 Web 内容和 Thymeleaf 模板。在 readinglist 目录里创建两个新的目录，名为
     * static 和 templates。
     *
     * cd readinglist
     * mkdir static
     * mkdir templates
     *
     * 这些目录的名字和基于 Java 的项目中 src/main/resources 里的目录同名。虽然 Spring Boot 并不像 Maven 和
     * Gradle 那样，对目录结构有严格的要求，但 Spring Boot 会自动配置一个 Spring ResourceHttpRequestHandler
     * 查找 static 目录（还有其他位置）的静态内容。还会配置 Thymeleaf 来解析 templates 目录里的模板。
     *
     * 说到静态内容和 Thymeleaf 模板，那些文件的内容和之前创建的一样。因此你不用担心稍后无法将它们回忆起来，直接把
     * style.css 复制到 static 目录，把 readingList.html 复制到 templates 目录即可。
     *
     * 现在项目已经设置好了，下面准备好编写 Groovy 代码了。
     *
     *
     *
     * 2、通过 Groovy 消除代码噪声
     *
     * Groovy 本身是种优雅的语言。与 Java 不同，Groovy 并不要求有 public 和 private 这样的限定符，也不要求在行尾
     * 有分号。此外，归功于 Groovy 的简化属性语法（GroovyBeans），JavaBean 的标准访问方法没有存在的必要了。
     *
     * 随之而来的结果是，用 Groovy 编写 Book 领域类相当简单。如果在阅读列表项目的根目录里创建一个新的文件，名为
     * Book.groovy，那么在这里编写如下 Groovy 类。
     *
     * class Book {
     *     Long id
     *     String reader
     *     String isbn
     *     String title
     *     String author
     *     String description
     * }
     *
     * 如你所见，Groovy 类与它的 Java 类相比，大小完全不在一个量级。这里没有 setter 和 getter 方法，没有 public
     * 和 private 修饰符，也没有分号。Java 中常见的代码噪声不复存在，剩下的内容都在描述书的基本信息。
     *
     *
     * PS：Spring Boot CLI 中的 JDBC 与 JPA：
     * 你也许已经注意到了，Book 的 Groovy 实现与 Java 实现有所不同，上面没有添加 JPA 注解。这是因为这里要用 Spring
     * 的 JdbcTemplate，而非 Spring Data JPA 访问数据库。
     *
     * 有好几个不错的理由能解释这个例子为什么选择 JDBC 而非 JPA。首先，在使用 Spring 的 JdbcTemplate 时，可以多用
     * 几种不同的方法，展示 Spring Boot 的更多自动配置技巧。选择 JDBC 的最主要原因是，Spring Data JPA 在生成仓库
     * 接口的自动实现时要求有一个 .class 文件。当你在命令行里运行 Groovy 脚本时，CLI 会在内存里编译脚本，并不会产生
     * .class 文件。因此， 当你在 CLI 里运行脚本时，Spring Data JPA 并不适用。
     *
     * 但 CLI 和 Spring Data JPA 并非完全不兼容。如果使用 CLI 的 jar 命令把应用程序打包成一个 JAR 文件，结果文件
     * 里就会包含所有 Groovy 脚本编译后的 .class 文件。当你想部署一个用 CLI 开发的应用程序时，在 CLI 里构建并运行
     * JAR 文件是一个不错的选择。但是如果你想在开发时快速看到开发内容的效果，这种做法就没那么方便了。
     *
     *
     * 既然定义好了 Book 领域类，就开始编写仓库接口吧。首先，编写 ReadingListRepository 接口：
     *
     * interface ReadingListRepository {
     *
     *     List<Book> findByReader(String reader)
     *
     *     void save(Book book)
     *
     * }
     *
     * 除了没有分号，以及接口上没有 public 修饰符，ReadingListRepository 的 Groovy 版本和与之对应的 Java 版本并
     * 无二致。最显著的区别是它没有扩展 JpaRepository。这里不用 Spring Data JPA，既然如此，就不得不自己实现
     * ReadingListRepository。如下代码就是 JdbcReadingListRepository.groovy 的内容。
     *
     * @Repository
     * class JdbcReadingListRepository implements ReadingListRepository {
     *
     *     @Autowired
     *     JdbcTemplate jdbc
     *
     *     List<Book> findByReader(String reader) {
     *         jdbc.query(
     *                 "select id, reader, isbn, title, author, description " +
     *                         "from Book where reader=?",
     *                 { rs, row ->
     *                     new Book(id: rs.getLong(1),
     *                             reader: rs.getString(2),
     *                             isbn: rs.getString(3),
     *                             title: rs.getString(4),
     *                             author: rs.getString(5),
     *                             description: rs.getString(6))
     *                 } as RowMapper,
     *                 reader)
     *     }
     *
     *     void save(Book book) {
     *         jdbc.update("insert into Book " +
     *                 "(reader, isbn, title, author, description) " +
     *                 "values (?, ?, ?, ?, ?)",
     *                 book.reader,
     *                 book.isbn,
     *                 book.title,
     *                 book.author,
     *                 book.description)
     *     }
     *
     * }
     *
     * 以上代码的大部分内容在实现一个典型的基于 JdbcTemplate 的仓库。它自动注入了一个 JdbcTemplate 对象的引用，用
     * 它查询数据库获取图书（在 findByReader() 方法里），将图书保存到数据库（在 save() 方法里）。
     *
     * 因为编写过程采用了 Groovy，所以在实现中可以使用一些 Groovy 的语法糖。举个例子，在 findByReader() 里，调用
     * query() 时可以在需要 RowMapper 实现的地方传入一个 Groovy 闭包（PS： 为了公平对待 Java，在 Java 8 里可以
     * 用 Lambda 和方法引用做类似的事情）。此外，闭包中创建了一个新的 Book 对象，在构造时设置对象的属性。
     *
     * 在考虑数据库持久化时，还需要创建一个名为 schema.sql 的文件。其中包含创建 Book 表所需的 SQL。仓库在发起查询
     * 时依赖这个数据表：
     *
     * create table Book (
     * 	id identity,
     * 	reader varchar(20) not null,
     * 	isbn varchar(10) not null,
     * 	title varchar(50) not null,
     * 	author varchar(50) not null,
     * 	description varchar(2000) not null
     * );
     *
     * 稍后会解释如何使用 schema.sql。现在你只需要知道，把它放在 Classpath 的根目录（即项目的根目录），就能创建出
     * 查询用的 Book 表了。
     *
     * Groovy 的所有部分差不多都齐全了，但还有一个 Groovy 类必须要写。这样 Groovy 化的阅读列表应用程序才完整。这
     * 里需要编写一个 ReadingListController 的 Groovy 实现来处理 Web 请求，为浏览器提供阅读列表。在项目的根目录，
     * 要创建一个名为 ReadingListController.groovy 的文件，内容如下所示。
     *
     * @Controller
     * @RequestMapping("/")
     * class ReadingListController {
     *
     *     String reader = "Craig"
     *
     *     @Autowired
     *     ReadingListRepository readingListRepository
     *
     *     @RequestMapping(method=RequestMethod.GET)
     *     def readersBooks(Model model) {
     *         List<Book> readingList =
     *                 readingListRepository.findByReader(reader)
     *
     *         if (readingList != null) {
     *             model.addAttribute("books", readingList)
     *         }
     *
     *         "readingList"
     *     }
     *
     *     @RequestMapping(method=RequestMethod.POST)
     *     def addToReadingList(Book book) {
     *         book.setReader(reader)
     *         readingListRepository.save(book)
     *         "redirect:/"
     *     }
     *
     * }
     *
     * 这个ReadingListController 和之前的版本有很多相似之处。主要的不同在于，Groovy 的语法消除了类和方法的修饰符、
     * 分号、访问方法和其他不必要的代码噪声。
     *
     * 你还会注意到，两个处理器方法都用 def 而非 String 来定义。两者都没有显式的 return 语句。如果你喜欢在方法上说
     * 明类型，喜欢显式的 retrun 语句，加上就好了 —— Groovy 并不在意这些细节。
     *
     * 在运行应用程序之前，还要做一件事。那就是创建一个新文件，名为 Grabs.groovy，内容包括如下三行：
     *
     * @Grab("h2")
     * @Grab("spring-boot-starter-thymeleaf")
     * class Grabs {}
     *
     * 稍后再来讨论这个类的作用，现在你只需要知道类上的 @Grab 注解会告诉 Groovy 在启动应用程序时自动获取一些依赖的
     * 库。
     *
     * 不管你信还是不信，此时已经可以运行这个应用程序了。这里创建了一个项目目录，向其中复制了一个样式表和 Thymeleaf
     * 模板，填充了一些 Groovy 代码。接下来，用 Spring Boot CLI（在项目目录里）运行即可：
     *
     * spring run .
     *
     * 几秒后，应用程序完全启动。打开浏览器，访问 http://localhost:8080。如果一切正常，你应该就能看到和之前一样的
     * 阅读列表应用程序。
     *
     * 此时此刻你也许会好奇这是怎么办到的。
     * （1）没有 Spring 配置，Bean 是如何创建并组装的？JdbcTemplate Bean 又是从哪来的？
     * （2）没有构建文件，Spring MVC 和 Thymeleaf 这样的依赖库是哪来的？
     * （3）没有 import 语句。如果不通过 import 语句来指定具体的包，Groovy 如何解析 JdbcTemplate 和
     * RequestMapping 的类型？
     * （4）没有部署应用，Web 服务器从何而来？
     *
     * 实际上，这里编写的代码看起来不止缺少分号。这些代码究竟是怎么运行起来的？
     *
     *
     *
     * 3、发生了什么
     *
     * 你可能已经猜到了，Spring Boot CLI 在这里不仅仅是便捷地使用 Groovy 编写了 Spring 应用程序。Spring Boot
     * CLI 施展了很多技能。
     * （1）CLI 可以利用 Spring Boot 的自动配置和起步依赖。
     * （2）CLI 可以检测到正在使用的特定类，自动解析合适的依赖库来支持那些类。
     * （3）CLI 知道多数常用类都在哪些包里，如果用到了这些类，它会把那些包加入 Groovy 的默认包里。
     * （4）应用自动依赖解析和自动配置后，CLI 可以检测到当前运行的是一个 Web 应用程序，并自动引入嵌入式 Web 容器
     * （默认是 Tomcat）供应用程序使用。
     *
     * 仔细想想，这些才是 CLI 提供的最重要的特性。Groovy 语法只是额外的福利！
     *
     * 通过 Spring Boot CLI 运行阅读列表应用程序，表面看似平凡无奇，实则大有乾坤。CLI 尝试用内嵌的 Groovy 编译器
     * 来编译 Groovy 代码。虽然你不知道，但实际上，未知类型（比如 JdbcTemplate、Controller 及 RequestMapping
     * 等等）最终会使代码编译失败。
     *
     * 但 CLI 不会放弃，它知道只要把 Spring Boot JDBC 起步依赖加入 Classpath 就能找到 JdbcTemplate。它还知道
     * 把 Spring Boot 的 Web 起步依赖加入 Classpath 就能找到 Spring MVC 的相关类。因此，CLI 会从 Maven 仓库
     * （默认为 Maven 中心仓库）里获取那些依赖。
     *
     * 如果此时 CLI 重新编译，那还是会失败，因为缺少 import 语句。但 CLI 知道很多常用类的包。利用定制 Groovy 编译
     * 器默认包导入的功能之后，CLI 把所有需要用到的包都加入了 Groovy 编译器的默认导入列表。
     *
     * 现在 CLI 可以尝试再一次编译了。假设没有其他 CLI 能力范围外的问题（比如，存在 CLI 不知道的语法或类型错误），
     * 代码就能完成编译。CLI 将通过内置的启动方法（与基于 Java 的例子里的 main() 方法类似）运行应用程序。
     *
     * 此时，Spring Boot 自动配置就能发挥作用了。它发现 Classpath 里存在 Spring MVC（因为 CLI 解析了 Web 起步
     * 依赖），就自动配置了合适的 Bean 来支持 Spring MVC，还有嵌入式 Tomcat Bean 供应用程序使用。它还发现
     * Classpath 里有 JdbcTemplate，所以自动创建了 JdbcTemplate Bean， 注入了同样自动创建的 DataSource Bean。
     *
     * 说起 DataSource Bean，这只是 Spring Boot 自动配置创建的众多 Bean 中的一个。Spring Boot 还自动配置了很
     * 多 Bean 来支持 Spring MVC 中的 Thymeleaf 模板。正是由于这里使用 @Grab 注解向 Classpath 里添加了 H2 和
     * Thymeleaf，这才触发了针对嵌入式 H2 数据库和 Thymeleaf 的自动配置。
     *
     * @Grab 注解的作用是方便添加 CLI 无法自动解析的依赖。虽然它看上去很简单，但实际上这个小小的注解作用远比你想象得
     * 要大。后续来仔细看看这个注解，看看 Spring Boot CLI 是如何通过一个 Artifact 名称找到这么多常用依赖，看看整
     * 个依赖解析的过程是如何配置的。
     */
    public static void main(String[] args) {

    }

}
