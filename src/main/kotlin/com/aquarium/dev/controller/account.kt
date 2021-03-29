package com.aquarium.dev.controller


import com.aquarium.dev.domain.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
internal class account (userRepository: UserRepository) {

    @Autowired
    private val userRepository: UserRepository

    @GetMapping("/foo")
    fun foo(): String {
        return "it is a foo"
    }

    @GetMapping("/home")
    fun home(): String {
        return "home"
    }



    init {
        this.userRepository = userRepository
    }


}