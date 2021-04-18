package com.siwuxie095.spring.boot.chapter5th.example5th;

/**
 * @author Jiajing Li
 * @date 2021-04-18 23:08:38
 */
public class Main {

    /**
     * 创建可部署的产物
     *
     * 在基于 Maven 和 Gradle 的传统 Java 项目中，构建系统负责产生部署单元 —— 一般是 JAR 文件或 WAR 文件。
     * 然而，有了 Spring Boot CLI，可以简单地通过 spring 命令在命令行里运行应用程序。
     *
     * 这是否就意味着要部署一个 Spring Boot CLI 应用程序，必须在服务器上安装 CLI，并手工在命令行里启动应用
     * 程序呢？在部署生产环境时，这看起来相当不方便（不用说，这还很危险）。
     *
     * 后续会讨论更多部署 Spring Boot 应用程序的方法。这里告诉你另一个 CLI 窍门。针对基于 CLI 的阅读列表应
     * 用程序，在命令行执行如下命令：
     *
     * spring jar ReadingList.jar .
     *
     * 这会将整个项目打包成一个可执行的 JAR 文件，包含所有依赖、Groovy 和一个嵌入式 Tomcat。打包完成后，就
     * 可以像下面这样在命令行里运行了（无需 CLI）：
     *
     * java -jar ReadingList.jar
     *
     * 除了可以在命令行里运行外，可执行的 JAR 文件也能部署到多个平台服务器（Platform as a Service，PaaS）
     * 云平台里，包括 Pivotal Cloud Foundry 和 Heroku。
     */
    public static void main(String[] args) {

    }

}
