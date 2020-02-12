package com.thul.androidepoxyrecyclerview.view.fragment.video

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.thul.androidepoxyrecyclerview.AppConstants
import com.thul.androidepoxyrecyclerview.R
import com.thul.androidepoxyrecyclerview.datasource.VideoModelFactory
import com.thul.androidepoxyrecyclerview.response.MovieApiResponse
import com.thul.androidepoxyrecyclerview.response.VideoResponse
import com.thul.androidepoxyrecyclerview.utils.setupGridManager
import com.thul.androidepoxyrecyclerview.view.activity.video.VideoActivity
import com.thul.androidepoxyrecyclerview.view.adapter.HomeController
import com.thul.androidepoxyrecyclerview.view.adapter.MovieClickListener
import com.thul.androidepoxyrecyclerview.view.adapter.VideoClickListener
import com.thul.androidepoxyrecyclerview.view.adapter.VideoController
import com.thul.androidepoxyrecyclerview.viewmodel.home.HomeViewModel
import com.thul.androidepoxyrecyclerview.viewmodel.video.VideoViewModel
import kotlinx.android.synthetic.main.fragment_popular.view.*
import kotlinx.android.synthetic.main.fragment_video.*

class VideoFragment : Fragment(){

    private lateinit var videoViewModel: VideoViewModel
    var i = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        videoViewModel = ViewModelProviders.of(this).get(VideoViewModel::class.java)


        val root = inflater.inflate(R.layout.fragment_video, container, false)
        i = arguments?.getInt("movieId")!!
        val moviesListController = VideoController(object : VideoClickListener {


            override fun onVideoItemClicked(movie: VideoResponse.Video) {

            }

        })

//        root.popularRecyclerview.setupGridManager(moviesListController)


        /*videoViewModel.videoLiveData.observe(this, Observer {
            moviesListController.submitList(it)
        })*/

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        i = arguments?.getInt("movieId")!!

        videoViewModel = ViewModelProviders.of(this, VideoModelFactory(i))
            .get(VideoViewModel::class.java)
        val reviewsController = VideoController(object : VideoClickListener {


            override fun onVideoItemClicked(video: VideoResponse.Video) {
                val intent = Intent (context, VideoActivity::class.java)
                intent.putExtra(AppConstants.INTENT_VIDEO_KEY,video.key)
                startActivity(intent)
            }

        })


        videoRecyclerview.adapter =reviewsController.adapter
        videoRecyclerview.setItemSpacingDp(4)


        val linearLayoutManager = LinearLayoutManager(context)
        videoRecyclerview.apply {
            layoutManager = linearLayoutManager
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)
            setHasFixedSize(true)
            adapter = reviewsController.adapter
//            addItemDecoration(DividerItemDecoration(this@MainActivity, linearLayoutManager.orientation ))
        }
        videoViewModel.videoLiveData.observe(this, Observer {
            reviewsController.submitList(it)
        })





    }

}