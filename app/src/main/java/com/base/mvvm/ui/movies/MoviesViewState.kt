package com.base.mvvm.ui.movies

import com.base.mvvm.domain.models.Movies

data class MoviesViewState(
        val loading: Boolean = false,
        val error: Exception? = null,
        val data: List<Movies>? = null
)

