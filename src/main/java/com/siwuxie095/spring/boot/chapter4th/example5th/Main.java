package com.siwuxie095.spring.boot.chapter4th.example5th;

/**
 * @author Jiajing Li
 * @date 2021-04-16 23:53:57
 */
public class Main {

    /**
     * 小结
     *
     * 测试是开发高质量软件的重要一环。没有好的测试，你永远无法保证应用程序能像期望的那样运行。
     *
     * 单元测试专注于单一组件或组件中的一个方法，此处并不一定要使用 Spring。Spring 提供了一
     * 些优势和技术 —— 松耦合、依赖注入和接口驱动设计。这些都简化了单元测试的编写。但 Spring
     * 不用直接涉足单元测试。
     *
     * 集成测试会涉及众多组件，这时就需要 Spring 帮忙了。实际上，如果 Spring 在运行时负责拼
     * 装那些组件，那么 Spring 在集成测试里同样应该肩负这一职责。
     *
     * Spring Framework 以 JUnit 类运行器的方式提供了集成测试支持，JUnit 类运行器会加载
     * Spring 应用程序上下文，把上下文里的 Bean 注入测试。Spring Boot 在 Spring 的集成
     * 测试之上又增加了配置加载器，以 Spring Boot 的方式加载应用程序上下文，包括了对外置属
     * 性的支持和 Spring Boot 日志。
     *
     * Spring Boot 还支持容器内测试 Web 应用程序，让你能用和生产环境一样的容器启动应用程序。
     * 这样一来，测试在验证应用程序行为的时候，会更加接近真实的运行环境。
     */
    public static void main(String[] args) {

    }

}
