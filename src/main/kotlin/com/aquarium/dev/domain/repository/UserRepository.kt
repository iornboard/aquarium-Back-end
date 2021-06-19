package com.aquarium.dev.domain.repository

import com.aquarium.dev.domain.entity.User.User
import org.springframework.data.jpa.repository.JpaRepository

// 출처 : https://github.com/codingspecialist/Sringboot-Security-Basic-V1/blob/master/java/com/cos/securityex01/repository/UserRepository.java

interface UserRepository : JpaRepository<User?, Int?> {
    open fun findByUsername(username: String?): User? // JPA quary methed 문법을 기준으로 만들 것 (메서드 이름은 변수를 따라갈 것)

}