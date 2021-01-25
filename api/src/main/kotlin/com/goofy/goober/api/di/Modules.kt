package com.goofy.goober.api.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.goofy.goober.api.client.ApiClient
import com.goofy.goober.api.service.Api
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://www.vyasaonline.com/wp-json/wp/v2/"

val networkModule = module {
    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    factory { provideApi(get()) }
    factory { ApiClient(get()) }
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder()
        .addNetworkInterceptor(StethoInterceptor())
        .build()
}

private fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
