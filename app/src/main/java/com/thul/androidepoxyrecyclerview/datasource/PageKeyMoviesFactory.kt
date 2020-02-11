package com.example.moviz.datasource

import androidx.lifecycle.MutableLiveData
import java.util.concurrent.Executor
import androidx.paging.DataSource
import com.thul.androidepoxyrecyclerview.response.Movie


class PageKeyMoviesFactory(val type:String, val retryExecutor:Executor):DataSource.Factory<Int, Movie>() {

    val popularMoviesMutableData = MutableLiveData<PageKeyedMoviesDataSource>()
    override fun create(): DataSource<Int, Movie> {
        val moviesDataSource = PageKeyedMoviesDataSource(type,retryExecutor)
        popularMoviesMutableData.postValue(moviesDataSource)
        return moviesDataSource
    }

}
