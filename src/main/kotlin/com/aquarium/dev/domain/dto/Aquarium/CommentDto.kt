package com.aquarium.dev.domain.dto.Aquarium

import com.aquarium.dev.domain.entity.Aquarium.Aquarium
import com.aquarium.dev.domain.entity.Aquarium.Comment
import com.aquarium.dev.domain.entity.Aquarium.Mention
import com.aquarium.dev.domain.entity.User.User
import java.time.LocalDateTime

data class CommentDto (

    var commentId : Int = 0,

    var commentText : String? = null,

    var commentLikeCount : Int = 0,

    var commentIsPrivate : Boolean = false,
    var commentIsBlinded : Boolean = false,

    var createdAt : LocalDateTime? = LocalDateTime.now(),
    var updatedAt : LocalDateTime? = LocalDateTime.now(),

    var userId : Int = 0,
    var aqrmId : Int = 0,
    var mentId : Int = 0

) {
    fun toCommen(user : User, aqrm : Aquarium, ment : Mention) : Comment {
        return Comment(
            commentId = commentId,
            commentText = commentText,
            commentLikeCount = commentLikeCount,
            commentIsPrivate = commentIsPrivate,
            commentIsBlinded = commentIsBlinded,
            createdAt = createdAt,
            updatedAt = updatedAt,

            user = user,
            aqrm = aqrm,
            ment = ment
        )
    }

}