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

    fun toPost( postDto: PostDto , userId : User) : Post {
        return Post(
            postId = postDto.postId,
            postTitle = postDto.postTitle,
            postText = postDto.postText,
            postContentType = postDto.postContentType,
            postImgUrl = postDto.postImgUrl,
            postVideoUrl = postDto.postVideoUrl,
            postCommentCount = postDto.postCommentCount,
            postLikeCount = postDto.postLikeCount,
            postViewCount = postDto.postViewCount,
            postIsPrivate = postDto.postIsPrivate,
            postIsBlinded = postDto.postIsBlinded,
            createdAt = postDto.createdAt,
            updatedAt = postDto.updatedAt,
            user = userId
        )
    }

}