package com.ekasoftware.english.view.reading.books.network

import com.ekasoftware.english.view.reading.books.model.Book
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface BooksApiService {
    @GET("api/v1/stories")
    suspend fun getBooks() : List<Book>

    //https://64b3dc980efb99d86268750d.mockapi.io/api/v1/stories

    companion object {
        var apiService: BooksApiService? = null
        fun getInstance() : BooksApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://64b3dc980efb99d86268750d.mockapi.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(BooksApiService::class.java)
            }
            return apiService!!
        }
    }
}