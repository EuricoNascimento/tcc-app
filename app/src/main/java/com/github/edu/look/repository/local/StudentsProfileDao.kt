package com.github.edu.look.repository.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.github.edu.look.data.student.StudentProfile

@Dao
interface StudentsProfileDao {
    @Query("SELECT authorization_bearer FROM student")
    fun getAuthorizationInRoom() : List<String>

    @Insert
    fun insertStudent(studentProfile: StudentProfile)

    @Delete
    fun deleteStudent(studentProfile: StudentProfile)

    @Query("SELECT * FROM student LIMIT 1")
    fun getCurrentStudent(): StudentProfile
}