package com.aquarium.dev.domain.repository.Chat

import com.aquarium.dev.domain.entity.Chat.ChatMessage
import org.springframework.data.jpa.repository.JpaRepository

interface ChatMessageRepository : JpaRepository<ChatMessage?, Int?> {
    open fun findAllByRoom_RoomId(roomId : Int) : MutableList<ChatMessage?>
}