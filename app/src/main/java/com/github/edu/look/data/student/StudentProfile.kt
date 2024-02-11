package com.github.edu.look.data.student

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
class StudentProfile(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "photo") var photo: String,
    @ColumnInfo(name = "authorization_bearer") var authorization: String?
)
