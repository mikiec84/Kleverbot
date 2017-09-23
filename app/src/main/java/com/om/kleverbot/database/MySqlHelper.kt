package com.om.kleverbot.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MySqlHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "kleverbot_db") {

  companion object {
    private var instance: MySqlHelper? = null

    @Synchronized
    fun getInstance(ctx: Context): MySqlHelper {
      if (instance == null) {
        instance = MySqlHelper(ctx.applicationContext)
      }
      return instance!!
    }

    val tableName: String = "Message"
  }

  override fun onCreate(db: SQLiteDatabase) {
    db.createTable(tableName, true,
        "_id" to INTEGER + PRIMARY_KEY + UNIQUE,
        "body" to TEXT,
        "sender" to TEXT,
        "recipient" to TEXT,
        "timestamp" to TEXT
    )
  }

  override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
  }

}

// Access property for Context
val Context.database: MySqlHelper
  get() = MySqlHelper.getInstance(applicationContext)
