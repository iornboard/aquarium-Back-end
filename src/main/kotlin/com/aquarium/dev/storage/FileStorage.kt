package com.aquarium.dev.storage

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path
import java.util.stream.Stream

interface FileStorage {
    fun save(file: MultipartFile, filename: String)
    fun loadFile(filename: String): Resource
    fun deleteAll()
    fun init()
    fun loadFiles(): Stream<Path>
}