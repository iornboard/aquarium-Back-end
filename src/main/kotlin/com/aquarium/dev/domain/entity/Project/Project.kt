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
data class Project (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
        var Id : Int = 0,

        var projectName : String? = null,
        var projectType : String? = null,
        var projectDescription : String? = null,
        var projectLastActivity : LocalDateTime? =null,
        var projectTaskCount : Int = 0,

        @CreatedDate
        @JsonIgnore
        var createdAt : LocalDateTime? = LocalDateTime.now(),

        @LastModifiedBy
        @JsonIgnore
        var updatedAt : LocalDateTime? = LocalDateTime.now()

        )