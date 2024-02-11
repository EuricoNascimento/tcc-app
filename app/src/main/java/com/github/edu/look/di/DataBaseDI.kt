package com.github.edu.look.di

import android.content.Context
import androidx.room.Room
import com.github.edu.look.repository.HomeworkRepository
import com.github.edu.look.repository.LoginRepository
import com.github.edu.look.repository.local.LookDataBase
import com.github.edu.look.repository.local.StudentsProfileDao
import com.github.edu.look.repository.remote.EduLookService
import com.github.edu.look.repository.remote.network.AuthInterceptor
import com.github.edu.look.repository.remote.network.Urls
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseDI {
    val DATABASE_NAME = "look"

    @Singleton
    @Provides
    fun provideHomeworkService() = HomeworkRepository()

    @Singleton
    @Provides
    fun providesOkHttpClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(Urls.LOOK_API)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): EduLookService = retrofit.create(EduLookService::class.java)

    @Singleton
    @Provides
    fun providesRepository(eduLookService: EduLookService) = LoginRepository(eduLookService)
}