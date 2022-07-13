package com.security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
        //Any request must be authenticated (ie client must provide username and password)
        http.csrf().disable()
                .authorizeRequests()
                //Permit all request for request to below given urls
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                // Allow all apis starting with /api/ to be accessed by users with role student only
                .antMatchers("/api/**").hasAnyAuthority(ApplicationUserRole.STUDENT.name())
                // Allow delete, post and put request to user with authority COURSE_WRITE in management/api/** apis
                .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
                // Allow users with role to access management/api/** apis get methods with roles Admin and Admin_Trainee
                .antMatchers(HttpMethod.GET, "/management/api/**")
                .hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.ADMIN_TRAINEE.name())
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
                .password(passwordEncoder.encode("test123"))
//                .roles(ApplicationUserRole.STUDENT.name())
                .authorities(ApplicationUserRole.STUDENT.getGrantedAuthorities())
                .build();

        UserDetails hari = User.builder().username("hari")
                .password(passwordEncoder.encode("test123"))
//                .roles(ApplicationUserRole.ADMIN.name())
                .authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
                .build();
        UserDetails sita = User.builder().username("sita")
                .password(passwordEncoder.encode("test123"))
//                .roles(ApplicationUserRole.ADMIN_TRAINEE.name())
                .authorities(ApplicationUserRole.ADMIN_TRAINEE.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(ram, hari, sita);
    }
}
