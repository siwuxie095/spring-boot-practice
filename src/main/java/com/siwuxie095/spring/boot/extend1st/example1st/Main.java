package com.siwuxie095.spring.boot.extend1st.example1st;

/**
 * @author Jiajing Li
 * @date 2021-05-01 10:28:52
 */
public class Main {

    /**
     * Spring Boot 开发者工具
     *
     * Spring Boot 1.3 引入了一组新的开发者工具，可以让你在开发时更方便地使用 Spring Boot，包括如下功能。
     * （1）自动重启：当 Classpath 里的文件发生变化时，自动重启运行中的应用程序。
     * （2）LiveReload 支持：对资源的修改自动触发浏览器刷新。
     * （3）远程开发：远程部署时支持自动重启和 LiveReload。
     * （4） 默认的开发时属性值：为一些属性提供有意义的默认开发时属性值。
     *
     * Spring Boot 的开发者工具采取了库的形式，可以作为依赖加入项目。如果你使用 Gradle 来构建项目，可以像
     * 下面这样在 build.gradle 文件里添加开发工具：
     *
     * compile "org.springframework.boot:spring-boot-devtools"
     *
     * 在 Maven POM 里添加 <dependency> 是这样的：
     *
     * <dependency>
     *   <groupId>org.springframework.boot</groupId>
     *   <artifactId>spring-boot-devtools</artifactId>
     * </dependency>
     *
     * 当应用程序以完整打包好的 JAR 或 WAR 文件形式运行时，开发者工具会被禁用，所以没有必要在构建生产部署包
     * 前移除这个依赖。
     */
    public static void main(String[] args) {

    }

}
