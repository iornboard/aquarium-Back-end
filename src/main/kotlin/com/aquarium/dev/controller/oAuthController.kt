package com.aquarium.dev.controller


import com.aquarium.dev.config.auth.PrincipalDetails
import com.aquarium.dev.config.auth.oauth.provider.FacebookUserInfo
import com.aquarium.dev.config.auth.oauth.provider.GoogleUserInfo
import com.aquarium.dev.config.auth.oauth.provider.NaverUserInfo
import com.aquarium.dev.config.auth.oauth.provider.OAuth2UserInfo
import com.aquarium.dev.config.jwt.JwtProperties
import com.aquarium.dev.domain.dto.AuthDto
import com.aquarium.dev.domain.dto.JwtDto
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
internal class oAuthController (userRepository: UserRepository) {

    @Autowired
    private val userRepository: UserRepository


    @Autowired
    private val bCryptPasswordEncoder: BCryptPasswordEncoder? = null

    @PostMapping("/oauth/jwt/google")
    fun createGoogleJwt(@RequestBody oauthData: Map<String?, Any?>): JwtDto? {

        val googleUser : OAuth2UserInfo = GoogleUserInfo(oauthData.get("profileObj") as Map<String, Any>)

        val username : String = googleUser?.provider.toString() + "_" + googleUser?.providerId.toString()


        val userEntity : User? = userRepository?.findByUsername(username)

        var user = User()

        if (userEntity==null) { // DB에서 내용이 없을때 새로 생성

            user.provider = googleUser?.provider
            user.providerId = googleUser?.providerId
            user.username = username
            user.userEmail = googleUser?.email
            user.userFullname = googleUser?.name
            user.userNickname = googleUser?.userNickname
            user.userImgUrl =  googleUser?.userImgUrl
            user.userRole = "ROLE_USER"
            user.password = bCryptPasswordEncoder?.encode(user.providerId)   // !! devlog 21.03.28 비밀번호 처리 방법을 고안할 것 !!

            userRepository!!.save(user)
        }else{  // DB에서 내용이 있으면 검증만
            user = userEntity
        }

        val jwtToken: String = JWT.create()
            .withSubject(user.username)
            .withExpiresAt(Date(System.currentTimeMillis() + (JwtProperties.EXPIRATION_TIME)))
            .withClaim("id", user.userId)   // 수정할 것
            .withClaim("username", user.username)
            .sign(Algorithm.HMAC512(JwtProperties.SECRET))

        return  JwtDto(redirectUrl = user.userNickname , authorization = JwtProperties.TOKEN_PREFIX + jwtToken)
    }




    @PostMapping("/oauth/jwt/facebook")
    fun createFacebookJwt(@RequestBody oauthData: Map<String?, Any?>): JwtDto? {

        val facebookUser : OAuth2UserInfo = FacebookUserInfo(oauthData as Map<String, Any>)

        val username : String = facebookUser?.provider.toString() + "_" + facebookUser?.providerId.toString()

        val userEntity : User? = userRepository?.findByUsername(username)

        var user = User()

        if (userEntity==null) { // DB에서 내용이 없을때 새로 생성

            user.provider = facebookUser?.provider
            user.providerId = facebookUser?.providerId
            user.username = username
            user.userEmail = facebookUser?.email
            user.userFullname = facebookUser?.name
            user.userNickname = facebookUser?.userNickname
            user.userImgUrl =  facebookUser?.userImgUrl
            user.userRole = "ROLE_USER"
            user.password = bCryptPasswordEncoder?.encode(user.providerId)   // !! devlog 21.03.28 비밀번호 처리 방법을 고안할 것 !!

            userRepository!!.save(user)
        }else{  // DB에서 내용이 있으면 검증만
            user = userEntity
        }

        val jwtToken: String = JWT.create()
            .withSubject(user.username)
            .withExpiresAt(Date(System.currentTimeMillis() + (JwtProperties.EXPIRATION_TIME)))
            .withClaim("id", user.userId)   // 수정할 것
            .withClaim("username", user.username)
            .sign(Algorithm.HMAC512(JwtProperties.SECRET))

        return  JwtDto(redirectUrl = user.userNickname , authorization = JwtProperties.TOKEN_PREFIX + jwtToken)
    }




    @PostMapping("/oauth/jwt/naver")
    fun createNaverJwt(@RequestBody oauthData: Map<String?, Any?>): JwtDto? {

        println("oauthData : $oauthData")

        val naverUser : OAuth2UserInfo = NaverUserInfo(oauthData.get("profileObj") as Map<String, Any>)

        val username : String = naverUser?.provider.toString() + "_" + naverUser?.providerId.toString()

        val userEntity : User? = userRepository?.findByUsername(username)

        var user = User()

        if (userEntity==null) { // DB에서 내용이 없을때 새로 생성

            user.provider = naverUser?.provider
            user.providerId = naverUser?.providerId
            user.username = username
            user.userEmail = naverUser?.email
            user.userFullname = naverUser?.name
            user.userNickname = naverUser?.userNickname
            user.userImgUrl =  naverUser?.userImgUrl
            user.userRole = "ROLE_USER"
            user.password = bCryptPasswordEncoder?.encode(user.providerId)   // !! devlog 21.03.28 비밀번호 처리 방법을 고안할 것 !!

            userRepository!!.save(user)
        }else{  // DB에서 내용이 있으면 검증만
            user = userEntity
        }

        val jwtToken: String = JWT.create()
            .withSubject(user.username)
            .withExpiresAt(Date(System.currentTimeMillis() + (JwtProperties.EXPIRATION_TIME)))
            .withClaim("id", user.userId)   // 수정할 것
            .withClaim("username", user.username)
            .sign(Algorithm.HMAC512(JwtProperties.SECRET))

        return  JwtDto(redirectUrl = user.userNickname , authorization = JwtProperties.TOKEN_PREFIX + jwtToken)
    }


    init {
        this.userRepository = userRepository
    }

}