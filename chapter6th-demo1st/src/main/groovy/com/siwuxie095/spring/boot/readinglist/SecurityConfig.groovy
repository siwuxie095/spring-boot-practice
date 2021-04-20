package com.siwuxie095.spring.boot.readinglist

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService

/**
 * @author Jiajing Li 
 * @date 2021-04-20 22:11:23
 */
@Configuration
class SecurityConfig extends WebSecurityConfigurerAdapter {

    void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").access("hasRole('READER')")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
    }

    void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(
                { username -> Reader.findByUsername(username) }
                        as UserDetailsService)
    }

}

