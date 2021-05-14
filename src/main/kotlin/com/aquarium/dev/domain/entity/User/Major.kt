package com.aquarium.dev.domain.entity.User

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Major (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)  //
        var Id : Int = 0,
        var userId : Int = 0,
        var tagId : Int = 0,

        var createdAt : LocalDateTime? =null,
        var updatedAt : LocalDateTime? =null


        )