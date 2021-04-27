package com.siwuxie095.spring.boot.chapter7th.example3rd;

/**
 * @author Jiajing Li
 * @date 2021-04-27 20:35:50
 */
@SuppressWarnings("all")
public class Main {

    /**
     * 连接 Actuator 的远程 shell
     *
     * Actuator 通过 REST 端点提供了不少非常有用的信息。另一个深入运行中应用程序内部的方式 是使用远程 shell。
     * Spring Boot 集成了 CRaSH，一种能嵌入任意 Java 应用程序的 shell。Spring Boot 还扩展了 CRaSH，添加
     * 了不少 Spring Boot 特有的命令，提供了与 Actuator 端点类似的功能。
     *
     * 要使用远程 shell，只需加入远程 shell 的起步依赖即可。如果用 Maven 构建项目，你需要在 pom.xml 文件里
     * 添加如下依赖：
     *
     * <dependency>
     *      <groupId>org.springframework.boot</groupId>
     *      <artifactId>spring-boot-starter-remote-shell</artifactId>
     * </dependency>
     *
     * 如果要用 Spring Boot CLI 来运行你所开发的应用程序，则需要如下 @Grab 注解：
     *
     * @Grab("spring-boot-starter-remote-shell")
     *
     * 添加了远程 shell 依赖后，就可以构建并运行应用程序了。在启动的时候，可以看到要写进日志的一行密码。这行密
     * 码所在的行大概是这样的：
     *
     * Using default security password: efe30c70-5bf0-43b1-9d50-c7a02dda7d79
     *
     * 与这个密码搭配使用的用户名是 user。密码本身是随机生成的，每次运行应用程序时都会有所变化。
     *
     * 现在你可以通过 SSH 工具连接 shell 了，它监听的端口号是 2000。如果你用的是 Unix 的 ssh 命令，那么它看
     * 起来大概是这样的：
     *
     * ssh user@localhost -p 2000
     *
     * 远程 shell 提供了 24 个可以在运行应用程序上下文中执行的命令，其中大部分都是 CRaSH 自带的。但 Spring
     * Boot 也添加了一些。下表列出了这些 Spring Boot 特有的命令。
     * （1）autoconfig：生成自动配置说明报告，和 /autoconfig 端点输出的内容类似，只是把 JSON 换成了纯文本；
     * （2）beans：列出 Spring 应用程序上下文里的 Bean，与 /beans 端点输出的内容类似；
     * （3）endpoint：调用 Actuator 端点；
     * （4）metrics：显示 Spring Boot 的度量信息，与 /metrics 端点类似，但显示的是实时更新的数据。
     *
     * 下面看看如何使用这些 Spring Boot 添加的 shell 命令。
     *
     *
     *
     * 1、查看 autoconfig 报告
     *
     * autoconfig 命令生成了一个与 Actuator 的 /autoconfig 端点类似的报告。
     *
     * 结果分为两组 —— 匹配和不匹配，和 /autoconfig 端点的结果一样。实际上，唯一的显著区别在于，autoconfig
     * 命令输出的是文本，而 /autoconfig 端点输出的是 JSON，其他都一样。
     *
     * 这里不打算去讲 CRaSH 自己提供的 shell 命令，但你可能想把 autoconfig 命令的输出和 CRaSH 的 less 命
     * 令用管道串起来：
     *
     * autoconfig | less
     *
     * less 命令和 Unix shell 里的同名命令很相似，能让你穿梭于文件中。autoconfig 的输出很长，但 less 命令
     * 会让它更容易读取和查阅。
     *
     *
     *
     * 2、列出应用程序的 Bean
     *
     * autoconfig shell 命令的输出和 /autoconfig 端点的输出类似，但也有不同。对比之下，你会发现 beans 命
     * 令的输出和 /beans 端点的输出一样。
     *
     * 和 /beans 端点一样，beans 命令会以 JSON 格式列出 Spring 应用程序上下文里所有的 Bean，包括所依赖的
     * Bean。
     *
     *
     *
     * 3、查看应用程序的度量信息
     *
     * metrics shell 命令会输出与 Actuator 的 /metrics 端点一样的信息。/metrics 端点以 JSON 格式输出当
     * 前度量信息的快照，而 metrics 命令则会接管 shell，以实时仪表盘显示结果。
     *
     * metrics命令的实时仪表盘中，内存、堆、 线程在不断消耗和释放，随着类的加载，仪表盘里显示的数量也会随之变
     * 化，以反映当前值。
     *
     * 一旦看完了 metrics 命令提供的度量信息，按 Ctrl+C 就能回到 shell 了。
     *
     *
     *
     * 4、调用 Actuator 端点
     *
     * 你现在应该已经意识到了，并非所有的 Actuator 端点都有对应的 shell 命令。这是否意味着 shell 不能完全代
     * 替 Actuator 端点呢？是否仍要直接查询这些端点来获取 Actuator 提供的内部信息呢？虽然 shell 没能完全匹
     * 配上这些端点，但 endpoint 命令可以让你在 shell 里调用 Actuator 的端点。
     *
     * 首先，你要知道自己想调用哪个端点。在 shell 提示符中键入 endpoint list 就能获得端点的列表。请注意，列
     * 表中的端点用的是它们的 Bean 名称，而非 URL 路径。
     *
     * 如果想在 shell 里调用其中某个端点，你可以使用 endpoint invoke 命令，传入不带 Endpoint 后缀的 Bean
     * 名称。举例来说，要调用健康检查端点，可以在 shell 提示符里键入 endpoint invoke health。
     *
     * 请注意，这些端点返回的信息都是原始格式的，即未格式化过的 JSON 文档。虽然在 shell 里调用 Actuator 的端
     * 点不错，但输出结果很难阅读。就这个问题，自带的功能帮不上忙。但如果爱折腾，你也可以创建一个自定义的 CRaSH
     * shell 命令，通过管道接受未格式化的 JSON，然后美化输出。或者，你总是可以剪切黏贴 endpoint 命令的输出，
     * 将其放入你喜欢的工具进行阅读或格式化。
     */
    public static void main(String[] args) {

    }

}
