package com.aquarium.dev.config.jwt


import com.aquarium.dev.domain.entity.User
import com.aquarium.dev.domain.repository.UserRepository
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.authentication.AuthenticationManager
import javax.servlet.ServletException
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest
import com.aquarium.dev.config.auth.PrincipalDetails
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder


//BasicAuthenticationFilter 대신(권한 인증이 필요한 요청을 받는 곳(낚아채는 곳))
class JwtAuthorizationFilter(authenticationManager: AuthenticationManager? , var userRepository : UserRepository?) : BasicAuthenticationFilter(authenticationManager) {

    @Throws(IOException::class, ServletException::class)
    protected override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {

        println("인증 요청이 실행됩니다. ")

        // 사용자의 헤더에 보내진 토큰을 받음
        val jwtHeader: String? = request.getHeader(JwtProperties.HEADER_STRING)


        // 해더가 있는지 or 우리 토큰이 맟는지 -> 없으면 끝냄
        if (jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        // 위에서 받은 토큰확인해서 username을 확인
        val token: String = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX, "")

        val username: String? = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
                .getClaim("username").asString()


        // ( 정상적인 처리 ) username이 정상적으로 있을 때 -> DB의 user객체를 찾는다.
        if (username != null) {
            val user: User = userRepository!!.findByUsername(username);

            // user정보를 바탕으로 Authentication 객체를 만든다.
            val principalDetails = PrincipalDetails(user)
            val authentication : Authentication = UsernamePasswordAuthenticationToken(
                    principalDetails,
                    null,  // 인증용이 아닌 Authentication 객체를 만들기 때문에 패스워드는 비워놓는다.
                    principalDetails.authorities)

            // 강제로 시큐리티의 세션에 접근해서 Authentication을 저장(인증용은 아닌 듯 )
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

}