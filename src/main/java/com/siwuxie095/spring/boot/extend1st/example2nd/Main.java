package com.siwuxie095.spring.boot.extend1st.example2nd;

/**
 * @author Jiajing Li
 * @date 2021-05-01 14:51:02
 */
public class Main {

    /**
     * 自动重启
     *
     * 在激活了开发者工具后，Classpath 里对文件做任何修改都会触发应用程序重启。为了让重启速度够快，
     * 不会修改的类（比如第三方 JAR 文件里的类）都加载到了基础类加载器里，而应用程序的代码则会加载
     * 到一个单独的重启类加载器里。检测到变更时，只有重启类加载器重启。
     *
     * 有些 Classpath 里的资源变更后不需要重启应用程序。像 Thymeleaf 这样的视图模板可以直接编辑，
     * 不用重启应用程序。在 /static 或 /public 里的静态资源也不用重启应用程序，所以 Spring Boot
     * 开发者工具会在重启时排除掉如下目录：/META-INF/resources、/resources、/static、/public
     * 和 /templates。
     *
     * 可以设置 spring.devtools.restart.exclude 属性来覆盖默认的重启排除目录。例如，你只排除
     * /static 和 /templates 目录，可以像这样设置 spring.devtools.restart.exclude：
     *
     * spring:
     *   devtools:
     *     restart:
     *       exclude: /static/**,/templates/**
     *
     * 另一方面，如果想彻底关闭自动重启，可以将 spring.devtools.restart.enabled 设置为 false：
     *
     * spring:
     *   devtools:
     *     restart:
     *       enabled: false
     *
     * 另外，还可以设置一个触发文件，必须修改这个文件才能触发重启。例如，在修改名为 .trigger 的文
     * 件前你都不希望执行重启，那么你只需像这样设置 spring.devtools.restart.trigger-file 属性：
     *
     * spring:
     *   devtools:
     *     restart:
     *       trigger-file: .trigger
     *
     * 如果你的 IDE 会连续编译修改的文件，那触发文件还是很有用的。没有触发文件的话，每次变更都会触
     * 发重启。有触发文件，就能保证只有你想重启时才会发生重启（修改触发文件即可）。
     */
    public static void main(String[] args) {

    }

}
