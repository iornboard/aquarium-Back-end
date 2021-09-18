package com.aquarium.dev.domain.dto.Aquarium

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

)