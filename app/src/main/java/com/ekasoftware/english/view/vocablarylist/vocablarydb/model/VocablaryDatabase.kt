package com.ekasoftware.english.view.vocablarylist.vocablarydb.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ekasoftware.english.view.mynotes.model.NoteDao
import com.ekasoftware.english.view.vocablarylist.vocablarydb.data.Vocablary

@Database(entities = [Vocablary::class], version = 2)
abstract class VocablaryDatabase : RoomDatabase() {

    abstract fun getVocablaryFromDao(): VocablaryDao

    companion object {
        @Volatile
        private var instance: VocablaryDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, VocablaryDatabase::class.java,
                "note_database")
                .fallbackToDestructiveMigrationFrom(1)
                .build()
    }
}

