package com.aquarium.dev.storage


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
class FileStorageService: FileStorage{

    val rootLocation = Paths.get("C:\\Users\\KUCK_SU\\Documents\\project-aquarium\\aquarium-Back-end\\src\\main\\kotlin\\com\\aquarium\\dev\\storage")

    override fun save(file: MultipartFile){
        Files.copy(file.getInputStream(), this.rootLocation.resolve(file.originalFilename.toString()))
    }

    override fun loadFile(filename: String): Resource{
        val file = rootLocation.resolve(filename)
        val resource = UrlResource(file.toUri())

        if(resource.exists() || resource.isReadable()) {
            return resource
        }else{
            throw RuntimeException("FAIL!")
        }
    }

    override fun deleteAll(){
        FileSystemUtils.deleteRecursively(rootLocation.toFile())
    }

    override fun init(){
        Files.createDirectory(rootLocation)
    }

    override fun loadFiles(): Stream<Path>{
        return Files.walk(this.rootLocation, 1)
            .filter{path -> !path.equals(this.rootLocation)}
            .map(this.rootLocation::relativize)
    }
}