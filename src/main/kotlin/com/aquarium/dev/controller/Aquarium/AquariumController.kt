package com.aquarium.dev.controller.Aquarium

import com.aquarium.dev.domain.repository.Aquarium.AquariumRepository
import com.aquarium.dev.domain.repository.User.UserRepository
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class AquariumController(userRepository : UserRepository, aquariumRepository: AquariumRepository) {

    private val userRepository : UserRepository
    private val aquariumRepository: AquariumRepository

    @PostMapping("/create-aquarium")
    fun createProject( @RequestBody body : String) {

    }


    @GetMapping("/aquarium")
    fun readProject(@RequestParam param: String)  {

    }


    @GetMapping("/update-aquarium")
    fun updateProject() {

    }


    @GetMapping("/delete-aquarium")
    fun deleteProject() {

    }


    init {
        this.userRepository = userRepository
        this.aquariumRepository = aquariumRepository
    }


}