package com.siwuxie095.spring.boot.readinglist;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Jiajing Li
 * @date 2021-04-12 21:24:16
 */
@SuppressWarnings("all")
@Component
@ConfigurationProperties("amazon")
public class AmazonProperties {

    private String associateId;

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }

    public String getAssociateId() {
        return associateId;
    }

}

