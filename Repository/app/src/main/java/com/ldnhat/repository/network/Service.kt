package com.ldnhat.repository.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


interface DevByteService{

    @GET("devbytes")
    suspend fun getPlayList() : NetworkVideoContainer
}

object DevByteNetwork{

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://android-kotlin-fun-mars-server.appspot.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val devbytes = retrofit.create(DevByteService::class.java)
}