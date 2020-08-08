package com.base.mvvm.data.remote

import com.base.mvvm.domain.entities.requests.LoginRequest
import com.base.mvvm.domain.entities.requests.RegistrationRequest
import com.base.mvvm.domain.entities.response.LoginResponse
import com.base.mvvm.domain.entities.response.RegistrationResponse
import io.reactivex.Single
import retrofit2.http.*

/**
 * Retrofit API
 */
interface RemoteAPI {

    @POST("/api/login")
    fun login(@Body loginRequest: LoginRequest?): Single<LoginResponse?>?

    @POST("/auth/local/register")
    fun registration(@Body registrationRequest: RegistrationRequest?): Single<RegistrationResponse?>?

    @GET("/movie/top_rated")
    fun getTopRated(@Query("api_key") apiKey: String,
                    @Query("page") page: Int,
                    @Query("language") language: String)

    @GET("/movie")
    fun getDetailMovie(@Path("id") movieId: Int,
                       @Query("api_key") apiKey: String,
                       @Query("language") language: String)

    

    @GET("/genre/movie/list")
    fun getGenre(@Query("api_key") apiKey: String,
                 @Query("language") language: String)

}