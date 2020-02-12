package com.thul.androidepoxyrecyclerview.view.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.navArgs
import com.thul.androidepoxyrecyclerview.R
import com.thul.androidepoxyrecyclerview.utils.loadTmdbImage
import com.thul.androidepoxyrecyclerview.view.fragment.review.ReviewFragment
import com.thul.androidepoxyrecyclerview.view.fragment.video.VideoFragment
import kotlinx.android.synthetic.main.fragment_movie_details.*


class DetailFragment :Fragment(){

    val args:DetailFragmentArgs by navArgs()
    private var movieId:Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = args.movieArg


        movie?.let {
            movieId = it.id.toInt()
            movieImageView.loadTmdbImage(it.backdropPath)
            textViewTitle.text = it.title
            textViewOverview.text = it.overview
            releaseDateView.text = it.releaseDate!!.split("-")[0]
            ratingBar.rating = it.voteAverage.div(2).toFloat()
//            Log.d("TAG","${movie.genres}")
            //genreTextView.text = getAppendedString(it.genres)
            val args = Bundle()
            args.putInt("movieId", movieId)

            val childFragment: Fragment = ReviewFragment()
            childFragment.setArguments(args)
            val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.child_fragment_container, childFragment).commit()

        }


        buttonReviews.setOnClickListener {
            val args = Bundle()
            args.putInt("movieId", movieId)

            val childFragment: Fragment = ReviewFragment()
            childFragment.setArguments(args)
            val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.child_fragment_container, childFragment).commit()
        }
        buttonTrailer.setOnClickListener {
            val args = Bundle()
            args.putInt("movieId", movieId)

            val childFragment: Fragment = VideoFragment()
            childFragment.setArguments(args)
            val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.child_fragment_container, childFragment).commit()
        }
    }

}