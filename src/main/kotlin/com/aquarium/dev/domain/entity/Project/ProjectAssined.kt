package com.aquarium.dev.domain.entity.Project

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ProjectAssined (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
        var Id : Int = 0,

        var userId : Int = 0,
        var projectId : Int = 0,

        var createdAt : LocalDateTime? =null,
        var updatedAt : LocalDateTime? =null

        )