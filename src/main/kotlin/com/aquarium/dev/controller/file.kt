package com.aquarium.dev.controller

import com.aquarium.dev.storage.FileStorage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes



@RestController
@RequestMapping("/api")
class file {

    @Autowired
    lateinit var fileStorage: FileStorage

    @PostMapping("/image")
    fun uploadMultipartFile(@RequestParam("img") file: MultipartFile, redirectAttributes: RedirectAttributes): String {
        println("file : $file")
        fileStorage.save(file);
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.originalFilename + "!")
        return "redirect:/"
    }

}