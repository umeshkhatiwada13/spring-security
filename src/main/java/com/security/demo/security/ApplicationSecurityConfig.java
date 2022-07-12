package com.security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author umeshkhatiwada13@infodev
 * @project demo1
 * @created 07/07/2022 - 9:20 AM
 */
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Anu request must be authenticated (ie client must provide username and password)
        http.authorizeRequests()
                //Permit all request for requrst to below given urls
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                // Allow all apis starting with /api/ to be accessed by users with role student only
                .antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
                .anyRequest()
                .authenticated()
                //Authenticity of client is done using basic authentication
                .and().httpBasic();
    }

    @Override
    @Bean
    // Method to retrieve user from database
    protected UserDetailsService userDetailsService() {
        UserDetails ram = User.builder().username("ram")
                .password(passwordEncoder.encode("ram123"))
                .roles(ApplicationUserRole.STUDENT.name()).build();
        UserDetails hari = User.builder().username("hari")
                .password(passwordEncoder.encode("hari123")).
                roles(ApplicationUserRole.ADMIN.name()).build();
        System.out.println("Hello world1");
        return new InMemoryUserDetailsManager(ram, hari);
    }
}
