package com.aquarium.dev.controller.chat

import com.aquarium.dev.domain.dto.Chat.ChatMessageDto
import com.aquarium.dev.domain.entity.Chat.ChatRoom
import com.aquarium.dev.domain.entity.User.User
import com.aquarium.dev.domain.repository.ChatMessageRepository
import com.aquarium.dev.domain.repository.ChatRoomRepository
import com.aquarium.dev.domain.repository.UserRepository
import org.springframework.web.bind.annotation.*
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.web.bind.annotation.RequestMapping





@RestController
@RequestMapping("/api")
class ChatController( userRepository : UserRepository , chatRoomRepository : ChatRoomRepository , chatMessageRepository : ChatMessageRepository ) {

    private val userRepository : UserRepository
    private val chatRoomRepository : ChatRoomRepository
    private val chatMessageRepository : ChatMessageRepository



    @MessageMapping("/chatting")
    fun sendMessage( chatMessageDto : ChatMessageDto ) {

        val chatUser : User = userRepository.getOne(chatMessageDto.author.id)
        val chatRoom : ChatRoom = chatRoomRepository.getOne(chatMessageDto.roomId)

        val userChatMessage = chatMessageDto.toChatMessage(chatUser,chatRoom)

        chatMessageRepository.save(userChatMessage)

    }

    @RequestMapping("/chat")
    fun getChattingHistory( @RequestParam roomId : Int ):  List<ChatMessageDto?>? {

        val messages = chatMessageRepository.findAllByRoom_RoomId(roomId)
        println(messages)
        return messages.map{  it?.toChatMessageDto()  }
    }



    @GetMapping("/update-chat")
    fun updateChat() {


    }


    @GetMapping("/delete-chat")
    fun deleteChat() {


    }

    init {
        this.chatMessageRepository = chatMessageRepository
        this.userRepository  = userRepository
        this.chatRoomRepository = chatRoomRepository
    }

}