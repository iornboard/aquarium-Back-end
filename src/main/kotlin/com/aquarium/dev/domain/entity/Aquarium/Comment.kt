package com.aquarium.dev.domain.entity.Aquarium

import com.aquarium.dev.domain.dto.Aquarium.CommentDto
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
    @JoinColumn(name = "aqrm_id", referencedColumnName = "aqrm_id", nullable = false)
    @JsonManagedReference
    var aqrm : Aquarium,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ment_id", referencedColumnName = "ment_id", nullable = false)
    @JsonManagedReference
    var ment : Mention

) {
    fun toCommentDto() : CommentDto {
        return CommentDto(
            commentId = commentId,
            commentText = commentText,
            commentLikeCount = commentLikeCount,
            commentIsPrivate = commentIsPrivate,
            commentIsBlinded = commentIsBlinded,
            createdAt = createdAt,
            updatedAt = updatedAt,

            userId = user.userId,
            aqrmId = aqrm.aqrmId,
            mentId = ment.mentId
        )
    }

}