package dev.ardijorganxhi.mlaas.config.security

import dev.ardijorganxhi.mlaas.filter.JwtAuthenticationFilter
import dev.ardijorganxhi.mlaas.service.AuthService
import dev.ardijorganxhi.mlaas.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
class SecurityConfiguration(@Lazy private val authService: AuthService, private val userService: UserService) {

    @Bean
    fun authenticationManagerBean(http: HttpSecurity): AuthenticationManager {
        val authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        authenticationManagerBuilder.userDetailsService(userService)
        return authenticationManagerBuilder.build()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.cors()
            .and()
            .csrf().disable()
            .headers()
            .frameOptions().disable()
            .and()
            .authenticationManager(authenticationManagerBean(http))
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(JwtAuthenticationFilter(authService), UsernamePasswordAuthenticationFilter::class.java)
            .authorizeRequests().anyRequest().authenticated()

        return http.build()
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.debug(false)
                .ignoring()
                .antMatchers(*getWhiteList())
        }
    }

    private fun getWhiteList(): Array<String> {
        return arrayOf(
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/webjars/**",
            "/v2/**",
            "/actuator/**",
            "/favicon.ico",
            "/csrf",
            "/",
            "/api/auth/**"
        )
    }
}