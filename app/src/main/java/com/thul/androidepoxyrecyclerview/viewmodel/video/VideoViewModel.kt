package com.thul.androidepoxyrecyclerview.viewmodel.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviz.datasource.PageKeyVideoFactory
import com.thul.androidepoxyrecyclerview.datasource.PageKeyReviewsFactory
import com.thul.androidepoxyrecyclerview.response.ReviewsResponse
import com.thul.androidepoxyrecyclerview.response.VideoResponse

import java.util.concurrent.Executor

class VideoViewModel(val id:Int): ViewModel()
{

    var videoLiveData: LiveData<PagedList<VideoResponse.Video>>
    private val pageSize = 25
    private val reviewsFactory: PageKeyVideoFactory = PageKeyVideoFactory( id, Executor {

    })

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(5)
            .setEnablePlaceholders(false)
            .build()

        videoLiveData = LivePagedListBuilder(reviewsFactory,config).build()

    }







}