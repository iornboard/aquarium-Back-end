package com.aquarium.dev.controller

import com.aquarium.dev.domain.entity.Post
import com.aquarium.dev.domain.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
internal class post( postRepository : PostRepository) {

    @Autowired
    private val postRepository : PostRepository


    @PostMapping("/create-post")
    fun createPost( @RequestBody post : Post): String? {

        postRepository.save(post)
        return "redirect:/api/signup"
    }

    @GetMapping("/post")
    fun post(): MutableList<Post?> {

        val posts : MutableList<Post?> = postRepository.findAll()
        return posts
    }


    init {
        this.postRepository = postRepository
    }

}