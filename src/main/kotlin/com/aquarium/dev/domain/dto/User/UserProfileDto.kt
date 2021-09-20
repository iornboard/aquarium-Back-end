package com.aquarium.dev.domain.dto.User

import com.aquarium.dev.domain.entity.User.UserProfile
import java.time.LocalDateTime

data class UserProfileDto(
    var Id : Int = 0,
    var userId : Int = 0,

    var profileBirthday : LocalDateTime? =null,
    var profileGender : String? = null,
    var profileJob : String? = null,
    var profileLocation : String? = null,
    var profilePageImgUrl : String? = null,
    var profileUserInfo : String? = null,

    var createdAt : LocalDateTime? = LocalDateTime.now(),
    var updatedAt : LocalDateTime? = LocalDateTime.now()


) 