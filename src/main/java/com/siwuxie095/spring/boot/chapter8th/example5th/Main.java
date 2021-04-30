package com.siwuxie095.spring.boot.chapter8th.example5th;

/**
 * @author Jiajing Li
 * @date 2021-04-30 23:58:32
 */
public class Main {

    /**
     * 小结
     *
     * Spring Boot 应用程序的部署方式有好几种，包括使用传统的应用服务器和云上的 PaaS 平台。在这里，了解了其中
     * 的一些部署方式，把阅读列表应用程序以 WAR 文件的方式部署到 Tomcat 和云上（Cloud Foundry 和 Heroku）。
     *
     * Spring Boot 应用程序的构建说明经常会配置为生成可执行的 JAR 文件。这里也看到了如何对构建进行微调，如何
     * 编写一个 SpringBootServletInitializer 实现，生成 WAR 文件，以便部署到应用服务器上。
     *
     * 随后，进一步了解了如何将应用程序部署到 Cloud Foundry 上。Cloud Foundry 非常灵活，能够接受各种形式的
     * Spring Boot 应用程序，包括可执行 JAR 文件、传统 WAR 文件，甚至还包括原始的 Spring Boot CLI Groovy
     * 脚本。这里还了解了 Cloud Foundry 如何自动将内嵌式数据源替换为绑定到应用程序上的数据库服务。
     *
     * 虽然 Heroku 不能像 Cloud Foundry 那样自动替换数据源的 Bean，但在最后，还是看到了如何通过添加 Spring
     * Cloud Foundry 库来实现一样的效果。这里使用绑定的数据库服务，而非内嵌式数据库。
     *
     * 在这里，还了解了如何在 Spring Boot 里使用 Flyway 和 Liquibase 这样的数据库迁移工具。在初次部署应用程
     * 序时，通过数据库迁移的方式完成了数据库的初始化，在后续的部署过程中，可以按需修改数据库。
     */
    public static void main(String[] args) {

    }

}
