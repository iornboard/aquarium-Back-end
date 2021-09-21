package com.aquarium.dev.domain.dto.User

import com.aquarium.dev.domain.entity.User.User
import java.time.LocalDateTime

data class UserDto (
    var userId : Int = 0,

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

    var createdAt : LocalDateTime? = LocalDateTime.now(),
    var updatedAt : LocalDateTime? = LocalDateTime.now(),

    var provider : String? = null  // 소셜 회원가입 정보 -> google, naver등

) {

    fun toUserDto(user : User) : UserDto {

        return UserDto().apply {
            this.userId = user.userId

            this.userEmail = user.userEmail
            this.userFullname = user.userFullname
            this.userNickname = user.userNickname
            this.userImgUrl = user.userImgUrl

            this.userFollowerCount = user.userFollowerCount
            this.userFollowingCount = user.userFollowingCount
            this.userNoticeCount = user.userNoticeCount

            this.userRole = user.userRole
            this.userIsAgree = user.userIsAgree

            this.userLastAccess = user.userLastAccess

            this.createdAt = user.createdAt
            this.updatedAt = user.updatedAt

            this.provider = user.provider
        }
    }
}




