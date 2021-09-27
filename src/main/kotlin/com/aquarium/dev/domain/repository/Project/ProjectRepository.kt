package com.aquarium.dev.domain.repository.Project

import com.aquarium.dev.domain.entity.Project.Project
import com.aquarium.dev.domain.entity.User.User
import org.springframework.data.jpa.repository.JpaRepository


interface ProjectRepository : JpaRepository<Project?, Int?> {
    open fun findAllByUser(user: User) : List<Project>?
}