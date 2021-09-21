package com.aquarium.dev.controller.Aquarium

import com.aquarium.dev.domain.dto.Aquarium.AquariumDto
import com.aquarium.dev.domain.entity.User.User
import com.aquarium.dev.domain.repository.Aquarium.AquariumRepository
import com.aquarium.dev.domain.repository.User.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class AquariumController(userRepository : UserRepository, aquariumRepository: AquariumRepository) {

    private val userRepository : UserRepository
    private val aquariumRepository: AquariumRepository

    @PostMapping("/create-aqrm")
    fun createAquarium( @RequestBody aqrm : AquariumDto) : ResponseEntity<Any?> {

        val user = userRepository.findByIdOrNull(aqrm.userId)

        return user?.let {
            aquariumRepository.save(aqrm.toAquarium(it))
            return ResponseEntity.status(200).build()
        } ?: let {
            return ResponseEntity.status(400).build()
        }
    }


    @GetMapping("/aqrm")
    fun readAquarium(@RequestParam aqrmId : Int) : ResponseEntity<Any?> {

        val aqrm = aquariumRepository.findByIdOrNull(aqrmId)

        return aqrm?.let {
            return ResponseEntity.ok( it.toAquariumDto() )
        } ?: let {
            return ResponseEntity.status(400).build()
        }
    }


    @GetMapping("/all-aqrm")
    fun readAllAquarium(@RequestParam userId: Int) : ResponseEntity<Any?> {

        val user = userRepository.findByIdOrNull(userId)

        return user?.let{
            return aquariumRepository.findAllByUser(it)?.let {
                return ResponseEntity.ok( it.map{it.toAquariumDto()} )

            } ?: run {
                return ResponseEntity.ok().body(null)
            }

        } ?: let {
            return ResponseEntity.status(400).build()
        }
    }


    @GetMapping("/update-aquarium")
    fun updateAquarium() {

    }


    @GetMapping("/delete-aquarium")
    fun deleteAquarium() {

    }


    init {
        this.userRepository = userRepository
        this.aquariumRepository = aquariumRepository
    }


}