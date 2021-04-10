package com.aquarium.dev.controller


import com.aquarium.dev.domain.entity.User
import com.aquarium.dev.domain.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
internal class account (userRepository: UserRepository) {

    @Autowired
    private val userRepository: UserRepository

    @Autowired
    private val bCryptPasswordEncoder: BCryptPasswordEncoder? = null

    @GetMapping("/foo")
    fun foo(): String {
        return "it is a foo"
    }

    @GetMapping("/login")
    fun login(): String {
        return "it is a foo"
    }

    @PostMapping("/join")
    fun join( @RequestBody  user : User ): String? {

        user.userRole = "USER_ROLE"
        user.username = user.userEmail
        val rawPassword : String? = user.password
        val encPassword = bCryptPasswordEncoder!!.encode(rawPassword)
        user.password = encPassword
        user.provider = "aquarium"
        user.providerId ="aquarium"
        userRepository.save(user)

        return "redirect:/api/signup"
    }

    init {
        this.userRepository = userRepository
    }

}