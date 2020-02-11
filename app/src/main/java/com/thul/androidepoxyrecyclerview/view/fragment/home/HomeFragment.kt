package com.thul.androidepoxyrecyclerview.view.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.thul.androidepoxyrecyclerview.R
import com.thul.androidepoxyrecyclerview.response.Movie
import com.thul.androidepoxyrecyclerview.utils.setupGridManager
import com.thul.androidepoxyrecyclerview.view.adapter.HomeController
import com.thul.androidepoxyrecyclerview.view.adapter.MovieClickListener
import com.thul.androidepoxyrecyclerview.viewmodel.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_popular.view.*

class HomeFragment : Fragment(){

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_popular, container, false)
        val moviesListController = HomeController(object : MovieClickListener {
            override fun onMovieItemClicked(movie: Movie) {
//                val action =  HomeFragmentDirections.popularToDetails(movie)
//                root.findNavController().navigate(action)
            }

        })

        root.popularRecyclerview.setupGridManager(moviesListController)


        homeViewModel.popularMoviesLiveData.observe(this, Observer {
            moviesListController.submitList(it)
        })

        return root
    }

}