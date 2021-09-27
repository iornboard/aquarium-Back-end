package com.aquarium.dev.domain.repository.Project

import com.aquarium.dev.domain.entity.Project.Project
import com.aquarium.dev.domain.entity.Project.Task
import com.aquarium.dev.domain.entity.User.User
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Task?, Int?> {
    open fun findAllByTeams(user : User) : List<Task>?

    open fun findAllByProject(project: Project) : List<Task>?
}