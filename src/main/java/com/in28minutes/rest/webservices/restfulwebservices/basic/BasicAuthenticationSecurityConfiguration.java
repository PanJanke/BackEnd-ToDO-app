package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class BasicAuthenticationSecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests(
                auth -> auth
                        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                       // .antMatchers("/**").permitAll()
                        // .mvcMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                        .anyRequest().authenticated()

                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf().disable()
                .build();
    }
}
/*
        return http
                .authorizeRequests(auth -> auth
                    .mvcMatchers("/authenticate").permitAll()
                    .mvcMatchers("/h2-console/**").permitAll() // h2-console is a servlet and NOT recommended for a production
                    .mvcMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                    .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(
                        OAuth2ResourceServerConfigurer::jwt)
                .httpBasic(
                        Customizer.withDefaults())
                .headers(header -> {header.frameOptions().sameOrigin();})
                .build();
    }
}



*/