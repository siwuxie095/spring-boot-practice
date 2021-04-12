package com.siwuxie095.spring.boot.chapter3rd.example2nd;

/**
 * @author Jiajing Li
 * @date 2021-04-12 08:20:41
 */
@SuppressWarnings("all")
public class Main {

    /**
     * 覆盖 Spring Boot 自动配置
     *
     * 一般来说，如果不用配置就能得到和显式配置一样的结果，那么不写配置是最直接的选择。既然如此，那干嘛还要多做
     * 额外的工作呢？如果不用编写和维护额外的配置代码也行，那何必还要它们呢？
     *
     * 大多数情况下，自动配置的 Bean 刚好能满足你的需要，不需要去覆盖它们。但某些情况下，Spring Boot 在自动
     * 配置时还不能很好地进行推断。
     *
     * 这里有个不错的例子：当你在应用程序里添加安全特性时，自动配置做得还不够好。安全配置并不是放之四海而皆准的，
     * 围绕应用程序安全有很多决策要做，Spring Boot 不能替你做决定。虽然 Spring Boot 为安全提供了一些基本的
     * 自动配置，但是你还是需要自己覆盖一些配置以满足特定的安全要求。
     *
     * 想知道如何用显式的配置来覆盖自动配置，这里先从为阅读列表应用程序添加 Spring Security 入手。在了解自动
     * 配置提供了什么之后，再来覆盖基础的安全配置，以满足特定的场景需求。
     *
     *
     *
     * 1、保护应用程序
     *
     * Spring Boot 自动配置让应用程序的安全工作变得易如反掌，你要做的只是添加 Security 起步依赖。以 Maven
     * 为例，应添加如下依赖：
     *
     * <dependency>
     *     <groupId>org.springframework.boot</groupId>
     *     <artifactId>spring-boot-starter-security</artifactId>
     * </dependency>
     *
     * 这样就搞定了！重新构建应用程序后运行即可，现在这就是一个安全的 Web 应用程序了！Security 起步依赖在应用
     * 程序的 Classpath 里添加了 Spring Security（和其他一些东西）。Classpath 里有 Spring Security 后，
     * 自动配置就能介入其中创建一个基本的 Spring Security 配置。
     *
     * 试着在浏览器里打开该应用程序，你马上就会看到 HTTP 基础身份验证对话框。此处的用户名是 user，密码就有点
     * 麻烦了。密码是在应用程序每次运行时随机生成后写入日志的，你需要查找日志消息（默认写入标准输出），找到此类
     * 内容：
     *
     * Using default security password: d4fd864d-befa-431d-a94e-f3ee48130798
     *
     * 虽然不能肯定，但这个特定的安全配置并不是你的理想选择。首先，HTTP 基础身份验证对话框有点粗糙，对用户并不
     * 友好。而且，你一般肯定不会开发这种只有一个用户的应用程序，而且他还要从日志文件里找到自己的密码。因此，你
     * 会希望修改 Spring Security 的一些配置，至少要有一个好看一些的登录页，还要有一个基于数据库或 LDAP
     * （Lightweight Directory Access Protocol）用户存储的身份验证服务。
     *
     * 下面看看如何写出 Spring Security 配置，覆盖自动配置的安全设置吧。
     *
     *
     *
     * 2、创建自定义的安全配置
     *
     * 覆盖自动配置很简单，就当自动配置不存在，直接显式地写一段配置。这段显式配置的形式不限，Spring 支持的 XML
     * 和 Groovy 形式配置都可以。
     *
     * 在编写显式配置时，会专注于 Java 形式的配置。在 Spring Security 的场景下，这意味着写一个扩展了
     * WebSecurityConfigurerAdapter 的配置类。如下代码中的 SecurityConfig 就是所需要的东西。
     *
     * @Configuration
     * @EnableWebSecurity
     * public class SecurityConfig extends WebSecurityConfigurerAdapter {
     *
     *     @Autowired
     *     private ReaderRepository readerRepository;
     *
     *     @Override
     *     protected void configure(HttpSecurity http) throws Exception {
     *         http
     *                 .authorizeRequests()
     *                 .antMatchers("/").access("hasRole('READER')")
     *                 .antMatchers("/**").permitAll()
     *                 .and()
     *                 .formLogin()
     *                 .loginPage("/login")
     *                 .failureUrl("/login?error=true");
     *     }
     *
     *     @Override
     *     protected void configure(
     *             AuthenticationManagerBuilder auth) throws Exception {
     *         auth
     *                 .userDetailsService(new UserDetailsService() {
     *                     @Override
     *                     public UserDetails loadUserByUsername(String username)
     *                             throws UsernameNotFoundException {
     *                         return readerRepository.findOne(username);
     *                     }
     *                 });
     *     }
     *
     * }
     *
     * SecurityConfig 是个非常基础的 Spring Security 配置，尽管如此，它还是完成了不少安全定制工作。通过这
     * 个自定义的安全配置类，让 Spring Boot 跳过了安全自动配置，转而使用这里的安全配置。
     *
     * 扩展了 WebSecurityConfigurerAdapter 的配置类可以覆盖两个不同的 configure() 方 法。在
     * SecurityConfig 里，第一个 configure() 方法指明，"/"（ReadingListController 的方法
     * 映射到了该路径）的请求只有经过身份认证且拥有 READER 角色的用户才能访问。其他的所有请求路径
     * 向所有用户开放了访问权限。这里还将登录页和登录失败页（带有一个 error 属性）指定到了 /login。
     *
     * Spring Security 为身份认证提供了众多选项，后端可以是 JDBC（Java Database Connectivity）、 LDAP
     * 和内存用户存储。在这个应用程序中，会通过 JPA 用数据库来存储用户信息。第二个 configure() 方法设置了一
     * 个自定义的 UserDetailsService，这个服务可以是任意实现了 UserDetailsService 的类，用于查找指定用户
     * 名的用户。这里提供了一个匿名内部类实现，简单地调用了注入 ReaderRepository（这是一个Spring Data JPA
     * 仓库接口）的 findOne() 方法。
     *
     * public interface ReaderRepository extends JpaRepository<Reader, String> {
     *
     * }
     *
     * 和 ReadingListRepository 类似，你无需自己实现 ReaderRepository。这是因为它扩展了 JpaRepository，
     * Spring Data JPA 会在运行时自动创建它的实现。这为你提供了 18 个操作 Reader 实体的方法。
     *
     * 说到 Reader 实体，Reader 类就是最后一块拼图了，它就是一个简单的 JPA 实体，其中有几个字段用来存储用户
     * 名、密码和用户全名。
     *
     * @Entity
     * public class Reader implements UserDetails {
     *
     *     private static final long serialVersionUID = 1L;
     *
     *     @Id
     *     private String username;
     *
     *     private String fullname;
     *     private String password;
     *
     *     @Override
     *     public String getUsername() {
     *         return username;
     *     }
     *
     *     public void setUsername(String username) {
     *         this.username = username;
     *     }
     *
     *     public String getFullname() {
     *         return fullname;
     *     }
     *
     *     public void setFullname(String fullname) {
     *         this.fullname = fullname;
     *     }
     *
     *     @Override
     *     public String getPassword() {
     *         return password;
     *     }
     *
     *     public void setPassword(String password) {
     *         this.password = password;
     *     }
     *
     *     @Override
     *     public Collection<? extends GrantedAuthority> getAuthorities() {
     *         return Arrays.asList(new SimpleGrantedAuthority("ROLE_READER"));
     *     }
     *
     *     @Override
     *     public boolean isAccountNonExpired() {
     *         return true;
     *     }
     *
     *     @Override
     *     public boolean isAccountNonLocked() {
     *         return true;
     *     }
     *
     *     @Override
     *     public boolean isCredentialsNonExpired() {
     *         return true;
     *     }
     *
     *     @Override
     *     public boolean isEnabled() {
     *         return true;
     *     }
     *
     * }
     *
     * 如你所见，Reader 用了 @Entity 注解，所以这是一个 JPA 实体。此外，它的 username 字段上有 @Id 注解，
     * 表明这是实体的 ID。这个选择无可厚非，因为 username 应该能唯一标识一个 Reader。
     *
     * 你应该还注意到 Reader 实现了 UserDetails 接口以及其中的方法，这样 Reader 就能代表 Spring Security
     * 里的用户了。getAuthorities() 方法被覆盖过了，始终会为用户授予 READER 权限。isAccountNonExpired()、
     * isAccountNonLocked()、isCredentialsNonExpired() 和 isEnabled() 方法都返回true，这样读者账户就
     * 不会过期，不会被锁定，也不会被撤销。
     *
     * 重新构建并重启应用程序后，你应该就能以读者身份登录应用程序了。
     *
     * PS：保持简单：在一个大型应用程序里，赋予用户的授权本身也可能是实体，它们被维护在独立的数据表里。同样，表
     * 示一个账户是否为非过期、非锁定且可用的布尔值也是数据库里的字段。但是，出于演示考虑，这里决定让这些细节保
     * 持简单，以免分散注意力，影响正在讨论的话题 —— 覆盖 Spring Boot 自动配置。
     *
     * 在安全配置方面，其实还能做更多事情，但此刻这样就足够了，上面的例子足以演示如何覆盖 Spring Boot 提供的
     * 安全自动配置。
     *
     * 再重申一次，想要覆盖 Spring Boot 的自动配置，你所要做的仅仅是编写一个显式的配置。Spring Boot 会发现你
     * 的配置，随后降低自动配置的优先级，以你的配置为准。想弄明白这是如何实现的，下面就来揭开 Spring Boot 自动
     * 配置的神秘面纱，看看它是如何运作的，以及它是怎么允许自己被覆盖的。
     *
     *
     *
     * 3、掀开自动配置的神秘面纱
     *
     * Spring Boot 自动配置自带了很多配置类，每一个都能运用在你的应用程序里。它们都使用了 Spring 4.0 的条件化
     * 配置，可以在运行时判断这个配置是该被运用，还是该被忽略。
     *
     * 大部分情况下，@ConditionalOnMissingBean 注解是覆盖自动配置的关键。Spring Boot 的
     * DataSourceAutoConfiguration 中定义的 JdbcTemplate Bean 就是一个非常简单的例子，
     * 演示了 @ConditionalOnMissingBean 如何工作：
     *
     *     @Bean
     *     @ConditionalOnMissingBean(JdbcOperations.class)
     *     public JdbcTemplate jdbcTemplate() {
     *       return new JdbcTemplate(this.dataSource);
     *     }
     *
     * jdbcTemplate() 方法上添加了 @Bean 注解，在需要时可以配置出一个 JdbcTemplate Bean。但它上面还加了
     * @ConditionalOnMissingBean 注解，要求当前不存在 JdbcOperations 类型（JdbcTemplate 实现了该接口）
     * 的 Bean 时才生效。如果当前已经有一个 JdbcOperations Bean 了，条件即不满足，不会执行 jdbcTemplate()
     * 方法。
     *
     * 什么情况下会存在一个 JdbcOperations Bean 呢？Spring Boot 的设计是加载应用级配置，随后再考虑自动配置
     * 类。因此，如果你已经配置了一个 JdbcTemplate Bean，那么在执行自动配置时就已经存在一个 JdbcOperations
     * 类型的 Bean 了，于是忽略自动配置的 JdbcTemplate Bean。
     *
     * 关于 Spring Security，自动配置会考虑几个配置类。在这里讨论每个配置类的细节是不切实际的，但覆盖 Spring
     * Boot 自动配置的安全配置时，最重要的一个类是 SpringBootWebSecurityConfiguration。以下是其中的一个代
     * 码片段：
     *
     * @Configuration
     * @EnableConfigurationProperties
     * @ConditionalOnClass({ EnableWebSecurity.class })
     * @ConditionalOnMissingBean(WebSecurityConfiguration.class)
     * @ConditionalOnWebApplication
     * public class SpringBootWebSecurityConfiguration {
     *      ...
     * }
     *
     * 如你所见，SpringBootWebSecurityConfiguration 上加了好几个注解。看到 @ConditionalOnClass 注解后，
     * 你就应该知道 Classpath 里必须要有 @EnableWebSecurity 注解。@ConditionalOnWebApplication 说明
     * 这必须是个 Web 应用程序。@ConditionalOnMissingBean 注解才是这里的安全配置类代替
     * SpringBootWebSecurityConfiguration 的关键所在。
     *
     * @ConditionalOnMissingBean 注解要求当下没有 WebSecurityConfiguration 类型的 Bean。虽然表面上并
     * 没有这么一个 Bean，但通过在 SecurityConfig 上添加 @EnableWebSecurity 注解，实际上间接创建了一个
     * WebSecurityConfiguration Bean。所以在自动配置时，这个 Bean 就已经存在了，
     * @ConditionalOnMissingBean 条件不成立，SpringBootWebSecurityConfiguration 提供的配置就被跳过了。
     *
     * 虽然 Spring Boot 的自动配置和 @ConditionalOnMissingBean 让你能显式地覆盖那些可以自动配置的 Bean，
     * 但并不是每次都要做到这种程度。后续将会介绍怎么通过设置几个简单的配置属性调整自动配置组件吧。
     */
    public static void main(String[] args) {

    }

}
