package com.siwuxie095.spring.boot.chapter4th.example3rd;

/**
 * @author Jiajing Li
 * @date 2021-04-15 22:16:49
 */
@SuppressWarnings("all")
public class Main {

    /**
     * 测试 Web 应用程序
     *
     * Spring MVC 有一个优点：它的编程模型是围绕 POJO 展开的，在 POJO 上添加注解，声明如何处理 Web 请求。
     * 这种编程模型不仅简单，还让你能像对待应用程序中的其他组件一样对待这些控制器。你还可以针对这些控制器编写
     * 测试，就像测试 POJO 一样。
     *
     * 举例来说，考虑 ReadingListController 里的 addToReadingList() 方法：
     *
     *     @RequestMapping(method=RequestMethod.POST)
     *     public String addToReadingList(Reader reader, Book book) {
     *         book.setReader(reader);
     *         readingListRepository.save(book);
     *         return "redirect:/";
     *     }
     *
     * 如果忽略 @RequestMapping 注解，你得到的就是一个相当基础的 Java 方法。你立马就能想到这样一个测试，
     * 提供一个 ReadingListRepository 的模拟实现，直接调用 addToReadingList()，判断返回值并验证对
     * ReadingListRepository 的 save() 方法有过调用。
     *
     * 该测试的问题在于，它仅仅测试了方法本身，当然，这要比没有测试好一点。然而，它没有测试该方法处理
     * /readingList 的 POST 请求的情况，也没有测试表单域绑定到 Book 参数的情况。虽然你可以判断返
     * 回的 String 包含特定值，但没法明确测试请求在方法处理完之后是否真的会重定向到 /readingList。
     *
     * 要恰当地测试一个 Web 应用程序，你需要投入一些实际的 HTTP 请求，确认它能正确地处理那些请求。幸运的是，
     * Spring Boot 开发者有两个可选的方案能实现这类测试。
     * （1）Spring Mock MVC：能在一个近似真实的模拟 Servlet 容器里测试控制器，而不用实际启动应用服务器。
     * （2）Web 集成测试：在嵌入式 Servlet 容器（比如 Tomcat 或 Jetty）里启动应用程序，在真正的应用服
     * 务器里执行测试。
     *
     * 这两种方法各有利弊。很明显，启动一个应用服务器会比模拟 Servlet 容器要慢一些，但毫无疑问基于服务器的
     * 测试会更接近真实环境，更接近部署到生产环境运行的情况。
     *
     * 接下来，你会看到如何使用 Spring Mock MVC 测试框架来测试 Web 应用程序。
     *
     * PS：后续你也会看到如何为运行在应用服务器里的应用程序编写测试。
     *
     *
     *
     * 1、模拟 Spring MVC
     *
     * 早在 Spring 3.2，Spring Framework 就有了一套非常实用的 Web 应用程序测试工具，能模拟 Spring MVC，
     * 不需要真实的 Servlet 容器也能对控制器发送 HTTP 请求。Spring 的 Mock MVC 框架模拟了 Spring MVC
     * 的很多功能。它几乎和运行在 Servlet 容器里的应用程序一样，尽管实际情况并非如此。
     *
     * 要在测试里设置 Mock MVC，可以使用 MockMvcBuilders，该类提供了两个静态方法。
     * （1）standaloneSetup()：构建一个 Mock MVC，提供一个或多个手工创建并配置的控制器。
     * （2）webAppContextSetup()：使用 Spring 应用程序上下文来构建 Mock MVC，该上下文里可以包含一个或
     * 多个配置好的控制器。
     *
     * 两者的主要区别在于，standaloneSetup() 希望你手工初始化并注入你要测试的控制器，而 webAppContextSetup()
     * 则基于一个 WebApplicationContext 的实例，通常由 Spring 加载。前者同单元测试更加接近，你可能只想让它专注
     * 于单一控制器的测试，而后者让 Spring 加载控制器及其依赖，以便进行完整的集成测试。
     *
     * 这里要用的是 webAppContextSetup()。Spring 完成了 ReadingListController 的初始化，并从 Spring Boot
     * 自动配置的应用程序上下文里将其注入，这里直接对其进行测试。
     *
     * webAppContextSetup() 接受一个 WebApplicationContext 参数。因此，需要为测试类加上 @WebAppConfiguration
     * 注解，使用 @Autowired 将 WebApplicationContext 作为实例变量注入测试类。如下代码演示了 Mock MVC 测试的执行
     * 入口。
     *
     * @RunWith(SpringJUnit4ClassRunner.class)
     * @SpringApplicationConfiguration(classes = ReadingListApplication.class)
     * @WebAppConfiguration
     * public class MockMvcWebTests {
     *
     *     @Autowired
     *     WebApplicationContext webContext;
     *
     *     private MockMvc mockMvc;
     *
     *     @Before
     *     public void setupMockMvc() {
     *         mockMvc = MockMvcBuilders
     *                 .webAppContextSetup(webContext)
     *                 .build();
     *     }
     *
     * }
     *
     * @WebAppConfiguration 注解声明，由 SpringJUnit4ClassRunner 创建的应用程序上下文应该是一个
     * WebApplicationContext（相对于基本的非 WebApplicationContext）。
     *
     * setupMockMvc() 方法上添加了 JUnit 的 @Before 注解，表明它应该在测试方法之前执行。它将
     * WebApplicationContext 注入 webAppContextSetup() 方法，然后调用 build() 产生了一个
     * MockMvc 实例，该实例赋给了一个实例变量，供测试方法使用。
     *
     * 现在有了一个 MockMvc，已经可以开始写测试方法了。先写个简单的测试方法，向 /readingList 发送一个 HTTP GET
     * 请求，判断模型和视图是否满足期望。下面的 homePage() 测试方法就是所需要的：
     *
     *     @Test
     *     public void homePage() throws Exception {
     *         mockMvc.perform(MockMvcRequestBuilders.get("/readingList"))
     *                 .andExpect(MockMvcResultMatchers.status().isOk())
     *                 .andExpect(MockMvcResultMatchers.view().name("readingList"))
     *                 .andExpect(MockMvcResultMatchers.model().attributeExists("books"))
     *                 .andExpect(MockMvcResultMatchers.model().attribute("books",
     *                 Matchers.is(Matchers.empty())));
     *     }
     *
     * 如你所见，这里在这个测试方法里使用了很多静态方法，包括 Spring 的 MockMvcRequestBuilders 和
     * MockMvcResultMatchers 里的静态方法，还有 Hamcrest 库的 Matchers 里的静态方法。
     *
     * 可以先添加一些静态 import，这样代码看起来更清爽一些：
     *
     *     @Test
     *     public void homePageWithImport() throws Exception {
     *         mockMvc.perform(get("/readingList"))
     *                 .andExpect(status().isOk())
     *                 .andExpect(view().name("readingList"))
     *                 .andExpect(model().attributeExists("books"))
     *                 .andExpect(model().attribute("books", is(empty())));
     *     }
     *
     * 现在这个测试方法读起来就很自然了。首先向 /readingList 发起一个 GET 请求，接下来希望该请求处理成功（isOk()
     * 会判断 HTTP 200 响应码），并且视图的逻辑名称为 readingList。测试还要断定模型包含一个名为 books 的属性，
     * 该属性是一个空集合。所有的断言都很直观。
     *
     * 值得一提的是，此处完全不需要将应用程序部署到 Web 服务器上，它是运行在模拟的 Spring MVC 中的，刚好能通过
     * MockMvc 实例处理这里给它的 HTTP 请求。
     *
     * 下面再来看一个测试方法，这次会更有趣，实际发送一个 HTTP POST 请求提交一本新书。这里应该期待 POST 请求处
     * 理后重定向回 /readingList，模型将包含新添加的图书。如下代码演示了如何通过 Spring 的 Mock MVC 来实现这
     * 个测试。
     *
     *     @Test
     *     public void postBook() throws Exception {
     *         mockMvc.perform(post("/readingList")
     *                 .contentType(APPLICATION_FORM_URLENCODED)
     *                 .param("title", "BOOK TITLE")
     *                 .param("author", "BOOK AUTHOR")
     *                 .param("isbn", "1234567890")
     *                 .param("description", "DESCRIPTION"))
     *                 .andExpect(status().is3xxRedirection())
     *                 .andExpect(header().string("Location", "/readingList"));
     *
     *         Book expectedBook = new Book();
     *         expectedBook.setId(1L);
     *         expectedBook.setReader("craig");
     *         expectedBook.setTitle("BOOK TITLE");
     *         expectedBook.setAuthor("BOOK AUTHOR");
     *         expectedBook.setIsbn("1234567890");
     *         expectedBook.setDescription("DESCRIPTION");
     *
     *         mockMvc.perform(get("/readingList"))
     *                 .andExpect(status().isOk())
     *                 .andExpect(view().name("readingList"))
     *                 .andExpect(model().attributeExists("books"))
     *                 .andExpect(model().attribute("books", hasSize(1)))
     *                 .andExpect(model().attribute("books",
     *                         contains(samePropertyValuesAs(expectedBook))));
     *     }
     *
     * 很明显，这段代码里的测试更加复杂，实际上是两个测试放在一个方法里。第一部分提交图书并检查了请求的结果，第二
     * 部分执行了一次对主页的 GET 请求，检查新建的图书是否在模型中。
     *
     * 在提交图书时，必须确保内容类型（通过 MediaType.APPLICATION_FORM_URLENCODED）设置为 application
     * /x-www-form-urlencoded，这才是运行应用程序时浏览器会发送的内容类型。随后，要用 MockMvcRequestBuilders
     * 的 param 方法设置表单域，模拟要提交的表单。一旦请求执行，要检查响应是否是一个到 /readingList 的重定向。
     *
     * 假定以上测试都通过，进入第二部分。首先设置一个 Book 对象，包含想要的值。这里用这个对象和首页获取的模型的
     * 值进行对比。
     *
     * 随后要对 /readingList 发起一个 GET 请求，大部分内容和之前测试主页时一样，只是之前模型中有一个空集合，
     * 而现在有一个集合项。这里要检查它的内容是否和创建的 expectedBook 一致。如此一来，这里的控制器看来保存
     * 了发送给它的图书，完成了工作。
     *
     * 至此，这些测试验证了一个未经保护的应用程序，但如果想要测试一个安全加固过的应用程序，又该怎么办？
     *
     *
     *
     * 2、测试 Web 安全
     *
     * Spring Security 能让你非常方便地测试安全加固后的 Web 应用程序。为了利用这点优势，你必须在项目里添加
     * Spring Security 的测试模块。
     *
     * 如果你用的是 Maven，可以添加以下 <dependency>：
     *
     * <dependency>
     *       <groupId>org.springframework.security</groupId>
     *       <artifactId>spring-security-test</artifactId>
     *       <scope>test</scope>
     * </dependency>
     *
     * 应用程序的 Classpath 里有了 Spring Security 的测试模块之后，只需在创建 MockMvc 实例时运用
     * Spring Security 的配置器。
     *
     *     @Before
     *     public void setupMockMvc() {
     *         mockMvc = MockMvcBuilders
     *                 .webAppContextSetup(webContext)
     *                 .apply(springSecurity())
     *                 .build();
     *     }
     *
     * springSecurity() 方法返回了一个 Mock MVC 配置器，为 Mock MVC 开启了 Spring Security 支持。只需
     * 像上面这样运用就行了，Spring Security 会介入 MockMvc 上执行的每个请求。具体的安全配置取决于你如何配
     * 置 Spring Security（或者 Spring Boot 如何自动配置 Spring Security）。
     *
     * PS：springSecurity() 是 SecurityMockMvcConfigurers 的一个静态方法，考虑到可读性，这里已经将其静
     * 态导入。
     *
     * 开启了 Spring Security 之后，在请求主页的时候，便不能只期待 HTTP 200 响应。如果请求未经身份验证，应
     * 该期待重定向到登录页面：
     *
     *     @Test
     *     public void homePage_unauthenticatedUser() throws Exception {
     *         mockMvc.perform(get("/"))
     *                 .andExpect(status().is3xxRedirection())
     *                 .andExpect(header().string("Location", "http://localhost/login"));
     *     }
     *
     * 不过，经过身份验证的请求又该如何发起呢？Spring Security 提供了两个注解。
     * （1）@WithMockUser：加载安全上下文，其中包含一个 UserDetails，使用了给定的用户名、密码和授权。
     * （2）@WithUserDetails：根据给定的用户名查找 UserDetails 对象，加载安全上下文。
     *
     * 在这两种情况下，Spring Security 的安全上下文都会加载一个 UserDetails 对象，添加了该注解的测试方法在
     * 运行过程中都会使用该对象。@WithMockUser 注解是两者里比较基础的那个，允许显式声明一个 UserDetails，并
     * 加载到安全上下文。
     *
     *     @Test
     *     @WithMockUser(username="craig", password="password", roles="READER")
     *     public void homePage_authenticatedUser() throws Exception {
     *          ...
     *     }
     *
     * 如你所见，@WithMockUser 绕过了对 UserDetails 对象的正常查询，用给定的值创建了一个 UserDetails 对象
     * 取而代之。在简单的测试里，这就够用了。但这里的测试需要 Reader（实现了 UserDetails）而非 @WithMockUser
     * 创建的通用 UserDetails。为此，需要 @WithUserDetails。
     *
     * @WithUserDetails 注解使用事先配置好的 UserDetailsService 来加载 UserDetails 对象。这里配置了一个
     * UserDetailsService Bean，它会根据给定的用户名查找并返回一个 Reader 对象。所以这里要为测试方法添加
     * @WithUserDetails 注解，如下所示。
     *
     *     @Test
     *     @WithUserDetails("craig")
     *     public void homePage_authenticatedUser() throws Exception {
     *         Reader expectedReader = new Reader();
     *         expectedReader.setUsername("craig");
     *         expectedReader.setPassword("password");
     *         expectedReader.setFullname("Craig Walls");
     *
     *         mockMvc.perform(get("/"))
     *                 .andExpect(status().isOk())
     *                 .andExpect(view().name("readingList"))
     *                 .andExpect(model().attribute("reader",
     *                         samePropertyValuesAs(expectedReader)))
     *                 .andExpect(model().attribute("books", hasSize(0)))
     *                 .andExpect(model().attribute("amazonID", "habuma-20"));
     *     }
     *
     * 这里通过 @WithUserDetails 注解声明要在测试方法执行过程中向安全上下文里加载 craig 用户。Reader 会放入
     * 模型，该测试方法先创建了一个期望的 Reader 对象，后续可以用来进行比较。随后 GET 请求发起，也有了针对视图
     * 名和模型内容的断言，其中包括名为 reader 的模型属性。
     *
     * 同样，此处没有启动 Servlet 容器来运行这些测试，Spring 的 Mock MVC 取代了实际的 Servlet 容器。这样做
     * 的好处是测试方法运行相对较快。因为不需要等待服务器启动，而且不需要打开 Web 浏览器发送表单，所以测试比较
     * 简单快捷。
     *
     * 不过，这并不是一个完整的测试。它比直接调用控制器方法要好，但它并没有真的在 Web 浏览器里执行应用程序，验证
     * 呈现出的视图。为此，需要启动一个真正的 Web 服务器，用真实浏览器来访问它。后续来看看 Spring Boot 如何启
     * 动一个真实的 Web 服务器来帮助测试。
     */
    public static void main(String[] args) {

    }

}
