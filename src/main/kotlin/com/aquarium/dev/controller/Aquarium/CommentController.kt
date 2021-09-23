package com.aquarium.dev.controller.Aquarium

import com.aquarium.dev.domain.dto.Aquarium.CommentDto
import com.aquarium.dev.domain.repository.Aquarium.AquariumRepository
import com.aquarium.dev.domain.repository.Aquarium.CommentRepository
import com.aquarium.dev.domain.repository.Aquarium.MentionRepository
import com.aquarium.dev.domain.repository.User.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class CommentController(userRepository : UserRepository, aquariumRepository: AquariumRepository, mentionRepository: MentionRepository, commentRepository: CommentRepository) {

    private val userRepository : UserRepository
    private val aquariumRepository : AquariumRepository
    private val mentionRepository: MentionRepository
    private val commentRepository: CommentRepository

    @PostMapping("/create-commnet")
    fun createCommnet( @RequestBody comment : CommentDto) : ResponseEntity<Any?> {

        val user = userRepository.findByIdOrNull(comment.userId)

        return user?.let{
            val aqrm = aquariumRepository.findByIdOrNull(comment.aqrmId)

            aqrm?.let{
                val ment = mentionRepository.findByIdOrNull(comment.mentId)

                ment?.let{
                    val res = commentRepository.save(comment.toComment(user,aqrm,it)).toCommentDto()
                    return ResponseEntity.status(200).body(res)
                } ?: let{
                    return ResponseEntity.status(400).body("not found Ment!!")
                }

            } ?: let{
                return ResponseEntity.status(400).body("not found Aqrm!!")
            }

        } ?: let {
            return ResponseEntity.status(400).body("not found User!!")
        }
    }


    @GetMapping("/commnet")
    fun readCommnet(@RequestParam commnetId: Int) : ResponseEntity<Any?> {
        val comment = commentRepository.findByIdOrNull(commnetId)

        return comment?.let{
            return ResponseEntity.ok(it.toCommentDto())
        } ?: let{
            return ResponseEntity.status(400).build()
        }

    }

    @GetMapping("/ment-commnet")
    fun readAllMentCommnet(@RequestParam mentId : Int) : ResponseEntity<Any?>  {
        val ment = mentionRepository.findByIdOrNull(mentId)

        ment?.let{
            val comments = commentRepository.findAllByMent(it)

            comments?.let{
                return ResponseEntity.ok( it.map { it.toCommentDto() })
            } ?: let{
                return ResponseEntity.ok().body(null)
            }

        } ?:let {
            return ResponseEntity.status(400).body("not found Ment!!")
        }

    }


    @GetMapping("/update-commnet")
    fun updateCommnet() {

    }


    @GetMapping("/delete-commnet")
    fun deleteCommnet() {

    }


    init {
        this.userRepository  = userRepository
        this.aquariumRepository = aquariumRepository
        this.mentionRepository = mentionRepository
        this.commentRepository = commentRepository
    }


}