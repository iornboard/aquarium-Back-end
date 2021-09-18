package com.aquarium.dev.domain.entity.Aquarium


import com.aquarium.dev.domain.dto.Aquarium.PostDto
import com.aquarium.dev.domain.entity.User.User
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
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

        @CreatedDate
        @JsonIgnore
        var createdAt : LocalDateTime? = LocalDateTime.now(),

        @LastModifiedBy
        @JsonIgnore
        var updatedAt : LocalDateTime? = LocalDateTime.now(),

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
        @JsonManagedReference
        var user : User
) {

        fun toPostDto( post : Post ) : PostDto {
                return PostDto(
                        postId = post.postId,
                        postTitle = post.postTitle,
                        postText = post.postText,
                        postContentType = post.postContentType,
                        postImgUrl = post.postImgUrl,
                        postVideoUrl = post.postVideoUrl,
                        postCommentCount = post.postCommentCount,
                        postLikeCount = post.postLikeCount,
                        postViewCount = post.postViewCount,
                        postIsPrivate = post.postIsPrivate,
                        postIsBlinded = post.postIsBlinded,
                        createdAt = post.createdAt,
                        updatedAt = post.updatedAt,
                        userId = post.user.userId
                )
        }
}
