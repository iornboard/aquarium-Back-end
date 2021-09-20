package com.aquarium.dev.config

import com.aquarium.dev.config.auth.oauth.AuthenticationSuccessHandler
import com.aquarium.dev.config.auth.oauth.PrincipalOauth2UserService
import com.aquarium.dev.config.jwt.JwtAuthenticationFilter
import com.aquarium.dev.config.jwt.JwtAuthorizationFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.web.filter.CorsFilter
import com.aquarium.dev.domain.repository.User.UserRepository
import org.springframework.web.cors.CorsConfiguration


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true )  // @secured 어노테이션 활성화 시킴 -> 특정 메서드에 간단히 걸 수 있는 인가 설정 , ( @PreAuthorize , @PostAuthorize 등등이 있다.)
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    //! 임시 !//
    @Autowired
    private val principalOauth2UserService: PrincipalOauth2UserService? = null
    //! 임시 !//

    @Autowired
    private val userRepository: UserRepository? = null

    @Autowired
    private val corsFilter : CorsFilter? = null

    // 매서드의 리턴되는 오브젝트는 IOC로 등록되는 용도  (비밀번호 암호화 인코더)
    @Bean
    open fun encodePwd(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {

        //http.addFilterBefore(JwtFilter(), SecurityContextPersistenceFilter::class.java)  // Authorization 필터
        http.cors().configurationSource { request -> CorsConfiguration().applyPermitDefaultValues() }   // 코드 내용은 잘 모르겠는데 CORS error를 막기위해서 사용할 수 있다고 함 (알아볼 것)  // 출처 : https://velog.io/@dsunni/Spring-Boot-React-JWT%EB%A1%9C-%EA%B0%84%EB%8B%A8%ED%95%9C-%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0
        http.csrf().disable()
        http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // 세션을 사용하지 않음 - JWT 사용!!  (토큰은 우선 나중에 적용하기)
            .and()
            .addFilter(corsFilter)  // cosr 필터시 cosr요청 외의 모든것도 허용됨
            .formLogin().disable()  // formLogin을 사용하지 않음
            .httpBasic().disable()  // http에서(https X ) httpBasic을 허용하지 않음 - JWT 사용!!
            .addFilter(JwtAuthenticationFilter(authenticationManager())) // formLogin 대신 커스텀 필터  + param AuthenticationManager
            .addFilter(JwtAuthorizationFilter(authenticationManager(), userRepository ))
            .authorizeRequests()
            .antMatchers("/home").permitAll()
            .anyRequest().permitAll()

        http.oauth2Login()
            .userInfoEndpoint().userService(principalOauth2UserService)
            .and()
            .successHandler(AuthenticationSuccessHandler(authenticationManager()))
//            .failureHandler(configFailureHandler())
            .permitAll()


    }

}