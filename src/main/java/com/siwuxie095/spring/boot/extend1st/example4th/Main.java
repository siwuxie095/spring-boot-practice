package com.siwuxie095.spring.boot.extend1st.example4th;

/**
 * @author Jiajing Li
 * @date 2021-05-01 15:16:44
 */
public class Main {

    /**
     * 远程开发
     *
     * 在远程运行应用程序时（比如部署到服务器上或云上），开发者工具的自动重启和 LiveReload 特性都是可选的。
     * 此外，Spring Boot 开发者工具还能远程调试 Spring Boot 应用程序。
     *
     * 在传统的开发过程中，你不会打开远程开发功能，因为这会影响性能。但在一些特殊的场景中，此类工具就很有用。
     * 比如，出于开发目的，所开发的应用程序部署在非生产环境里。如果应用程序不是在本地开发环境里，而是在云端
     * 部署，则尤其如此。
     *
     * 你必须设置一个远程安全码来开启远程开发功能：
     *
     * spring:
     *   devtools:
     *     remote:
     *       secret: myappsecret
     *
     * 有了这个属性后，运行中的应用程序就会启动一个服务器组件以支持远程开发。它会监听接受变更的请求，可以重
     * 启应用程序或者触发浏览器刷新。
     *
     * 为了使用这个远程服务器，你需要在本地运行远程开发工具的客户端。这个远程客户端是一个类，全限定类名是
     * org.springframework.boot.devtools.RemoteSpringApplication。它会运行在 IDE 里，要求提供
     * 一个参数，告知远程应用程序部署在哪里。
     *
     * 例如，假设你正远程运行阅读列表应用程序，部署在 Cloud Foundry 上，地址是 https://readinglist
     * .cfapps.io。如果你正在使用 Eclipse 或 Spring ToolSuite，可以通过如下步骤开启远程客户端。
     * （1）选择 Run > Run Configurations 菜单项。
     * （2）创建一个新的 Java Application 运行配置。
     * （3）在 Project 里选中 Reading List 项目（可以键入项目名或者点击 Browse 按钮找到这个项目）。
     * （4）在 Main Class 里键入 org.springframework.boot.devtools.RemoteSpringApplication。
     * （5）切换到 Arguments 标签页，在 ProgramArguments 里键入 https://readinglist.cfapps.io。
     *
     * 客户端启动后，就可以在 IDE 里修改应用程序了。在检测到变动后，这些修改点会被推送到远端并加以应用。如
     * 果修改的内容涉及呈现的 Web 资源（比如样式表或 JavaScript），LiveReload 还会触发浏览器刷新。
     *
     * 远程客户端还会开启基于 HTTP 的远程调试通道，这样就能在 IDE 里调试部署在远程的应用程序了。你要做的
     * 就是确保远程应用程序打开了远程调试功能。这通常可以通过配置 JAVA_OPTS 来实现。
     *
     * 比方说，你的应用程序部署在 Cloud Foundry 上，可以像下面这样在应用程序的 manifest.yml 里设置
     * JAVA_OPTS。
     *
     * ---
     *   env:
     *     JAVA_OPTS: "-Xdebug -Xrunjdwp:server=y,transport=dt_socket,suspend=n"
     *
     * 远程应用程序启动后，会和本地调试服务器建立一个连接。你可以设置断点，一步步执行远程应用程序里的代码，
     * 就好像它们运行在本地一样（出于网络原因，速度会有点慢）。
     */
    public static void main(String[] args) {

    }

}
