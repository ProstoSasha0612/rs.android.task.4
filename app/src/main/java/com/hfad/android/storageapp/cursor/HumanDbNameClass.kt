package com.hfad.android.storageapp.cursor

import android.provider.BaseColumns

object HumanDbNameClass {
    const val TABLE_NAME = "peoples-database"
    const val COLUMN_ID = "id"
    const val COLUMN_NAME = "name"
    const val COLUMN_SURNAME = "surname"
    const val COLUMN_AGE = "age"
    const val COLUMN_GENDER = "gender"
    const val SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "$COLUMN_ID INTEGER NOT NULL," +
            "$COLUMN_NAME TEXT NOT NULL," +
            "$COLUMN_SURNAME TEXT NOT NULL," +
            "$COLUMN_AGE INTEGER NOT NULL," +
            "$COLUMN_GENDER TEXT NOT NULL," +
            "PRIMARY KEY($COLUMN_ID AUTOINCREMENT)" +
            ");"

}