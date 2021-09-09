package com.hfad.android.storageapp.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.hfad.android.storageapp.model.Human
import java.util.*

@Dao
interface HumansDao{
    @Query("SELECT * FROM peoples")
    fun getAll(): LiveData<List<Human>>

    @Query("SELECT * FROM peoples WHERE id=(:id)")
    fun get(id:Int): LiveData<Human>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(human: Human)

    @Delete
    fun delete(human: Human)

    @Update
    fun update(human: Human)

    @Query("SELECT * FROM peoples ORDER BY name")
    fun getInAlphabetOrder():LiveData<List<Human>>
//    fun getInAlphabetOrder():List<Human>
}