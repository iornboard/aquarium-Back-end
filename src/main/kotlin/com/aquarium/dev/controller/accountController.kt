package com.aquarium.dev.controller


import com.aquarium.dev.config.auth.PrincipalDetails
import com.aquarium.dev.config.auth.oauth.provider.FacebookUserInfo
import com.aquarium.dev.config.auth.oauth.provider.GoogleUserInfo
import com.aquarium.dev.config.auth.oauth.provider.NaverUserInfo
import com.aquarium.dev.config.auth.oauth.provider.OAuth2UserInfo
import com.aquarium.dev.config.jwt.JwtProperties
import com.aquarium.dev.domain.dto.AuthDto
import com.aquarium.dev.domain.entity.User.User
import com.aquarium.dev.domain.repository.UserRepository
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

import org.springframework.web.bind.annotation.RequestBody

import org.springframework.web.bind.annotation.PostMapping
import java.util.*


@RestController
@RequestMapping("/api")
internal class accountController (userRepository: UserRepository) {

    @Autowired
    private val userRepository: UserRepository


    @Autowired
    private val bCryptPasswordEncoder: BCryptPasswordEncoder? = null

    @GetMapping("/foo")
    fun foo(): String {
        return "it is a foo"
    }

    @GetMapping("/auth")
    fun auth(@RequestHeader( JwtProperties.HEADER_STRING ) jwtHeader : String): AuthDto? {

        // 해더가 있는지 or 우리 토큰이 맟는지 -> 없으면 끝냄
        if (jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
            return null
        }

        // 위에서 받은 토큰확인해서 username을 확인
        val token: String = jwtHeader.replace(JwtProperties.TOKEN_PREFIX, "")

        val username: String? = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
            .getClaim("username").asString()

        val user : User? = userRepository.findByUsername(username)
        val responseAuth = AuthDto()

        if(user != null){
            responseAuth.userId = user.userId
            responseAuth.userEmail = user.userEmail
            responseAuth.userFullname = user.userFullname
            responseAuth.userNickname = user.userNickname
            responseAuth.userImgUrl = user.userImgUrl
            responseAuth.userRole = user.userRole
        }

        return responseAuth
    }

    @PostMapping("/join")
    fun join( @RequestBody  user : User): String? {

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

    @PatchMapping("/user-image")
    fun userImgUpdate( @RequestBody responseUser : User): String? {

        val targetUser : User = userRepository.getOne(responseUser.userId)

        targetUser.userImgUrl = responseUser.userImgUrl
        userRepository.save(targetUser)

        return "redirect:/api/signup"
    }

    @GetMapping("/user-info")
    fun getUserIdToInfo( @RequestParam userId : Int ): User? {

        val targetUserInfo : User = userRepository.getOne(userId)

        println(targetUserInfo)

        return targetUserInfo
    }


    init {
        this.userRepository = userRepository
    }

}