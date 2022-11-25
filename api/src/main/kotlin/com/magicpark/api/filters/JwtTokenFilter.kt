package com.magicpark.api.filters


import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/*
class JwtTokenFilter(val headerName: String) : AbstractAuthenticationProcessingFilter() {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        TODO("Not yet implemented")
    }
}

    /*
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
        if (!JwtUtilvalidateAccessToken(token)) {
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
        println("TAG:: le token est ${token}")
    }

    private fun getUser(token: String): User {
        val user = User()
        val jwtSubject: List<String> = JwtUtilgetSubject(token).split(",")
        user.id = jwtSubject[0].toLong()
        user.mail = jwtSubject[1]
        return user
    }
}*/