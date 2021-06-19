package com.aquarium.dev.controller

import com.aquarium.dev.domain.entity.User.User
import com.aquarium.dev.domain.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.RequestHeader


@Controller
internal class SampleController(userRepository: UserRepository) {

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

    @GetMapping("/token" )
    fun token(): String {

        return "redirect:http://www.naver.com"

    }

    @PostMapping("/join")
    fun sample( user : User): String? {
        println(user)
        user.userRole = "USER_ROLE"
        val rawPassword : String? = user.password
        val encPassword = bCryptPasswordEncoder!!.encode(rawPassword)
        user.password = encPassword
        userRepository.save(user)

        return "redirect:/loginForm"
    }


    init {
        this.userRepository = userRepository
    }

}