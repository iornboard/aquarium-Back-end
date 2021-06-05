package com.aquarium.dev.domain.entity.Chat

import com.aquarium.dev.domain.entity.User.User
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class ChatRoom(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
    @Column(name = "room_id")
    var roomId : Int = 0,

    @CreatedDate
    @JsonIgnore
    var createdAt : LocalDateTime? = LocalDateTime.now(),

    @LastModifiedBy
    @JsonIgnore
    var updatedAt : LocalDateTime? = LocalDateTime.now(),

    @ManyToMany
    @JoinTable(
        name = "chat_assined",
        joinColumns = [JoinColumn(name = "room_id" ,referencedColumnName = "room_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id" ,referencedColumnName = "user_id")]
    )
    @JsonIgnore
    var user : Set<User>

)
