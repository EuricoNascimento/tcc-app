package com.github.edu.look.di

import android.content.Context
import com.github.edu.look.repository.CourseRepository
import com.github.edu.look.repository.HomeworkRepository
import com.github.edu.look.repository.remote.EduLookService
import com.github.edu.look.repository.remote.network.Urls
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
    fun provideMoshi(): Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(Urls.LOOK_API)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): EduLookService = retrofit.create(EduLookService::class.java)

    @Singleton
    @Provides
    fun providesCourseRepository(
        @ApplicationContext context: Context,
        eduLookService: EduLookService
    ) = CourseRepository(context, eduLookService)
}