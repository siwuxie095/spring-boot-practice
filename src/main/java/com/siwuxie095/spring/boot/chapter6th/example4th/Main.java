package com.siwuxie095.spring.boot.chapter6th.example4th;

/**
 * @author Jiajing Li
 * @date 2021-04-21 22:05:26
 */
@SuppressWarnings("all")
public class Main {

    /**
     * 结合 Spring Boot 与 Grails 3
     *
     * Grails 一直都是构建于 Spring、Groovy、Hibernate 和其他巨人肩膀之上的高阶框架。到了 Grails 3，Grails 已经
     * 基于 Spring Boot，带来了令人愉悦的开发体验。Grails 开发者和 Spring Boot 开发者都能驾轻就熟。
     *
     * 要使用 Grails 3，首先要进行安装。在 Mac OS X 和大部分 Unix 系统上，最简单的安装方法是在命令行里使用 SDKMAN：
     *
     * sdk install grails
     *
     * 如果你用的是 Windows，或者无法使用 SDKMAN，就需要下载二进制发布包。解压后要将 bin 目录添加到系统路径里去。
     *
     * 无论用哪种安装方式，你都可以在命令行中查看 Grails 的版本，验证安装是否成功：
     *
     * grails -version
     *
     * 如果安装成功，现在就可以创建 Grails 项目了。
     *
     *
     *
     * 1、创建新的 Grails 项目
     *
     * 在 Grails 项目中，你会使用 grails 命令行工具执行很多任务，包括创建项目。要创建阅读列表项目，可以这样使用
     * grails 命令：
     *
     * grails create-app readinglist
     *
     * 正如这个命令的名字所示，create-app 创建了新的应用程序项目。这个例子里的项目名为 readinglist。
     *
     * 等 grails 工具创建完应用程序，cd 到了 readinglist 目录里，就可以看到所创建的内容（项目结构的概览）。
     *
     * 在这个项目目录结构里，你应该认出了一些熟悉的东西。这里有一个 Gradle 的构建说明文件和配置（build.gradle 和
     * gradle.properties）。src 目录里还有一个标准的 Gradle 项目结构，但是 grails-app 应该是里面最有趣的目录。
     * 如果用过老版本的 Grails，你就会知道这个目录的作用。这里面放的是你写的控制器、领域类和其他构成 Grails 项目的
     * 代码。
     *
     * 如果再深挖一下，打开 build.gradle 文件，会发现一些更熟悉的东西。首先，构建说明文件里使用了 Spring Boot 的
     * Gradle 插件：
     *
     * apply plugin: "spring-boot"
     *
     * 这意味着你能像使用其他 Spring Boot 应用程序那样构建并运行这个 Grails 应用程序。
     *
     * 其中，项目的依赖里有不少有用的 Spring Boot 库。这些库为 Grails 应用程序提供了 Spring Boot 的自动配置、日志，
     * 还有 Actuator 及嵌入式 Tomcat。把应用当作可执行 JAR 运行时，这个 Tomcat 可以提供服务。
     *
     * 实际上，这是一个 Spring Boot 项目，同时也是 Grails 项目，因为 Grails 3 就是构建在 Spring Boot 的基础上的。
     *
     *
     * 1.1、运行应用程序
     *
     * 运行 Grails 应用程序最直接的方式是在命令行里使用 grails 工具的 run-app 命令：
     *
     * grails run-app
     *
     * 就算一行代码都还没写，也能运行应用程序，在浏览器里进行访问。一旦应用程序启动，就可以在浏览器里访问
     * http://localhost:8080。
     *
     * 在 Grails 里运行应用程序要使用 run-app 命令，这种方式已经用了很多年，上个版本的 Grails 也是这样。Grails 3
     * 项目的 Gradle 说明里使用了 Spring Boot 的 Gradle 插件，你还可以用各种运行 Spring Boot 项目的方式来运行这
     * 个应用程序。此处通过 Gradle 引入了 bootRun 任务：
     *
     * gradle bootRun
     *
     * 你还可以构建项目，运行生成的可执行 JAR 文件：
     *
     * gradle build
     *
     * java -jar build/lib/readingList-0.1.jar
     *
     * 当然，构建产生的 WAR 文件可以部署到你喜欢的各种 Servlet 3.0 容器里。
     *
     * 在开发早期就能运行应用程序，这一点十分方便，能帮你确认应用程序已正确初始化。但是这时应用程序还没做什么有意思的事
     * 情，在初始化后的项目上做什么完全取决于开发者。接下来，开始定义领域模型吧。
     *
     *
     *
     * 2、定义领域模型
     *
     * 阅读列表应用程序里的核心领域模型是 Book 类。虽然可以手工创建 Book.groovy 文件，但通常还是用 grails 工具来创
     * 建领域模型类比较好。因为它知道该把文件放到哪里，并且能在同一时间生成各种相关内容。
     *
     * 要创建 Book 类，会使用 grails 工具的 create-domain-class 命令：
     *
     * grails create-domain-class Book
     *
     * 这条命令会生成两个源文件：一个 Book.groovy 文件和一个 BookSpec.groovy 文件。后者是一个 Spock 说明，用来测
     * 试 Book 类。一开始这个文件是空的，你可以填入各种测试内容来验证 Book 的各种功能。
     *
     * Book.groovy 文件里定义了 Book 类，你可以在 grails-app/domain/readingList 里找到这个文件。它一开始基本没
     * 什么内容：
     *
     * package readinglist
     *
     * class Book {
     *   static constraints = {
     *   }
     * }
     *
     * 需要添加一些字段来定义一本书，比如书名、作者和 ISBN。在添加了这些字段后，Book.groovy 看起来是这样的：
     *
     * class Book {
     *   static constraints = {
     *   }
     *   String reader
     *   String isbn
     *   String title
     *   String author
     *   String description
     * }
     *
     * 静态的 constraints 变量里可以定义各种附加在 Book 实例上的验证约束。这里主要关注阅读列表应用程序的构建，看看
     * 如何基于 Spring Boot 构建应用程序，不会太关注验证的问题。因此，这里的 constraints 内容为空。当然，如果有需
     * 要的话，你可以随意添加约束。
     *
     * 为了使用 Grails，要保持阅读列表应用程序的简洁，要和之前的程序一致。因此，接下来要创建 Reader 领域模型，还有
     * 控制器。
     *
     *
     *
     * 3、开发 Grails 控制器
     *
     * 有了领域模型，通过 grails 工具创建出控制器就很容易了。关于控制器，有几个命令可供选择。
     * （1）create-controller：创建空控制器，让开发者来编写控制器的功能。
     * （2）generate-controller：生成一个控制器，其中包含特定领域模型类的基本 CRUD 操作。
     * （3）generate-all：生成针对特定领域模型类的基本 CRUD 控制器，及其视图。
     *
     * 脚手架控制器很好用，也是 Grails 中比较知名的特性，但这里仍然会保持简洁，写一个仅包含必要功能的控制器，能匹配之
     * 前的应用程序功能就好。因此，这里用 create-controller 命令来创建原始的控制器，然后填入所需的方法。
     *
     * grails create-controller ReadingList
     *
     * 这个命令会在 grails-app/controllers/readingList 里创建一个名为 ReadingListController 的控制器：
     *
     * package readinglist
     * class ReadingListController {
     *   def index() { }
     * }
     *
     * 一行代码都不用改，这个控制器就能运行了，虽然它干不成什么事。此时，它能处理发往 /readingList 的请求，将请求转
     * 给 grails-app/views/readingList/index.gsp 里定义的视图（现在还没有，稍后会创建的）。
     *
     * 这里需要控制器来显示图书列表，还有添加新书的表单。这里还需要提交表单，将新书保存到数据库里的功能。下面的代码就
     * 是所需要的 ReadingListController。
     *
     * package readinglist
     * import static org.springframework.http.HttpStatus.*
     * import grails.transaction.Transactional
     * class ReadingListController {
     *   def index() {
     *     respond Book.list(params), model:[book: new Book()]
     *   }
     *
     *   @Transactional
     *   def save(Book book) {
     *     book.reader = 'Craig'
     *     book.save flush:true
     *     redirect(action: "index")
     *   }
     * }
     *
     * 虽然相比等效的 Java 控制器，代码长度大幅缩短，但这个版本的 ReadingListController 功能已经基本完整。它可以处
     * 理发往 /readingList 的 GET 请求，获取并展示图书列表。在表单提交后，它还会处理 POST 请求，保存图书，随后重定
     * 向回 index 动作（由 index() 方法来处理）。
     *
     * 太不可思议了，这里已经基本完成了 Grails 版本的阅读列表应用程序。剩下的就是创建一个视图，显示图书列表和表单。
     *
     *
     *
     * 4、创建视图
     *
     * Grails 应用程序通常都用 GSP 模板来做视图。你已经看到过如何在 Spring Boot 应用程序里使用 GSP 了，因此，此处
     * 的模板并不会和之前的模板有太多不同。
     *
     * 这里要做的是，利用 Grails 提供的布局设施，将公共的设计风格运用到整个应用程序里。如下所示，这就是个很简单的修改。
     *
     * <!DOCTYPE html>
     *     <html>
     *       <head>
     *         <meta name="layout" content="main"/>
     *         <title>Reading List</title>
     *         <link rel="stylesheet"
     *               href="/assets/main.css?compile=false"  />
     *         <link rel="stylesheet"
     *               href="/assets/mobile.css?compile=false"  />
     *         <link rel="stylesheet"
     *               href="/assets/application.css?compile=false"  />
     *   </head>
     *   <body>
     *       <h2>Your Reading List</h2>
     *       <g:if test="${bookList && !bookList.isEmpty()}">
     *         <g:each in="${bookList}" var="book">
     *         <dl>
     *           <dt class="bookHeadline">
     *             ${book.title}</span> by ${book.author}
     *             (ISBN: ${book.isbn}")
     *           </dt>
     *           <dd class="bookDescription">
     *             <g:if test="${book.description}">
     *             ${book.description}
     *             </g:if>
     *             <g:else>
     *             No description available
     *             </g:else>
     *           </dd>
     *         </dl>
     *         </g:each>
     *       </g:if>
     *       <g:else>
     *       <p>You have no books in your book list</p>
     *       </g:else>
     *       <hr/>
     *       <h3>Add a book</h3>
     *       <g:form action="save">
     *       <fieldset class="form">
     *         <label for="title">Title:</label>
     *         <g:field type="text" name="title" value="${book?.title}"/><br/>
     *         <label for="author">Author:</label>
     *         <g:field type="text" name="author"
     *                       value="${book?.author}"/><br/>
     *         <label for="isbn">ISBN:</label>
     *         <g:field type="text" name="isbn" value="${book?.isbn}"/><br/>
     *         <label for="description">Description:</label><br/>
     *         <g:textArea name="description" value="${book?.description}"
     *                                  rows="5" cols="80"/>
     *       </fieldset>
     *       <fieldset class="buttons">
     *         <g:submitButton name="create" class="save"
     *            value="${message(code: 'default.button.create.label',
     *                                                  default: 'Create')}" />
     *       </fieldset>
     *       </g:form>
     *   </body>
     * </html>
     *
     * 在 <head> 元素里移除了引用样式表的 <link> 标签。这里放了一个 <meta> 标签，引入了 Grails 应用程序的 main
     * 布局。这样一来，应用程序就能用上 Grails 的外观了。
     *
     * 虽然 Grails 风格比之前用的简单的样式表更吸引眼球。但很显然的是，要让阅读列表应用程序更好看，还有一些工作要做。
     * 首先要让应用程序和 Grails 不那么像，而是和想象更接近一点。修改应用程序的样式表不在这里的讨论范围之内，但如果
     * 你对样式微调感兴趣，可以在 grails-app/assets/stylesheets 目录里找到样式表文件。
     */
    public static void main(String[] args) {

    }

}
