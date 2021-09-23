package com.aquarium.dev.domain.entity.Project

import com.aquarium.dev.domain.dto.User.UserDto
import com.aquarium.dev.domain.entity.User.User
import java.time.LocalDateTime


data class ProjectDto (

    var projectId : Int = 0,
    var projectName : String? = null,
    var projectDescription : String? = null,
    var projectTaskCount : Int = 0,
    var createdAt : LocalDateTime? = LocalDateTime.now(),
    var updatedAt : LocalDateTime? = LocalDateTime.now(),

    var userId : Int? = null,

    var userInfo : UserDto? = null

    ) {
    fun toProject( user: User ) : Project{
        return Project(
            projectId = projectId,
            projectName = projectName,
            projectDescription = projectDescription,
            projectTaskCount = projectTaskCount,
            createdAt = createdAt,
            updatedAt = updatedAt,
            user = user
        )
    }
}