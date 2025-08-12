package com.arielgos.mpos.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.arielgos.mpos.DATABASE_NAME
import com.arielgos.mpos.DATABASE_VERSION
import com.arielgos.mpos.TAG
import com.arielgos.mpos.entities.Customer
import com.arielgos.mpos.entities.Order
import com.arielgos.mpos.entities.Product
import com.arielgos.mpos.entities.User

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    val entities = listOf(Order::class.java, Customer::class.java, Product::class.java, User::class.java)
    override fun onCreate(db: SQLiteDatabase?) {
        if (db == null) {
            throw Error("Database is null")
        }

        entities.forEach { entity ->
            db.execSQL(dropTableQuery(entity))
            db.execSQL(createTableQuery(entity))
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db == null) {
            throw Error("Database is null")
        }
        onCreate(db)
    }
}