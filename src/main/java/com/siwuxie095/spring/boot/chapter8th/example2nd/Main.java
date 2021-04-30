package com.siwuxie095.spring.boot.chapter8th.example2nd;

/**
 * @author Jiajing Li
 * @date 2021-04-30 20:19:47
 */
public class Main {

    /**
     * 衡量多种部署方式
     *
     * Spring Boot 应用程序有多种构建和运行方式，其中一些你已经使用过了。
     * （1）在 IDE 中运行应用程序（涉及 Spring ToolSuite 或 IntelliJ IDEA）。
     * （2）使用 Maven 的 spring-boot:run 或 Gradle 的 bootRun，在命令行里运行。
     * （3）使用 Maven 或 Gradle 生成可运行的 JAR 文件，随后在命令行中运行。
     * （4）使用 Spring Boot CLI 在命令行中运行 Groovy 脚本。
     * （5）使用 Spring Boot CLI 来生成可运行的 JAR 文件，随后在命令行中运行。
     *
     * 这些选项每一个都适合运行正在开发的应用程序。但是，如果要将应用程序部署到生产环境或其他非开发环境中，
     * 又该怎么办呢？
     *
     * 虽然这些选项看起来没有一个能将应用部署于非开发环境，但事实上，它们之中只有一个选项不可用于生产环境
     * —— 在 IDE 中运行应用显然不可取。可运行的 JAR 文件和 Spring Boot CLI 还是可以考虑的，两者还可
     * 以很好地将应用程序部署到云环境里。
     *
     * 也许你很想知道如何把 Spring Boot 应用程序部署到一个更加传统的应用服务器环境里，比如 Tomcat、
     * WebSphere 或 WebLogic。在这些情境中，可执行 JAR 文件和 Groovy 代码不适用。针对应用服务器的部
     * 署，你需要将应用程序打包成一个 WAR 文件。
     *
     * 实际上，Spring Boot 应用程序可以用多种方式打包。
     * （1）
     * 部署产物：Groovy 源码
     * 产生方式：手写
     * 目标环境：Cloud Foundry 及容器部署，比如 Docker
     * （2）
     * 部署产物：可执行 JAR
     * 产生方式：Maven、Gradle 或 Spring Boot CLI
     * 目标环境：云环境，包括 Cloud Foundry 和 Heroku，还有容器部署，比如 Docker
     * （3）
     * 部署产物：WAR
     * 产生方式：Maven 或 Gradle
     * 目标环境：Java 应用服务器或云环境，比如 Cloud Foundry
     *
     * 如你所见，在做最终选择时需要考虑目标环境。如果要将应用程序部署到自己数据中心的 Tomcat 服务器上，
     * WAR 文件就是你的选择。另一方面，如果要部署到 Cloud Foundry，可以使用上面列出的各种选项。
     *
     * 这里将关注以下选项。
     * （1）向 Java 应用服务器里部署 WAR 文件。
     * （2）向 Cloud Foundry 里部署可执行 JAR 文件。
     * （3）向 Heroku 里部署可执行 JAR 文件（构建过程是由 Heroku 执行的）。
     *
     * 探索这些场景的时候，还要处理一件事。在开发应用程序时使用了嵌入式的 H2 数据库，现在得把它替换成生产
     * 环境所需的数据库了。
     *
     * 后续先来看看如何将阅读列表应用程序构建为 WAR 文件。这样才能把它部署到 Java 应用服务器里，比如
     * Tomcat、WebSphere 或 WebLogic。
     */
    public static void main(String[] args) {

    }

}
