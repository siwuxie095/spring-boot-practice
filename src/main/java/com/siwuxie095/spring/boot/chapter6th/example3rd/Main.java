package com.siwuxie095.spring.boot.chapter6th.example3rd;

/**
 * @author Jiajing Li
 * @date 2021-04-21 07:10:52
 */
@SuppressWarnings("all")
public class Main {

    /**
     * 使用 Groovy Server Pages 定义视图
     *
     * 到目前为止，这里都在用 Thymeleaf 模板定义阅读列表应用程序的视图。除了 Thymeleaf，Spring Boot 还
     * 支持 Freemarker、Velocity 和基于 Groovy 的模板。无论选择哪种模板，你要做的就是添加合适的起步依赖，
     * 在 Classpath 根部的 templates/ 目录里编写模板。自动配置会处理剩下的事情。
     *
     * Grails 项目也提供 GSP 的自动配置。如果你想在 Spring Boot 应用程序里使用 GSP，必须向项目里添加
     * Spring Boot 的 GSP 库：
     *
     *         <dependency>
     *             <groupId>org.grails</groupId>
     *             <artifactId>grails-gsp-spring-boot</artifactId>
     *             <version>1.0.0</version>
     *         </dependency>
     *
     * 和 Spring Boot 提供的其他视图模板一样，库放在 Classpath 里就会触发自动配置，设置所需的视图解析器，
     * 以便在 Spring MVC 的视图层里使用 GSP。
     *
     * 剩下的就是为应用程序编写 GSP 模板了。在阅读列表应用程序中，要把 Thymeleaf 的 readingList.html
     * 文件用 GSP 的形式重写，放在 readingList.gsp 文件（位于 src/main/resources/templates）里。
     * 如下代码就是新的 GSP 模板的代码。
     *
     * <!DOCTYPE html>
     * <html>
     *   <head>
     *     <title>Reading List</title>
     *     <link rel="stylesheet" href="/style.css"></link>
     *   </head>
     *
     *   <body>
     *     <h2>Your Reading List</h2>
     *
     *     <g:if test="${books}">
     *     <g:each in="${books}" var="book">
     *       <dl>
     *         <dt class="bookHeadline">
     *           ${book.title} by ${book.author}
     *           (ISBN: ${book.isbn}")
     *         </dt>
     *         <dd class="bookDescription">
     *           <g:if test="book.description">
     *             ${book.description}
     *           </g:if>
     *           <g:else>
     *             No description available
     *           </g:else>
     *         </dd>
     *       </dl>
     *     </g:each>
     *     </g:if>
     *     <g:else>
     *       <p>You have no books in your book list</p>
     *     </g:else>
     *
     *     <hr/>
     *
     *     <h3>Add a book</h3>
     *
     *     <form method="POST">
     *       <label for="title">Title:</label>
     *       <input type="text" name="title"
     *                          value="${book?.title}"/><br/>
     *       <label for="author">Author:</label>
     *       <input type="text" name="author"
     *                          value="${book?.author}"/><br/>
     *       <label for="isbn">ISBN:</label>
     *       <input type="text" name="isbn"
     *                          value="${book?.isbn}"/><br/>
     *       <label for="description">Description:</label><br/>
     *       <textarea name="description" rows="5" cols="80">${book?.description}</textarea>
     *       <input type="submit" value="Add Book" />
     *       <input type="hidden" name="${_csrf.parameterName}"
     *        value="${_csrf.token}" />
     *     </form>
     *
     *   </body>
     * </html>
     *
     * 如你所见，GSP 模板中使用了表达式语言引用（用 ${} 包围的部分）以及 GSP 标签库（例如 <g:if> 和
     * <g:each>）。这并不是 Thymeleaf 那样的纯 HTML。但如果习惯用 JSP，你会很熟悉这种方式，而且会
     * 觉得这是一个不错的选择。
     *
     * 代码里的绝大部分内容和之前的 Thymeleaf 模板类似，映射 GSP 模板上的元素。但是有一点要注意，你
     * 必须要放一个隐藏域，其中包含 CSRF（Cross-Site Request Forgery）令牌。Spring Security
     * 在提交 POST 请求时要求带有这个令牌，Thymeleaf 在呈现 HTML 时会自动包含这个令牌，但在 GSP
     * 里你必须在隐藏域显式地包含它。
     *
     * 虽然 GORM 和 GSP 这样的 Grails 特性很吸引人，让 Spring Boot 应用程序更简单，但你在这里还不
     * 能真正体验 Grails。后续会再往 Spring Boot 的花生酱里放一点 Grails 巧克力。来看看 Grails 3
     * 如何将两者合二为一，带来完整的 Spring Boot 和 Grails 开发体验。
     */
    public static void main(String[] args) {

    }

}
