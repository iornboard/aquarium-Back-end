package com.aquarium.dev.domain.entity.Project

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Project (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
        var Id : Int = 0,

        var projectName : String? = null,
        var projectType : String? = null,
        var projectDescription : String? = null,
        var projectLastActivity : LocalDateTime? =null,
        var projectTaskCount : Int = 0,

        var createdAt : LocalDateTime? =null,
        var updatedAt : LocalDateTime? =null

        )