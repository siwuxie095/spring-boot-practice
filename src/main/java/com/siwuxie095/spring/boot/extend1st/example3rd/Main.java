package com.siwuxie095.spring.boot.extend1st.example3rd;

/**
 * @author Jiajing Li
 * @date 2021-05-01 15:10:03
 */
public class Main {

    /**
     * LiveReload
     *
     * 在 Web 应用程序开发过程中，最常见的步骤大致如下。
     * （1）修改要呈现的内容（比如图片、样式表、模板）。
     * （2）点击浏览器里的刷新按钮，查看修改的结果。
     * （3）回到第（1）步。
     *
     * 虽然这并不难，但如果能不点刷新就直接看到修改结果，那岂不是更好？
     *
     * Spring Boot 的开发者工具集成了 LiveReload（http://livereload.com），可以消除刷新的步骤。
     * 激活开发者工具后，Spring Boot 会启动一个内嵌的 LiveReload 服务器，在资源文件变化时会触发浏
     * 览器刷新。你要做的就是在浏览器里安装 LiveReload 插件。
     *
     * 如果想要禁用内嵌的 LiveReload 服务器，可以将 spring.devtools.livereload.enabled 设置为
     * false：
     *
     *  spring:
     *     devtools:
     *       livereload:
     *         enabled: false
     */
    public static void main(String[] args) {

    }

}
