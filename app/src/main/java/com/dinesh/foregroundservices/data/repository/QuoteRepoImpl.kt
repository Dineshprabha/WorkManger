package com.dinesh.foregroundservices.data.repository

import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.dinesh.foregroundservices.data.local.QuoteDao
import com.dinesh.foregroundservices.data.worker.FetchWorker
import com.dinesh.foregroundservices.data.worker.NotificationWorker
import com.dinesh.foregroundservices.data.worker.PeriodicWorker
import com.dinesh.foregroundservices.domain.model.Quote
import com.dinesh.foregroundservices.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit

class QuoteRepoImpl(
    private val workManager: WorkManager,
    private val quoteDao: QuoteDao,
) : QuoteRepository {

    override fun getQuote() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workRequest = OneTimeWorkRequestBuilder<FetchWorker>()
            .setConstraints(constraints)
            .build()

        val notificationWorkRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .build()

        workManager.beginWith(workRequest)
            .then(notificationWorkRequest)
            .enqueue()
    }

    override fun getAllQuotes(): Flow<List<Quote>> = quoteDao.getAllQuotes()

    override fun setPeriodicWorkRequest() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest =
            PeriodicWorkRequest.Builder(PeriodicWorker::class.java, 15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()

        workManager.enqueueUniquePeriodicWork(
            "periodic_request",
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )

    }
}