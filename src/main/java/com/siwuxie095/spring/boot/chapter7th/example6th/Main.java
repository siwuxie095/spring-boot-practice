package com.siwuxie095.spring.boot.chapter7th.example6th;

/**
 * @author Jiajing Li
 * @date 2021-04-28 22:49:29
 */
@SuppressWarnings("all")
public class Main {

    /**
     * 保护 Actuator 端点
     *
     * 很多 Actuator 端点发布的信息都可能涉及敏感数据，还有一些端点（比如/shutdown），非常危险，可以用来
     * 关闭应用程序。因此，保护这些端点尤为重要，能访问它们的只能是那些经过授权的客户端。
     *
     * 实际上，Actuator 的端点保护可以用和其他 URL 路径一样的方式 —— 使用 Spring Security。在 Spring
     * Boot 应用程序中，这意味着将 Security 起步依赖作为构建依赖加入，然后让安全相关的自动配置来保护应用
     * 程序，其中当然也包括了 Actuator 端点。
     *
     * 之前看到了默认安全自动配置如何把所有 URL 路径保护起来，要求 HTTP 基本身份验证，用户名是 user，密码
     * 在启动时随机生成并写到日志文件里去。这不是所希望的 Actuator 保护方式。
     *
     * 这里已经添加了一些自定义安全配置，仅限带有 READER 权限的授权用访问根URL路径（/）。要保护 Actuator
     * 的端点，需要对 SecurityConfig.java 的 configure() 方法做些修改。
     *
     *     @Override
     *     protected void configure(HttpSecurity http) throws Exception {
     *         http
     *                 .authorizeRequests()
     *                 .antMatchers("/").access("hasRole('READER')")
     *                 .antMatchers("/shutdown").access("hasRole('ADMIN')")
     *                 .antMatchers("/**").permitAll()
     *                 .and()
     *                 .formLogin()
     *                 .loginPage("/login")
     *                 .failureUrl("/login?error=true");
     *     }
     *
     * 现在要访问 /shutdown 端点，必须用一个带 ADMIN 权限的用户来做身份验证。
     *
     * 然而，之前自定义的 UserDetailsService 只对通过 ReaderRepository 加载的用户赋予 READER 权限。
     * 因此，你需要创建一个更聪明的 UserDetailsService 实现，对某些用户赋予 ADMIN 权限。你可以配置一
     * 个额外的身份验证实现，比如如下代码里的内存实现。
     *
     *     @Override
     *     protected void configure(
     *             AuthenticationManagerBuilder auth) throws Exception {
     *         auth
     *                 .userDetailsService(new UserDetailsService() {
     *                     @Override
     *                     public UserDetails loadUserByUsername(String username)
     *                             throws UsernameNotFoundException {
     *                         UserDetails user = readerRepository.findOne(username);
     *                         if (user != null) {
     *                             return user;
     *                         }
     *                         throw new UsernameNotFoundException("User '" + username + "' not found.");
     *                     }
     *                 })
     *                 .and()
     *                 .inMemoryAuthentication()
     *                 .withUser("manager").password("s3cr3t").roles("ADMIN", "READER");
     *     }
     *
     * 新加的内存身份验证中，用户名定义为 admin，密码为 s3cr3t，同时被授予 ADMIN 和 READER 权限。
     *
     * 现在，除了那些拥有 ADMIN 权限的用户，谁都无法访问 /shutdown 端点。但 Actuator 的其他端点呢？假
     * 设你只想让 ADMIN 的用户访问它们（像 /shutdown 一样），可以在调用 antMatchers() 时列出这些 URL。
     * 例如，要保护 /metrics、/confiprops 和 /shutdown，可以像这样调用 antMatchers()：
     *
     * .antMatchers("/shutdown", "/metrics", "/configprops")
     *                           .access("hasRole('ADMIN')")
     *
     * 虽然这么做能奏效，但也只适用于少数 Actuator 端点的保护。如果要保护全部 Actuator 端点，这种做法就
     * 不太方便了。
     *
     * 比起在调用 antMatchers() 方法时显式地列出所有的 Actuator 端点，用通配符在一个简单的 Ant 风格表
     * 达式里匹配全部的 Actuator 端点更容易。但是，这么做也小有点挑战，因为不同的端点路径之间没有什么共同
     * 点，也不能在 /** 上运用 ADMIN 权限。这样一来，除了根路径（/）之外，什么要有 ADMIN 权限。
     *
     * 为此，可以通过 management.context-path 属性设置端点的上下文路径。默认情况下，这个属性是空的，所
     * 以 Actuator 的端点路径都是相对于根路径的。在 application.yaml 里增加如下内容，可以让这些端点都
     * 带上 /mgmt 前缀。
     *
     * management:
     *   context-path: /mgmt
     *
     * 你也可以在 application.properties 里做类似的事情：
     *
     * management.context-path=/mgmt
     *
     * 将 management.context-path 设置为 /mgmt 后，所有的 Actuator 端点都会与 /mgmt 路径相关。例如，
     * /metrics 端点的 URL 会变为 /mgmt/metrics。
     *
     * 有了这个新的路径，就有了公共的前缀，在为 Actuator 端点赋予 ADMIN 权限限制时就能借助这个公共前缀：
     *
     * .antMatchers("/mgmt/**").access("hasRole('ADMIN')")
     *
     * 现在所有以 /mgmt 开头的请求（包含了所有的 Actuator 端点），都只让授予了 ADMIN 权限的认证用户访问。
     */
    public static void main(String[] args) {

    }

}
