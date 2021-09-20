package com.aquarium.dev.domain.repository.Aquarium


import com.aquarium.dev.domain.entity.Aquarium.Aquarium
import org.springframework.data.jpa.repository.JpaRepository

interface AquariumRepository : JpaRepository<Aquarium?, Int?> {
}