package com.aquarium.dev.domain.entity.Project

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
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

        @CreatedDate
        @JsonIgnore
        var createdAt : LocalDateTime? = LocalDateTime.now(),

        @LastModifiedBy
        @JsonIgnore
        var updatedAt : LocalDateTime? = LocalDateTime.now()

        )