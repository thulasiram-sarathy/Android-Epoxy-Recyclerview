package com.thul.androidepoxyrecyclerview.datasource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thul.androidepoxyrecyclerview.viewmodel.review.ReviewViewModel
import com.thul.androidepoxyrecyclerview.viewmodel.video.VideoViewModel

class VideoModelFactory(private val id:Int):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VideoViewModel(id) as T
    }
}