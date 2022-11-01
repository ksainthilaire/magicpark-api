package com.magicpark.api.filters

import com.magicpark.api.model.database.User
import com.magicpark.api.utils.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.ObjectUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class JwtTokenFilter : OncePerRequestFilter() {
    @Autowired
    private lateinit var  jwtUtil: JwtUtil

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (!hasAuthorizationBearer(request)) {
            filterChain.doFilter(request, response)
            return
        }
        val token = getAccessToken(request)
        if (!jwtUtil.validateAccessToken(token)) {
            filterChain.doFilter(request, response)
            return
        }
        setAuthenticationContext(token, request)
        filterChain.doFilter(request, response)
    }

    private fun hasAuthorizationBearer(request: HttpServletRequest): Boolean {
        val header = request.getHeader("Authorization")
        return !(ObjectUtils.isEmpty(header) || !header.startsWith("Bearer"))
    }

    private fun getAccessToken(request: HttpServletRequest): String {
        val header = request.getHeader("Authorization")
        return header.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1].trim { it <= ' ' }
    }

    private fun setAuthenticationContext(token: String, request: HttpServletRequest) {
        val user = getUser(token)

        val authentication = UsernamePasswordAuthenticationToken(user, null, null)
        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authentication
    }

    private fun getUser(token: String): User {
        val user = User()
        val jwtSubject: List<String> = jwtUtil.getSubject(token).split(",")
        user.id = jwtSubject[0].toLong()
        user.mail = jwtSubject[1]
        return user
    }
}