package com.thul.androidepoxyrecyclerview.view.epoxyui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.ms.square.android.expandabletextview.ExpandableTextView
import com.squareup.picasso.Picasso
import com.thul.androidepoxyrecyclerview.R
import com.thul.androidepoxyrecyclerview.utils.loadTmdbImage

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT,fullSpan = false)
class HomeView @JvmOverloads constructor(context: Context,
                                          attributes: AttributeSet?=null,
                                          defStyle:Int = 0)
    :CardView(context,attributes,defStyle)
{


    private val posterImage:ImageView
    private val movieName:TextView
    private val movieRating:RatingBar
    private val movieCardView:CardView

    init {
        View.inflate(context, R.layout.movie_item,this)
        posterImage = findViewById(R.id.posterImageView)
        movieName = findViewById(R.id.movieName)
        movieRating = findViewById(R.id.movieRating)
        movieCardView = findViewById(R.id.movieCardLayout)
    }

    @ModelProp
    fun setImage(url:String?)
    {
        posterImage.loadTmdbImage(url)

    }

    @ModelProp
    fun setMoviename(movie:String?)
    {
        movieName.text = movie

    }
    @ModelProp
    fun setMovieRating(rating:Float)
    {
        movieRating.rating = rating

    }

    @CallbackProp
    fun setMovieItemClickListener(listener: OnClickListener?) {
        movieCardView.setOnClickListener(listener)
    }


}




@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT,fullSpan = false)
class LoadingView @JvmOverloads constructor(context: Context,
                                            attributes: AttributeSet?=null,
                                            defStyle:Int = 0)
    :LinearLayout(context,attributes,defStyle)
{
    init {
        View.inflate(context,R.layout.loading_item,this)
    }
}


@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT,fullSpan = false)
class ReviewView @JvmOverloads constructor(context: Context,
                                           attributes: AttributeSet?=null,
                                           defStyle:Int = 0):CardView(context,attributes,defStyle) {
    private val authorTextView: TextView
    private val contentTextView: ExpandableTextView

    init {
        View.inflate(context, R.layout.review_layout, this)
        authorTextView = findViewById(R.id.authorTextView)
        contentTextView = findViewById(R.id.contentTextView)
    }


    @TextProp
    fun setAuthor(author: CharSequence){
        authorTextView.text = author
    }

    @TextProp
    fun setContent(content:CharSequence) {
        contentTextView.text = content
    }


}
