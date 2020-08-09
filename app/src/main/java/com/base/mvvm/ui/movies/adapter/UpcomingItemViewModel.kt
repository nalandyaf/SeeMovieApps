package com.base.mvvm.ui.movies.adapter

import androidx.databinding.ObservableField
import com.base.mvvm.databinding.AdapterMovieUpcomingBinding
import com.base.mvvm.domain.models.Movies
import com.squareup.picasso.Picasso
import java.util.*

class UpcomingItemViewModel(itemData: Movies?, var actionDetail: (Movies) -> Unit, var binding: AdapterMovieUpcomingBinding) : Observable() {
    var releaseDate = ObservableField<String>()

    var data: Movies? = itemData

    init {
        releaseDate.set(data?.releaseDate)
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + data?.posterPath).into(binding.image)
    }

    fun toDetail() {
        with(binding) {
            executePendingBindings()
        }
        actionDetail(data!!)
    }
}