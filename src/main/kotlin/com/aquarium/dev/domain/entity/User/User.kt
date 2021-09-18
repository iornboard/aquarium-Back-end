package com.aquarium.dev.domain.entity.User


import com.aquarium.dev.domain.dto.User.UserDto
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class User (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
    @Column(name = "user_id")
    var userId : Int = 0,
    var username : String? = null,
    var password : String? = null,

    var userEmail : String? = null,
    var userFullname : String? = null,
    var userNickname : String? = null,
    var userImgUrl : String? = null,

    var userFollowerCount :Int = 0,
    var userFollowingCount : Int = 0,
    var userNoticeCount : Int = 0,

    var userRole : String? = null,
    var userIsAgree : Boolean = false,

    var userLastAccess : LocalDateTime? =null,
    @CreatedDate
    @JsonIgnore
    var createdAt : LocalDateTime? = LocalDateTime.now(),

    @LastModifiedBy
    @JsonIgnore
    var updatedAt : LocalDateTime? = LocalDateTime.now(),

    var provider : String? = null,  // 소셜 회원가입 정보 -> google, naver등
    var providerId : String? = null  // 소셜 회원가입 ID(숫자로 되어 있는 것)

) {
    fun toUser(userDto : UserDto) : User{

        return User().apply {
            this.userId =  userDto.userId
            this.username =  userDto.username
            this.password = userDto.password

            this.userEmail = userDto.userEmail
            this.userFullname = userDto.userFullname
            this.userNickname = userDto.userNickname
            this.userImgUrl = userDto.userImgUrl

            this.userFollowerCount = userDto.userFollowerCount
            this.userFollowingCount = userDto.userFollowingCount
            this.userNoticeCount = userDto.userNoticeCount

            this.userRole = userDto.userRole
            this.userIsAgree = userDto.userIsAgree

            this.userLastAccess = userDto.userLastAccess

            this.createdAt = userDto.createdAt
            this.updatedAt = userDto.updatedAt

            this.provider = userDto.provider
            this.providerId = userDto.providerId
        }
    }
}
