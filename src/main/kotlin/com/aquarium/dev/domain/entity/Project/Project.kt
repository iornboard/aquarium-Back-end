package com.aquarium.dev.domain.entity.Project

import com.aquarium.dev.domain.dto.User.UserDto
import com.aquarium.dev.domain.entity.User.User
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Project (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
        @Column(name = "project_id")
        var projectId : Int = 0,

        var projectName : String? = null,
        var projectDescription : String? = null,
        var projectTaskCount : Int = 0,

        @CreatedDate
        @JsonIgnore
        var createdAt : LocalDateTime? = LocalDateTime.now(),

        @LastModifiedBy
        @JsonIgnore
        var updatedAt : LocalDateTime? = LocalDateTime.now(),

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
        @JsonManagedReference
        var user : User



        ) {

        fun toProjectDto() : ProjectDto{
                return ProjectDto(
                        projectId = projectId,
                        projectName = projectName,
                        projectDescription = projectDescription,
                        projectTaskCount = projectTaskCount,
                        createdAt = createdAt,
                        updatedAt = updatedAt,

                        userId = user.userId
                )
        }

        fun toJoinedProjectDto() : ProjectDto{
                return ProjectDto(
                        projectId = projectId,
                        projectName = projectName,
                        projectDescription = projectDescription,
                        projectTaskCount = projectTaskCount,
                        createdAt = createdAt,
                        updatedAt = updatedAt,

                        userInfo = UserDto().toUserDto(user)
                )
        }
}