package com.aquarium.dev.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry


@Configuration
class WebMvcConfig {
    fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/home").setViewName("home")
        registry.addViewController("/").setViewName("home")
        registry.addViewController("/hello").setViewName("hello")
        registry.addViewController("/login").setViewName("loginFrom")
    }
}