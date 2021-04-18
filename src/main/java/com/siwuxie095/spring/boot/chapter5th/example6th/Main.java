package com.siwuxie095.spring.boot.chapter5th.example6th;

/**
 * @author Jiajing Li
 * @date 2021-04-18 23:14:35
 */
public class Main {

    /**
     * 小结
     *
     * Spring Boot CLI 利用了 Spring Boot 自动配置和起步依赖的便利之处，并将其发扬光大。借由 Groovy 语言的优雅，
     * CLI 能让开发者在最少的代码噪声下开发 Spring 应用程序。
     *
     * 这里彻底重写了之前的阅读列表应用程序，只是这次用 Groovy 把它写成了 Spring Boot CLI 应用程序。通过自动添加
     * 很多常用包和类的 import 语句，CLI 让 Groovy 更优雅。它还可以自动解析很多依赖库。
     *
     * 对于 CLI 无法自动解析的库，基于 CLI 的应用程序可以利用 Grape 的 @Grab 注解，不用构建说明也能显式地声明依赖。
     * Spring Boot 的 CLI 扩展了 @Grab 注解，针对很多常用库依赖，只需声明 Module ID 就可以了。
     *
     * 最后，你还了解了如何用 Spring Boot CLI 来执行测试和构建可部署产物，这些通常都是由构建系统来负责的。
     *
     * Spring Boot 和 Groovy 结合得很好，两者的简洁性相辅相成。
     */
    public static void main(String[] args) {

    }

}
