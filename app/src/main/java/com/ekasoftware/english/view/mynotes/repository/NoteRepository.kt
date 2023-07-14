package com.ekasoftware.english.view.mynotes.repository

import com.ekasoftware.english.view.mynotes.data.Note
import com.ekasoftware.english.view.mynotes.model.NoteDatabase

class NoteRepository(private val db : NoteDatabase) {

    suspend fun insertNote(note: Note) = db.getNotesFromDao().addNote(note)
    suspend fun deleteNote(note: Note) = db.getNotesFromDao().deleteNote(note)
    suspend fun updateNote(note: Note) = db.getNotesFromDao().updateNote(note)
    fun getAllNotes() = db.getNotesFromDao().getAllNotes()

}