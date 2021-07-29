package com.ldnhat.repository.devbyteviewer

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ldnhat.repository.database.getDatabase
import com.ldnhat.repository.repos.VideoRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params : WorkerParameters) : CoroutineWorker(appContext, params){

    companion object{
        const val WORK_NAME = "com.ldnhat.repository.devbyteviewer.RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = VideoRepository(database)
        try {
            repository.refreshVideos()
        }catch (e : HttpException){
            return Result.retry()
        }
        return Result.success()
    }
}