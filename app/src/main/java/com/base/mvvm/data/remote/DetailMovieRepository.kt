package com.base.mvvm.data.remote

import com.base.mvvm.BuildConfig
import com.base.mvvm.domain.entities.BaseResponseEntity
import com.base.mvvm.domain.entities.DetailMovieEntitiy
import com.base.mvvm.domain.entities.MovieEntity
import io.reactivex.Single

class DetailMovieRepository : BaseRepository<BaseResponseEntity<DetailMovieEntitiy>>() {

    override fun get(): Single<BaseResponseEntity<MovieEntity>>? {
       return null
    }

    override fun getById(id: Int): Single<List<BaseResponseEntity<DetailMovieEntitiy>>?>? {
        TODO("Not yet implemented")
    }

    fun getDetailMovie(id:Int): Single<DetailMovieEntitiy>{
        return remoteAPI.getDetailMovie(id,BuildConfig.API_KEY,BuildConfig.LANGUAGE)
    }


}