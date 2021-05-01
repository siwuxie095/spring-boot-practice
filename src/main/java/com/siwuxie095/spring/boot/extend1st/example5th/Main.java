package com.siwuxie095.spring.boot.extend1st.example5th;

/**
 * @author Jiajing Li
 * @date 2021-05-01 15:30:22
 */
public class Main {

    /**
     * 默认的开发时属性
     *
     * 有些配置属性通常在开发时设置，从来不用在生产环境里。比如视图模板缓存，在开发时最好关掉，这样你可以
     * 立刻看到修改的结果。但在生产环境里，为了追求更好的性能，应该开启视图模版缓存。
     *
     * 默认情况下，Spring Boot 会为其支持的各种视图模板（Thymeleaf、Freemarker、Velocity、 Mustache
     * 和 Groovy 模板）开启缓存选项。但如果存在 Spring Boot 的开发者工具，这些缓存就会禁用。
     *
     * 实际上，这就是说在开发者工具激活后，如下属性会设置为 false。
     * （1）spring.thymeleaf.cache
     * （2）spring.freemarker.cache
     * （3）spring.velocity.cache
     * （4）spring.mustache.cache
     * （5）spring.groovy.template.cache
     *
     * 这样一来，就不用在开发时（在一个开发时使用的 Profile 配置里）禁用它们了。
     */
    public static void main(String[] args) {

    }

}
