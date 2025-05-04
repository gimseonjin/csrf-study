package com.kerry.miniiam.config

import com.kerry.miniiam.config.auth.UserDetailsServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.csrf.CookieCsrfTokenRepository

@Configuration
class SecurityConfig(
    private val userDetailsServiceImpl: UserDetailsServiceImpl
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf {
            it.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        }

        http.formLogin {
            it
                .loginPage("/login")
                .defaultSuccessUrl("/my-documents", true)
                .permitAll()
        } // 기본 form login 사용

        http
            .authorizeHttpRequests {
                it
                    .requestMatchers("/login", "/register", "/users", "/csrf-attack.html").permitAll()
                    .anyRequest().authenticated()
            }

        return http.build()
    }

    @Bean
    fun authenticationManager(http: HttpSecurity): AuthenticationManager {
        return http.getSharedObject(AuthenticationManagerBuilder::class.java)
            .userDetailsService(userDetailsServiceImpl)
            .passwordEncoder(passwordEncoder())
            .and()
            .build()
    }
}