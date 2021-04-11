package com.siwuxie095.spring.boot.chapter2nd.example4th;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author Jiajing Li
 * @date 2021-04-11 17:08:20
 */
@SuppressWarnings("all")
public class JdbcTemplateCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context,
                           AnnotatedTypeMetadata metadata) {
        try {
            context.getClassLoader().loadClass(
                    "org.springframework.jdbc.core.JdbcTemplate");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
