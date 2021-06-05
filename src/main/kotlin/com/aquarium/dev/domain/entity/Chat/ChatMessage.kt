package com.aquarium.dev.domain.entity.Chat

import com.aquarium.dev.domain.dto.Chat.AuthorDto
import com.aquarium.dev.domain.dto.Chat.ButtonsDto
import com.aquarium.dev.domain.dto.Chat.ChatMessageDto
import com.aquarium.dev.domain.entity.User.User
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import java.awt.TrayIcon
import java.sql.Timestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class ChatMessage(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
    @Column(name = "message_id")
    var messageId : Int = 0,

    var messageText : String? = null,
    var messageType : String? = null,
    var messageImgUrl  : String? = null,
    var messageIsDeleted  : Boolean = false,
    var messageIsModified : Boolean = false,

    @CreatedDate
    @JsonIgnore
    var createdAt : LocalDateTime? = LocalDateTime.now(),

    @LastModifiedBy
    @JsonIgnore
    var updatedAt : LocalDateTime? = LocalDateTime.now(),


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @JsonManagedReference
    var user : User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", referencedColumnName = "room_id", nullable = false)
    @JsonManagedReference
    var room : ChatRoom
) {

    fun toChatMessageDto() : ChatMessageDto{
        return ChatMessageDto(
            id = messageId,
            roomId = room.roomId,
            text = messageText,
            type = messageType,
            timestamp =  Timestamp.valueOf(updatedAt).time,
            hasError = false,

            author = AuthorDto(username=user.username, id=user.userId, avatarUrl = user.userImgUrl)
            //buttons = ButtonsDto()
        )
    }

}
