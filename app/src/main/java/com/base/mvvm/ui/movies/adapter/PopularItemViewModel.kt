package com.base.mvvm.ui.movies.adapter

import androidx.databinding.ObservableField
import com.base.mvvm.databinding.AdapterMoviePopularBinding
import com.base.mvvm.domain.models.Movies
import com.squareup.picasso.Picasso
import java.util.*

class PopularItemViewModel(itemData: Movies?, var binding: AdapterMoviePopularBinding) : Observable() {
    var imageUrl = ObservableField<String>()

    var data: Movies? = itemData

    init {
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + data!!.posterPath).into(binding!!.image)
    }
}