package com.hfad.android.storageapp.database

import android.content.Context
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.hfad.android.storageapp.cursor.HumanDatabaseCursor
import com.hfad.android.storageapp.model.Human
import java.util.*

class Repository(private val db:HumanDatabase, private val context: Context) {

    private val dao get() = db.humansDao
    private val daoSQLite = HumanDatabaseCursor(context)

//    private val dao:HumansDao
//        get() {
//            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//            return if (sharedPreferences.getBoolean("switchDatabaseRealization", true))
//                db.humansDao
//            else HumanDatabaseCursor(context)
//        }


//    fun getAll() = dao.getAll()
    fun getAll() = daoSQLite.getAll()

    fun getById(id:Int) = dao.get(id)

    fun add(human: Human) = dao.add(human)

    fun update(human: Human) = dao.update(human)

    fun delete(human: Human) = dao.delete(human)
//    fun delete(human: Human) = daoSQLite.delete(human)

    fun getInAlphabetOrder() = dao.getInAlphabetOrder()
//    fun getInAlphabetOrder() = daoSQLite.getInAlphabetOrder()



    companion object{
        private var INSTANCE: Repository? = null

        private fun createDb(context: Context):HumanDatabase{
            return Room.databaseBuilder(
                context,
                HumanDatabase::class.java,
                DATABASE_NAME
            ).build()
        }

        fun initialize(context: Context){
            if(INSTANCE == null){
                INSTANCE = Repository(createDb(context), context)
            }
        }

        fun get():Repository{
            return INSTANCE?:
            throw IllegalAccessException()
        }

        const val DATABASE_NAME = "peoples-database"
    }

}