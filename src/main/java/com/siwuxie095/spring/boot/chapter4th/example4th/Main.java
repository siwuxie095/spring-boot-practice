package com.siwuxie095.spring.boot.chapter4th.example4th;

/**
 * @author Jiajing Li
 * @date 2021-04-16 22:42:08
 */
@SuppressWarnings("all")
public class Main {

    /**
     * 测试运行中的应用程序
     *
     * 说到测试 Web 应用程序，至此还没接触实质内容。在真实的服务器里启动应用程序，用真实的 Web 浏览器访问它，这样
     * 比使用模拟的测试引擎更能展现应用程序在用户端的行为。
     *
     * 但是，用真实的 Web 浏览器在真实的服务器上运行测试会很麻烦。虽然构建时的插件能把应用程序部署到 Tomcat 或者
     * Jetty 里，但它们配置起来多有不便。而且测试这么多，几乎不可能隔离运行，也很难不启动构建工具。
     *
     * 然而 Spring Boot 找到了解决方案。它支持将 Tomcat 或 Jetty 这样的嵌入式 Servlet 容器作为运行中的应用程
     * 序的一部分，可以运用相同的机制，在测试过程中用嵌入式 Servlet 容器来启动应用程序。
     *
     * Spring Boot 的 @WebIntegrationTest 注解就是这么做的。在测试类上添加 @WebIntegrationTest 注解，可以
     * 声明你不仅希望 Spring Boot 为测试创建应用程序上下文，还要启动一个嵌入式的 Servlet 容器。一旦应用程序运行
     * 在嵌入式容器里，你就可以发起真实的 HTTP 请求，断言结果了。
     *
     * 举例来说，考虑一下如下代码里的那段简单的 Web 测试。这里采用 @WebIntegrationTest，在服务器里启动了应用程
     * 序，以 Spring 的 RestTemplate 对应用程序发起 HTTP 请求。
     *
     * @RunWith(SpringJUnit4ClassRunner.class)
     * @SpringApplicationConfiguration(classes=ReadingListApplication.class)
     * @WebIntegrationTest
     * public class SimpleWebTest {
     *
     *     @Test(expected=HttpClientErrorException.class)
     *     public void pageNotFound() {
     *         try {
     *             RestTemplate rest = new RestTemplate();
     *             rest.getForObject("http://localhost:8080/bogusPage", String.class);
     *             fail("Should result in HTTP 404");
     *         } catch (HttpClientErrorException e) {
     *             assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
     *             throw e;
     *         }
     *     }
     *
     * }
     *
     * 虽然这个测试非常简单，但足以演示如何使用 @WebIntegrationTest 在服务器里启动应用程序。要判断实际启动的服
     * 务器究竟是哪个，可以遵循在命令行里运行应用程序时的逻辑。默认情况下，会有一个监听 8080 端口的 Tomcat 启动。
     * 但是，如果 Classpath 里有的话，Jetty 或者 Undertow 也能启动这些服务器。
     *
     * 测试方法的主体部分假设应用程序已经运行，监听了 8080 端口。它使用了 Spring 的 RestTemplate 对一个不存在
     * 的页面发起请求，判断服务器的响应是否为 HTTP 404（NOT FOUND）。如果返回了其他响应，则测试失败。
     *
     *
     *
     * 1、用随机端口启动服务器
     *
     * 前面提到过，此处的默认行为是启动服务器监听 8080 端口。在一台机器上一次只运行一个测试的话，这没什么问题，因
     * 为没有其他服务器监听 8080 端口。但如果本机总是有其他服务器在监听 8080 端口，那该怎么办？这时测试会失败，
     * 因为端口冲突，服务器启动不了。一定要有更好的办法才行。
     *
     * 幸运的是，让 Spring Boot 在随机选择的端口上启动服务器很方便。一种办法是将 server.port 属性设置为 0，让
     * Spring Boot 选择一个随机的可用端口。@WebIntegrationTest 的 value 属性接受一个 String 数组，数组中的
     * 每项都是键值对，形如 name=value，用来设置测试中使用的属性。要设置 server.port，你可以这样做：
     *
     * @WebIntegrationTest(value={"server.port=0"})
     *
     * 另外，因为只要设置一个属性，所以还能有更简单的形式：
     *
     * @WebIntegrationTest("server.port=0")
     *
     * 通过 value 属性来设置属性通常还算方便。但 @WebIntegrationTest 还提供了一个 randomPort 属性，更明确地
     * 表示让服务器在随机端口上启动。你可以将 randomPort 设置为 true，启用随机端口：
     *
     * @WebIntegrationTest(randomPort=true)
     *
     * 既然在随机端口上启动了服务器，就需要在发起 Web 请求时确保使用正确的端口。此时的 getForObject() 方法在
     * URL 里硬编码了 8080 端口。如果端口是随机选择的，那在构造请求时又该怎么确定正确的端口呢？
     *
     * 首先，需要以实例变量的形式注入选中的端口。为了方便，Spring Boot 将 local.server.port 的值设置为了选中
     * 的端口。这里只需使用 Spring 的 @Value 注解将其注入即可：
     *
     * @Value("${local.server.port}")
     * private int port;
     *
     * 有了端口之后，只需对 getForObject() 稍作修改，使用这个 port 就好了：
     *
     * rest.getForObject(
     *      "http://localhost:{port}/bogusPage", String.class, port);
     *
     * 这里在 URL 里把硬编码的 8080 改为 {port} 占位符。在 getForObject() 调用里把 port 属性作为最后一个参
     * 数传入，就能确保该占位符被替换为注入 port 的值了。
     *
     *
     *
     * 2、使用 Selenium 测试 HTML 页面
     *
     * RestTemplate 对于简单的请求而言使用方便，是测试 REST 端点的理想工具。但是，就算它能对返回 HTML 页面的
     * URL 发起请求，也不方便对页面内容或者页面上执行的操作进行断言。结果 HTML 里的内容最好能够精确判断（这种
     * 测试很脆弱）。不过你无法轻易判断页面上选中的内容，或者执行诸如点击链接或提交表单这样的操作。
     *
     * 对于 HTML 应用程序测试，有一个更好的选择 —— Selenium（www.seleniumhq.org 或 www.selenium.dev），
     * 它的功能远不止提交请求和获取结果。它能实际打开一个 Web 浏览器，在浏览器的上下文中执行测试。Selenium 尽
     * 量接近手动执行测试，但与手工测试不同。Selenium 的测试是自动的，而且可以重复运行。
     *
     * 为了用 Selenium 测试阅读列表应用程序，下面先写一个测试来获取首页，为新书填写表单，提交表单，随后判断返回
     * 的页面里是否包含新添加的图书。
     *
     * 首先需要把 Selenium 作为测试依赖添加到项目里：
     *
     *         <dependency>
     *             <groupId>org.seleniumhq.selenium</groupId>
     *             <artifactId>selenium-java</artifactId>
     *             <version>2.45.0</version>
     *         </dependency>
     *
     * 现在就可以编写测试了。如下代码是一个基本的 Selenium 测试模板，使用了 Spring Boot
     * 的 @WebIntegrationTest。
     *
     * @RunWith(SpringJUnit4ClassRunner.class)
     * @SpringApplicationConfiguration(classes=ReadingListApplication.class)
     * @WebIntegrationTest(randomPort=true)
     * public class ServerWebTests {
     *
     *     private static FirefoxDriver browser;
     *
     *     @Value("${local.server.port}")
     *     private int port;
     *
     *     @BeforeClass
     *     public static void openBrowser() {
     *         browser = new FirefoxDriver();
     *         browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
     *     }
     *
     *     @AfterClass
     *     public static void closeBrowser() {
     *         browser.quit();
     *     }
     *
     * }
     *
     * 和之前更简单的 Web 测试一样，这个类添加了 @WebIntegrationTest 注解，将 randomPort 设置为 true，这样
     * 应用程序启动后会运行一个监听随机端口的服务器。同样，端口号注入 port 属性，这样就能用它来构造指向运行中应用
     * 程序的 URL 了。
     *
     * 静态方法 openBrowser() 会创建一个 FirefoxDriver 的实例，它将打开 Firefox 浏览器（需要在运行测试的服
     * 务器上安装该浏览器）。
     *
     * 测试方法将通过 FirefoxDriver 实例来执行浏览器操作。在页面上查找元素时，FirefoxDriver 配置了 10 秒的
     * 等候时间（以防元素加载过慢）。
     *
     * 测试执行完毕，需要关闭 Firefox 浏览器。因此要在 closeBrowser() 里要调用 FirefoxDriver 实例的 quit()
     * 方法，关闭浏览器。
     *
     * PS：选择浏览器：
     * 虽然这里用 Firefox 进行了测试，但 Selenium 还提供了不少其他浏览器的驱动，包括 IE、Google 的 Chrome，
     * 还有 Apple 的 Safari。测试可以使用其他浏览器。你也可以使用你想支持的各种浏览器，这也许也是个不错的想法。
     *
     * 现在可以开始编写测试方法了，给你提个醒，这里想要加载首页，填充并发送表单，然后判断登录的页面是否包含刚刚
     * 添加的新书。如下代码演示了如何用 Selenium 实现这个功能。
     *
     *     @Test
     *     public void addBookToEmptyList() {
     *         String baseUrl = "http://localhost:" + port;
     *         browser.get(baseUrl);
     *
     *         String currentUrl = browser.getCurrentUrl();
     *         assertEquals(baseUrl +"/readingList", currentUrl);
     *
     *         assertEquals("You have no books in your book list",
     *                 browser.findElementByTagName("div").getText());
     *
     *         browser.findElementByName("title").sendKeys("BOOK TITLE");
     *         browser.findElementByName("author").sendKeys("BOOK AUTHOR");
     *         browser.findElementByName("isbn").sendKeys("1234567890");
     *         browser.findElementByName("description").sendKeys("DESCRIPTION");
     *         browser.findElementByTagName("form").submit();
     *
     *         WebElement dl =
     *                 browser.findElementByCssSelector("dt.bookHeadline");
     *         assertEquals("BOOK TITLE by BOOK AUTHOR (ISBN: 1234567890)",
     *                 dl.getText());
     *         WebElement dt =
     *                 browser.findElementByCssSelector("dd.bookDescription");
     *         assertEquals("DESCRIPTION", dt.getText());
     *     }
     *
     * 该测试方法所做的第一件事是使用 FirefoxDriver 来发起 GET 请求，获取阅读列表的主页，随后查找页面里的一个
     * <div> 元素，从它的文本里判断列表里没有图书。
     *
     * 接下来的几行查找表单里的元素，使用驱动的 sendKeys() 方法模拟敲击键盘事件（实际上就是用给定的值填充那些表
     * 单域）。最后，找到 <form> 元素并提交。
     *
     * 提交的表单经处理后，浏览器就会跳到一个页面，上面的列表包含了新添加的图书。因此最后几行查找列表里的 <dt>
     * 和 <dd> 元素，判断其中是否包含测试表单里提交的数据。
     *
     * 运行测试时，你会看到浏览器打开，加载阅读列表应用程序。如果够仔细，你还会看到填充表单的过程，就好像幽灵在操
     * 作，当然，并没有幽灵使用你的应用程序 —— 这只是一个测试。
     *
     * 这个测试里最值得注意的是，@WebIntegrationTest 可以启动应用程序和服务器，这样 Selenium 才可以用 Web
     * 浏览器执行测试。但真正有趣的是你可以使用 IDE 的测试功能来运行测试，运行几次都行，无需依赖构建过程中的某些
     * 插件启动服务器。
     */
    public static void main(String[] args) {

    }

}
