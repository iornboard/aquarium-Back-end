package com.aquarium.dev.domain.entity.User


import com.aquarium.dev.domain.entity.Community.Post
import com.fasterxml.jackson.annotation.JsonBackReference
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AI
    @Column(name = "user_id")
    var userId : Int = 0,
    var username : String? = null,
    var password : String? = null,

    var userEmail : String? = null,
    var userFullname : String? = null,
    var userNickname : String? = null,
    var userImgUrl : String? = null,

    var userFollowerCount :Int = 0,
    var userFollowingCount : Int = 0,
    var userNoticeCount : Int = 0,

    var userRole : String? = null,
    var userIsAgree : Boolean = false,

    var userLastAccess : LocalDateTime? =null,
    var createdAt : LocalDateTime? =null,
    var updatedAt : LocalDateTime? =null,

    var provider : String? = null,  // 소셜 회원가입 정보 -> google, naver등
    var providerId : String? = null  // 소셜 회원가입 ID(숫자로 되어 있는 것)

//    @OneToMany(mappedBy = "user")
//    @JsonBackReference
//    var posts : Set<Post>

)
