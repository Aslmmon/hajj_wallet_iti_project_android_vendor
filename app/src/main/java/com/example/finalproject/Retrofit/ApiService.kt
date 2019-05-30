package com.example.finalproject.Retrofit

import com.example.finalproject.Model.Users
import com.example.finalproject.Model.response
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST




//private const val BASE_URL = "https://jsonplaceholder.typicode.com"
private const val BASE_URL = "https://reqres.in"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()



interface ApiService {
    @GET("/posts")
    fun getText():
            /*  The Coroutine Call Adapter allows us to return a Deferred, a Job with a result*/
            Deferred<String>

   // @POST("/posts")
    @POST("/api/login")
    @FormUrlEncoded
    fun login(
       @Field("email") email: String,
       @Field("password") pass: String
       // @Field("userId") userId: Long
    ): Deferred<response>
}

object service {
    val retrofitService : ApiService by lazy { retrofit.create(ApiService::class.java) }
}