package com.aquarium.dev.controller.project

import com.aquarium.dev.domain.entity.Project.ProjectDto
import com.aquarium.dev.domain.repository.Project.ProjectRepository
import com.aquarium.dev.domain.repository.User.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class ProjectController(userRepository : UserRepository, projectRepository: ProjectRepository) {

    private val userRepository : UserRepository
    private val projectRepository : ProjectRepository

    @PostMapping("/create-project")
    fun createProject( @RequestBody project : ProjectDto) : ResponseEntity<Any?> {

        val user = userRepository.findByIdOrNull(project.userId)

        return user?.let{
            projectRepository.save(project.toProject(user))
            return ResponseEntity.status(200).build()
        } ?: let{
            return ResponseEntity.status(400).body("not found User!!")
        }

    }

    @GetMapping("/project")
    fun readAllProject(@RequestParam projectId: Int) : ResponseEntity<Any?> {

        val project = projectRepository.findByIdOrNull(projectId)

        project?.let{
            return ResponseEntity.ok( it.toProjectDto() )
        } ?: let{
            return ResponseEntity.status(400).build()
        }

    }


    @GetMapping("/all-project")
    fun readProject(@RequestParam userId : Int) : ResponseEntity<Any?> {

        val user = userRepository.findByIdOrNull(userId)

        user?.let{
            val prej = projectRepository.findAllByUser(it)

            prej?.let{
                return ResponseEntity.ok( it.map{ it.toProjectDto()} )
            } ?: let{
                return ResponseEntity.ok().body(null)
            }

        } ?: let{
            return ResponseEntity.status(400).body("not found User!!")
        }

    }


    @GetMapping("/update-project")
    fun updateProject() {

    }


    @GetMapping("/delete-project")
    fun deleteProject() {

    }


    init {
        this.userRepository  = userRepository
        this.projectRepository  = projectRepository
    }


}