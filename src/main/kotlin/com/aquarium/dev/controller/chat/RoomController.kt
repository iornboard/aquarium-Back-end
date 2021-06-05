package com.aquarium.dev.controller.chat

import com.aquarium.dev.domain.dto.Chat.ChatMessageDto
import com.aquarium.dev.domain.entity.Chat.ChatRoom
import com.aquarium.dev.domain.entity.User.User
import com.aquarium.dev.domain.repository.ChatMessageRepository
import com.aquarium.dev.domain.repository.ChatRoomRepository
import com.aquarium.dev.domain.repository.UserRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class RoomController(userRepository : UserRepository, chatRoomRepository : ChatRoomRepository, chatMessageRepository : ChatMessageRepository) {

    private val userRepository : UserRepository
    private val chatRoomRepository : ChatRoomRepository
    private val chatMessageRepository : ChatMessageRepository

    @GetMapping("/create-room")
    fun createRoom(@RequestParam requestId : Int): ChatRoom? {

        val requestUser = userRepository.getOne(requestId)
        val newChatRoom = ChatRoom(user = setOf<User>(requestUser))

        val createRoom = chatRoomRepository.save(newChatRoom)

        return createRoom
    }


    @GetMapping("/rooms")
    fun readRoom(@RequestParam userId : Int) : MutableList<ChatRoom?>{

        val user = userRepository.getOne(userId)

        return chatRoomRepository.findChatRoomsByUser(user)
    }

    @GetMapping("/update-room")
    fun updateRoom() {
    }

    @GetMapping("/delete-room")
    fun deleteRoom() {
    }


    init {
        this.chatMessageRepository = chatMessageRepository
        this.userRepository  = userRepository
        this.chatRoomRepository = chatRoomRepository
    }
}