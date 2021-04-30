package com.siwuxie095.spring.boot.readinglist;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author Jiajing Li
 * @date 2021-04-30 21:27:28
 */
@SuppressWarnings("all")
public class ReadingListServletInitializer
        extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(ReadingListApplication.class);
    }

}

