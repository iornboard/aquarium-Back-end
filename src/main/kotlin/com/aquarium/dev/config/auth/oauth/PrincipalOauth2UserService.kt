package com.aquarium.dev.config.auth.oauth

import com.aquarium.dev.domain.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.stereotype.Service
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.oauth2.core.OAuth2AuthenticationException
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import com.aquarium.dev.domain.repository.UserRepository


// 구글에서 받은 userRequest 데이터를 여기에서 받는다. (사후처리를 여기서 처리할 것)
@Service
class PrincipalOauth2UserService : DefaultOAuth2UserService() {


    @Autowired
    private val bCryptPasswordEncoder : BCryptPasswordEncoder? = null

    @Autowired
    private val userRepository: UserRepository? = null


    @Throws(OAuth2AuthenticationException::class)
    override fun loadUser(userRequest : OAuth2UserRequest) : OAuth2User {

        val oAuth2User = super.loadUser(userRequest)  //  oAuth2User == 유저 회원 정보(프로필)


        return super.loadUser((userRequest))
    }

}