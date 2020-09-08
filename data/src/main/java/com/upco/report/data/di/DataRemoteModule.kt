package com.upco.report.data.di

import com.google.gson.GsonBuilder
import com.upco.report.data.R
import com.upco.report.data.remote.api.ServerApi
import com.upco.report.data.remote.deserializer.DateDeserializer
import com.upco.report.data.remote.deserializer.LogChannelDeserializer
import com.upco.report.data.remote.deserializer.LogLevelDeserializer
import com.upco.report.data.remote.deserializer.UserRoleDeserializer
import com.upco.report.data.remote.source.RemoteDataSource
import com.upco.report.data.remote.source.RemoteDataSourceImpl
import com.upco.report.data.utils.TokenInterceptor
import com.upco.report.domain.enums.LogChannel
import com.upco.report.domain.enums.LogLevel
import com.upco.report.domain.enums.UserRole
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

val dataRemoteModule = module {
    factory {
        provideOkHttpClient(
            tokenInterceptor = get()
        )
    }
    single {
        createWebService<ServerApi>(
            okHttpClient = get(),
            url = androidContext().getString(R.string.base_url)
        )
    }
    factory<RemoteDataSource> {
        RemoteDataSourceImpl(serverApi = get())
    }
}

fun provideOkHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor().setLevel(Level.BASIC)
    return OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .addInterceptor(tokenInterceptor)
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, DateDeserializer())
        .registerTypeAdapter(LogLevel::class.java, LogLevelDeserializer())
        .registerTypeAdapter(LogChannel::class.java, LogChannelDeserializer())
        .registerTypeAdapter(UserRole::class.java, UserRoleDeserializer())
        .create()
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(url)
        .client(okHttpClient)
        .build()
        .create(T::class.java)
}