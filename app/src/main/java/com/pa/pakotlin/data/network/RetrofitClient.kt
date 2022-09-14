package com.pa.pakotlin.data.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val CONNECTION_TIMEOUT = 10L
    private const val READ_TIMEOUT = 20L
    private const val WRITE_TIMEOUT = 20L
    fun api(baseURL: String): ApiService {
        val retrofit: Retrofit.Builder by lazy {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient.Builder()
            okHttpClient.addInterceptor(interceptor)
            okHttpClient.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            okHttpClient.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            okHttpClient.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            // Configuración necesaria para Retrofit
            // .baseURL
            // .client(OkHttpClient.Builder())
            Retrofit.Builder()
                .baseUrl(baseURL)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        }
        return retrofit.build().create(ApiService::class.java)
    }
}
