package com.github.edu.look.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.edu.look.data.student.StudentProfile

@Database(entities = [StudentProfile::class], version = 1)
abstract class LookDataBase: RoomDatabase() {
    abstract fun studentProfileDao(): StudentsProfileDao
}