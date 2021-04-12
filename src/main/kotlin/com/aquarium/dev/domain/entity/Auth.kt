package com.aquarium.dev.domain.entity

import java.time.LocalDateTime

data class Auth(

    var id : Int = 0,
    var userEmail : String? = null,
    var userFullname : String? = null,
    var userNickname : String? = null,
    var userImgUrl : String? = null,
    var userRole : String? = null

)