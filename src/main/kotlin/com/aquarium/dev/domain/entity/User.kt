package com.aquarium.dev.domain.entity

import lombok.Data
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
    var id : Int = 0,
    var userEmail : String? = null,
    var username : String? = null,
    var password : String? = null,
    var userNickname : String? = null,
    var userRole : String? = null,
    var createdAt : LocalDateTime? =null,
    var updatedAt : LocalDateTime? =null,

    var provider : String? = null,  // 소셜 회원가입 정보 -> google, naver등
    var providerId : String? = null  // 소셜 회원가입 ID(숫자로 되어 있는 것)


)