package com.chullian.template.network.utils

import com.chullian.template.network.clients.AuthClient
import com.chullian.template.network.services.ApiServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitClient @Inject constructor(
    private val requestInterceptor: RequestInterceptor
) {
    private var service: ApiServices

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val interceptor =
            OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(logging)
                .build()
        var build = Retrofit.Builder()
            .client(interceptor)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("BASE_URL")
            .build()
        service = build.create(ApiServices::class.java)
    }

    fun provideAuthClient() = AuthClient(this.service)
    fun provideDefaultClient() = AuthClient(this.service)

}