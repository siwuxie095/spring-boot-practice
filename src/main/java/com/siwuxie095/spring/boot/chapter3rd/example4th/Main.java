package com.siwuxie095.spring.boot.chapter3rd.example4th;

/**
 * @author Jiajing Li
 * @date 2021-04-14 07:10:25
 */
public class Main {

    /**
     * 定制应用程序错误页面
     *
     * 错误总是会发生的，那些在生产环境里最健壮的应用程序偶尔也会遇到麻烦。虽然减小用户遇到错误的概率很重要，
     * 但让应用程序展现一个好的错误页面也同样重要。
     *
     * 近年来，富有创意的错误页已经成为了一种艺术。如果你曾见到过 GitHub.com 的星球大战错误页，或者是
     * DropBox.com 的 Escher 立方体错误页的话，你就能明白这里在说什么了。
     *
     * 你在使用阅读列表应用程序时也会碰到错误。
     *
     * Spring Boot 默认提供一个 "白标"（whitelabel）错误页，这是自动配置的一部分。虽然这比 Stacktrace
     * 页面要好一点，但和网上那些伟大的错误页艺术品却不可同日而语。为了让你的应用程序故障页变成大师级作品，
     * 你需要为应用程序创建一个自定义的错误页。
     *
     * Spring Boot 自动配置的默认错误处理器会查找名为 error 的视图，如果找不到就用默认的白标错误视图。因
     * 此，最简单的方法就是创建一个自定义视图，让解析出的视图名为 error。
     *
     * 这一点归根到底取决于错误视图解析时的视图解析器。
     * （1）实现了 Spring 的 View 接口的 Bean，其 ID 为 error（由 Spring 的 BeanNameViewResolver
     * 所解析）。
     * （2）如果配置了 Thymeleaf，则有名为 error.html 的 Thymeleaf 模板。
     * （3）如果配置了 FreeMarker，则有名为 error.ftl 的 FreeMarker 模板。
     * （4）如果配置了 Velocity，则有名为 error.vm 的 Velocity 模板。
     * （5）如果是用 JSP 视图，则有名为 error.jsp 的 JSP 模板。
     *
     * 因为这里的阅读列表应用程序使用了 Thymeleaf，所以要做的就是创建一个名为 error.html 的文件，把它和
     * 其他的应用程序模板一起放在模板文件夹里。如下代码是一个简单有效的错误页，可以用来代替默认的白标错误页。
     *
     * <html>
     *   <head>
     *     <title>Oops!</title>
     *     <link rel="stylesheet" th:href="@{/style.css}"></link>
     *   </head>
     *
     *   <html>
     *     <div class="errorPage">
     *       <span class="oops">Oops!</span><br/>
     *       <img th:src="@{/MissingPage.png}"></img>
     *       <p>There seems to be a problem with the page you requested
     *          (<span text="${path}">/readingList</span>).</p>
     *       <p th:text="${'Details: ' + message}"></p>
     *     </div>
     *   </html>
     *
     * </html>
     *
     * 这个自定义的错误模板应该命名为 error.html，放在模板目录里，这样 Thymeleaf 模板解析器才能找到它。
     * 在典型的 Maven 或 Gradle 项目里，这就意味着要把该文件放在 src/main/resources/templates 中，
     * 运行时它就在 Classpath 的根目录里。
     *
     * 基本上，这个简单的 Thymeleaf 模板就是显示一张图片和一些提示错误的文字。其中有两处特别的信息需要呈现：
     * 错误的请求路径和异常消息。但这还不是错误页上的全部细节。默认情况下，Spring Boot 会为错误视图提供如
     * 下错误属性。
     * （1）timestamp：错误发生的时间。
     * （2）status：HTTP 状态码。
     * （3）error：错误原因。
     * （4）exception：异常的类名。
     * （5）message：异常消息（如果这个错误是由异常引起的）。
     * （6）errors：BindingResult 异常里的各种错误（如果这个错误是由异常引起的）。
     * （7） trace：异常跟踪信息（如果这个错误是由异常引起的）。
     * （8）path：错误发生时请求的 URL 路径。
     *
     * 其中某些属性，比如 path，在向用户交待问题时还是很有用的。其他的，比如 trace，用起来要保守一点，将其
     * 隐藏，或者用得聪明点，让错误页尽可能对用户友好。
     *
     * 请注意，模板里还引用了一张名为 MissingPage.png 的图片。图片的实际内容并不重要，所以尽情挑选适合你
     * 的图片就好了，但请一定将它放在 src/main/resources/static 或 src/main/resources/public 里，
     * 这样应用程序运行时才能找到它。
     */
    public static void main(String[] args) {

    }

}
