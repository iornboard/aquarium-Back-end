package com.aquarium.dev.domain.entity.Project

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Task (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
        var Id : Int = 0,

        var projectId : Int = 0,

        var taskName : String? = null,
        var taskDescription : String? = null,
        var taskStatus : String? = null,
        var taskImgUrl : String? = null,
        var taskVideoUrl : String? = null,
        var taskMemo : String? = null,
        var taskType : String? = null,

        var taskStartDate : LocalDateTime? =null,
        var taskEndDate : LocalDateTime? =null,

        var taskProperties : String? = null,

        var taskIsWorking : Boolean = false,
        var taskIsEnd : Boolean = false,
        var taskIsAccept : Boolean = false,

        var createdAt : LocalDateTime? =null,
        var updatedAt : LocalDateTime? =null

        )