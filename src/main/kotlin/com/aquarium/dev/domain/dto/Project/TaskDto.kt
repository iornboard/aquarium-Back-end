package com.aquarium.dev.domain.dto.Project

import com.aquarium.dev.domain.entity.Project.Task
import com.aquarium.dev.domain.entity.User.User
import java.time.LocalDateTime

data class TaskDto(

    var taskId : Int = 0,

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

    var createdAt : LocalDateTime? = LocalDateTime.now(),
    var updatedAt : LocalDateTime? = LocalDateTime.now(),

    var userIdList : Set<Int>? = null,

    // !!hardCoding!! //

    var chatRoomId: Int? = null

    // !!hardCoding!! //

) {

    fun toTask( user : Set<User>) : Task {
        return Task(
            taskId = taskId,
            projectId = projectId,
            taskName = taskName,
            taskDescription = taskDescription,
            taskStatus =  taskStatus,
            taskImgUrl = taskImgUrl,
            taskVideoUrl = taskVideoUrl,
            taskMemo = taskMemo,
            taskType = taskType,
            taskStartDate = taskStartDate,
            taskEndDate = taskEndDate,
            taskProperties = taskProperties,
            taskIsWorking = taskIsWorking,
            taskIsEnd = taskIsEnd,
            taskIsAccept = taskIsAccept,
            user = user,

            chatRoomId = chatRoomId
        )
    }

}