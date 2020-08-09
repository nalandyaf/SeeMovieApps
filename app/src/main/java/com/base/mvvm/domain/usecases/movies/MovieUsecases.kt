package com.base.mvvm.domain.usecases.movies

import com.base.mvvm.data.remote.MovieRepository
import com.base.mvvm.domain.entities.BasePaginationEntity
import com.base.mvvm.domain.entities.Genre
import com.base.mvvm.domain.entities.MovieEntity
import com.base.mvvm.domain.entities.response.BaseResponsePagination
import com.base.mvvm.domain.entities.response.MoviesList
import com.base.mvvm.domain.entities.response.ResponseGenres
import com.base.mvvm.domain.mappers.MovieMapper
import com.base.mvvm.domain.models.MovieReview
import io.reactivex.Single

class MovieUsecases(mapper: MovieMapper?, repository: MovieRepository)
    : IMoviesUsecases(mapper!!, repository) {
    override fun getDiscoverMovies(page: Int): Single<MoviesList> {
        return repository.getMovie(page).flatMap { responses: BasePaginationEntity<MovieEntity> ->
            val moviesList = MoviesList()
            val movies = mapper.convertToObjectList(responses.results!!)
            moviesList.movies = movies
            return@flatMap Single.just(moviesList)
        }

    }

    override fun getGenres(): Single<List<Genre>> {
        return repository.getGenres().flatMap { response: ResponseGenres ->
            val genreList = response.genres
            return@flatMap Single.just(genreList)
        }
    }

    override fun getMovieReview(movieId: Int, page: Int): Single<List<MovieReview>> {
        return repository.getMovieReview(movieId, page).flatMap { responses: BaseResponsePagination<MovieReview> ->
            val responseReview = responses.results
            return@flatMap Single.just(responseReview)
        }
    }
}