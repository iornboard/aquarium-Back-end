package com.aquarium.dev.controller

import com.aquarium.dev.domain.entity.Post
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity

import org.springframework.web.multipart.MultipartFile
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.servlet.mvc.support.RedirectAttributes

import org.springframework.web.bind.annotation.RequestParam










@RestController
@RequestMapping("/api")
class file {

    @PostMapping("/imagesd")
    fun uploadFile( @RequestBody post : Post): String? {

        return "redirect:/api/signup"
    }

//    @PostMapping(value = ["/image"], consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE])
//    fun uploadFileImage(img : MultipartFile): ResponseEntity<*>? {
//
//        println("img : $img")
//
//        return ResponseEntity.ok().build<Any>()
//    }

    @PostMapping("/")
    fun fileUpload( @RequestParam("file") file: MultipartFile,  redirectAttributes: RedirectAttributes): String? {
//        storageService.store(file)
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.originalFilename + "!")
        return "redirect:/"
    }

}