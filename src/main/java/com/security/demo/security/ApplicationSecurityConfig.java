package com.security.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author umeshkhatiwada13@infodev
 * @project demo1
 * @created 07/07/2022 - 9:20 AM
 */
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Anu request must be authenticated (ie client must provide username and password)
        http.authorizeRequests()
                //Permit all request for requrst to below given urls
                .antMatchers("/", "index","/css/*","/js/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                //Authenticity of client is done using basic authentication
                .and().httpBasic();
    }
}