package com.siwuxie095.spring.boot.chapter4th.example2nd;

/**
 * @author Jiajing Li
 * @date 2021-04-14 21:23:20
 */
@SuppressWarnings("all")
public class Main {

    /**
     * 集成测试自动配置
     *
     * Spring Framework 的核心工作是将所有组件编织在一起，构成一个应用程序。整个过程就是读取配置说明（可以是
     * XML、基于 Java 的配置、基于 Groovy 的配置或其他类型的配置），在应用程序上下文里初始化 Bean，将 Bean
     * 注入依赖它们的其他 Bean 中。
     *
     * 对 Spring 应用程序进行集成测试时，让 Spring 遵照生产环境来组装测试目标 Bean 是非常重要的一点。当然，
     * 你也可以手动初始化组件，并将它们注入其他组件，但对那些大型应用程序来说，这是项费时费力的工作。而且，
     * Spring 提供了额外的辅助功能，比如组件扫描、自动织入和声明性切面（缓存、事务和安全等等）。你要把这些活都
     * 干了，基本也就是把 Spring 再造了一次，最好还是让 Spring 替你把重活都做了吧，哪怕是在集成测试里。
     *
     * Spring 自 1.1.1 版就向集成测试提供了极佳的支持。自 Spring 2.5 开始，集成测试支持的形式就变成了
     * SpringJUnit4ClassRunner。这是一个 JUnit 类运行器，会为 JUnit 测试加载 Spring 应用程序上下文，
     * 并为测试类自动织入所需的 Bean。
     *
     * 举例来说，看一下如下代码，这是一个非常基本的 Spring 集成测试。
     *
     * @RunWith(SpringJUnit4ClassRunner.class)
     * @ContextConfiguration(classes = AddressBookConfiguration.class)
     * public class AddressServiceTests {
     *
     *     @Autowired
     *     private AddressService addressService;
     *
     *     @Test
     *     public void testService() {
     *         Address address = addressService.findByLastName("Sheman");
     *         assertEquals("P", address.getFirstName());
     *         assertEquals("Sherman", address.getLastName());
     *         assertEquals("42 Wallaby Way", address.getAdressLine1());
     *         assertEquals("Sydney", address.getCity());
     *         assertEquals("New South Wales", address.getState());
     *         assertEquals("2000", address.getPostCode());
     *     }
     *
     * }
     *
     * 如你所见，AddressServiceTests 上加注了 @RunWith 和 @ContextConfiguration 注解。@RunWith 的参
     * 数是 SpringJUnit4ClassRunner.class，开启了 Spring 集成测试支持。与此同时，@ContextConfiguration
     * 指定了如何加载应用程序上下文。此处让它加载 AddressBookConfiguration 里配置的 Spring 应用程序上下文。
     *
     * PS：在 Spring 4.2 里，你可以选择基于规则的 SpringClassRule 和 SpringMethodRule 来代替
     * SpringJUnit4ClassRunner。
     *
     * 除了加载应用程序上下文，SpringJUnit4ClassRunner 还能通过自动织入从应用程序上下文里向测试本身注入 Bean。
     * 因为这是一个针对 AddressService Bean 的测试，所以需要将它注入测试。最后，testService() 方法调用地址
     * 服务并验证了结果。
     *
     * 虽然 @ContextConfiguration 在加载 Spring 应用程序上下文的过程中做了很多事情，但它没能加载完整的
     * Spring Boot。Spring Boot 应用程序最终是由 SpringApplication 加载的。它可以显式加载，在这里也
     * 可以使用 SpringBootServletInitializer。SpringApplication 不仅加载应用程序上下文，还会开启日志、
     * 加载外部属性（application.properties 或 application.yml），以及其他 Spring Boot 特性。用
     * @ContextConfiguration 则得不到这些特性。
     *
     * 要在集成测试里获得这些特性，可以把 @ContextConfiguration 注解替换为 Spring Boot 的
     * @SpringApplicationConfiguration 注解：
     *
     * @RunWith(SpringJUnit4ClassRunner.class)
     * @SpringApplicationConfiguration(classes = AddressBookConfiguration.class)
     * public class AddressServiceTests {
     *
     *      ...
     *
     * }
     *
     * @SpringApplicationConfiguration 的用法和 @ContextConfiguration 大致相同，但也有不同的地方，
     * @SpringApplicationConfiguration 加载 Spring 应用程序上下文的方式同 SpringApplication 相同，
     * 处理方式和生产应用程序中的情况相同。这包括加载外部属性和 Spring Boot 日志。
     *
     * 这里有充分的理由说，在大多数情况下，为 Spring Boot 应用程序编写测试时应该用
     * @SpringApplicationConfiguration 代替 @ContextConfiguration。
     *
     * 在这里，当然也会用 @SpringApplicationConfiguration 来为 Spring Boot 应用程序（包括那些面向前端
     * 的应用程序）编写测试。
     */
    public static void main(String[] args) {

    }

}
