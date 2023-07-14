package com.ekasoftware.english.view.stories

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class StoryListViewModel : ViewModel() {

    val storyList = mutableStateListOf<Story>()

    init {

        StoryList.storylist.forEach { story ->
            storyList.add(story)
        }
    }
}