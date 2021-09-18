package com.aquarium.dev.domain.entity.Aquarium

import com.aquarium.dev.domain.entity.User.User
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Mention(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
        @Column(name = "ment_id")
        var mentId : Int = 0,

        var mentText : String? = null,
        var mentImgUrl : String? = null,

        var mentCommentCount : Int = 0,
        var mentLikeCount : Int = 0,
        var mentViewCount : Int = 0,

        var mentIsPrivate : Boolean = false,
        var mentIsBlinded : Boolean = false,

        @CreatedDate
        @JsonIgnore
        var createdAt : LocalDateTime? = LocalDateTime.now(),

        @LastModifiedBy
        @JsonIgnore
        var updatedAt : LocalDateTime? = LocalDateTime.now(),

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
        @JsonManagedReference
        var user : User,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "aqrm_id", referencedColumnName = "aqrm_id", nullable = false)
        @JsonManagedReference
        var aqrm : Aquarium


)