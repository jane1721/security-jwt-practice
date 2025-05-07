package com.jane.securityjwtpractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/hello").permitAll() // 누구나 접근 가능
                    .requestMatchers("/api/secure").authenticated() // 인증 필요
                    .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.disable()) // 임시 비활성화
            .httpBasic(httpBasic -> httpBasic.disable()) // http basic 비활성화
            .formLogin(form -> form.disable()); // form 로그인 비활성화
        return http.build();
    }
}
