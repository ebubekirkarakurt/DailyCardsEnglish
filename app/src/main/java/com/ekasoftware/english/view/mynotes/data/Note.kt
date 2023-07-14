package com.ekasoftware.english.view.mynotes.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note")
class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title : String,
    var comment : String
)