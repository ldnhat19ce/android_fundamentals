package com.ldnhat.lesson8.network

import com.squareup.moshi.Json

data class MarsPropety(
    val id:String,

    @Json(name = "img_src")
    val imgSrcUrl:String,
    val type:String,
    val price:Double

)