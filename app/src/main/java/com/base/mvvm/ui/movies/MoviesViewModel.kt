package com.base.mvvm.ui.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.base.mvvm.domain.models.Movies
import com.base.mvvm.domain.usecases.movies.IMoviesUsecases
import com.base.mvvm.ui.base.BaseViewModel
import com.base.mvvm.ui.movies.adapter.AdapterPopular
import com.base.mvvm.ui.movies.adapter.AdapterTopRated
import com.base.mvvm.ui.movies.adapter.AdapterUpcoming
import com.base.mvvm.ui.movies.seeMore.SeeMoreActivity
import com.base.mvvm.utils.SchedulerProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

@Suppress("UNCHECKED_CAST")
class MoviesViewModel(movieUsecases: IMoviesUsecases, schedulerProvider: SchedulerProvider)
    : BaseViewModel<IMoviesUsecases?, MoviesNavigator>(movieUsecases, schedulerProvider) {

    private val mViewState = MutableLiveData<MoviesViewState>().apply {
        value = MoviesViewState(loading = true)
    }

    val viewState: LiveData<MoviesViewState>
        get() = mViewState

    private var adaperUpcoming: AdapterUpcoming? = null

    private var adapterTopRated: AdapterTopRated? = null

    private var adapterPopular: AdapterPopular? = null


    override fun defineLayout() {

    }

    fun fetchData() {
        viewModelScope.launch {
            isLoading(true)
            try {
                try {
                    val dataPopular = async { baseUsecase?.getPopularMovies(1) }
                    val arrayPopular = dataPopular.await()
                    adapterPopular?.addItems(arrayPopular?.blockingGet()?.movies as List<Movies>)
                } catch (e: Exception) {
                    //Do Nothing becasue adapter still empty
                }
                try {
                    val dataTopRated = async { baseUsecase?.getTopRatedMovies(1) }
                    val arrayToprated = dataTopRated.await()
                    adapterTopRated?.addItems(arrayToprated?.blockingGet()?.movies as List<Movies>)
                } catch (e: Exception) {
                    //Do Nothing becasue adapter still empty
                }
                try {
                    val dataUpcoming = async { baseUsecase?.getUpcomingMovies(1) }
                    val arrayUpcoming = dataUpcoming.await()
                    adaperUpcoming?.addItems(arrayUpcoming?.blockingGet()?.movies as List<Movies>)
                } catch (e: Exception) {
                    //Do Nothing becasue adapter still empty
                }
                if (adaperUpcoming?.listData?.size!! > 0
                        && adapterPopular?.listData?.size!! >
                        0 && adapterTopRated?.listData?.size!! > 0)
                mViewState.value = mViewState.value?.copy(loading = false, error = null, data = adapterPopular?.listData)

            } catch (e: Exception) {
                mViewState.value = mViewState.value?.copy(loading = false, error = e, data = null)
            }
        }
    }

    fun getAdapterUpcoming(): AdapterUpcoming {
        adaperUpcoming = AdapterUpcoming(ArrayList(), ::toDetailMovie)
        return adaperUpcoming!!
    }

    fun getAdapterTopRated(): AdapterTopRated {
        adapterTopRated = AdapterTopRated(ArrayList(), ::toDetailMovie)
        return adapterTopRated!!
    }

    fun getAdapterPopular(): AdapterPopular {
        adapterPopular = AdapterPopular(ArrayList(), ::toDetailMovie)
        return adapterPopular!!
    }

    fun seeMorePopular() {
        navigator?.seeMore(SeeMoreActivity.TYPE_POPULAR)
    }

    fun seeMoreTopRated() {
        navigator?.seeMore(SeeMoreActivity.TYPE_TOP_RATED)
    }

    fun seeMoreUpcoming() {
        navigator?.seeMore(SeeMoreActivity.TYPE_UPCOMING)
    }

    fun toDetailMovie(movies: Movies) {
        navigator?.toDetail(movies.id)
    }


}