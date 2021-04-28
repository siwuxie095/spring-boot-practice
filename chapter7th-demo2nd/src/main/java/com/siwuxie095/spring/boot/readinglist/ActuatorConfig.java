package com.siwuxie095.spring.boot.readinglist;

import org.springframework.boot.actuate.trace.InMemoryTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jiajing Li
 * @date 2021-04-28 22:25:40
 */
@SuppressWarnings("all")
@Configuration
public class ActuatorConfig {

    @Bean
    public InMemoryTraceRepository traceRepository() {
        InMemoryTraceRepository traceRepo = new InMemoryTraceRepository();
        traceRepo.setCapacity(1000);
        return traceRepo;
    }

}
