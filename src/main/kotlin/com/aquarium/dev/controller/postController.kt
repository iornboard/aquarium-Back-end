package com.aquarium.dev.controller

import com.aquarium.dev.domain.dto.Community.PostDto
import com.aquarium.dev.domain.entity.Community.Post
import com.aquarium.dev.domain.entity.User.User
import com.aquarium.dev.domain.repository.PostRepository
import com.aquarium.dev.domain.repository.UserRepository
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
internal class postController(userRepository: UserRepository ,postRepository : PostRepository) {

    private val userRepository : UserRepository
    private val postRepository : PostRepository

    @PostMapping("/create-post")
    fun createPost( @RequestBody postDto : PostDto): String? {

        val postUser : User = userRepository.getOne(postDto.userId)

        var post = postDto.toPost(postUser)

        postRepository.save(post)

        return "redirect:/api/signup"

    }

    @GetMapping("/post")
    fun post(): MutableList<Post?> {

        val posts : MutableList<Post?> = postRepository.findAll()

        // !! hardCoding !!
        posts.reverse()
        // !! hardCoding !!

        println("posts : $posts")

        return posts
    }

    init {
        this.userRepository  = userRepository
        this.postRepository = postRepository
    }

}