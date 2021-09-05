package com.aquarium.dev.config.auth.oauth

import com.aquarium.dev.config.auth.PrincipalDetails
import com.aquarium.dev.config.jwt.JwtProperties
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import javax.servlet.ServletException

import java.io.IOException
import java.util.*

import javax.servlet.http.HttpServletResponse

import javax.servlet.http.HttpServletRequest


class AuthenticationSuccessHandler( authenticationManager : AuthenticationManager ) : SimpleUrlAuthenticationSuccessHandler() {

    val authManager: AuthenticationManager

    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationSuccess(
        request: HttpServletRequest?, response: HttpServletResponse, authResult: Authentication ) {

        val principalDetailis = authResult.principal as PrincipalDetails
        val jwtToken: String = JWT.create()
            .withSubject(principalDetailis.username)
            .withExpiresAt(Date(System.currentTimeMillis() + (JwtProperties.EXPIRATION_TIME)))
            .withClaim("id", principalDetailis.getUser()!!.userId)   // 수정할 것
            .withClaim("username", principalDetailis.username)
            .sign(Algorithm.HMAC512(JwtProperties.SECRET))

        //응답 처리 (in header)
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken)  // 추후에 " "으로 분리 시킬 것임 공백이 필요
        redirectStrategy.sendRedirect(request,response,"/token")
    }

    init {
        authManager = authenticationManager
    }

}