package com.siwuxie095.spring.boot.chapter3rd.example5th;

/**
 * @author Jiajing Li
 * @date 2021-04-14 07:52:51
 */
public class Main {

    /**
     * 小结
     *
     * Spring Boot 消除了 Spring 应用程序中经常要用到的很多样板式配置。让 Spring Boot 处理全部配置，
     * 你可以仰仗它来配置那些适合你的应用程序的组件。当自动配置无法满足需求时，Spring Boot 允许你覆盖
     * 并微调它提供的配置。
     *
     * 覆盖自动配置其实很简单，就是显式地编写那些没有 Spring Boot 时你要做的 Spring 配置。 Spring
     * Boot 的自动配置被设计为优先使用应用程序提供的配置，然后才轮到自己的自动配置。
     *
     * 即使自动配置合适，你仍然需要调整一些细节。Spring Boot 会开启多个属性解析器，让你通过环境变量、
     * 属性文件、YAML 文件等多种方式来设置属性，以此微调配置。这套基于属性的配置模型也能用于应用程序
     * 自己定义的组件，可以从外部配置源加载属性并注入到 Bean 里。
     *
     * Spring Boot 还自动配置了一个简单的白标错误页，虽然它比异常跟踪信息友好一点，但在艺术性方面还有
     * 很大的提升空间。幸运的是，Spring Boot 提供了好几种选项来自定义或完全替换这个白标错误页，以满足
     * 应用程序的特定风格。
     */
    public static void main(String[] args) {

    }

}
