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
    var userName : String? = null,
    var userPassword : String? = null,
    var userNickname : String? = null,
    var userRole : String? = null,
    var createdAt : LocalDateTime? =null,
    var updatedAt : LocalDateTime? =null
)