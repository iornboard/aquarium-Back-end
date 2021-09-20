package com.aquarium.dev.controller._Ect

import com.aquarium.dev.domain.dto._Ect.FileDto
import com.aquarium.dev.storage.FileStorage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.io.IOException

import javax.servlet.http.HttpServletRequest

import org.springframework.web.bind.annotation.PathVariable

import org.springframework.web.bind.annotation.GetMapping


//출처1 : https://www.youtube.com/watch?v=tSKg5bXPXZA
//출처2 : https://grokonez.com/spring-framework/spring-boot/kotlin-spring-boot/kotlin-springboot-upload-download-file-multipartfile-thymeleaf-bootstrap-4

@RestController
@RequestMapping("/api")
class FileController {

    @Autowired
    lateinit var fileStorage: FileStorage

    @PostMapping("/image")
    fun uploadMultipartFile(@RequestParam("img") file: MultipartFile):ResponseEntity<FileDto> {
        fileStorage.save(file)

        val fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/api/file/")
            .path(file.originalFilename.toString())
            .toUriString()

        println("fileDownloadUri : $fileDownloadUri")

        val fileResponse = FileDto()
        fileResponse.filename =  file.originalFilename.toString()
        fileResponse.fileDownloadUri = fileDownloadUri
        fileResponse.fileType = file.contentType
        fileResponse.size = file.size

        return ResponseEntity<FileDto>(fileResponse, HttpStatus.OK)
    }


    @GetMapping("/file/{fileName:.+}")
    fun downloadFile(@PathVariable fileName: String, request: HttpServletRequest): ResponseEntity<Resource?>? {

        println("실행")

        val resource: Resource = fileStorage.loadFile(fileName)
        var contentType: String? = null
        try {
            contentType = request.servletContext.getMimeType(resource.getFile().getAbsolutePath())
        } catch (ex: IOException) {
            println("Could not determine fileType")
        }
        if (contentType == null) {
            contentType = "application/octet-stream"
        }
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .body<Resource?>(resource)
    }


}