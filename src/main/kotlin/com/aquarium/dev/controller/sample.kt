package com.aquarium.dev.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.GetMapping




@Controller
class sample {

    @GetMapping(path = ["", "/","/home"])
    fun home(): String? {
        return "home"
    }

    @GetMapping("/login")
    fun login(): String? {
        return "login"
    }

    @GetMapping("/hello")
    fun hello(): String? {
        return "hello"
    }

}