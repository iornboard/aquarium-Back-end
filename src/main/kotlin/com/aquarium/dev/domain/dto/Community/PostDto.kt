package com.aquarium.dev.domain.dto.Community

import com.aquarium.dev.domain.entity.Community.Post
import com.aquarium.dev.domain.entity.User.User
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class PostDto(
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

    var userId : Int = 0
) {

    fun toPost( user : User) : Post {
        return Post(
            postId = postId,
            postTitle = postTitle,
            postText = postText,
            postContentType = postContentType,
            postImgUrl = postImgUrl,
            postVideoUrl = postVideoUrl,
            postCommentCount = postCommentCount,
            postLikeCount = postLikeCount,
            postViewCount = postViewCount,
            postIsPrivate = postIsPrivate,
            postIsBlinded = postIsBlinded,
            createdAt = createdAt,
            updatedAt = updatedAt,
            user = user
        )
    }

}