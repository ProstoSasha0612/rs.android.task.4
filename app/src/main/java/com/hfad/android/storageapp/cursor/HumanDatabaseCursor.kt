package com.hfad.android.storageapp.cursor

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.hfad.android.storageapp.database.HumansDao
import com.hfad.android.storageapp.database.Repository
import com.hfad.android.storageapp.model.Human
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HumanDatabaseCursor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION), HumansDao {


    private val selectQuery = "SELECT * FROM ${HumanDbNameClass.TABLE_NAME}"
    private val selectQueryInAlphabetOrder =
        "SELECT * FROM ${HumanDbNameClass.TABLE_NAME} ORDER BY name"

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            db?.execSQL(HumanDbNameClass.SQL_CREATE_TABLE)
        } catch (exception: SQLException) {
            print(exception)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}


    private suspend fun getHumanList(selectQuery: String): List<Human> {
        return withContext(Dispatchers.IO) {
            val db = readableDatabase
            val listOfHumans = mutableListOf<Human>()
            val cursor = db.rawQuery(HumanDbNameClass.SQL_CREATE_TABLE, null)
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndex(HumanDbNameClass.COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndex(HumanDbNameClass.COLUMN_NAME))
                val surname =
                    cursor.getString(cursor.getColumnIndex(HumanDbNameClass.COLUMN_SURNAME))
                val age = cursor.getInt(cursor.getColumnIndex(HumanDbNameClass.COLUMN_AGE))
                val gender = cursor.getString(cursor.getColumnIndex(HumanDbNameClass.COLUMN_GENDER))
                listOfHumans.add(Human(id, name, surname, age, gender))
            }
            cursor.close()
            db.close()
            listOfHumans
        }
    }

    private suspend fun getById(id: Int): Human? {
        return withContext(Dispatchers.IO) {
            var human: Human? = null
            val db = readableDatabase
            val getByIdQuery = "SELECT * FROM peoples WHERE id = $id"
            val cursor = db.rawQuery(getByIdQuery, null)
            while (cursor.moveToNext()) {
                if (cursor.getInt(cursor.getColumnIndex(HumanDbNameClass.COLUMN_ID)) == id) {
                    val name = cursor.getString(cursor.getColumnIndex(HumanDbNameClass.COLUMN_NAME))
                    val surname =
                        cursor.getString(cursor.getColumnIndex(HumanDbNameClass.COLUMN_SURNAME))
                    val age = cursor.getInt(cursor.getColumnIndex(HumanDbNameClass.COLUMN_AGE))
                    val gender =
                        cursor.getString(cursor.getColumnIndex(HumanDbNameClass.COLUMN_GENDER))
                    human = Human(id, name, surname, age, gender)
                    break
                }
            }
            cursor.close()
            db.close()
            human
        }
    }

    private fun addHuman(human: Human) {
        val db = writableDatabase
        val valueToAdd = ContentValues().apply {
            with(human) {
                put(HumanDbNameClass.COLUMN_NAME, name)
                put(HumanDbNameClass.COLUMN_SURNAME, secondName)
                put(HumanDbNameClass.COLUMN_AGE, age)
                put(HumanDbNameClass.COLUMN_GENDER, gender)
            }
        }
        db.insert(HumanDbNameClass.TABLE_NAME, null, valueToAdd)
        db.close()
    }

    override fun getAll(): LiveData<List<Human>> {
        return liveData { emit(getHumanList(selectQuery)) }
    }

    override fun get(id: Int): LiveData<Human?> {
        return liveData { emit(getById(id)) }
    }

    override fun add(human: Human) {
        addHuman(human)
    }

    override fun delete(human: Human) {
        val db = writableDatabase
        db.delete(
            HumanDbNameClass.TABLE_NAME,
            HumanDbNameClass.COLUMN_ID + "=?",
            arrayOf(human.id.toString())
        )
        db.close()
    }

    override fun update(human: Human) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(HumanDbNameClass.COLUMN_ID, human.id)
            put(HumanDbNameClass.COLUMN_NAME, human.name)
            put(HumanDbNameClass.COLUMN_SURNAME, human.secondName)
            put(HumanDbNameClass.COLUMN_AGE, human.age)
            put(HumanDbNameClass.COLUMN_GENDER, human.gender)
        }
        db.update(
            HumanDbNameClass.TABLE_NAME,
            values,
            HumanDbNameClass.COLUMN_ID + "=?",
            arrayOf(human.id.toString())
        )
        db.close()
    }

    override fun getInAlphabetOrder(): LiveData<List<Human>> {
        return liveData { emit(getHumanList(selectQueryInAlphabetOrder)) }
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = Repository.DATABASE_NAME
    }
}