package com.aquarium.dev.controller

import com.aquarium.dev.domain.entity.Post
import com.aquarium.dev.domain.entity.User
import com.aquarium.dev.domain.repository.PostRepository
import com.aquarium.dev.domain.repository.UserRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
internal class postController(postRepository : PostRepository, userRepository: UserRepository) {

    //  !! hardCoding !!   //
    data class RequestPost (
        var userId : Int = 0,
        var postTitle : String? = null,
        var postText : String? = null,
        var postContentType : String? = null,
        var postImgUrl : String? = null,
        var postVideoUrl : String? = null,
        var postCommentCount : Int = 0,
        var postLikeCount : Int = 0,
        var postViewCount : Int = 0,
        var postIsPrivate : Boolean = false,
        var postIsBlinded : Boolean = false
    )
    //  !! hardCoding !!   //

    private val postRepository : PostRepository
    private val userRepository : UserRepository

    @PostMapping("/create-post")
    fun createPost( @RequestBody requestPost : RequestPost): String? {

        val postUser : User = userRepository.getOne(requestPost.userId)

        var post = Post(user = postUser)

        //  !! hardCoding !!   //
        post.postTitle = requestPost.postTitle
        post.postText = requestPost.postText
        post.postContentType = requestPost.postContentType
        post.postImgUrl = requestPost.postImgUrl
        post.postVideoUrl = requestPost.postVideoUrl
        post.postIsPrivate = requestPost.postIsPrivate
        post.postIsBlinded = requestPost.postIsBlinded
        //  !! hardCoding !!  //

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