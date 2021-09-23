package com.aquarium.dev.domain.repository.Aquarium

import com.aquarium.dev.domain.entity.Aquarium.Comment
import com.aquarium.dev.domain.entity.Aquarium.Mention
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment?, Int?> {

    open fun findAllByMent(mention: Mention) : List<Comment>?

}