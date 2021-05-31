package com.aquarium.dev.domain.dto



data class FileDto(

    var filename : String? = null,
    var fileDownloadUri : String? = null,
    var fileType : String? = null,
    var size : Long? = null

)