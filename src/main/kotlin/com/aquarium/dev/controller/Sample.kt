package com.aquarium.dev.controller

import com.aquarium.dev.domain.entity.User
import com.aquarium.dev.domain.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder





@Controller
internal class Sample(userRepository: UserRepository) {

    @Autowired
    private val userRepository: UserRepository

    @Autowired
    private val bCryptPasswordEncoder: BCryptPasswordEncoder? = null

    @GetMapping(path = ["", "/","/home"])
    fun home(): String? {
        return "home"
    }

    @GetMapping("/loginForm")
    fun login(): String? {
        return "loginFrom"
    }

    @GetMapping("/hello")
    fun hello(): String? {
        return "hello"
    }

    @GetMapping("/sample")
    fun join(): String? {
        return "sample"
    }

    @PostMapping("/push")
    fun push(): String? {
        return "sample"
    }


    @PostMapping("/token")
    fun token() {
    }

    @PostMapping("/join")
    fun sample( user : User ): String? {
        println(user)
        user.userRole = "USER_ROLE"
        val rawPassword : String? = user.password
        val encPassword = bCryptPasswordEncoder!!.encode(rawPassword)
        user.password = encPassword
        user.provider = "aquarium"
        user.providerId ="aquarium"
        userRepository.save(user)

        return "redirect:/loginForm"
    }


    init {
        this.userRepository = userRepository
    }

}