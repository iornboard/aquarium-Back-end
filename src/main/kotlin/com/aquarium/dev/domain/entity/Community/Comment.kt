package com.aquarium.dev.domain.entity.Community

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Comment(

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
    var updatedAt : LocalDateTime? =null
)