package com.siwuxie095.spring.boot.readinglist;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jiajing Li
 * @date 2021-04-22 23:06:16
 */
@SuppressWarnings("all")
@Component
public class AmazonHealth implements HealthIndicator {

    @Override
    public Health health() {

        try {
            RestTemplate rest = new RestTemplate();
            rest.getForObject("http://www.amazon.com", String.class);
            return Health.up().build();
        } catch (Exception e) {
            return Health.down().withDetail("reason", e.getMessage()).build();
        }
    }

}

