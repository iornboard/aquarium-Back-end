package com.aquarium.dev.domain.dto.Community

import com.aquarium.dev.domain.entity.Community.Comment
import com.aquarium.dev.domain.entity.Community.Post
import com.aquarium.dev.domain.entity.User.User
import java.time.LocalDateTime

data class CommentDto (

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
    fun toComment( user : User, post : Post ) : Comment{
        return Comment(
            commentId = commentId,
            commentText = commentText,
            commentLikeCount = commentLikeCount,
            commentReCommentCount = commentReCommentCount,
            commentIsPrivate = commentIsPrivate,
            commentIsBlinded = commentIsBlinded,
            createdAt = createdAt,
            updatedAt = updatedAt,
            user = user,
            post = post
        )
    }

}