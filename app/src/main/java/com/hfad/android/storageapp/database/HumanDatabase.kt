package com.hfad.android.storageapp.database

import android.content.Context
import androidx.room.*
import com.hfad.android.storageapp.model.Human

@Database(entities = [Human::class],version = 1)
abstract class HumanDatabase: RoomDatabase(){
    abstract val humansDao: HumansDao

}