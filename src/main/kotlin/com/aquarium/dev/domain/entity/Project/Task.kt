package com.aquarium.dev.domain.entity.Project

import com.aquarium.dev.domain.dto.Project.TaskDto
import com.aquarium.dev.domain.dto.User.UserDto
import com.aquarium.dev.domain.entity.Aquarium.Aquarium
import com.aquarium.dev.domain.entity.User.User
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
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
        var aqrmId : Int? = null,
        var masterId : Int? = null,

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
        var teams : Set<User>,



        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "project_id", referencedColumnName = "project_id", nullable = false)
        @JsonManagedReference
        var project: Project


        )  {

        fun toTaskDto() : TaskDto {
                return TaskDto(
                        taskId = taskId,
                        title = taskName,
                        taskDescription = taskDescription,
                        taskStatus = taskStatus,
                        taskMemo = taskMemo,
                        startDate = taskStartDate,
                        endDate = taskEndDate,
                        taskIsWorking = taskIsWorking,
                        taskIsEnd = taskIsEnd,
                        taskIsAccept = taskIsAccept,
                        chatRoomId = chatRoomId,
                        aqrmId = aqrmId,
                        masterId =  masterId,
                        createdAt = createdAt,
                        updatedAt = updatedAt,

                        teamsInfo = teams.map{it -> UserDto().toUserDto(it)},
                        projectId = project.projectId

                )
        }

        fun toJoinedTaskDto() : TaskDto {
                return TaskDto(
                        taskId = taskId,
                        title = taskName,
                        taskDescription = taskDescription,
                        taskStatus = taskStatus,
                        taskMemo = taskMemo,
                        startDate = taskStartDate,
                        endDate = taskEndDate,
                        taskIsWorking = taskIsWorking,
                        taskIsEnd = taskIsEnd,
                        taskIsAccept = taskIsAccept,
                        chatRoomId = chatRoomId,
                        aqrmId = aqrmId,
                        masterId =  masterId,
                        createdAt = createdAt,
                        updatedAt = updatedAt,

                        teamsInfo = teams.map{it -> UserDto().toUserDto(it)},
                        projectInfo = project.toProjectDto()
                )
        }
}