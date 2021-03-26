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
    var user_email : String? = null,
    var user_name : String? = null,
    var user_password : String? = null,
    var user_nickname : String? = null,
    var user_role : String? = null,
    var createdAt : LocalDateTime? =null,
    var updatedAt : LocalDateTime? =null
)