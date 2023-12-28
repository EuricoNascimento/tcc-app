package com.github.edu.look.di

import com.github.edu.look.services.HomeworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseDI {
    @Singleton
    @Provides
    fun provideHomeworkService() = HomeworkService()
}