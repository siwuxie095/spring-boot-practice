package com.siwuxie095.spring.boot.readinglist;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

/**
 * @author Jiajing Li
 * @date 2021-04-22 23:17:54
 */
@SuppressWarnings("all")
@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(
            RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> attributes = super.getErrorAttributes(requestAttributes, includeStackTrace);

        attributes.put("foo", "bar");

        return attributes;
    }

}

