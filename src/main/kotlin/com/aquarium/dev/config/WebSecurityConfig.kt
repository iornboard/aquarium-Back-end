package com.aquarium.dev.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
@EnableWebSecurity
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
                    .antMatchers("/", "/home","/sample","/join").permitAll()
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