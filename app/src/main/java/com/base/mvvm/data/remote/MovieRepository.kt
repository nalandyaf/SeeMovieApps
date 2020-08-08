package com.base.mvvm.data.remote

import com.base.mvvm.BuildConfig
import com.base.mvvm.domain.entities.BasePaginationEntity
import com.base.mvvm.domain.entities.BaseResponseEntity
import com.base.mvvm.domain.entities.MovieEntity
import io.reactivex.Single

class MovieRepository : BaseRepository<BaseResponseEntity<MovieEntity>>() {
    override fun get(): Single<BaseResponseEntity<MovieEntity>>? {
        return null
    }

    override fun getById(id: Int): Single<List<BaseResponseEntity<MovieEntity>>?>? {
        TODO("Not yet implemented")
    }

    fun getMovie(page: Int): Single<BasePaginationEntity<MovieEntity>> {
        return remoteAPI.getMovies(BuildConfig.API_KEY, BuildConfig.LANGUAGE, "popularity.desc", page)
    }

}