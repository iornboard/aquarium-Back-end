package com.aquarium.dev.domain.repository.Aquarium

import com.aquarium.dev.domain.entity.Aquarium.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment?, Int?> {

}