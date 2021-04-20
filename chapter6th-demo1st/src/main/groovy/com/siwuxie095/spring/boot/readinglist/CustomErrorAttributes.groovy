package com.siwuxie095.spring.boot.readinglist

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestAttributes

/**
 * @author Jiajing Li 
 * @date 2021-04-20 22:08:01
 */
@Component
class CustomErrorAttributes extends DefaultErrorAttributes {

    Map<String, Object> getErrorAttributes(
            RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> attributes = Object.getErrorAttributes(requestAttributes, includeStackTrace)
        attributes["foo"] = "bar"
        attributes
    }

}

