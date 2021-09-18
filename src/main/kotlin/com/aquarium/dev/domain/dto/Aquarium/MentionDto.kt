package com.aquarium.dev.domain.dto.Aquarium

import java.time.LocalDateTime

data class MentionDto (

    var mentId : Int = 0,

    var mentText : String? = null,
    var mentImgUrl : String? = null,

    var mentCommentCount : Int = 0,
    var mentLikeCount : Int = 0,
    var mentViewCount : Int = 0,

    var mentIsPrivate : Boolean = false,
    var mentIsBlinded : Boolean = false,

    var createdAt : LocalDateTime? = null,
    var updatedAt : LocalDateTime? = null,

    var userId : Int = 0,
    var aqrmId : Int = 0

)