package com.base.mvvm.ui.movies.seeMore

import androidx.lifecycle.viewModelScope
import com.base.mvvm.domain.entities.response.MoviesList
import com.base.mvvm.domain.models.Movies
import com.base.mvvm.domain.usecases.movies.IMoviesUsecases
import com.base.mvvm.ui.base.BaseViewModel
import com.base.mvvm.ui.movies.seeMore.adapter.SeeMoreAdapter
import com.base.mvvm.utils.SchedulerProvider
import io.reactivex.Single
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

@Suppress("UNCHECKED_CAST")
class SeeMoreViewModel(movieUsecases: IMoviesUsecases, schedulerProvider: SchedulerProvider)
    : BaseViewModel<IMoviesUsecases?, SeeMoreNavigator>(movieUsecases, schedulerProvider) {

    var page: Int? = 1
    var totalPages: Int? = 0

    private lateinit var adapterSeeMoreAdapter: SeeMoreAdapter

    var dataMovies: Single<MoviesList>? = null

    override fun defineLayout() {

    }

    fun onLoadMore(type: Int) {
        if (page!! < totalPages!!.minus(1)) {
            page = page!!.plus(1)
            fetchData(type)
        }
    }

    fun fetchData(type: Int) {
        when (type) {
            SeeMoreActivity.TYPE_POPULAR -> getDataPopular(page)
            SeeMoreActivity.TYPE_TOP_RATED -> getDataTopRated(page)
            SeeMoreActivity.TYPE_UPCOMING -> getDataUpcoming(page)
        }
    }

    private fun getDataUpcoming(page: Int?) {
        viewModelScope.launch {
            supervisorScope {
                try {
                    val responseMoviesList = baseUsecase?.getUpcomingMovies(page!!)
                    dataMovies = responseMoviesList
                    adapterSeeMoreAdapter.addItems(dataMovies?.blockingGet()?.movies as List<Movies>)
                    totalPages = dataMovies?.blockingGet()?.page
                } catch (e: java.lang.Exception) {
                    //adapter no updae
                }
                if (adapterSeeMoreAdapter.listData.size > 0) navigator?.hideLoad()
            }
        }
    }

    private fun getDataTopRated(page: Int?) {
        viewModelScope.launch {
            supervisorScope {
                try {
                    val responseMoviesList = baseUsecase?.getTopRatedMovies(page!!)
                    dataMovies = responseMoviesList
                    adapterSeeMoreAdapter.addItems(dataMovies?.blockingGet()?.movies as List<Movies>)
                    totalPages = dataMovies?.blockingGet()?.page
                } catch (e: java.lang.Exception) {
                    //adapter no updae
                }
                if (adapterSeeMoreAdapter.listData.size > 0) navigator?.hideLoad()
            }
        }
    }

    private fun getDataPopular(page: Int?) {
        viewModelScope.launch {
            supervisorScope {
                try {
                    val responseMoviesList = baseUsecase?.getPopularMovies(page!!)
                    dataMovies = responseMoviesList
                    adapterSeeMoreAdapter.addItems(dataMovies?.blockingGet()?.movies as List<Movies>)
                    totalPages = dataMovies?.blockingGet()?.page
                } catch (e: java.lang.Exception) {
                    //adapter no updae
                }
                if (adapterSeeMoreAdapter.listData.size > 0) navigator?.hideLoad()
            }
        }
    }

    fun onSuccessGetData(moviesList: MoviesList) {
        navigator?.hideLoad()
        totalPages = moviesList.page
        adapterSeeMoreAdapter.addItems(moviesList.movies as List<Movies>)
    }

    fun getAdapter(): SeeMoreAdapter {
        adapterSeeMoreAdapter = SeeMoreAdapter(ArrayList(), ::toDetailMovie)
        return adapterSeeMoreAdapter
    }

    fun toDetailMovie(movies: Movies) {
        navigator?.toDetail(movies.id)
    }

}