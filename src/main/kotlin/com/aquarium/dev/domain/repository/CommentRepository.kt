package com.aquarium.dev.domain.repository

import com.aquarium.dev.domain.entity.Aquarium.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment?, Int?> {
    open fun findAllByPost_PostId(postId: Int) : MutableList<Comment?>
}