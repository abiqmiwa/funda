package com.dicoding.picodiploma.mysubs2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.dicoding.picodiploma.mysubs2.DbContract.FavoriteColumns.Companion.TABLE_NAME
import com.dicoding.picodiploma.mysubs2.DbContract.FavoriteColumns.Companion.USERNAME
import com.dicoding.picodiploma.mysubs2.DbContract.FavoriteColumns.Companion._ID
import java.sql.SQLException
import kotlin.jvm.Throws

class HelperFav(context: Context) {

    companion object{
        private const val DATABASE_TABLE = TABLE_NAME
        private lateinit var helperDb: HelperDb
        private var INSTANCE: HelperFav? = null
        fun getInstance(context: Context): HelperFav =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: HelperFav(context)
            }
        private lateinit var database: SQLiteDatabase
    }

    init {
        helperDb = HelperDb(context)
    }

    @Throws(SQLException::class)
    fun open(){
        database = helperDb.writableDatabase
    }

    fun close(){
        helperDb.close()

        if (database.isOpen)
            database.close()
    }

    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC")
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun deleteById(id: String): Int {
        return database.delete(DATABASE_TABLE, "$_ID = '$id'", null)
    }

    fun queryById(id: String): Cursor {
        return database.query(DATABASE_TABLE, null, "$_ID = ?", arrayOf(id), null, null, null, null)
    }

    fun queryByUsername(username: String): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            "$USERNAME = ?",
            arrayOf(username),
            null,
            null,
            null,
            null
        )
    }

    fun deleteByUsername(username: String): Int {
        return database.delete(DATABASE_TABLE, "$USERNAME = '$username'", null)
    }

    fun update(id: String, values: ContentValues?): Int {
        return database.update(DATABASE_TABLE, values, "$_ID = ?", arrayOf(id))
    }
}