package com.aquarium.dev.domain.entity



data class File(

    var filename : String? = null,
    var fileDownloadUri : String? = null,
    var fileType : String? = null,
    var size : Long? = null

)