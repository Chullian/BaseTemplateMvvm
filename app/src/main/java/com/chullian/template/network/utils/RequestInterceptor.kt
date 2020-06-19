package com.chullian.template.network.utils

import com.chullian.template.persistance.session.DefaultPreference
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RequestInterceptor @Inject constructor(
    private val defaultPreference: DefaultPreference
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()


        val request = originalRequest.newBuilder()
            .addHeader("Authorization", defaultPreference.token)
            .addHeader("Accept-Encoding", "gzip")
            .addHeader("Accept", "application/json")
            .url(originalRequest.url).build()
        return chain.proceed(request)
    }
}