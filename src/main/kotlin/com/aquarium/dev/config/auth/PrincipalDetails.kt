package com.aquarium.dev.config.auth

import com.aquarium.dev.domain.entity.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User

import java.util.ArrayList


// Security Session => Authentication   => UserDetails( == principalDetails)
// 출처 : https://github.com/codingspecialist/Sringboot-Security-Basic-V1/blob/master/java/com/cos/securityex01/config/auth/PrincipalDetails.java

//  !! 2개 타입을 구분없이 받기위해서 2개의 타입을 구현함

class PrincipalDetails(private val user: User) : UserDetails {


    fun getUser(): User? {
        return user
    }

    override fun getPassword(): String {
        return user.password.toString()
    }

    override fun getUsername(): String {
        return user.username.toString()
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    // 아래 정보는 공부가 좀 더 필요함
    override fun getAuthorities(): Collection<GrantedAuthority> {
        val collet: MutableCollection<GrantedAuthority> = ArrayList()
        collet.add(GrantedAuthority { user.userRole.toString() })
        return collet
    }
}