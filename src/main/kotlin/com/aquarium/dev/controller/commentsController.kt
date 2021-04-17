package com.aquarium.dev.controller

import com.aquarium.dev.domain.entity.Comment
import com.aquarium.dev.domain.repository.CommentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class commentsController(commentRepository: CommentRepository) {

    @Autowired
    private val commentRepository: CommentRepository

    @GetMapping("/comments")
    fun comments(): MutableList<Comment?> {

        val comments = commentRepository.findAll()
        return  comments
    }

    @PostMapping("/create-comment")
    fun createcomment( @RequestBody comment : Comment): String {
        commentRepository.save(comment)
        return "200 ok"
    }

    init {
        this.commentRepository = commentRepository
    }

}