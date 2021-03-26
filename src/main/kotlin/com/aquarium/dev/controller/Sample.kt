package com.aquarium.dev.controller

import com.aquarium.dev.domain.entity.User
import com.aquarium.dev.domain.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder





@Controller
internal class sample(userRpository: UserRepository) {

    @Autowired
    private val userRpository: UserRepository

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

    @PostMapping("/join")
    fun sample( user : User): String? {
        println(user)
        user.user_role = "USER_ROLE"
        val rawPassword : String ?= user.user_password
        val encPassword = bCryptPasswordEncoder!!.encode(rawPassword)
        user.user_password = encPassword
        userRpository.save(user)

        return "redirect:/sample"
    }


    init {
        this.userRpository = userRpository
    }

}