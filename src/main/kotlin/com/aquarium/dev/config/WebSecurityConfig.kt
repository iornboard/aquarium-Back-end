package com.aquarium.dev.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import com.aquarium.dev.config.auth.oauth.PrincipalOauth2UserService
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.web.filter.CorsFilter


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true )  // @secured 어노테이션 활성화 시킴 -> 특정 메서드에 간단히 걸 수 있는 인가 설정 , ( @PreAuthorize , @PostAuthorize 등등이 있다.)
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private val corsFilter : CorsFilter? = null

    @Autowired
    private val principalOauth2UserService: PrincipalOauth2UserService? = null

    // 매서드의 리턴되는 오브젝트는 IOC로 등록되는 용도  (비밀번호 암호화 인코더)
    @Bean
    open fun encodePwd(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // 세션을 사용하지 않음 - JWT 사용!!  (토큰은 우선 나중에 적용하기)
//                    .and()
//                    .addFilter(corsFilter)  // cosr 필터시 cosr요청 외의 모든것도 허용됨
//                    .httpBasic().disable()  // http에서(https X ) httpBasic을 허용하지 않음 - JWT 사용!!
                .authorizeRequests()
                    .antMatchers("/", "/home","/sample","/join","/login","/logout","/token").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/loginForm")
                    .loginProcessingUrl("/login") // '/login' 요청시에 시큐리티가 대신 처리하는 구조
                    .defaultSuccessUrl("/")
                    .permitAll()
                    .and()
                    .oauth2Login()  // 아래부터 구글 로그인 로직
                    .loginPage("/loginForm")
                    .userInfoEndpoint()
                    .userService(principalOauth2UserService)


        //http.addFilterBefore(DevFliter(), BasicAuthenticationFilter::class.java)  // 커스텀 필터를 거는 방법(직접 걸기)
    }


}