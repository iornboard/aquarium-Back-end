package com.aquarium.dev.controller.project

import com.aquarium.dev.domain.dto.Project.TaskDto
import com.aquarium.dev.domain.entity.Chat.ChatRoom
import com.aquarium.dev.domain.entity.Project.Task
import com.aquarium.dev.domain.entity.User.User
import com.aquarium.dev.domain.repository.ChatRoomRepository
import com.aquarium.dev.domain.repository.TaskRepository
import com.aquarium.dev.domain.repository.UserRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class TaskController(userRepository : UserRepository , taskRepository : TaskRepository , chatRoomRepository : ChatRoomRepository) {

    private val userRepository : UserRepository
    private val taskRepository : TaskRepository
    private val chatRoomRepository : ChatRoomRepository


    @PostMapping("/create-task")
    fun createTask( @RequestBody taskDto : TaskDto ) {

        // 가장 쉬운 방법
        val taskUsers : Set<User> = taskDto.userIdList!!.map{ it -> userRepository.getOne(it) }.toSet()
        val taskChatRoom : ChatRoom = chatRoomRepository.save(ChatRoom(user=taskUsers))

        val task = taskDto.toTask(taskUsers)
        task.chatRoomId = taskChatRoom.roomId

        taskRepository.save(task)
    }


    @GetMapping("/task")
    fun readTask(@RequestParam userId : Int) : MutableList<Task?> {

        val RequestUser = userRepository.getOne(userId)

        return taskRepository.findAllByUser(RequestUser)

    }


    @PostMapping("/update-task")
    fun updateTask( @RequestBody body : String ) {

    }


    @GetMapping("/delete-task")
    fun deleteTask() {

    }

    init {
        this.userRepository  = userRepository
        this.taskRepository  = taskRepository
        this.chatRoomRepository = chatRoomRepository
    }


}