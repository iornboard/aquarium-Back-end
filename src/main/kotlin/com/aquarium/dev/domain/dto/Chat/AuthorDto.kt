package com.aquarium.dev.domain.dto.Chat

data class AuthorDto(
    var username : String? = null,
    var id : Int = 0, //  == userId
    var avatarUrl : String? = null
)
