package com.aquarium.dev.domain.repository

import com.aquarium.dev.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User?, Int?> {
}