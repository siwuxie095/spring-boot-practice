package com.siwuxie095.spring.boot.chapter7th.example7th;

/**
 * @author Jiajing Li
 * @date 2021-04-28 23:07:41
 */
public class Main {

    /**
     * 小结
     *
     * 想弄清楚运行的应用程序里正在发生什么，这是件很困难的事。Spring Boot 的 Actuator 为你打开了
     * 一扇大门，深入 Spring Boot 应用程序的内部细节。它发布的组件、度量和指标能帮你理解应用程序的
     * 运作情况。
     *
     * 在这里，先了解了 Actuator 的 Web 端点 —— 通过 HTTP 发布运行时细节信息的 REST 端点。这些端
     * 点的功能包括查看 Spring 应用程序上下文里所有的 Bean、查看自动配置决策、查看 Spring MVC 映射、
     * 查看线程活动、查看应用程序健康信息，还有多种度量、指标和计数器。
     *
     * 除了 Web 端点，Actuator 还提供了另外两种获取它所提供信息的途径。远程 shell 让你能在 shell
     * 里安全地连上应用程序，发起指令，获得与 Actuator 端点相同的数据。与此同时，所有的 Actuator
     * 端点也都发布成了 MBean，可以通过 JMX 客户端进行监控和管理。
     *
     * 随后还了解了如何定制 Actuator，包括如何通过端点的 ID 来修改 Actuator 端点的路径，如何启用和
     * 禁用端点，诸如此类。还插入了一些定制的度量信息，创建了定制的跟踪信息仓库，替换了默认的内存跟踪
     * 仓库。
     *
     * 最后，学习了如何保护 Actuator 的端点，只让经过授权的用户访问它们。
     */
    public static void main(String[] args) {

    }

}
