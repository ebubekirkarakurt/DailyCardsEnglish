package com.ekasoftware.english.view.vocablarylist.vocablarydb.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ekasoftware.english.view.vocablarylist.vocablarydb.repository.VocablaryRepository

class VocablaryViewModelProviderFactory(
    val app: Application,
    private val vocRepository: VocablaryRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VocablaryViewModel(app, vocRepository) as T
    }
}