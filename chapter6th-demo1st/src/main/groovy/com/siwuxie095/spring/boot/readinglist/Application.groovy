package com.siwuxie095.spring.boot.readinglist

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

/**
 * @author Jiajing Li 
 * @date 2021-04-20 22:06:07
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
class Application extends WebMvcConfigurerAdapter {

    static void main(String[] args) {
        SpringApplication.run(Application.class, args)
    }

    void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/readingList")
        registry.addViewController("/login").setViewName("login")
    }

    void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new ReaderHandlerMethodArgumentResolver())
    }

}

