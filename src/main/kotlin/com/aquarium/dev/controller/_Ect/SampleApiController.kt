package com.aquarium.dev.controller._Ect

import com.aquarium.dev.domain.repository.User.UserRepository
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class SampleApiController(userRepository : UserRepository) {

    private val userRepository : UserRepository

    @PostMapping("/create-Sample")
    fun create( @RequestBody body : String) {

    }


    @GetMapping("/Sample")
    fun read(@RequestParam param: String)  {

    }


    @GetMapping("/update-Sample")
    fun update() {

    }


    @GetMapping("/delete-Sample")
    fun delete() {

    }


    init {
        this.userRepository  = userRepository
    }


}