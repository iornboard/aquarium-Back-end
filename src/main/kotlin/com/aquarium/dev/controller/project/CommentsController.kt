package com.aquarium.dev.controller.project

import com.aquarium.dev.domain.dto.Aquarium.CommentDto
import com.aquarium.dev.domain.entity.Aquarium.Comment
import com.aquarium.dev.domain.entity.Aquarium.Post
import com.aquarium.dev.domain.entity.User.User
import com.aquarium.dev.domain.repository.CommentRepository
import com.aquarium.dev.domain.repository.PostRepository
import com.aquarium.dev.domain.repository.UserRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class commentsController( userRepository : UserRepository , postRepository : PostRepository , commentRepository: CommentRepository ) {

    private val userRepository : UserRepository
    private val postRepository : PostRepository
    private val commentRepository: CommentRepository

    @GetMapping("/comments")
    fun comments(): MutableList<Comment?> {

        val comments = commentRepository.findAll()
        return  comments
    }

    @GetMapping("/post-comments")
    fun postComments( @RequestParam postId : Int ): MutableList<Comment?> {

        println("postId : $postId")
        val comments = commentRepository.findAllByPost_PostId(postId)
        println("comments : $comments")
        return comments
    }

    @PostMapping("/create-comment")
    fun createcomment( @RequestBody commentDto : CommentDto): String {

        val commentUser : User = userRepository.getOne(commentDto.userId)
        val commentPost : Post = postRepository.getOne(commentDto.postId)

        val comment = commentDto.toComment( commentUser , commentPost)

        commentRepository.save(comment)
        return "200 ok"
    }



    init {
        this.userRepository  = userRepository
        this.postRepository = postRepository
        this.commentRepository = commentRepository
    }

}