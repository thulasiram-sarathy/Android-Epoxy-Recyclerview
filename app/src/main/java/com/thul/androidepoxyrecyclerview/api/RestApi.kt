package com.an.github.rest


import com.thul.androidepoxyrecyclerview.AppConstants.Companion.BASE_URL
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Single
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path


interface RestApi {

    @GET("movie/{type}")
    fun getMovies(@Path("type") type:String, @Query("api_key") api_key:String, @Query("page") page:Int): Call<ResponseBody>

    @GET("movie/{movie_id}/reviews")
    fun getReviews(@Path("movie_id") movieId:Int, @Query("api_key") api_key:String, @Query("page") page:Int): Call<ResponseBody>

    @GET("movie/{movie_id}/videos")
    fun getVideos(@Path("movie_id") movieId:Int, @Query("api_key") api_key:String): Call<ResponseBody>


    companion object {

        fun create(httpUrl: HttpUrl): RestApi {
            val okHttpClient = OkHttpClient.Builder()
                .build()
            return Retrofit.Builder()
                .baseUrl(httpUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(RestApi::class.java)
        }


        fun create(): RestApi = create(HttpUrl.parse(BASE_URL)!!)
    }

}