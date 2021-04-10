package com.aquarium.dev.domain.entity

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Post(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
        var id : Int = 0,
        var postText : String? = null,
        var createdAt : LocalDateTime? =null,
        var updatedAt : LocalDateTime? =null

        )