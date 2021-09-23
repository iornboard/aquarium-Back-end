package com.aquarium.dev.domain.entity.Aquarium

import com.aquarium.dev.domain.dto.Aquarium.MentionDto
import com.aquarium.dev.domain.dto.Aquarium.MentionMakerDto
import com.aquarium.dev.domain.dto.User.UserDto
import com.aquarium.dev.domain.entity.User.User
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Mention(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
        @Column(name = "ment_id")
        var mentId : Int = 0,

        var mentText : String? = null,
        var mentImgUrl : String? = null,

        var mentCommentCount : Int = 0,
        var mentLikeCount : Int = 0,
        var mentViewCount : Int = 0,

        var xPosition : Int = 0,
        var yPosition : Int = 0,
        var startTime : Int = 0,
        var endTime : Int = 0,

        var mentIsPrivate : Boolean = false,
        var mentIsBlinded : Boolean = false,

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
        var aqrm : Aquarium

) {
        fun toMentionDto() : MentionDto {
                return MentionDto(
                        mentId = mentId,
                        mentText = mentText,
                        mentImgUrl = mentImgUrl,
                        mentCommentCount = mentCommentCount,
                        mentLikeCount = mentLikeCount,
                        mentViewCount = mentViewCount,

                        x = xPosition,
                        y = yPosition,
                        start = startTime,
                        end = endTime,

                        mentIsPrivate = mentIsPrivate,
                        mentIsBlinded = mentIsBlinded,
                        createdAt = createdAt,
                        updatedAt = updatedAt,

                        userId = user.userId,
                        aqrmId = aqrm.aqrmId,

                        userInfo = UserDto().toUserDto(user)  // 기본으로 추가
                )
        }


        fun toJoinedMentionDto() : MentionDto {
                return MentionDto(
                        mentId = mentId,
                        mentText = mentText,
                        mentImgUrl = mentImgUrl,
                        mentCommentCount = mentCommentCount,
                        mentLikeCount = mentLikeCount,
                        mentViewCount = mentViewCount,

                        x = xPosition,
                        y = yPosition,
                        start = startTime,
                        end = endTime,

                        mentIsPrivate = mentIsPrivate,
                        mentIsBlinded = mentIsBlinded,
                        createdAt = createdAt,
                        updatedAt = updatedAt,

                        userInfo = UserDto().toUserDto(user),
                        aqrmInfo = aqrm.toAquariumDto()
                )
        }


        fun toMentMaker() : MentionMakerDto {
                return MentionMakerDto(
                        mentId = mentId,
                        mentText = mentText,

                        x = xPosition,
                        y = yPosition,
                        start = startTime,
                        end = endTime,

                        avatarImg = user.userImgUrl,

                        mentIsPrivate = mentIsPrivate,
                        mentIsBlinded = mentIsBlinded,
                        createdAt = createdAt,
                        updatedAt = updatedAt

                )
        }

}