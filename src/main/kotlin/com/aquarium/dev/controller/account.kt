package com.aquarium.dev.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/account")
public class account {


    @GetMapping("/login")
    public fun login() : String {
        return "account/"
    }
}