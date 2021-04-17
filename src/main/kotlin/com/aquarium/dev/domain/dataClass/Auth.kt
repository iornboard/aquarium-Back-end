package com.aquarium.dev.domain.dataClass

import java.time.LocalDateTime

data class Auth(

    var userId : Int = 0,
    var userEmail : String? = null,
    var userFullname : String? = null,
    var userNickname : String? = null,
    var userImgUrl : String? = null,
    var userRole : String? = null

)