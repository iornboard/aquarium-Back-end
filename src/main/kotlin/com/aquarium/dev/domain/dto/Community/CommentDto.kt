package com.aquarium.dev.domain.dto.Community

import com.aquarium.dev.domain.entity.Community.Comment
import com.aquarium.dev.domain.entity.Community.Post
import com.aquarium.dev.domain.entity.User.User
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

data class CommentDto (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
    @Column(name = "comment_id")
    var commentId : Int = 0,

    var commentText : String? = null,

    var commentLikeCount : Int = 0,
    var commentReCommentCount  : Int = 0,

    var commentIsPrivate : Boolean = false,
    var commentIsBlinded : Boolean = false,

    var createdAt : LocalDateTime? =null,
    var updatedAt : LocalDateTime? =null,

    var userId : Int = 0,
    var postId : Int = 0

) {
    fun toComment( commentDto: CommentDto, user : User, post : Post ) : Comment{
        return Comment(
            commentId = commentDto.commentId,
            commentText = commentDto.commentText,
            commentLikeCount = commentDto.commentLikeCount,
            commentReCommentCount = commentDto.commentReCommentCount,
            commentIsPrivate = commentDto.commentIsPrivate,
            commentIsBlinded = commentDto.commentIsBlinded,
            createdAt = commentDto.createdAt,
            updatedAt = commentDto.updatedAt,
            user = user,
            post = post
        )
    }

}