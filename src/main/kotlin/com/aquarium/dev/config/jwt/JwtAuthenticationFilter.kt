package com.aquarium.dev.config.jwt

import com.aquarium.dev.config.auth.PrincipalDetails
import com.aquarium.dev.domain.entity.User.User
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import javax.servlet.ServletException
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.authentication.AuthenticationManager


//formLogin 필터 부분(커스텀)
class JwtAuthenticationFilter( authenticationManager : AuthenticationManager ) : UsernamePasswordAuthenticationFilter() {

    val authManager: AuthenticationManager

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication {
        println("JwtAuthenticationFilter : 진입")

        //web -> json -> mapper (로그인 요청)
        val mapper = ObjectMapper()
        val user : User = mapper.readValue(request.inputStream, User::class.java)

        // 토큰 생성
        val authenticationToken = UsernamePasswordAuthenticationToken(user.username, user.password )

        println("authenticationToken : $authenticationToken")

        //authenticationManager -> principalservice의 로더 실행(authentication == 로그인 정보)
        //DB에 유저 정보가 존재 하는지
        val authentication = authManager.authenticate(authenticationToken)


        //세션 영역에 저장됨
        return authentication
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(
        request: HttpServletRequest?, response: HttpServletResponse, chain: FilterChain?,
        authResult: Authentication
    ) {

        val principalDetailis = authResult.principal as PrincipalDetails
        val jwtToken: String = JWT.create()
            .withSubject(principalDetailis.username)
            .withExpiresAt(Date(System.currentTimeMillis() + (JwtProperties.EXPIRATION_TIME)))
            .withClaim("id", principalDetailis.getUser()!!.userId)   // 수정할 것
            .withClaim("username", principalDetailis.username)
            .sign(Algorithm.HMAC512(JwtProperties.SECRET))

        //응답 처리 (in header)
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken)  // 추후에 " "으로 분리 시킬 것임 공백이 필요
        response.addHeader("redirectUrl", principalDetailis.getUserNickname())  // 추후에 " "으로 분리 시킬 것임 공백이 필요
    }

    init {
        authManager = authenticationManager
        this.setFilterProcessesUrl("/api/login")
        this.usernameParameter = "username"
        this.passwordParameter ="password"
    }
}