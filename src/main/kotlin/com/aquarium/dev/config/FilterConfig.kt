package com.aquarium.dev.config

import com.aquarium.dev.filter.DevFliter1
import com.aquarium.dev.filter.DevFliter2
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.boot.web.servlet.FilterRegistrationBean



@Configuration
class FilterConfig {

//    @Bean
//    fun filter1(): FilterRegistrationBean<DevFliter1>? {
//        val bean: FilterRegistrationBean<DevFliter1> = FilterRegistrationBean<DevFliter1>(DevFliter1())
//        bean.addUrlPatterns("/*")
//        bean.setOrder(0) // 실행 순서
//        return bean
//    }
//
//    @Bean
//    fun filter2(): FilterRegistrationBean<DevFliter2>? {
//        val bean: FilterRegistrationBean<DevFliter2> = FilterRegistrationBean<DevFliter2>(DevFliter2())
//        bean.addUrlPatterns("/*")
//        bean.setOrder(1) // 실행 순서
//        return bean
//    }

}