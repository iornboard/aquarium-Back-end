package com.aquarium.dev.config.auth.oauth.provider

interface OAuth2UserInfo {
    val providerId: String?
    val provider: String?
    val email: String?
    val name: String?
    val userNickname : String?
    val userImgUrl : String?
}