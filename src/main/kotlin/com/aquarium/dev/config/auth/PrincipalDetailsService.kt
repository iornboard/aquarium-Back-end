package com.aquarium.dev.config.auth

import com.aquarium.dev.domain.entity.User
import com.aquarium.dev.domain.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

// 출처 : https://github.com/codingspecialist/Sringboot-Security-Basic-V1/blob/master/java/com/cos/securityex01/config/auth/PrincipalDetailsService.java

//WebSecurityConfig 에서  loginProcessingUrl() 실행시에
// 자동으로 UserDetailsService 타입으로 IoC 되어있는 loadUserByUserName 함수가 실행될 것

// [구조]
// Security Session(Authentication( UserDetails == principalDetails(userEntity)))
@Service
class PrincipalDetailsService : UserDetailsService {
    @Autowired
    private val userRepository: UserRepository? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String?): UserDetails? {
        val user: User = userRepository?.findByUsername(username) ?: return null
        return PrincipalDetails(user)
    }
}