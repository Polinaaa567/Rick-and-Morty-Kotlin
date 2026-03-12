package com.example.ramk.core.remote.network

import okhttp3.Interceptor
import okhttp3.Response


class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(name = "Accept", value = "application/json")
            .addHeader(name = "Content-Type", value = "application/json")

        return chain.proceed(request = request.build())
    }
}