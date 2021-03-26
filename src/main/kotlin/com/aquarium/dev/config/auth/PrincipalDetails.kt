package com.aquarium.dev.config.auth

import com.aquarium.dev.domain.entity.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.GrantedAuthority

import java.util.ArrayList


// Security Session => Authentication   => UserDetails( == principalDetails)
// 출처 : https://github.com/codingspecialist/Sringboot-Security-Basic-V1/blob/master/java/com/cos/securityex01/config/auth/PrincipalDetails.java


class PrincipalDetails(private val user: User) : UserDetails {
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

    override fun getAuthorities(): Collection<GrantedAuthority> {
        val collet: MutableCollection<GrantedAuthority> = ArrayList()
        collet.add(GrantedAuthority { user.userRole.toString() })
        return collet
    }
}