package com.aquarium.dev.controller.project

import com.aquarium.dev.domain.dto.Project.TaskDto
import com.aquarium.dev.domain.entity.Chat.ChatRoom
import com.aquarium.dev.domain.entity.Project.Task
import com.aquarium.dev.domain.entity.User.User
import com.aquarium.dev.domain.repository.Aquarium.AquariumRepository
import com.aquarium.dev.domain.repository.Chat.ChatRoomRepository
import com.aquarium.dev.domain.repository.Project.ProjectRepository
import com.aquarium.dev.domain.repository.Project.TaskRepository
import com.aquarium.dev.domain.repository.User.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class TaskController(userRepository : UserRepository, projectRepository: ProjectRepository ,taskRepository : TaskRepository) {

    private val userRepository : UserRepository
    private val projectRepository: ProjectRepository
    private val taskRepository : TaskRepository

    @PostMapping("/create-task")
    fun createTask( @RequestBody task : TaskDto ) : ResponseEntity<Any?> {

        task.teamsId?.let{
            val teams = userRepository.findAllById(it).toSet() as Set<User>?


            teams?. let{
                val proj = projectRepository.findByIdOrNull(task.projectId)

                proj?.let{
                    val res = taskRepository.save(task.toTask(teams,proj)).toTaskDto()
                    return ResponseEntity.status(200).body(res)
                } ?: let{
                    return ResponseEntity.status(400).body("not found Project!!")
                }

            } ?: let{
                return ResponseEntity.status(400).body("not found User!!")
            }

        } ?: let{
            return ResponseEntity.status(400).build()
        }

    }


    @GetMapping("/task")
    fun readTask(@RequestParam taskId : Int) : ResponseEntity<Any?> {

        val task = taskRepository.findByIdOrNull(taskId)

        task?.let{
            return ResponseEntity.ok(it.toTaskDto())
        } ?: let{
            return ResponseEntity.status(400).build()
        }

    }


    @GetMapping("/all-user-task")
    fun readAllUserTask(@RequestParam userId : Int) : ResponseEntity<Any?>  {

        val user = userRepository.findByIdOrNull(userId)

        user?.let{
            val tasks = taskRepository.findAllByTeams(it)

            tasks?.let{
                return ResponseEntity.ok(it.map{ it.toTaskDto()})
            } ?: let{
                return ResponseEntity.ok().body(null)
            }

        } ?: let{
            return ResponseEntity.status(400).body("not found User!!")
        }

    }

    @GetMapping("/all-project-task")
    fun readAllProjectTask(@RequestParam projectId : Int) : ResponseEntity<Any?>  {

        val proj = projectRepository.findByIdOrNull(projectId)

        proj?.let{
            val tasks = taskRepository.findAllByProject(it)

            tasks?.let{
                return ResponseEntity.ok(it.map{ it.toTaskDto()})
            } ?: let{
                return ResponseEntity.ok().body(null)
            }

        } ?: let{
            return ResponseEntity.status(400).body("not found User!!")
        }

    }

    @PutMapping("/update-task")
    fun updateTask( @RequestBody newTask : TaskDto ) : ResponseEntity<Any?> {

        newTask.teamsId?.let{
            val teams = userRepository.findAllById(it).toSet() as Set<User>?


            teams?. let{
                val proj = projectRepository.findByIdOrNull(newTask.projectId)

                proj?.let{
                    val res = taskRepository.save(newTask.toTask(teams,proj)).toTaskDto()
                    return ResponseEntity.status(200).body(res)
                } ?: let{
                    return ResponseEntity.status(400).body("not found Project!!")
                }

            } ?: let{
                return ResponseEntity.status(400).body("not found User!!")
            }

        } ?: let{
            return ResponseEntity.status(400).build()
        }

    }


    @PutMapping("/update-task-info")
    fun updateTaskInfo( @RequestBody newTask : TaskDto ) : ResponseEntity<Any?> {

        val oldTask = taskRepository.findByIdOrNull(newTask.taskId)

        oldTask?.let{
            val res = taskRepository.save(oldTask.mergeDto(newTask)).toTaskDto()
            return ResponseEntity.status(200).body(res)
        } ?: let{
            return ResponseEntity.status(400).body("not found Task!!")
        }

    }

    @DeleteMapping("/delete-task")
    fun deleteTask( @RequestParam taskId : Int ): ResponseEntity<Any?>  {

        taskRepository.deleteById(taskId).let{ it ->
            return ResponseEntity.status(200).body(it)
        }
    }


    init {
        this.userRepository  = userRepository
        this.projectRepository = projectRepository
        this.taskRepository  = taskRepository
    }


}