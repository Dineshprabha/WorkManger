package com.dinesh.foregroundservices.data.remote

import com.dinesh.foregroundservices.data.model.QuoteDTO
import retrofit2.http.GET

interface ApiService {

//    https://dummyjson.com/quotes/random

    @GET("quotes/random")
    suspend fun getQuotes(): QuoteDTO
}