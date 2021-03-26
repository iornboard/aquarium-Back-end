package com.aquarium.dev.controller

import com.aquarium.dev.domain.entity.User
import com.aquarium.dev.domain.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
internal class account (repository: UserRepository) {

    @Autowired
    private val userRpository: UserRepository

    @GetMapping("/foo")
    fun foo(): String {
        return "it is a foo"
    }

    @GetMapping("/user")
    fun all(): List<User?> {
        return userRpository.findAll()
    }

    // end::get-aggregate-root[]
    @PostMapping("/sign-up")
    fun signUp (@RequestBody newUser: User) : String {
        userRpository.save(newUser)
        return "redirect:/api/login"
    }


//    // end::get-aggregate-root[]
//    @PostMapping("/sign-up")
//    fun signUp (@RequestBody newUser: User): User {
//        return repository.save(newUser)
//    }

    init {
        this.userRpository = repository
    }


}