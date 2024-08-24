package com.dinesh.foregroundservices.domain.repository


import com.dinesh.foregroundservices.domain.model.Quote
import kotlinx.coroutines.flow.Flow


interface QuoteRepository {

    fun getQuote()

    fun getAllQuotes() : Flow<List<Quote>>

    fun setPeriodicWorkRequest()
}