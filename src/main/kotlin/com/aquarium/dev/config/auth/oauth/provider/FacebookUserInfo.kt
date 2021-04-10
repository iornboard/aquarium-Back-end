package com.aquarium.dev.config.auth.oauth.provider

class FacebookUserInfo(private val attributes: Map<String, Any>) : OAuth2UserInfo {

    override val providerId: String?
        get() = attributes["id"] as String?

    override val name: String?
        get() = attributes["name"] as String?

    override val email: String?
        get() = attributes["email"] as String?

    override val provider: String
        get() = "facebook"

}