package com.base.mvvm.domain.usecases.movies

import com.base.mvvm.data.remote.MovieRepository
import com.base.mvvm.domain.entities.response.MoviesList
import com.base.mvvm.domain.exceptions.MapperException
import com.base.mvvm.domain.mappers.MovieMapper
import com.base.mvvm.domain.usecases.base.BaseUsecase
import io.reactivex.Single

abstract class IMoviesUsecases(mapper: MovieMapper, movieRepository: MovieRepository?)
    : BaseUsecase<MovieMapper,MovieRepository>(mapper, movieRepository!!) {
    @Throws(MapperException::class)
    abstract fun getDiscoverMovies(page: Int): Single<MoviesList>
}