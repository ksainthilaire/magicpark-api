package com.magicpark.api.adapters

import com.magicpark.api.adapters.MyCustomDsl.Companion.customDsl
import com.magicpark.api.services.UserService
import com.magicpark.api.utils.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.OrRequestMatcher


class MyCustomDsl(private val service: UserService) : AbstractHttpConfigurer<MyCustomDsl?, HttpSecurity?>() {


    override fun configure(http: HttpSecurity?) {
        val authenticationManager = http?.getSharedObject(
            AuthenticationManager::class.java
        )
        http?.addFilter(JWTInterceptor(ApplicationSecurity.AUTH_HEADER_NAME, service, authenticationManager!!))
    }

    companion object {
        fun customDsl(service: UserService): MyCustomDsl {
            return MyCustomDsl(service)
        }
    }
}

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
class ApplicationSecurity {

    companion object {
        const val AUTH_HEADER_NAME = "token"
    }


    @Autowired
    private lateinit var service: UserService


    @Throws(Exception::class)
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable()
        http
            .requestMatchers()
            .antMatchers("/api/v1/customer/user", "/api/v1/customer/order", "/api/v1/customer/wallet")
            .and()
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .apply(customDsl(service))

        return http.build()
    }
}