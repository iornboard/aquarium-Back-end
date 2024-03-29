package com.aquarium.dev.controller.User


import com.aquarium.dev.config.jwt.JwtProperties
import com.aquarium.dev.domain.dto.User.UserDto
import com.aquarium.dev.domain.dto._Ect.AuthDto
import com.aquarium.dev.domain.entity.User.User
import com.aquarium.dev.domain.repository.User.UserRepository
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

import org.springframework.web.bind.annotation.RequestBody

import org.springframework.web.bind.annotation.PostMapping


@RestController
@RequestMapping("/api")
internal class AccountController (userRepository: UserRepository) {

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

    @GetMapping("/auth-userpage")
    fun authUserPage( @RequestParam  userNickname : String ): ResponseEntity<Any> {

        println(userNickname)

        val user : User? = userRepository.findByUserNickname(userNickname)

        if(user == null){
            return ResponseEntity.ok().body(null)

        }else{
            return ResponseEntity.ok(UserDto().toUserDto(user))
        }
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

        return targetUserInfo
    }

    @GetMapping("/user-info-all")
    fun getAllUserIdToInfo( @RequestParam userId : Int? ): Set<User?> {

        val targetUserInfo = userRepository.findAll()

        if(userId != null){
            val requestUser = userRepository.getOne(userId)

            return targetUserInfo.toSet() - setOf<User>(requestUser)
        }
        else{
            return targetUserInfo.toSet()
        }
    }

    init {
        this.userRepository = userRepository
    }

}