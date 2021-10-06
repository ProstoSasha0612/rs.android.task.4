package com.hfad.android.storageapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hfad.android.storageapp.model.Human

@Database(entities = [Human::class],version = 1)
abstract class HumanDatabase: RoomDatabase(){
    abstract val humansDao: HumansDao
}