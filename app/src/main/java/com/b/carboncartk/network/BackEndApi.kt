package com.b.carboncartk.network

import com.b.carboncartk.model.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface BackEndApi {
    @FormUrlEncoded
    @POST("loginseeker")
    fun LOGIN(@Field("email") email: String, @Field("password") password: String): Call<User>


}