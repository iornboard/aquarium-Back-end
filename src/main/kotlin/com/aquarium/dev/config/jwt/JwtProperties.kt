package com.aquarium.dev.config.jwt

interface JwtProperties {
    companion object {
        const val SECRET = "kuck_su" // 비밀키
        const val EXPIRATION_TIME = 864000000 // 10일 (1/1000초)
        const val TOKEN_PREFIX = "Bearer "
        const val HEADER_STRING = "Authorization"
    }
}