package com.aquarium.dev.domain.entity.User

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class UserProfile (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
        var Id : Int = 0,
        var userId : Int = 0,

        var profileBirthday : LocalDateTime? =null,
        var profileGender : String? = null,
        var profileJob : String? = null,
        var profileLocation : String? = null,
        var profilePageImgUrl : String? = null,
        var profileUserInfo : String? = null,

        var createdAt : LocalDateTime? =null,
        var updatedAt : LocalDateTime? =null

        )