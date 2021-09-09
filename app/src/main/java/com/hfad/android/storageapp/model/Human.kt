package com.hfad.android.storageapp.model

import androidx.room.*
import java.util.*

@Entity(tableName = "peoples")
data class Human(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var name: String,
    var secondName: String,
    var age: Int,
    val gender: String
)

