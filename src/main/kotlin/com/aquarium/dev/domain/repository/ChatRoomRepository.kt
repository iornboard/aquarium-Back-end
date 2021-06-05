package com.aquarium.dev.domain.repository

import com.aquarium.dev.domain.entity.Chat.ChatRoom
import com.aquarium.dev.domain.entity.User.User
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRoomRepository : JpaRepository<ChatRoom?, Int?> {
    open fun findChatRoomsByUser(user : User) : MutableList<ChatRoom?>
}