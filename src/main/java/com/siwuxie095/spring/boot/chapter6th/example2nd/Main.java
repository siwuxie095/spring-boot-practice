package com.siwuxie095.spring.boot.chapter6th.example2nd;

/**
 * @author Jiajing Li
 * @date 2021-04-20 22:26:52
 */
@SuppressWarnings("all")
public class Main {

    /**
     * 使用 GORM 进行数据持久化
     *
     * Grails 里最让人着迷的恐怕就是 GORM 了。GORM 将数据库相关工作简化到和声明要持久化的实体一样容易。例如，
     * 如下代码演示了阅读列表里的 Book 该如何用 Groovy 写成 GORM 实体。
     *
     * @Entity
     * class Book {
     *
     *   Reader reader
     *   String isbn
     *   String title
     *   String author
     *   String description
     *
     * }
     *
     * 就和 Book 的 Java 版本一样，这个类里有很多描述图书的属性。但又与 Java 版本不一样，这里没有分号、public
     * 或 private 修饰符、setter 和 getter 方法或其他 Java 中常见的代码噪声。是 Grails 的 @Entity 注解让
     * 这个类变成了 GORM 实例。这个简单的实体可干了不少事，包括将对象映射到数据库，为 Book 添加持久化方法，通过
     * 这些方法可以存取图书。
     *
     * 要在 Spring Boot 项目里使用 GORM，必须在项目里添加 GORM 依赖。在 Maven 中，<dependency> 看起来是这
     * 样的：
     *
     * <dependency>
     *   <groupId>org.grails</groupId>
     *   <artifactId>gorm-hibernate4-spring-boot</artifactId>
     *   <version>1.1.0.RELEASE</version>
     * </dependency>
     *
     * 这个库自带了一些 Spring Boot 自动配置，会自动配置所有支持 GORM 所需的 Bean。你只管写代码就好了。
     *
     *
     * PS：GORM 在 Spring Boot 里的另一个选择：
     * 正如其名，gorm-hibernate4-spring-boot 是通过 Hibernate 开启 GORM 数据持久化的。对很多项目而言，这
     * 很好。但如果你想用 MongoDB，那你会对 Spring Boot 里的 MongoDB GORM 支持很感兴趣。
     *
     * 它的 Maven 依赖是这样的：
     *
     * <dependency>
     *   <groupId>org.grails</groupId>
     *   <artifactId>gorm-mongodb-spring-boot</artifactId>
     *   <version>1.1.0.RELEASE</version>
     * </dependency>
     *
     *
     * GORM 的工作原理要求实体类必须用 Groovy 来编写。已经写了一个 Book 实体，下面再写一个 Reader 实体，如下
     * 所示。
     *
     * @Entity
     * class Reader implements UserDetails {
     *
     *     String username
     *     String fullname
     *     String password
     *
     *     Collection<? extends GrantedAuthority> getAuthorities() {
     *         Arrays.asList(new SimpleGrantedAuthority("ROLE_READER"))
     *     }
     *
     *     boolean isAccountNonExpired() {
     *         true
     *     }
     *
     *     boolean isAccountNonLocked() {
     *         true
     *     }
     *
     *     boolean isCredentialsNonExpired() {
     *         true
     *     }
     *
     *     boolean isEnabled() {
     *         true
     *     }
     *
     * }
     *
     * 现在，阅读列表应用程序里有了两个 GORM 实体，需要重写剩下的应用程序来使用这两个实体。因为使用 Groovy 是
     * 如此令人愉悦（和 Grails 十分相似），所以其他类也会用 Groovy 来编写。
     *
     * 首先是 ReadingListController，如下所示。
     *
     * @Controller
     * @RequestMapping("/")
     * @ConfigurationProperties("amazon")
     * class ReadingListController {
     *
     *     @Autowired
     *     AmazonConfig amazonConfig
     *
     *     @ExceptionHandler(value=RuntimeException.class)
     *     @ResponseStatus(value=HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
     *     def error() {
     *         "error"
     *     }
     *
     *     @RequestMapping(method=RequestMethod.GET)
     *     def readersBooks(Reader reader, Model model) {
     *         List<Book> readingList = Book.findAllByReader(reader)
     *         model.addAttribute("reader", reader)
     *
     *         if (readingList) {
     *             model.addAttribute("books", readingList)
     *             model.addAttribute("amazonID", amazonConfig.getAssociateId())
     *         }
     *         "readingList"
     *     }
     *
     *     @RequestMapping(method=RequestMethod.POST)
     *     def addToReadingList(Reader reader, Book book) {
     *
     *         println("SAVING: " + book)
     *
     *         Book.withTransaction {
     *             book.setReader(reader)
     *             book.save()
     *         }
     *         "redirect:/"
     *     }
     *
     * }
     *
     * 这个版本的 ReadingListController 和之前的相比，最明显的不同之处在于，它是用 Groovy 写的，没有 Java
     * 的那些代码噪声。最重要的不同之处在于，无需再注入 ReadingListRepository，它直接通过 Book 类型持久化。
     *
     * 在 readersBooks() 方法里，它调用了 Book 的 findAllByReader() 静态方法，传入了指定的读者信息。虽然
     * Book 里没有提供 findAllByReader() 方法，但这段代码仍然可以执行，因为 GORM 会实现这个方法。
     *
     * 与之类似，addToReadingList() 方法使用了静态方法 withTransaction() 和实例方法 save()。这两个方法也
     * 是 GORM 提供的，用于将 Book 保存到数据库里。
     *
     * 这里所要做的就是声明一些属性，在 Book 上添加 @Entity 注解。这笔买卖很划算。
     *
     * SecurityConfig 也要做类似的修改，通过 GORM 而非 ReadingListRepository 来获取 Reader。如下就是新
     * 的 SecurityConfig。
     *
     * @Configuration
     * class SecurityConfig extends WebSecurityConfigurerAdapter {
     *
     *     void configure(HttpSecurity http) throws Exception {
     *         http
     *                 .authorizeRequests()
     *                 .antMatchers("/").access("hasRole('READER')")
     *                 .antMatchers("/**").permitAll()
     *                 .and()
     *                 .formLogin()
     *                 .loginPage("/login")
     *                 .failureUrl("/login?error=true")
     *     }
     *
     *     void configure(AuthenticationManagerBuilder auth) throws Exception {
     *         auth
     *                 .userDetailsService(
     *                 { username -> Reader.findByUsername(username) }
     *                         as UserDetailsService)
     *     }
     *
     * }
     *
     * 除了用 Groovy 重写，SecurityConfig 里最明显的变化无疑就是第二个 configure() 方法。如你所见，它使用
     * 了一个闭包（UserDetailsService 的实现类），其中调用静态方法 findByUsername() 来查找 Reader，这个
     * 功能是 GORM 提供的。
     *
     * 你也许会好奇 —— 在这个 GORM 版本的应用程序里，ReadingListRepository 变成什么了？GORM 处理了所有的
     * 持久化工作，这里已经不再需要 ReadingListRepository 了，它的实现也都不需要了。你会同意代码越少越好这
     * 个观点。
     *
     * 此刻，你可以通过各种运行 Spring Boot 应用程序的方法来启动阅读列表应用程序。启动后，应用程序应该能像从
     * 前一样工作。但是背后的持久化机制已经被改变了。
     *
     * 除了 GORM，Grails 应用程序通常还会用 Groovy Server Pages 将模型数据以 HTML 的方式呈现给浏览器。后
     * 续应用程序的 Grails 化还会继续。会把 Thymeleaf 替换为等价的 GSP。
     */
    public static void main(String[] args) {

    }

}
