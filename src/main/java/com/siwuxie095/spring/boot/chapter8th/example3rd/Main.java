package com.siwuxie095.spring.boot.chapter8th.example3rd;

/**
 * @author Jiajing Li
 * @date 2021-04-30 21:33:09
 */
@SuppressWarnings("all")
public class Main {

    /**
     * 部署到应用服务器
     *
     * 到目前为止，阅读列表应用程序每次运行，Web 应用程序都通过内嵌在应用里的 Tomcat 提供服务。情况和传统 Java Web
     * 应用程序正好相反。不是应用程序部署在 Tomcat 里，而是 Tomcat 部署在了应用程序里。
     *
     * 归功于 Spring Boot 的自动配置功能，这里不需要创建 web.xml 文件或者 Servlet 初始化类来声明 Spring MVC 的
     * DispatcherServlet。但如果要将应用程序部署到 Java 应用服务器里，就需要构建 WAR 文件了。这样应用服务器才能知
     * 道如何运行应用程序。那个 WAR 文件里还需要一个对 Servlet 进行初始化的东西。
     *
     *
     *
     * 1、构建 WAR 文件
     *
     * 实际上，构建 WAR 文件并不困难。如果使用 Maven 构建项目，获取 WAR 文件会更容易。只需把 <packaging> 元素的值
     * 从 jar 改为 war。
     *
     * <packaging>war</packaging>
     *
     * 这样就能生成 WAR 文件了。但如果 WAR 文件里没有启用 Spring MVC DispatcherServlet 的 web.xml 文件或者
     * Servlet 初始化类，这个 WAR 文件就一无是处。
     *
     * 此时就该 Spring Boot 出马了。它提供的 SpringBootServletInitializer 是一个支持 Spring Boot 的 Spring
     * WebApplicationInitializer 实现。除了配置 Spring 的 DispatcherServlet，SpringBootServletInitializer
     * 还会在 Spring 应用程序上下文里查找 Filter、Servlet 或 ServletContextInitializer 类型的 Bean，把它们绑
     * 定到 Servlet 容器里。
     *
     * 要使用 SpringBootServletInitializer，只需创建一个子类，覆盖 configure() 方法来指定 Spring 配置类。如下
     * 代码是 ReadingListServletInitializer，也就是为阅读列表应用程序写的 SpringBootServletInitializer 的子
     * 类。
     *
     * public class ReadingListServletInitializer
     *         extends SpringBootServletInitializer {
     *
     *     @Override
     *     protected SpringApplicationBuilder configure(
     *             SpringApplicationBuilder builder) {
     *         return builder.sources(ReadingListApplication.class);
     *     }
     *
     * }
     *
     * 如你所见，configure() 方法传入了一个 SpringApplicationBuilder 参数，并将其作为结果返回。期间它调用
     * sources() 方法注册了一个 Spring 配置类。本例只注册了一个 Application 类。回想一下，这个类既是启动类
     * （带有 main() 方法），也是一个 Spring 配置类。
     *
     * 虽然阅读列表应用程序里还有其他 Spring 配置类，但没有必要在这里把它们全部注册进来。Application 类上添加
     * 了 @SpringBootApplication 注解。这会隐性开启组件扫描，而组件扫描则会发现并应用其他配置类。
     *
     * 现在可以构建应用程序了。对于基于 Maven 的项目，可以使用 package：
     *
     * mvn package
     *
     * 没问题的话，你可以在 build/libs 里看到一个名为 readinglist-0.0.1-SNAPSHOT.war 的文件。
     *
     * 成功构建之后，你可以在 target 目录里找到 WAR 文件。
     *
     * 剩下的工作就是部署应用程序了。应用服务器不同，部署过程会有所区别，因此请参考应用服务器的部署说明文档。
     *
     * 对于 Tomcat 而言，可以把 WAR 文件复制到 Tomcat 的 webapps 目录里。如果 Tomcat 正在运行（要是没有运行，则
     * 在下次启动时检测），则会检测到 WAR 文件，解压并进行安装。
     *
     * 假设你没有在部署前重命名 WAR 文件，Servlet 上下文路径会与 WAR 文件的主文件名相同，在本例中是
     * /readinglist-0.0.1-SNAPSHOT。用你的浏览器打开 http://server:port/readinglist-0.0.1-SNAPSHOT
     * 就能访问应用程序了。
     *
     * 还有一点值得注意：就算在构建的是 WAR 文件，这个文件仍旧可以脱离应用服务器直接运行。如果你没有删除 Application
     * 里的 main() 方法，构建过程生成的 WAR 文件仍可直接运行，一如可执行的 JAR 文件：
     *
     * java -jar readinglist-0.0.1-SNAPSHOT.war
     *
     * 这样一来，同一个部署产物就能有两种部署方式了。
     *
     * 现在，应用程序应该已经在 Tomcat 里顺利地运行起来了。但是它还在使用内嵌的 H2 数据库。开发应用程序时，嵌入式数
     * 据库很好用，但对生产环境而言这不是一个明智的选择。下面来看看如何在部署到生产环境时选择不同的数据源。
     *
     *
     *
     * 2、创建生产 Profile
     *
     * 多亏了自动配置，有了一个指向嵌入式 H2 数据库的 DataSource Bean。更确切地说，DataSource Bean 是一个数据库
     * 连接池，通常是 org.apache.tomcat.jdbc.pool.DataSource。
     *
     * 因此，很明显，要使用嵌入式 H2 之外的数据库，只需声明自己的 DataSource Bean，指向选择的生产数据库，用它覆盖
     * 自动配置的 DataSource Bean。
     *
     * 例如，假设想使用运行 localhost 上的 PostgreSQL 数据库，数据库名字是 readingList。下面的 @Bean 方法就能
     * 声明对应的 DataSource Bean：
     *
     * @Bean
     * @Profile("production")
     * public DataSource dataSource() {
     *      DataSource ds = new DataSource();
     *      ds.setDriverClassName("org.postgresql.Driver");
     *      ds.setUrl("jdbc:postgresql://localhost:5432/readinglist");
     *      ds.setUsername("habuma");
     *      ds.setPassword("password");
     *      return ds;
     * }
     *
     * 这里 DataSource 的类型是 Tomcat 的 org.apache.tomcat.jdbc.pool.DataSource，不要和 javax.sql
     * .DataSource 搞混了。前者是后者的实现。连接数据库所需的细节（包括 JDBC 驱动类名、数据库 URL、用户名
     * 和密码）提供给了 DataSourse 实例。声明了这个 Bean 之后，默认自动配置的 DataSource Bean 就会忽略。
     *
     * 这个 @Bean 方法最关键的一点是，它还添加了 @Profile 注解，说明只有在 production Profile 被激活时才会创建
     * 该 Bean。所以，在开发时还能继续使用嵌入式的 H2 数据库。激活 production Profile 后就能使用 PostgreSQL
     * 数据库了。
     *
     * 虽然这么做能达到目的，但是配置数据库细节的时候，最好还是不要显式地声明自己的 DataSource Bean。在不替换自动
     * 配置的 Datasource Bean 的情况下，还能通过 application.yml 或 application.properties 来配置数据库的
     * 细节。如下列出了在配置 DataSource Bean 时用到的全部属性（带有 spring.datasource. 前缀）。
     * （1）name：数据源的名称
     * （2）initialize：是否执行 data.sql（默认：true）
     * （3）schema：Schema（DDL）脚本资源的名称
     * （4）data：数据（DML）脚本资源的名称
     * （5）sql-script-encoding：读入 SQL 脚本的字符集
     * （6）platform：读入 Schema 资源时所使用的平台（例如：schema-{platform}.sql）
     * （7）continue-on-error：如果初始化失败是否还要继续（默认：false）
     * （8）separator：SQL 脚本的分隔符（默认：;）
     * （9）driver-class-name：JDBC 驱动的全限定类名（通常能通过 URL 自动推断出来）
     * （10）url：数据库 URL
     * （11）username：数据库的用户名
     * （12）password：数据库的密码
     * （13）jndi-name：通过 JNDI 查找数据源的 JNDI 名称
     * （14）max-active：最大的活跃连接数（默认：100）
     * （15）max-idle：最大的闲置连接数（默认：8）
     * （16）min-idle：最小的闲置连接数（默认：8）
     * （17）initial-size：连接池的初始大小（默认：10）
     * （18）validation-query：用来验证连接的查询语句
     * （19）test-on-borrow：从连接池借用连接时是否检查连接（默认：false）
     * （20）test-on-return：向连接池归还连接时是否检查连接（默认：false）
     * （21）test-while-idle：连接空闲时是否测试连接（默认：false）
     * （22）time-between-eviction-runs-millis：多久（单位为毫秒）清理一次连接（默认：5000）
     * （23）min-evictable-idle-time-millis：在被测试是否要清理前，连接最少可以空闲多久（单位为毫秒，默认：60000）
     * （24）max-wait：当没有可用连接时，连接池在返回失败前最多等多久（单位为毫秒，默认：30000）
     * （25）jmx-enabled：数据源是否可以通过 JMX 进行管理（默认：false）
     *
     * 这里的大部分属性都是用来微调连接池的。怎么设置这些属性以适应你的需要，这就交给你来解决了。现在要设置属性，让
     * DataSource Bean 指向 PostgreSQL 而非内嵌的 H2 数据库。具体来说，要设置的是 spring.datasource.url、
     * spring.datasource.username 以及 spring.datasource.password 属性。
     *
     * 在设置这些内容时，在本地运行了一个 PostgreSQL 数据库，监听 5432 端口。用户名和密码分别是 habuma 和
     * password。因此，application.yml 的 production Profile 里需要如下内容：
     *
     * ---
     * spring:
     *   profiles: production
     *   datasource:
     *     url: jdbc:postgresql://localhost:5432/readinglist
     *     username: habuma
     *     password:
     *   jpa:
     *     database-platform: org.hibernate.dialect.PostgreSQLDialect
     *
     * 请注意，这个代码片段以 --- 开头，设置的第一个属性是 spring.profiles。这说明随后的属性都只在 production
     * Profile 激活时才会生效。
     *
     * 随后设置的是 spring.datasource.url、spring.datasource.username 和 spring.datasource.password
     * 属性。注意，spring.datasource.driver-class-name 属性一般无需设置。Spring Boot 可以根据
     * spring.datasource.url 属性的值做出相应推断。
     *
     * 这里还设置了一些 JPA 的属性。spring.jpa.database-platform 属性将底层的 JPA 引擎设置为 Hibernate 的
     * PostgreSQL 方言。
     *
     * 要开启这个 Profile，需要把 spring.profiles.active 属性设置为 production。实现方式有很多，但最方便的
     * 还是在运行应用服务器的机器上设置一个系统环境变量。在启动 Tomcat 前开启 production Profile，需要像这样
     * 设置 SPRING_PROFILES_ACTIVE 环境变量：
     *
     * export SPRING_PROFILES_ACTIVE=production
     *
     * 你也许已经注意到了，SPRING_PROFILES_ACTIVE 不同于 spring.profiles.active。因为无法在环境变量名里使用
     * 句点，所以变量名需要稍作修改。站在 Spring 的角度看，这两个名字是等价的。
     *
     * 现在基本已经可以在应用服务器上部署并运行应用程序了。实际上，如果你喜欢冒险，也可以直接尝试一下。不过你会遇到
     * 一点小问题。
     *
     * 默认情况下，在使用内嵌的 H2 数据库时，Spring Boot 会配置 Hibernate 来自动创建 Schema。更确切地说，这是
     * 将 Hibernate 的 hibernate.hbm2ddl.auto 设置为 create-drop，说明在 Hibernate 的 SessionFactory
     * 创建时会创建 Schema，SessionFactory 关闭时删除 Schema。
     *
     * 但如果没使用内嵌的 H2 数据库，那么它什么都不会做。也就是，说应用程序的数据表尚不存在，在查询那些不存在的表时
     * 会报错。
     *
     *
     *
     * 3、开启数据库迁移
     *
     * 一种途径是通过 Spring Boot 的 spring.jpa.hibernate.ddl-auto 属性将 hibernate.hbm2ddl.auto 属性设
     * 置为 create、create-drop 或 update。例如，要把 hibernate.hbm2ddl.auto 设置为 create-drop，可以在
     * application.yml 里加入如下内容：
     *
     * spring:
     *   jpa:
     *     hibernate:
     *       ddl-auto: create-drop
     *
     * 然而，这对生产环境来说并不理想，因为应用程序每次重启数据库，Schema 就会被清空，从头开始重建。它可以设置为
     * update，但就算这样，也不建议将其用于生产环境。
     *
     * 还有一个途径。可以在 schema.sql 里定义 Schema。在第一次运行时，这么做没有问题，但随后每次启动应用程序时，
     * 这个初始化脚本都会失败，因为数据表已经存在了。这就要求在书写初始化脚本时格外注意，不要重复执行那些已经做过
     * 的工作。
     *
     * 一个比较好的选择是使用数据库迁移库（database migration library）。它使用一系列数据库脚本，而且会记录哪些
     * 已经用过了，不会多次运用同一个脚本。应用程序的每个部署包里都包含了这些脚本，数据库可以和应用程序保持一致。
     *
     * Spring Boot 为两款流行的数据库迁移库提供了自动配置支持。
     * （1）Flyway（https://flywaydb.org）
     * （2）Liquibase（https://www.liquibase.org）
     *
     * 当你想要在 Spring Boot 里使用其中某一个库时，只需在项目里加入对应的依赖，然后编写脚本就可以了。下面先从
     * Flyway 开始了解吧。
     *
     *
     * 3.1、用 Flyway 定义数据库迁移过程
     *
     * Flyway 是一个非常简单的开源数据库迁移库，使用 SQL 来定义迁移脚本。它的理念是，每个脚本都有一个版本号，
     * Flyway 会顺序执行这些脚本，让数据库达到期望的状态。它也会记录已执行的脚本状态，不会重复执行。
     *
     * 在阅读列表应用程序这里，先从一个没有数据表和数据的空数据库开始。因此，这个脚本里需要先创建 Reader 和 Book
     * 表，包含外键约束和初始化数据。如下代码就是从空数据库到可用状态的 Flyway 脚本。
     *
     * create table Reader (
     *   username varchar(25) unique not null,
     *   password varchar(25) not null,
     *   fullname varchar(50) not null
     * );
     *
     * create table Book (
     *   id serial primary key,
     *   author varchar(50) not null,
     *   description varchar(1000) not null,
     *   isbn varchar(10) not null,
     *   title varchar(250) not null,
     *   reader_username varchar(25) not null,
     *   foreign key (reader_username) references Reader(username)
     * );
     *
     * create sequence hibernate_sequence;
     *
     * insert into Reader (username, password, fullname)
     *              values ('craig', 'password', 'Craig Walls');
     *
     * 如你所见，Flyway 脚本就是 SQL。让其发挥作用的是其在 Classpath 里的位置和文件名。Flyway 脚本都遵循一个
     * 命名规范，含有版本号，具体如下所示。
     *
     * V1__initialize.sql
     *
     * 所有 Flyway 脚本的名字都以大写字母 V 开头，随后是脚本的版本号。后面跟着两个下划线和对脚本的描述。因为这是
     * 整个迁移过程中的第一个脚本，所以它的版本是 1。描述可以很灵活，主要用来帮助理解脚本的用途。稍后需要向数据库
     * 添加新表，或者向已有数据表添加新字段。可以再创建一个脚本，标明版本号为 2。
     *
     * Flyway 脚本需要放在相对于应用程序 Classpath 根路径的 /db/migration 路径下。因此，项目中，脚本需要放在
     * src/main/resources/db/migration 里。
     *
     * 你还需要将 spring.jpa.hibernate.ddl-auto 设置为 none，由此告知 Hibernate 不要创建数据表。这关系到
     * application.yml 中的如下内容：
     *
     * spring:
     *   jpa:
     *     hibernate:
     *       ddl-auto: none
     *
     * 剩下的就是将 Flyway 添加为项目依赖。在 Maven 项目里，<dependency> 是这样的：
     *
     * <dependency>
     *   <groupId>org.flywayfb</groupId>
     *   <artifactId>flyway-core</artifactId>
     * </dependency>
     *
     * 在应用程序部署并运行起来后，Spring Boot 会检测到 Classpath 里的 Flyway，自动配置所需的 Bean。Flyway
     * 会依次查看 /db/migration 里的脚本，如果没有执行过就运行这些脚本。每个脚本都执行过后，向 schema_version
     * 表里写一条记录。应用程序下次启动时，Flyway 会先看 schema_version 里的记录，跳过那些脚本。
     *
     *
     * 3.2、用 Liquibase 定义数据库迁移过程
     *
     * Flyway 用起来很简便，在 Spring Boot 自动配置的帮助下尤其如此。但是，使用 SQL 来定义迁移脚本是一把双刃剑。
     * SQL 用起来便捷顺手，却要冒着只能在一个数据库平台上使用的风险。
     *
     * Liquibase 并不局限于特定平台的 SQL，可以用多种格式书写迁移脚本，不用关心底层平台（其中包括 XML、YAML 和
     * JSON）。如果你有这个期望的话，Liquibase 当然也支持 SQL 脚本。
     *
     * 要在 Spring Boot 里使用 Liquibase，第一步是添加依赖。对于 Maven 项目，你需要添加如下 <dependency>：
     *
     * <dependency>
     *   <groupId>org.liquibase</groupId>
     *   <artifactId>liquibase-core</artifactId>
     * </dependency>
     *
     * 有了这个依赖，Spring Boot 自动配置就能接手，配置好用于支持 Liquibase 的 Bean。默认情况下，那些 Bean
     * 会在 /db/changelog（相对于 Classpath 根目录）里查找 db.changelog-master.yaml 文件。
     *
     * 这个文件里都是迁移脚本。如下代码的初始化脚本为阅读列表应用程序进行了数据库初始化。
     *
     * databaseChangeLog:
     *   - changeSet:
     *       id: 1
     *       author: habuma
     *       changes:
     *         - createTable:
     *             tableName: reader
     *             columns:
     *               - column:
     *                   name: username
     *                   type: varchar(25)
     *                   constraints:
     *                     unique: true
     *                     nullable: false
     *               - column:
     *                   name: password
     *                   type: varchar(25)
     *                   constraints:
     *                     nullable: false
     *               - column:
     *                   name: fullname
     *                   type: varchar(50)
     *                   constraints:
     *                     nullable: false
     *         - createTable:
     *             tableName: book
     *             columns:
     *               - column:
     *                   name: id
     *                   type: bigserial
     *                   autoIncrement: true
     *                   constraints:
     *                     primaryKey: true
     *                     nullable: false
     *               - column:
     *                   name: author
     *                   type: varchar(50)
     *                   constraints:
     *                     nullable: false
     *               - column:
     *                   name: description
     *                   type: varchar(1000)
     *                   constraints:
     *                     nullable: false
     *               - column:
     *                   name: isbn
     *                   type: varchar(10)
     *                   constraints:
     *                     nullable: false
     *               - column:
     *                   name: title
     *                   type: varchar(250)
     *                   constraints:
     *                     nullable: false
     *               - column:
     *                   name: reader_username
     *                   type: varchar(25)
     *                   constraints:
     *                     nullable: false
     *                     references: reader(username)
     *                     foreignKeyName: fk_reader_username
     *         - createSequence:
     *             sequenceName: hibernate_sequence
     *         - insert:
     *            tableName: reader
     *            columns:
     *              - column:
     *                 name: username
     *                 value: craig
     *              - column:
     *                 name: password
     *                 value: password
     *              - column:
     *                 name: fullname
     *                 value: Craig Walls
     *
     * 如你所见，比起等效的 Flyway SQL 脚本，YAML 格式略显繁琐，但看起来还是很清晰的，而且这个脚本不与任何特定的
     * 数据库平台绑定。
     *
     * 与 Flyway 不同，Flyway 有多个脚本，每个脚本对应一个变更集。Liquibase 变更集都集中在一个文件里。请注意，
     * changeset 命令后的那行有一个 id 属性，要对数据库进行后续变更。可以添加一个新的 changeset，只要 id 不一
     * 样就行。此外，id 属性也不一定是数字，可以包含任意内容。
     *
     * 应用程序启动时，Liquibase 会读取 db.changelog-master.yaml 里的变更集指令集，与之前写入
     * databaseChangeLog 表里的内容做对比，随后执行未运行过的变更集。
     *
     * 虽然这里的例子使用的是 YAML 格式，但你也可以任意选择 Liquibase 所支持的其他格式，比如 XML 或 JSON。只需
     * 简单地设置 liquibase.change-log 属性（在 application.properties 或 application.yml 里），标明希望
     * Liquibase 加载的文件即可。举个例子，要使用 XML 变更集，可以这样设置 liquibase.change-log：
     *
     * liquibase:
     *   change-log: classpath:/db/changelog/db.changelog-master.xml
     *
     * Spring Boot 的自动配置让 Liquibase 和 Flyway 的使用变得轻而易举。但实际上所有数据库迁移库都有更多功能，
     * 这里不便一一列举。建议参考官方文档，了解更多详细内容。
     *
     * 已经了解了如何将 Spring Boot 应用程序部署到传统的 Java 应用服务器上，基本就是创建一个
     * SpringBootServletInitializer 的子类，调整构建说明来生成一个 WAR 文件，而非 JAR 文
     * 件。后续会看到，Spring Boot 应用程序在云端使用更方便。
     */
    public static void main(String[] args) {

    }

}
