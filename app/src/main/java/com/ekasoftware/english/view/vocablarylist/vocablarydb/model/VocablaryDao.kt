package com.ekasoftware.english.view.vocablarylist.vocablarydb.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import com.ekasoftware.english.view.vocablarylist.vocablarydb.data.Vocablary

@Dao
interface VocablaryDao {

    @Query("SELECT * FROM vocablary ORDER BY id ASC")
    fun getAllVocs() : LiveData<List<Vocablary>>

    @Insert(onConflict = IGNORE)
    suspend fun addVoc (vocablary : Vocablary)

    @Delete
    suspend fun deleteVoc(vocablary : Vocablary)

}