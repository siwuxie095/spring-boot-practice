package com.siwuxie095.spring.boot.readinglist

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * @author Jiajing Li 
 * @date 2021-04-20 22:04:09
 */
@Component
@ConfigurationProperties("amazon")
class AmazonConfig {

    String associateId

    def getAssociateId() {
        associateId
    }

}

