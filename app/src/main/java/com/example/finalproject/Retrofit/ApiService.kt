package com.example.finalproject.Retrofit

import com.example.finalproject.Model.Categories.CategoriesResponse
import com.example.finalproject.Model.CheckWallet.walletExistenceResponse
import com.example.finalproject.Model.CreateWallet.CreateWalletBody
import com.example.finalproject.Model.CreateWallet.CreateWalletData
import com.example.finalproject.Model.CreateWallet.SuccessWalletCreated
import com.example.finalproject.Model.Nationalities.NationalitiesResponse
import com.example.finalproject.Model.PaymentsData.PaymentsDummyData
import com.example.finalproject.Model.QrCodeData.DummyData
import com.example.finalproject.Model.SignOut.SignOutResponse
import com.example.finalproject.Model.TransactionsData.ErrorX
import com.example.finalproject.Model.TransactionsData.transactionsData
import com.example.finalproject.Model.UserLogin.UserResponse
import com.example.finalproject.Model.UserLogin.UserSignUp
import com.example.finalproject.Model.UserSignUp.SignUpResponse
import com.example.finalproject.Model.walletCharged.CardFields
import com.example.finalproject.Model.walletCharged.SuccessWalletCharged
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


//private const val BASE_URL = "https://jsonplaceholder.typicode.com"
private const val BASE_URL = "https://hajwallet.herokuapp.com"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()



interface ApiService {

    @POST("/wallet/create/")
    fun createWallet(@Header("Authorization") token:String,@Body createWalletBody: CreateWalletBody):
            Call<SuccessWalletCreated>

    @POST("/wallet/charge/")
    fun chargeWallet(@Header("Authorization") token: String, @Body cardFields: CardFields):
            Deferred<SuccessWalletCharged>

    @POST("/vendors/registration/vendors_register")
    fun SignUpVendor(@Body sign:UserSignUp):Call<SignUpResponse>


    @GET("/vendors/transactions")
    fun getTransactions(@Header("Authorization") token:String ):
            /*  The Coroutine Call Adapter allows us to return a Deferred, a Job with a result*/
            Deferred<List<transactionsData>>



    @GET("/wallet/exists")
    fun getCheckWalletExistence(@Header("Authorization") token:String):
    Deferred<walletExistenceResponse>

    @GET("/accounts/nationalities")
    fun getNationalities(): Call<List<NationalitiesResponse>>


    @GET("/vendors/categories")
    fun getCategories(): Call<List<CategoriesResponse>>

    @POST("/rest_auth/logout/")
    fun LogOut(@Header("Authorization") token:String):
    Call<SignOutResponse>

   // @POST("/posts")
    @POST("/custom/login/")
    @FormUrlEncoded
    fun login(
       @Field("password") pass: String,
       @Field("username") userNAme: String
   ): Call<UserResponse>


}
interface ApiService2 {

    @GET("api/json/get/bUXEREUPqW?indent=2")
    fun getPayments():
            Deferred<List<PaymentsDummyData>>
}

object service {
    val retrofitService : ApiService by lazy { retrofit.create(ApiService::class.java) }
   // val retrofitService2 : ApiService2 by lazy { retrofit2.create(ApiService2::class.java) }

}