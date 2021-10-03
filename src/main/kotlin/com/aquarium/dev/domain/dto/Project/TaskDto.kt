package com.aquarium.dev.domain.dto.Project

import com.aquarium.dev.domain.dto.Aquarium.AquariumDto
import com.aquarium.dev.domain.dto.User.UserDto
import com.aquarium.dev.domain.entity.Aquarium.Aquarium
import com.aquarium.dev.domain.entity.Project.Project
import com.aquarium.dev.domain.entity.Project.ProjectDto
import com.aquarium.dev.domain.entity.Project.Task
import com.aquarium.dev.domain.entity.User.User
import java.time.LocalDateTime


data class TaskDto(

    var taskId : Int = 0,
    var title : String? = null,
    var taskDescription : String? = null,
    var taskStatus : String? = null,
    var taskMemo : String? = null,
    var startDate : LocalDateTime? =null,
    var endDate : LocalDateTime? =null,
    var taskIsWorking : Boolean = false,
    var taskIsEnd : Boolean = false,
    var taskIsAccept : Boolean = false,
    var chatRoomId : Int? = null,
    var aqrmId : Int? = null,
    var masterId : Int? = null,
    var createdAt : LocalDateTime? = LocalDateTime.now(),
    var updatedAt : LocalDateTime? = LocalDateTime.now(),

    var teamsId : List<Int>? = null,
    var projectId : Int? = null,

    var teamsInfo : List<UserDto>? = null,
    var projectInfo: ProjectDto? = null

) {
     fun toTask(teams : Set<User>, project : Project) : Task {
         return Task(
             taskId = taskId,
             taskName = title,
             taskDescription = taskDescription,
             taskStatus = taskStatus,
             taskMemo = taskMemo,
             taskStartDate = startDate,
             taskEndDate = endDate,
             taskIsWorking = taskIsWorking,
             taskIsEnd = taskIsEnd,
             taskIsAccept = taskIsAccept,
             chatRoomId = chatRoomId,
             aqrmId = aqrmId,
             masterId =  masterId,
             createdAt = createdAt,
             updatedAt = updatedAt,

             teams = teams,
             project = project

         )
     }


}
