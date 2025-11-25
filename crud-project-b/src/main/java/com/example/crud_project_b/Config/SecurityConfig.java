package com.example.crud_project_b.Config;

import com.example.crud_project_b.Service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable()) // 필요 시 CORS 설정은 따로
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/user/join", "/user/login").permitAll()
                        .anyRequest().permitAll()     // 개발 단계에서는 모든 요청 허용 추천
                )
                .formLogin(form -> form.disable())     // 🔥 formLogin 끄기
                .httpBasic(basic -> basic.disable());  // 🔥 기본 로그인도 끄기

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}