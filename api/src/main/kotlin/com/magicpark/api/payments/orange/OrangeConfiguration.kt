package com.magicpark.api.payments.orange

import com.google.gson.GsonBuilder
import com.magicpark.api.OrangeApi
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@SpringBootApplication
class RetrofitConfiguration {

    @Bean
    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor {
            val request = it.request()
                .newBuilder()
                .build()
            it.proceed(request)
        }.callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Bean
    fun loginApiCall(@Autowired okHttpClient: OkHttpClient): OrangeApi {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl("https://api.orange.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(OrangeApi::class.java)
    }

}