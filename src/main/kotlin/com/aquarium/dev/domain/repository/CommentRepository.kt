package com.aquarium.dev.domain.repository

import com.aquarium.dev.domain.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment?, Int?> {
}