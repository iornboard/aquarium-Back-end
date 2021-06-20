package com.aquarium.dev.domain.repository

import com.aquarium.dev.domain.entity.Chat.ChatMessage
import com.aquarium.dev.domain.entity.Project.Task
import com.aquarium.dev.domain.entity.User.User
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Task?, Int?> {
    open fun findAllByUser(user : User) : MutableList<Task?>

}