package com.magicpark.api.adapters

import com.magicpark.api.model.database.User
import com.magicpark.api.services.UserService
import com.magicpark.api.utils.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.stereotype.Component
import java.io.IOException
import java.lang.RuntimeException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse



class CustomAuthentication(var user: User): Authentication {
    override fun getName(): String? {
        return user.fullName
    }

    override fun getAuthorities():  MutableCollection<out GrantedAuthority> = mutableListOf()

    override fun getCredentials(): String? {
        return user.token
    }

    override fun getDetails(): User {
        return user
    }

    override fun getPrincipal(): User {
       return user
    }

    override fun isAuthenticated(): Boolean = true

    override fun setAuthenticated(isAuthenticated: Boolean) {}

}


class JWTInterceptor(private val headerName: String, private val service: UserService, authenticationManager: AuthenticationManager) : BasicAuthenticationFilter(authenticationManager) {





    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        try {
            val jwtToken = request.getHeader(headerName)

            if (!JwtUtil.validateAccessToken(jwtToken)) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.reasonPhrase);
                return
            }

            val jwtSubject: List<String> = JwtUtil.getSubject(jwtToken).split(",")
            val userId = jwtSubject[0].toLong()
            val userMail = jwtSubject[1]

            val user = service.getUserById(userId)
            println("Token: ${user} :: ${userId}")

            SecurityContextHolder.getContext().authentication = CustomAuthentication(user)
            chain.doFilter(request, response)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
}
/*

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        try {
            val jwtToken = request?.getHeader(headerName)

            if (jwtToken == null || !JwtUtilvalidateAccessToken(jwtToken)) {
                throw BadCredentialsException("The token was not found")
            }

            val jwtSubject: List<String> = JwtUtilgetSubject(jwtToken).split(",")
            val userId = jwtSubject[0].toLong()
            val userMail = jwtSubject[1]

            val user = service.getUserById(userId)
            println("Token: ${user}")
            return authenticationManager.authenticate(CustomAuthentication(user))
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

}

 */

