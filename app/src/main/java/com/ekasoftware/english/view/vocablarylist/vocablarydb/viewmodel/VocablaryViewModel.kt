package com.ekasoftware.english.view.vocablarylist.vocablarydb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ekasoftware.english.view.vocablarylist.vocablarydb.data.Vocablary
import com.ekasoftware.english.view.vocablarylist.vocablarydb.repository.VocablaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VocablaryViewModel(app: Application,
                     private val vocRepository: VocablaryRepository
) :
    AndroidViewModel(app){


    fun getAllVocs() = vocRepository.getAllVocs()

    fun addVoc(vocablary: Vocablary) {
        viewModelScope.launch(Dispatchers.IO) {
            vocRepository.insertVoc(vocablary)
        }
    }

    fun deleteVoc(vocablary: Vocablary) = viewModelScope.launch {
        vocRepository.deleteVoc(vocablary)
    }
}