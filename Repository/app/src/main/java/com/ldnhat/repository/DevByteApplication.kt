package com.ldnhat.repository

import android.app.Application
import androidx.work.*
import com.ldnhat.repository.devbyteviewer.RefreshDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class DevByteApplication : Application() {

    val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.UNMETERED).build()

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    private fun delayInit(){
        applicationScope.launch {
            setupRecurringWork()
        }
    }

    override fun onCreate() {
        super.onCreate()
        delayInit()
    }

    private fun setupRecurringWork(){
        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(2, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            RefreshDataWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )
    }
}