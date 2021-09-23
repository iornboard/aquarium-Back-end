package com.aquarium.dev.controller.Aquarium

import com.aquarium.dev.domain.dto.Aquarium.MentionDto
import com.aquarium.dev.domain.repository.Aquarium.AquariumRepository
import com.aquarium.dev.domain.repository.Aquarium.MentionRepository
import com.aquarium.dev.domain.repository.User.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class MentionController(userRepository : UserRepository, aquariumRepository: AquariumRepository, mentionRepository: MentionRepository) {

    private val userRepository : UserRepository
    private val aquariumRepository : AquariumRepository
    private val mentionRepository: MentionRepository

    @PostMapping("/create-ment")
    fun createMention( @RequestBody ment : MentionDto) : ResponseEntity<Any?> {

        val user = userRepository.findByIdOrNull(ment.userId)

        return user?.let{
            val aqrm = aquariumRepository.findByIdOrNull(ment.aqrmId)

            aqrm?. let{
                val res = mentionRepository.save(ment.toMention(user,it)).toMentMaker()
                return ResponseEntity.status(200).body(res)
            } ?: let {
                return ResponseEntity.status(400).body("not found Aqrm!!")
            }

        } ?: let {
            return ResponseEntity.status(400).body("not found User!!")
        }

    }


    @GetMapping("/ment")
    fun readMention(@RequestParam mentId : Int) : ResponseEntity<Any?> {

        val ment = mentionRepository.findByIdOrNull(mentId)

        return ment?.let{
            return ResponseEntity.ok( it.toMentionDto() )
        } ?: let {
            return ResponseEntity.status(400).build()
        }

    }


    @GetMapping("/all-ment")
    fun readAllMention(@RequestParam  userId: Int ) : ResponseEntity<Any?> {

        val user = userRepository.findByIdOrNull(userId)

        return user?.let{
            val ments = mentionRepository.findAllByUser(it)

            ments?.let{
                return ResponseEntity.ok( it.map { it.toMentionDto() })
            } ?: let{
                return ResponseEntity.ok().body(null)
            }

        } ?: let{
            return ResponseEntity.status(400).body("not found User!!")
        }

    }


    @GetMapping("/update-ment")
    fun updateMention() {

    }


    @GetMapping("/delete-ment")
    fun deleteMention() {

    }

    @GetMapping("/all-mark")
    fun readAllMentMark(@RequestParam  aqrmId: Int ) : ResponseEntity<Any?> {

        val aqrm = aquariumRepository.findByIdOrNull(aqrmId)

        aqrm?.let {
            val ments = mentionRepository.findAllByAqrm(it)

            ments?.let {
                val markers = it.map { it.toMentMaker() }
                return ResponseEntity.ok(markers)
            } ?: let {
                return ResponseEntity.ok().build()
            }

        } ?: let {
            return ResponseEntity.status(400).body("not found Aqrm!!")
        }
    }


    init {
        this.userRepository  = userRepository
        this.aquariumRepository = aquariumRepository
        this.mentionRepository = mentionRepository
    }


}