package com.hfad.android.storageapp.database

import android.content.Context
import androidx.room.Room
import com.hfad.android.storageapp.model.Human
import java.util.*

class Repository(private val db:HumanDatabase) {

    private val dao get() = db.humansDao


    fun getAll() = dao.getAll()

    fun getById(id:Int) = dao.get(id)

    fun add(human: Human) = dao.add(human)

    fun update(human: Human) = dao.update(human)

    fun delete(human: Human) = dao.delete(human)

    fun getInAlphabetOrder() = dao.getInAlphabetOrder()




    companion object{
        private var INSTANCE: Repository? = null

        private fun createDb(context: Context):HumanDatabase{
            return Room.databaseBuilder(
                context,
                HumanDatabase::class.java,
                "peoples-database"
            ).build()
        }

        fun initialize(context: Context){
            if(INSTANCE == null){
                INSTANCE = Repository(createDb(context))
            }
        }

        fun get():Repository{
            return INSTANCE?:
            throw IllegalAccessException()
        }
    }
}