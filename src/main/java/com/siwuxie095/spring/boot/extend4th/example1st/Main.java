package com.siwuxie095.spring.boot.extend4th.example1st;

/**
 * @author Jiajing Li
 * @date 2021-05-02 11:41:45
 */
@SuppressWarnings("all")
public class Main {

    /**
     * Spring Boot 依赖
     *
     * 无论在构建项目时使用的是 Maven、Gradle 还是 Spring Boot CLI，Spring Boot 都为 Spring
     * 应用程序常用的很多库提供了依赖管理支持功能。
     *
     * 在很多情况下，这些依赖都会通过某个 Spring Boot 起步依赖自动添加到项目和 Classpath 里。但
     * 是，如果你正在使用的起步依赖没有覆盖到某个库，而你需要使用这个库，那就得在 Maven 或 Gradle
     * 的构建说明里显式地声明这个依赖。
     *
     * 举例来说，如果你的项目需要引入 H2 嵌入式数据库，那么你需要在 Gradle 里加入如下声明：
     *
     * compile("com.h2database:h2")
     *
     * 在 Maven 里可以添加类似的声明：
     *
     * <dependency>
 *        <groupId>com.h2database</groupId>
 *        <version>h2</version>
     * </dependency>
     *
     * 请注意，在这两种情况下，都不需要指定版本号，Spring Boot 的依赖管理会替你处理这个问题的。但
     * 是，如果想覆盖 Spring Boot 选择的版本，你也可以显式地提供一个版本号。
     *
     * 如果在使用 Spring Boot CLI 运行应用程序，你可以在 Groovy 里像这样使用 @Grab 注解：
     *
     * @Grab("h2")
     *
     * 在使用 @Grab 注解引入库时，你只需要指定 Artifact ID 就可以了。Spring Boot 扩展了 @Grab，
     * 它可以推测出 Group ID 和版本号。
     */
    public static void main(String[] args) {

    }

}
