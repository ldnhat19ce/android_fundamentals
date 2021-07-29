package com.ldnhat.repository.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.ldnhat.repository.database.VideoDatabase
import com.ldnhat.repository.database.asDomainModel
import com.ldnhat.repository.domain.DevByteVideos
import com.ldnhat.repository.network.DevByteNetwork
import com.ldnhat.repository.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class VideoRepository (private val database : VideoDatabase){

    val videos : LiveData<List<DevByteVideos>> = Transformations.map(database.videoDao.getVideos()){
        it.asDomainModel()
    }


    suspend fun refreshVideos(){
        withContext(Dispatchers.IO){
            Timber.d("refresh videos is called");
            val playlist = DevByteNetwork.devbytes.getPlayList()
            database.videoDao.insertAll(playlist.asDatabaseModel())
        }
    }
}