package com.johurulislam.springsecuritybasics.config;

import com.johurulislam.springsecuritybasics.service.CustomerUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
public class WebSecurity {
    private final CustomerUserDetailsService customerUserDetailsService;
    public WebSecurity(CustomerUserDetailsService customerUserDetailsService) {
        this.customerUserDetailsService = customerUserDetailsService;
    }
    @Bean
    @SuppressWarnings("deprecation")
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headerConfigurer -> headerConfigurer
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                )
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(antMatcher("/login/**")).permitAll()
                        .requestMatchers(antMatcher("/register-user")).permitAll()
                        .requestMatchers(antMatcher("/register-admin")).hasRole("ADMIN")
                        .requestMatchers(antMatcher("/user-dashboard")).hasAnyRole("USER", "ADMIN")
                        .requestMatchers(antMatcher("/admin-dashboard")).hasRole("ADMIN")
                        .requestMatchers(antMatcher("/login.css")).permitAll()
                        .requestMatchers(antMatcher("/user-dashboard.css")).permitAll()
                        .anyRequest().fullyAuthenticated()

                )
                .formLogin(loginCongiger -> loginCongiger
                        .loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/dashboard",true)
                        .failureUrl("/login?error=true")
                )
                .logout(logoutConfigurer -> logoutConfigurer
                        .logoutUrl("/logout")
                )
                .userDetailsService(customerUserDetailsService);

        return httpSecurity.build();
    }

}
