package com.aquarium.dev.controller.project

import com.aquarium.dev.domain.repository.UserRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class TaskController(userRepository : UserRepository) {

    private val userRepository : UserRepository

    @PostMapping("/create-task")
    fun createTask( @RequestBody body : String ) {


    }


    @GetMapping("/task")
    fun readTask(@RequestParam param: String)  {

    }


    @GetMapping("/update-task")
    fun updateTask() {

    }


    @GetMapping("/delete-task")
    fun deleteTask() {

    }

    init {
        this.userRepository  = userRepository
    }


}