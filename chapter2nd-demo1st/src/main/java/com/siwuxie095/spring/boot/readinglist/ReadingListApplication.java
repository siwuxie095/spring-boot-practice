package com.siwuxie095.spring.boot.readinglist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ReadingListApplication.java 不仅是启动引导类，还是配置类
 *
 * @author Jiajing Li
 * @date 2021-04-08 08:02:06
 */
@SuppressWarnings("all")
// 开启组件扫描和自动配置
@SpringBootApplication
public class ReadingListApplication {

    public static void main(String[] args) {
        // 负责启动引导应用程序
        SpringApplication.run(ReadingListApplication.class, args);
    }

}
