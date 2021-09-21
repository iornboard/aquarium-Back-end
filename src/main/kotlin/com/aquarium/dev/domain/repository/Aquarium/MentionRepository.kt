package com.aquarium.dev.domain.repository.Aquarium

import com.aquarium.dev.domain.entity.Aquarium.Aquarium
import com.aquarium.dev.domain.entity.Aquarium.Mention
import com.aquarium.dev.domain.entity.User.User
import org.springframework.data.jpa.repository.JpaRepository


interface MentionRepository : JpaRepository<Mention?, Int?> {
    open fun findAllByUser(user : User) : List<Mention>?

    open fun findAllByAqrm(aqrm : Aquarium) : List<Mention>?
}