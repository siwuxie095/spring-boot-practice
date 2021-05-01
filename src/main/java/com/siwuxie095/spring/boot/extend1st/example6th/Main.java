package com.siwuxie095.spring.boot.extend1st.example6th;

/**
 * @author Jiajing Li
 * @date 2021-05-01 15:36:52
 */
public class Main {

    /**
     * 全局配置开发者工具
     *
     * 你应该已经注意到了，在使用开发者工具时，你通常会在多个项目里使用相同的设置。举个例子，如果你
     * 使用了重启触发文件，那么你很可能在多个项目里都使用相同的触发文件名。相比在每个项目里重复开发
     * 者工具配置，对开发者工具做全局配置显得更方便一些。
     *
     * 要实现这个目的，可以在你的主目录（home directory）里创建一个名为 .spring-boot-devtools
     * .properties 的文件（请注意，文件名用 "." 开头）。在那个文件里，你可以设置希望在多个项目里
     * 共享的各种开发者工具属性。
     *
     * 例如，假设你想把触发文件的名称设置为 .trigger，在所有 Spring Boot 项目里禁用 LiveReload。
     * 你可以创建一个 .spring-boot-devtools.properties 文件，包含如下内容：
     *
     * spring.devtools.restart.trigger-file=.trigger
     * spring.devtools.livereload.enabled=false
     *
     * 要是你想覆盖这些配置，可以在每个项目的 application.properties 或 application.yml 文件
     * 里设置特定于每个项目的属性。
     */
    public static void main(String[] args) {

    }

}
