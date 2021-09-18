package com.aquarium.dev.domain.repository

import com.aquarium.dev.domain.entity.Aquarium.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post?, Int?> {
}