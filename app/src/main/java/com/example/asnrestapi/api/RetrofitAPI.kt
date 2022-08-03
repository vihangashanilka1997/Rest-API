package com.example.asnrestapi.api

import com.example.asnrestapi.data.comment
import com.example.asnrestapi.data.post
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitAPI {

    @GET("posts")
    fun getPost():Call<List<post>>

    @GET("posts/{id}")
    fun getPostDetails(@Path("id") id:Int):Call<post>

    @GET("comments")
    fun getComments():Call<List<comment>>

    companion object {
         val BASE_URL = "https://jsonplaceholder.typicode.com/"

        fun create() : RetrofitAPI{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(RetrofitAPI::class.java)
        }
    }
}