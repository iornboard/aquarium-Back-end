package com.aquarium.dev.config.auth.oauth

import com.aquarium.dev.config.auth.oauth.provider.FacebookUserInfo
import com.aquarium.dev.config.auth.oauth.provider.GoogleUserInfo
import com.aquarium.dev.config.auth.oauth.provider.NaverUserInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.stereotype.Service
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.oauth2.core.OAuth2AuthenticationException
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import com.aquarium.dev.domain.repository.UserRepository
import com.aquarium.dev.config.auth.oauth.provider.OAuth2UserInfo
import com.aquarium.dev.domain.entity.User
import com.aquarium.dev.config.auth.PrincipalDetails

import java.util.*


// 출처 : https://github.com/codingspecialist/Springboot-Security-OAuth2.0-V2/blob/master/src/main/java/com/cos/securityex01/config/oauth/PrincipalOauth2UserService.java
// 이 부분이 실행되는 원리는? 세큐리티가 알아서 처리하는가?
// 구글에서 받은 userRequest 데이터를 여기에서 받는다. (사후처리를 여기서 처리할 것)
@Service
class PrincipalOauth2UserService : DefaultOAuth2UserService() {

    @Autowired
    private val userRepository: UserRepository? = null


    @Throws(OAuth2AuthenticationException::class)
    override fun loadUser(userRequest : OAuth2UserRequest) : OAuth2User {

        val oAuth2User = super.loadUser(userRequest)  //  oAuth2User == 유저 회원 정보(프로필)

        var oAuth2UserInfo: OAuth2UserInfo? = null  // oAuth2UserInfo == Oauth2로 받아온 각 플팻폼의 유저 프로파일

        if (userRequest.clientRegistration.registrationId.equals("google")) {
            println("구글 로그인 요청")
            oAuth2UserInfo = GoogleUserInfo(oAuth2User.attributes)
        } else if (userRequest.clientRegistration.registrationId.equals("facebook")) {
            println("페이스북 로그인 요청")
            oAuth2UserInfo = FacebookUserInfo(oAuth2User.attributes)
        } else if (userRequest.clientRegistration.registrationId.equals("naver")) {
            println("네이버 로그인 요청")
            oAuth2UserInfo = NaverUserInfo(oAuth2User.attributes["response"] as Map<String, Any>)
        } else {
            println("지원하지 않은 로그인 형식입니다.")
        }


        // 아래부터는 아직 이해하지못해서 급한대로 코드를 그냥 복붙해버림
        // 추후에 공부하고 수정해야 함
//        val userOptional: Optional<User?>? =
//            userRepository!!.findByProviderAndProviderId(oAuth2UserInfo!!.provider, oAuth2UserInfo.providerId)
//
//        val user: User
//        if (userOptional!!.isPresent()) {
//            user = userOptional.get()
//            // user가 존재하면 update 해주기
//            user.userEmail = oAuth2UserInfo.email
//            userRepository.save(user)
//        } else {
//            // user의 패스워드가 null이기 때문에 OAuth 유저는 일반적인 로그인을 할 수 없음.
//            user = User.builder()
//                .username(oAuth2UserInfo.provider + "_" + oAuth2UserInfo.providerId)
//                .email(oAuth2UserInfo.email)
//                .role("ROLE_USER")
//                .provider(oAuth2UserInfo.provider)
//                .providerId(oAuth2UserInfo.providerId)
//                .build()
//            userRepository.save(user)
//        }
//
       return super.loadUser(userRequest)

    }

}