package com.aquarium.dev.domain.entity.Community

import com.aquarium.dev.domain.dto.Community.CommentDto
import com.aquarium.dev.domain.entity.User.User
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
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

    @CreatedDate
    @JsonIgnore
    var createdAt : LocalDateTime? = LocalDateTime.now(),

    @LastModifiedBy
    @JsonIgnore
    var updatedAt : LocalDateTime? = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @JsonManagedReference
    var user : User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "post_id", nullable = false)
    @JsonManagedReference
    var post : Post

) {
    fun toCommentDto( comment: Comment ) : CommentDto{
        return CommentDto(
            commentId = comment.commentId,
            commentText = comment.commentText,
            commentLikeCount = comment.commentLikeCount,
            commentReCommentCount = comment.commentReCommentCount,
            commentIsPrivate = comment.commentIsPrivate,
            commentIsBlinded = comment.commentIsBlinded,
            createdAt = comment.createdAt,
            updatedAt = comment.updatedAt,
            userId = comment.user.userId,
            postId = comment.post.postId
        )
    }

}