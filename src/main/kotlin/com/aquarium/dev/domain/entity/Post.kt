package com.aquarium.dev.domain.entity


import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Post(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
        @Column(name = "post_id")
        var postId : Int = 0,

        var postTitle : String? = null,
        var postText : String? = null,
        var postContentType : String? = null,

        var postImgUrl : String? = null,
        var postVideoUrl : String? = null,

        var postCommentCount : Int = 0,
        var postLikeCount : Int = 0,
        var postViewCount : Int = 0,

        var postIsPrivate : Boolean = false,
        var postIsBlinded : Boolean = false,

        var createdAt : LocalDateTime? =null,
        var updatedAt : LocalDateTime? =null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        var user : User

        )