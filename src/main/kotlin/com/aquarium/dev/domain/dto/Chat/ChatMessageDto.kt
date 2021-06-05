package com.aquarium.dev.domain.dto.Chat

import com.aquarium.dev.domain.entity.Chat.ChatMessage
import com.aquarium.dev.domain.entity.Chat.ChatRoom
import com.aquarium.dev.domain.entity.User.User
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

data class ChatMessageDto(

    var author : AuthorDto = AuthorDto(),
    //var buttons : ButtonsDto = ButtonsDto(),  // 버튼은 우선 아직 보류 해야 할 듯

    var id : Int = 0,
    var roomId : Int = 0,
    var timestamp  : Long = 0,
    var text : String? = null,
    var type  : String? = null,
    var hasError: Boolean? = false

) {
    fun toChatMessage(user : User , room : ChatRoom) : ChatMessage {
        return ChatMessage(
            messageText = text,
            messageType = type,
            createdAt = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), TimeZone.getDefault().toZoneId()),
            updatedAt = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), TimeZone.getDefault().toZoneId()),
            user = user,
            room = room
        )
    }

}
