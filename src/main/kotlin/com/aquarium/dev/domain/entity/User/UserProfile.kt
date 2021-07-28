package com.aquarium.dev.domain.entity.User

import com.aquarium.dev.domain.dto.User.UserProfileDto
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
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

        @CreatedDate
        @JsonIgnore
        var createdAt : LocalDateTime? = LocalDateTime.now(),

        @LastModifiedBy
        @JsonIgnore
        var updatedAt : LocalDateTime? = LocalDateTime.now()

){
      fun toUserProfile() : UserProfileDto {

              return UserProfileDto(
                      Id = Id,
                      userId = userId,

                      profileBirthday = profileBirthday,
                      profileGender  = profileGender,
                      profileJob  = profileJob,
                      profileLocation  = profileLocation,
                      profilePageImgUrl  = profilePageImgUrl,
                      profileUserInfo  = profileUserInfo,

                      createdAt = createdAt,
                      updatedAt = updatedAt
              )
      }
}