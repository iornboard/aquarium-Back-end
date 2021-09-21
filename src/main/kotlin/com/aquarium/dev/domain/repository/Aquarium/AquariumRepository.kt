package com.aquarium.dev.domain.repository.Aquarium


import com.aquarium.dev.domain.entity.Aquarium.Aquarium
import com.aquarium.dev.domain.entity.User.User
import org.springframework.data.jpa.repository.JpaRepository

interface AquariumRepository : JpaRepository<Aquarium?, Int?> {
    open fun findAllByUser(user : User) : List<Aquarium>?
}