package com.siwuxie095.spring.boot.extend2nd.example1st;

/**
 * @author Jiajing Li
 * @date 2021-05-01 21:08:48
 */
public class Main {

    /**
     * Spring Boot 起步依赖
     *
     * Spring Boot 起步依赖大大简化了项目构建说明中的依赖配置，因为常用的依赖聚合于更粗粒度的依赖。你的构建项目
     * 会传递解析到起步依赖中声明的其他依赖。
     *
     * 起步依赖不仅能让构建说明中的依赖配置更简单，还根据提供给应用程序的功能将它们组织到一起。例如，与其指定用于
     * 验证的特定库（比如 Hibernate Validator 和 Tomcat 的内嵌表达式语言），还不如简单地添加
     * spring-boot-starter-validation 起步依赖。
     *
     * 如下列出了 Spring Boot 的所有起步依赖，还有它们传递声明的依赖。
     *
     * PS：如下起步依赖的 Group ID 均为 org.springframework.boot
     *
     * （1）
     * 起步依赖：
     * spring-boot-starter
     * 传递依赖：
     * org.springframework.boot:spring-boot、
     * org.springframework.boot:spring-boot-autoconfigure、
     * org.springframework.boot:spring-boot-starter-logging、
     * org.springframework:spring-core（excludes commons-logging:commons-logging）
     * org.yaml:snakeyaml
     * （2）
     * 起步依赖：
     * spring-boot-starter-actuator
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework.boot:spring-boot-actuator
     * （3）
     * 起步依赖：
     * spring-boot-starter-amqp
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework:spring-messaging
     * org.springframework.amqp:spring-rabbit
     * （4）
     * 起步依赖：
     * spring-boot-starter-aop
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework:spring-aop
     * org.aspectj:aspectjrt
     * org.aspectj:aspectjweaver
     * （5）
     * 起步依赖：
     * spring-boot-starter-artemis
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework:spring-jms
     * org.apache.activemq:artemis-jms-client
     * （6）
     * 起步依赖：
     * spring-boot-starter-batch
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.hsqldb:hsqldb
     * org.springframework:spring-jdbc
     * org.springframework.batch:spring-batch-core
     * （7）
     * 起步依赖：
     * spring-boot-starter-cache
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework:spring-context
     * org.springframework:spring-context-support
     * （8）
     * 起步依赖：
     * spring-boot-starter-cloud-connectors
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework.cloud:spring-cloud-spring-service-connector
     * org.springframework.cloud:spring-cloud-cloudfoundry-connector
     * org.springframework.cloud:spring-cloud-heroku-connector
     * org.springframework.cloud:spring-cloud-localconfig-connector
     * （9）
     * 起步依赖：
     * spring-boot-starter-data-elasticsearch
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework.data:spring-data-elasticsearch
     * （10）
     * 起步依赖：
     * spring-boot-starter-data-gemfire
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * com.gemstone.gemfire:gemfire（excludes commons-logging:commons-logging）
     * org.springframework.data:spring-data-gemfire
     * （11）
     * 起步依赖：
     * spring-boot-starter-data-jpa
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework.boot:spring-boot-starter-aop
     * org.springframework.boot:spring-boot-starter-jdbc
     * org.hibernate:hibernate-entitymanager
     * （excludes org.jboss.spec.javax.transaction:jboss-transaction-api_1.2_spec）
     * javax.transaction:javax.transaction-api
     * org.springframework.data:spring-data-jpa
     * org.springframework:spring-aspects
     * （12）
     * 起步依赖：
     * spring-boot-starter-data-mongodb
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.mongodb:mongo-java-driver
     * org.springframework.data:spring-data-mongodb
     * （13）
     * 起步依赖：
     * spring-boot-starter-data-rest
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework.boot:spring-boot-starter-web
     * com.fasterxml.jackson.core:jackson-annotations
     * com.fasterxml.jackson.core:jackson-databind
     * org.springframework.data:spring-data-rest-webmvc
     * （14）
     * 起步依赖：
     * spring-boot-starter-data-solr
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.apache.solr:solr-solrj（excludes log4j:log4j）
     * org.springframework.data:spring-data-solr
     * org.apache.httpcomponents:httpmime
     * （15）
     * 起步依赖：
     * spring-boot-starter-freemarker
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework.boot:spring-boot-starter-web
     * org.freemarker:freemarker
     * org.springframework:spring-context-support
     * （16）
     * 起步依赖：
     * spring-boot-starter-groovy-templates
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework.boot:spring-boot-starter-web
     * org.codehaus.groovy:groovy-templates
     * （17）
     * 起步依赖：
     * spring-boot-starter-hateoas
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter-web
     * org.springframework.hateoas:spring-hateoas
     * org.springframework.plugin:spring-plugin-core
     * （18）
     * 起步依赖：
     * spring-boot-starter-hornetq
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework:spring-jms
     * org.hornetq:hornetq-jms-client
     * （19）
     * 起步依赖：
     * spring-boot-starter-integration
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework.boot:spring-boot-starter-aop
     * org.springframework.integration:spring-integration-core
     * org.springframework.integration:spring-integration-file
     * org.springframework.integration:spring-integration-http
     * org.springframework.integration:spring-integration-ip
     * org.springframework.integration:spring-integration-stream
     * （20）
     * 起步依赖：
     * spring-boot-starter-jdbc
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.apache.tomcat:tomcat-jdbc
     * org.springframework:spring-jdbc
     * （21）
     * 起步依赖：
     * spring-boot-starter-jersey
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework.boot:spring-boot-starter-tomcat
     * org.springframework.boot:spring-boot-starter-validation
     * com.fasterxml.jackson.core:jackson-databind
     * org.springframework:spring-web
     * org.glassfish.jersey.core:jersey-server
     * org.glassfish.jersey.containers:jersey-container-servlet-core
     * org.glassfish.jersey.containers:jersey-container-servlet
     * org.glassfish.jersey.ext:jersey-bean-validation
     * （excludes javax.el:javax.el-api, org.glassfish.web:javax.el）
     * org.glassfish.jersey.ext:jersey-spring3
     * org.glassfish.jersey.media:jersey-media-json-jackson
     * （22）
     * 起步依赖：
     * spring-boot-starter-jetty
     * 传递依赖：
     * org.eclipse.jetty:jetty-servlets
     * org.eclipse.jetty:jetty-webapp
     * org.eclipse.jetty.websocket:websocket-server
     * org.eclipse.jetty.websocket:javax-websocket-server-impl
     * （23）
     * 起步依赖：
     * spring-boot-starter-jooq
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework.boot:spring-boot-starter-jdbc
     * org.springframework:spring-tx
     * org.jooq:jooq
     * （24）
     * 起步依赖：
     * spring-boot-starter-jta-atomikos
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * com.atomikos:transactions-jms
     * com.atomikos:transactions-jta
     * （excludes org.apache.geronimo.specs:geronimo-jta_1.0.1B_spec）
     * com.atomikos:transactions-jdbc
     * javax.transaction:javax.transaction-api
     * （25）
     * 起步依赖：
     * spring-boot-starter-jta-bitronix
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * javax.jms:jms-api
     * javax.transaction:javax.transaction-api
     * org.codehaus.btm:btm（excludes javax.transaction:jta）
     * （26）
     * 起步依赖：
     * spring-boot-starter-log4j
     * 传递依赖：
     * org.slf4j:jcl-over-slf4j
     * org.slf4j:jul-to-slf4j
     * org.slf4j:slf4j-log4j12
     * log4j:log4j
     * （27）
     * 起步依赖：
     * spring-boot-starter-log4j2
     * 传递依赖：
     * org.apache.logging.log4j:log4j-slf4j-impl
     * org.apache.logging.log4j:log4j-api
     * org.apache.logging.log4j:log4j-core
     * org.slf4j:jcl-over-slf4j
     * org.slf4j:jul-to-slf4j
     * （28）
     * 起步依赖：
     * spring-boot-starter-logging
     * 传递依赖：
     * ch.qos.logback:logback-classic
     * org.slf4j:jcl-over-slf4j
     * org.slf4j:jul-to-slf4j
     * org.slf4j:log4j-over-slf4j
     * （29）
     * 起步依赖：
     * spring-boot-starter-mail
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework:spring-context
     * org.springframework:spring-context-support
     * com.sun.mail:javax.mail
     * （30）
     * 起步依赖：
     * spring-boot-starter-mobile
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework.boot:spring-boot-starter-web
     * org.springframework.mobile:spring-mobile-device
     * （31）
     * 起步依赖：
     * spring-boot-starter-mustache
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework.boot:spring-boot-starter-web
     * com.samskivert:jmustache
     * （32）
     * 起步依赖：
     * spring-boot-starter-redis
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework.data:spring-data-redis
     * redis.clients:jedis
     * （33）
     * 起步依赖：
     * spring-boot-starter-remote-shell
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework.boot:spring-boot-starter-actuator
     * org.crashub:crash.cli
     * org.crashub:crash.connectors.ssh（excludes org.codehaus.groovy:groovy-all）
     * org.crashub:crash.connectors.telnet
     * （excludes javax.servlet:servlet-api,log4j:log4j,commons-logging:commonslogging）
     * org.crashub:crash.embed.spring
     * （excludes org.springframework:spring-web,org.codehaus.groovy:groovy-all）
     * org.crashub:crash.plugins.cron（excludes org.codehaus.groovy:groovy-all）
     * org.crashub:crash.plugins.mail（excludes org.codehaus.groovy:groovy-all）
     * org.crashub:crash.shell（excludes org.codehaus.groovy:groovy-all）
     * org.codehaus.groovy:groovy
     * （34）
     * 起步依赖：
     * spring-boot-starter-security
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework:spring-aop
     * org.springframework.security:spring-security-config
     * org.springframework.security:spring-security-web
     * （35）
     * 起步依赖：
     * spring-boot-starter-social-facebook
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework.boot:spring-boot-starter-web
     * org.springframework.social:spring-social-config
     * org.springframework.social:spring-social-core
     * org.springframework.social:spring-social-web
     * org.springframework.social:spring-social-facebook
     * （36）
     * 起步依赖：
     * spring-boot-starter-social-linkedin
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework.boot:spring-boot-starter-web
     * org.springframework.social:spring-social-config
     * org.springframework.social:spring-social-core
     * org.springframework.social:spring-social-web
     * org.springframework.social:spring-social-linkedin
     * （37）
     * 起步依赖：
     * spring-boot-starter-social-twitter
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework.boot:spring-boot-starter-web
     * org.springframework.social:spring-social-config
     * org.springframework.social:spring-social-core
     * org.springframework.social:spring-social-web
     * org.springframework.social:spring-social-twitter
     * （38）
     * 起步依赖：
     * spring-boot-starter-test
     * 传递依赖：
     * junit:junit
     * org.mockito:mockito-core
     * org.hamcrest:hamcrest-core
     * org.hamcrest:hamcrest-library
     * org.springframework:spring-core（excludes commons-logging:commons-logging）
     * org.springframework:spring-test
     * （39）
     * 起步依赖：
     * spring-boot-starter-thymeleaf
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework.boot:spring-boot-starter-web
     * org.thymeleaf:thymeleaf-spring4
     * nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect
     * （40）
     * 起步依赖：
     * spring-boot-starter-tomcat
     * 传递依赖：
     * org.apache.tomcat.embed:tomcat-embed-core
     * org.apache.tomcat.embed:tomcat-embed-el
     * org.apache.tomcat.embed:tomcat-embed-logging-juli
     * org.apache.tomcat.embed:tomcat-embed-websocket
     * （41）
     * 起步依赖：
     * spring-boot-starter-undertow
     * 传递依赖：
     * io.undertow:undertow-core
     * io.undertow:undertow-servlet（excludes org.jboss.spec.javax.servlet:jboss-servlet-api_3.1_spec）
     * io.undertow:undertow-websockets-jsr
     * javax.servlet:javax.servlet-api
     * org.glassfish:javax.el
     * （42）
     * 起步依赖：
     * spring-boot-starter-validation
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.apache.tomcat.embed:tomcat-embed-el
     * org.hibernate:hibernate-validator
     * （43）
     * 起步依赖：
     * spring-boot-starter-velocity
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework.boot:spring-boot-starter-web
     * commons-beanutils:commons-beanutils
     * commons-collections:commons-collections
     * commons-digester:commons-digester
     * org.apache.velocity:velocity
     * org.apache.velocity:velocity-tools
     * org.springframework:spring-context-support
     * （44）
     * 起步依赖：
     * spring-boot-starter-web
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework.boot:spring-boot-starter-tomcat
     * org.springframework.boot:spring-boot-starter-validation
     * com.fasterxml.jackson.core:jackson-databind
     * org.springframework:spring-web
     * org.springframework:spring-webmvc
     * （45）
     * 起步依赖：
     * spring-boot-starter-websocket
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework.boot:spring-boot-starter-web
     * org.springframework:spring-messaging
     * org.springframework:spring-websocket
     * （46）
     * 起步依赖：
     * spring-boot-starter-ws
     * 传递依赖：
     * org.springframework.boot:spring-boot-starter
     * org.springframework.boot:spring-boot-starter-web
     * org.springframework:spring-jms
     * org.springframework:spring-oxm
     * org.springframework.ws:spring-ws-core
     * org.springframework.ws:spring-ws-support
     */
    public static void main(String[] args) {

    }

}
