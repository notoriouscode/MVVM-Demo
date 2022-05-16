package com.example.mvvm_architecture.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mvvm_architecture.models.Result
import com.example.mvvm_architecture.utils.DbConverter
import java.util.*

@Database(entities = [Result::class], version = 1)
/*for date inputs
@TypeConverters(DbConverter::class)*/
abstract class QuoteDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao

    companion object {

        // for database migration
        /* val migration_1_2 = object : Migration(1, 2) {
             override fun migrate(database: SupportSQLiteDatabase) {
                  database.execSQL("ALTER TABLE quote ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1)")
             }
         }*/

        @Volatile
        private var INSTANCE: QuoteDatabase? = null
        fun getDatabase(context: Context): QuoteDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context, QuoteDatabase::class.java, "quoteDb")
/*
                            .addMigrations(migration_2_3)
*/
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}