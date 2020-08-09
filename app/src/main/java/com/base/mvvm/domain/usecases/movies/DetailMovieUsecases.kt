package com.base.mvvm.domain.usecases.movies

import com.base.mvvm.data.remote.DetailMovieRepository
import com.base.mvvm.domain.entities.DetailMovieEntitiy
import com.base.mvvm.domain.mappers.DetailMovieMapper
import com.base.mvvm.domain.models.DetailMovies
import io.reactivex.Single

class DetailMovieUsecases(mapper: DetailMovieMapper?, repository: DetailMovieRepository?)
    : IDetailMovieUsecases(mapper!!, repository) {
    override fun getDetailMovies(movieId: Int): Single<DetailMovies> {
        return repository.getDetailMovie(movieId).flatMap { responses: DetailMovieEntitiy ->
            val detailMovies = mapper.convertToObject(responses)
            return@flatMap Single.just(detailMovies)
        }
    }
}