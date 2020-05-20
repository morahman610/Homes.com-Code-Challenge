package com.example.homescom_code_challenge.Repository

import com.example.homescom_code_challenge.BuildConfig
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIServiceProvider {

    val baseURL : String = "https://api.themoviedb.org/3/"

    val tmdbService : TMDBService
    
    init {
        val client = createOkHttpClient()
        tmdbService = provideTMDBRetrofit(client)

    }

    private fun createOkHttpClient(): OkHttpClient {
        val requestInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .build()
            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .build()
    }


    private fun provideTMDBRetrofit(client: OkHttpClient) = Retrofit.Builder()
        .client(client)
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TMDBService::class.java)

}