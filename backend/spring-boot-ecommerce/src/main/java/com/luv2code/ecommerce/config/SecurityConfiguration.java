package com.luv2code.ecommerce.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 暂时禁用认证，允许所有请求
        http.authorizeRequests()
                .anyRequest().permitAll();

        // add CORS filters
        http.cors();

        // disable CSRF since we are not using Cookies for session tracking
        http.csrf().disable();
    }
}













