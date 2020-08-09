package com.base.mvvm.ui.movies.adapter

import androidx.databinding.ObservableField
import com.base.mvvm.databinding.AdapterMovieTopRatedBinding
import com.base.mvvm.domain.models.Movies
import java.util.*

class TopRatedItemViewModel(itemData:Movies?,var binding:AdapterMovieTopRatedBinding) : Observable() {

    var truckName = ObservableField<String>()
}