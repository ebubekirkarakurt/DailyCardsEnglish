package com.ekasoftware.english.view.vocablarylist.vocablarydb.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vocablary")
class Vocablary(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var ENG : String,
    var TR : String
)