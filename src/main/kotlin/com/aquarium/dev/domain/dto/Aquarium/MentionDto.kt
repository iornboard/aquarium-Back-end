package com.aquarium.dev.domain.dto.Aquarium

import com.aquarium.dev.domain.dto.User.UserDto
import com.aquarium.dev.domain.entity.Aquarium.Aquarium
import com.aquarium.dev.domain.entity.Aquarium.Mention
import com.aquarium.dev.domain.entity.User.User
import java.time.LocalDateTime

data class MentionDto (

    var mentId : Int = 0,

    var mentText : String? = null,
    var mentImgUrl : String? = null,

    var mentCommentCount : Int = 0,
    var mentLikeCount : Int = 0,
    var mentViewCount : Int = 0,

    var x : Int = 0,
    var y : Int = 0,
    var start : Int = 0,
    var end : Int = 0,

    var mentIsPrivate : Boolean = false,
    var mentIsBlinded : Boolean = false,

    var createdAt : LocalDateTime? = null,
    var updatedAt : LocalDateTime? = null,

    var userId : Int = 0,
    var aqrmId : Int = 0,

    var userInfo : UserDto? = null

) {
    fun toMention( user : User, aqrm : Aquarium ) : Mention {
        return Mention(
            mentId = mentId,
            mentText = mentText,
            mentImgUrl = mentImgUrl,
            mentCommentCount = mentCommentCount,
            mentLikeCount = mentLikeCount,
            mentViewCount = mentViewCount,

            xPosition = x,
            yPosition = y,
            startTime = start,
            endTime = end,

            mentIsPrivate = mentIsPrivate,
            mentIsBlinded = mentIsBlinded,
            createdAt = createdAt,
            updatedAt = updatedAt,

            user = user,
            aqrm = aqrm
        )
    }
}