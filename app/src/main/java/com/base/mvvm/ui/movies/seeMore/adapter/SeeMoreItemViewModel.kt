package com.base.mvvm.ui.movies.seeMore.adapter

import com.base.mvvm.databinding.AdapterSeeMoreBinding
import com.base.mvvm.domain.models.Movies
import com.squareup.picasso.Picasso
import java.util.*

class SeeMoreItemViewModel(itemData: Movies?, var actionDetail: (Movies) -> Unit, var binding: AdapterSeeMoreBinding) : Observable() {

    var data: Movies? = itemData

    init{
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + data!!.posterPath).into(binding.image)
    }

    fun toDetail(){
        actionDetail(data!!)
    }
}