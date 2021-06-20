package com.aquarium.dev.controller

import com.aquarium.dev.domain.repository.UserRepository
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class SampleApiController(userRepository : UserRepository) {

    private val userRepository : UserRepository

    @PostMapping("/create-Sample")
    fun createProject( @RequestBody body : String) {

    }


    @GetMapping("/Sample")
    fun readProject(@RequestParam param: String)  {

    }


    @GetMapping("/update-Sample")
    fun updateProject() {

    }


    @GetMapping("/delete-Sample")
    fun deleteProject() {

    }


    init {
        this.userRepository  = userRepository
    }


}