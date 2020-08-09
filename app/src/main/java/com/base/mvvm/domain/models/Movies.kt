package com.base.mvvm.domain.models

data class Movies(
        var title: String? = null,
        var backdropPath: String? = null,
        var releaseDate: String? = null,
        var voteAverage: Double? = null,
        var voteCount: Int? = null) : BaseObject()