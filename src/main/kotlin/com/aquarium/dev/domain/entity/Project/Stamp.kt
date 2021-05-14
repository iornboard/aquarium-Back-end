package com.aquarium.dev.domain.entity.Project

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Stamp (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
        var Id : Int = 0,

        var taskId : Int = 0,

        var stampType : String? = null,
        var stampText : String? = null,

        var stampPositionX : Int = 0,
        var stampPositionY : Int = 0,
        var stampNumber : Int = 0,

        var stampEndTime : String? = null,
        var stampStartTime : String? = null,

        var createdAt : LocalDateTime? =null,
        var updatedAt : LocalDateTime? =null

        )