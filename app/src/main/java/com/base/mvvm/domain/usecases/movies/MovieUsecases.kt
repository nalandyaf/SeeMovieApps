package com.base.mvvm.domain.usecases.movies

import com.base.mvvm.data.remote.MovieRepository
import com.base.mvvm.domain.entities.BasePaginationEntity
import com.base.mvvm.domain.entities.MovieEntity
import com.base.mvvm.domain.entities.response.MoviesList
import com.base.mvvm.domain.mappers.MovieMapper
import io.reactivex.Single

class MovieUsecases(mapper: MovieMapper?, repository: MovieRepository) : IMoviesUsecases(mapper!!,repository) {
    override fun getDiscoverMovies(page: Int): Single<MoviesList> {
        return repository.getMovie(page).flatMap { responses: BasePaginationEntity<MovieEntity> ->
            val moviesList = MoviesList()
            val movies = mapper.convertToObjectList(responses.results!!)
            moviesList.movies = movies
            return@flatMap Single.just(moviesList)
        }

    }
}