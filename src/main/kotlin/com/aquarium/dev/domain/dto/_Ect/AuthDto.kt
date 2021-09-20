package com.aquarium.dev.domain.dto._Ect

data class AuthDto(

    var userId : Int = 0,
    var userEmail : String? = null,
    var userFullname : String? = null,
    var userNickname : String? = null,
    var userImgUrl : String? = null,
    var userRole : String? = null

)