package com.aquarium.dev.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true )  // @secured 어노테이션 활성화 시킴 -> 특정 메서드에 간단히 걸 수 있는 인가 설정 , ( @PreAuthorize , @PostAuthorize 등등이 있다.)
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    // 매서드의 리턴되는 오브젝트는 IOC로 등록되는 용도  (비밀번호 암호화 인코더)
    @Bean
    open fun encodePwd(): BCryptPasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                    .antMatchers("/", "/home","/sample","/join","/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/loginForm")
                    .loginProcessingUrl("/login") // '/login' 요청시에 시큐리티가 대신 처리하는 구조
                    .defaultSuccessUrl("/")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()

    }


}