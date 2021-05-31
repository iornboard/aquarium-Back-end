package com.aquarium.dev.controller

import com.aquarium.dev.domain.dto.Community.PostDto
import com.aquarium.dev.domain.entity.Community.Post
import com.aquarium.dev.domain.entity.User.User
import com.aquarium.dev.domain.repository.PostRepository
import com.aquarium.dev.domain.repository.UserRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
internal class postController(postRepository : PostRepository, userRepository: UserRepository) {

    private val postRepository : PostRepository
    private val userRepository : UserRepository

    @PostMapping("/create-post")
    fun createPost( @RequestBody requestPost : PostDto): String? {

        val postUser : User = userRepository.getOne(requestPost.userId)

        var post = PostDto().toPost(requestPost , postUser)

        postRepository.save(post)

        return "redirect:/api/signup"

    }

    @GetMapping("/post")
    fun post(): MutableList<Post?> {

        val posts : MutableList<Post?> = postRepository.findAll()
        println("posts : $posts")
        return posts
    }

    init {
        this.postRepository = postRepository
        this.userRepository  = userRepository
    }

}