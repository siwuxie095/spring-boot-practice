package com.siwuxie095.spring.boot.chapter7th.example4th;

/**
 * @author Jiajing Li
 * @date 2021-04-27 21:05:55
 */
public class Main {

    /**
     * 通过 JMX 监控应用程序
     *
     * 除了 REST 端点和远程 shell，Actuator 还把它的端点以 MBean 的方式发布了出来，可以通过 JMX 来
     * 查看和管理。使用 JMX 是管理 Spring Boot 应用程序的一个好方法，如果你已在用 JMX 管理应用程序中
     * 的其他 MBean，则尤其如此。
     *
     * Actuator 的端点都发布在 org.springframework.boot 域下。比如，你想要查看应用程序的请求映射关
     * 系，那么可以通过 JConsole 查看请求映射端点。
     *
     * 在 requestMappingEndpoint 下可以找到请求映射端点，位于 org.springframework.boot 域中的
     * Endpoint 下。Data 属性中包含了该端点所要输出的 JSON 内容。
     *
     * 和其他 MBean 一样，端点 MBean 有可供调用的操作。大部分端点 MBean 只有访问操作，返回其中的某个
     * 属性，但 /shutdown 端点提供了一些有趣（同时具有毁灭性）的操作。
     *
     * 如果你想要关闭应用程序（或者喜欢冒险），那么关闭应用的端点正合你意。
     *
     * 在那以后，你的应用程序就关闭了。应用已经关闭，自然就没办法发布其他用来重启它的 MBean 操作。你必
     * 须重启，和一开始的启动方式一样。
     */
    public static void main(String[] args) {

    }

}
