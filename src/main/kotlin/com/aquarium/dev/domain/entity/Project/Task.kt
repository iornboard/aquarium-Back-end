package com.aquarium.dev.domain.entity.Project

import com.aquarium.dev.domain.dto.Project.TaskDto
import com.aquarium.dev.domain.entity.User.User
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Task (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
        @Column(name = "task_id")
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

        @CreatedDate
        @JsonIgnore
        var createdAt : LocalDateTime? = LocalDateTime.now(),

        @LastModifiedBy
        @JsonIgnore
        var updatedAt : LocalDateTime? = LocalDateTime.now(),

        @ManyToMany
        @JoinTable(
                name = "task_assined",
                joinColumns = [JoinColumn(name = "task_id" ,referencedColumnName = "task_id")],
                inverseJoinColumns = [JoinColumn(name = "user_id" ,referencedColumnName = "user_id")]
        )
        @JsonIgnore
        var user : Set<User>

        )  {

        fun toTaskDto() : TaskDto {
                return TaskDto(
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
                        userId = user.map{ it -> it.userId }.toSet()
                )
        }
}