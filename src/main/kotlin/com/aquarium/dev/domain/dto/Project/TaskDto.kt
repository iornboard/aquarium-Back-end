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
    var taskName : String? = null,
    var taskDescription : String? = null,
    var taskStatus : String? = null,
    var taskMemo : String? = null,
    var taskStartDate : LocalDateTime? =null,
    var taskEndDate : LocalDateTime? =null,
    var taskIsWorking : Boolean = false,
    var taskIsEnd : Boolean = false,
    var taskIsAccept : Boolean = false,
    var chatRoomId : Int? = null,
    var createdAt : LocalDateTime? = LocalDateTime.now(),
    var updatedAt : LocalDateTime? = LocalDateTime.now(),

    var employeeId : List<Int>? = null,
    var masterId : Int? = null,
    var projectId : Int? = null,
    var aqrmId : Int? = null,

    var employeeInfo : List<UserDto>? = null,
    var masterInfo : UserDto? = null,
    var projectInfo: ProjectDto? = null,
    var aqrmInfo : AquariumDto? = null

) {
     fun toTask(employee : Set<User>, master : User, project : Project, aqrm : Aquarium) : Task {
         return Task(
             taskId = taskId,
             taskName = taskName,
             taskDescription = taskDescription,
             taskStatus = taskStatus,
             taskMemo = taskMemo,
             taskStartDate = taskStartDate,
             taskEndDate = taskEndDate,
             taskIsWorking = taskIsWorking,
             taskIsEnd = taskIsEnd,
             taskIsAccept = taskIsAccept,
             chatRoomId = chatRoomId,
             createdAt = createdAt,
             updatedAt = updatedAt,

             employee = employee,
             master = master,
             project = project,
             aqrm = aqrm
         )
     }
}
