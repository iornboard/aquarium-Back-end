package com.aquarium.dev.domain.dto.Aquarium

import com.aquarium.dev.domain.dto.User.UserDto
import com.aquarium.dev.domain.entity.Aquarium.Aquarium
import com.aquarium.dev.domain.entity.User.User
import java.time.LocalDateTime

data class AquariumDto (
    var aqrmId : Int = 0,

    var aqrmTitle : String? = null,
    var aqrmContentType : String? = null,

    var aqrmImgUrl : String? = null,
    var aqrmVideoUrl : String? = null,

    var aqrmMentionCount : Int = 0,
    var aqrmLikeCount : Int = 0,
    var aqrmViewCount : Int = 0,

    var aqrmIsPrivate : Boolean = false,
    var aqrmIsBlinded : Boolean = false,

    var createdAt : LocalDateTime? = null,
    var updatedAt : LocalDateTime? = null,

    var userId : Int? = null,

    var userInfo : UserDto? = null

    ) {

    fun toAquarium(user : User) : Aquarium {
        return Aquarium(
            aqrmId = aqrmId,
            aqrmTitle = aqrmTitle,
            aqrmContentType = aqrmContentType,
            aqrmImgUrl = aqrmImgUrl,
            aqrmVideoUrl = aqrmVideoUrl,
            aqrmMentionCount = aqrmMentionCount,
            aqrmLikeCount = aqrmLikeCount,
            aqrmViewCount = aqrmViewCount,
            aqrmIsPrivate = aqrmIsPrivate,
            aqrmIsBlinded = aqrmIsBlinded,
            createdAt = createdAt,
            updatedAt = updatedAt,

            user = user
        )

    }
}