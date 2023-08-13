package com.ekasoftware.english.view.vocablarylist.vocablarydb.repository

import com.ekasoftware.english.view.vocablarylist.vocablarydb.data.Vocablary
import com.ekasoftware.english.view.vocablarylist.vocablarydb.model.VocablaryDatabase

class VocablaryRepository(private val db : VocablaryDatabase) {

    suspend fun insertVoc(vocablary: Vocablary) = db.getVocablaryFromDao().addVoc(vocablary)
    suspend fun deleteVoc(vocablary: Vocablary) = db.getVocablaryFromDao().deleteVoc(vocablary)
    fun getAllVocs() = db.getVocablaryFromDao().getAllVocs()

    fun getRandomVocs(): List<Vocablary> = db.getVocablaryFromDao().getRandomVocs()

}