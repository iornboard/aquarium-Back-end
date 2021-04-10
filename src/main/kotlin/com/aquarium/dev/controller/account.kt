package com.aquarium.dev.controller


import com.aquarium.dev.config.jwt.JwtProperties
import com.aquarium.dev.domain.entity.User
import com.aquarium.dev.domain.repository.UserRepository
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@RestController
@RequestMapping("/api")
internal class account (userRepository: UserRepository) {

    @Autowired
    private val userRepository: UserRepository

    @Autowired
    private val bCryptPasswordEncoder: BCryptPasswordEncoder? = null

    @GetMapping("/foo")
    fun foo(): String {
        return "it is a foo"
    }

    @GetMapping("/login")
    fun login(): String {
        return "it is a foo"
    }

    @GetMapping("/auth")
    fun auth(@RequestHeader( JwtProperties.HEADER_STRING ) jwtHeader : String): User? {

        // 해더가 있는지 or 우리 토큰이 맟는지 -> 없으면 끝냄
        if (jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
            return null
        }

        // 위에서 받은 토큰확인해서 username을 확인
        val token: String = jwtHeader.replace(JwtProperties.TOKEN_PREFIX, "")

        val username: String? = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
            .getClaim("username").asString()

        val user : User = userRepository.findByUsername(username)

        user.password = "protected"
        user.createdAt  = null
        user.updatedAt = null
        user.provider  = "protected"
        user.providerId  = "protected"

        return user
    }

    @PostMapping("/join")
    fun join( @RequestBody  user : User ): String? {

        user.userRole = "USER_ROLE"
        user.username = user.userEmail
        val rawPassword : String? = user.password
        val encPassword = bCryptPasswordEncoder!!.encode(rawPassword)
        user.password = encPassword
        user.provider = "aquarium"
        user.providerId ="aquarium"
        userRepository.save(user)

        return "redirect:/api/signup"
    }

    init {
        this.userRepository = userRepository
    }

}