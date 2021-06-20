package com.aquarium.dev.domain.entity.User

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class MajorTag (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)  //
        var Id : Int = 0,
        var tagName : String? = null,
        var description : String? = null,

        @CreatedDate
        @JsonIgnore
        var createdAt : LocalDateTime? = LocalDateTime.now(),

        @LastModifiedBy
        @JsonIgnore
        var updatedAt : LocalDateTime? = LocalDateTime.now()

        )