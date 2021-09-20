package com.aquarium.dev.domain.dto._Ect



data class FileDto(

    var filename : String? = null,
    var fileDownloadUri : String? = null,
    var fileType : String? = null,
    var size : Long? = null

)