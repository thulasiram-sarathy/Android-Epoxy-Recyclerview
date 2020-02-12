package com.thul.androidepoxyrecyclerview.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.thul.androidepoxyrecyclerview.response.ReviewsResponse

import java.util.concurrent.Executor

class PageKeyReviewsFactory(val id:Int, val retryExecutor: Executor):
    DataSource.Factory<Int, ReviewsResponse.Review>() {

    val reviewsMutableLiveData = MutableLiveData<PageKeyedReviewDataSource>()
    override fun create(): DataSource<Int, ReviewsResponse.Review> {
        val reviewsDataSource = PageKeyedReviewDataSource(id,retryExecutor)
        reviewsMutableLiveData.postValue(reviewsDataSource)
        return reviewsDataSource
    }

}