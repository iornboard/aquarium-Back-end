package com.aquarium.dev.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter


// Cors의 대해서 개요를 좀 더 공부해 보기
// 출처 : https://github.com/codingspecialist/Springboot-Security-JWT-Easy/blob/master/src/main/java/com/cos/jwtex01/config/CorsConfig.java
@Configuration
class CorsConfig {

    @Bean
    fun corsFilter(): CorsFilter {

        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true  // 서버에서 응답시(axios fetch) json을 자바스크립트에서 처리할 수 있게 할지 설정 하는 부분
        config.addAllowedOrigin("*") //  모든 ip응답 허용
        config.addAllowedHeader("*") //  모든 헤더 응답 허용
        config.addAllowedMethod("*") //  모든 메서드 응답 허용
        source.registerCorsConfiguration("/api/**", config)  //   /api로 들어오는 모든 주소를 허용

        return CorsFilter(source)
    }

}