package com.aquarium.dev.domain.dto.Aquarium

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

    var userId : Int = 0
    )