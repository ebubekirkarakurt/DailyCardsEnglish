package com.ekasoftware.english.view.mynotes.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import androidx.room.Update
import com.ekasoftware.english.view.mynotes.data.Note


@Dao
interface NoteDao {

    @Query("SELECT * FROM note ORDER BY id ASC")
    fun getAllNotes() : LiveData<List<Note>>

    @Insert(onConflict = IGNORE)
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}