package com.dinesh.foregroundservices.di

import android.content.Context
import androidx.work.WorkManager
import com.dinesh.foregroundservices.data.local.QuoteDao
import com.dinesh.foregroundservices.data.local.QuoteDatabase
import com.dinesh.foregroundservices.data.remote.ApiService
import com.dinesh.foregroundservices.data.repository.QuoteRepoImpl
import com.dinesh.foregroundservices.domain.repository.QuoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : QuoteDatabase {
        return QuoteDatabase.getInstance(context)
    }

    @Provides
    fun provideDao(quoteDatabase: QuoteDatabase) : QuoteDao {
        return quoteDatabase.getQuoteDao()
    }

    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context) : WorkManager {
        return WorkManager.getInstance(context)
    }

    @Provides
    fun provideQuoteRepository(workManager: WorkManager, quoteDao: QuoteDao) : QuoteRepository {
        return QuoteRepoImpl(workManager, quoteDao)
    }
}
