package com.jane.securityjwtpractice.auth.config;

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
                    .requestMatchers("/api/auth/signup").permitAll() // 누구나 접근 가능
                    .requestMatchers("/h2-console/**").permitAll() // h2-console 은 누구나 접속 허용
                    .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.disable()) // 임시 비활성화
            .httpBasic(httpBasic -> httpBasic.disable()) // http basic 비활성화
            .formLogin(form -> form.disable()) // form 로그인 비활성화
            .headers(headers -> headers
                    .frameOptions(frame -> frame.sameOrigin()) // h2-console iframe 허용
            );
        return http.build();
    }
}
