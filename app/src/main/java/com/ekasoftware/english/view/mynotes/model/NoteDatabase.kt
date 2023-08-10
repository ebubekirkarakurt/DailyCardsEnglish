package com.ekasoftware.english.view.mynotes.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ekasoftware.english.view.mynotes.data.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase(){

    abstract fun getNotesFromDao() : NoteDao

    companion object{

        @Volatile
        private var instance : NoteDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?:
            createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "note_db"
        ).build()


    }

}
