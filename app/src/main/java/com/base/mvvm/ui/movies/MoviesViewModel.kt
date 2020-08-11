package com.base.mvvm.ui.movies

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.base.mvvm.domain.models.Movies
import com.base.mvvm.domain.usecases.movies.IMoviesUsecases
import com.base.mvvm.ui.base.BaseViewModel
import com.base.mvvm.ui.movies.adapter.AdapterPopular
import com.base.mvvm.ui.movies.adapter.AdapterTopRated
import com.base.mvvm.ui.movies.adapter.AdapterUpcoming
import com.base.mvvm.utils.SchedulerProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

@Suppress("UNCHECKED_CAST")
class MoviesViewModel(movieUsecases: IMoviesUsecases, schedulerProvider: SchedulerProvider)
    : BaseViewModel<IMoviesUsecases?, MoviesNavigator>(movieUsecases, schedulerProvider) {

    private lateinit var adaperUpcoming: AdapterUpcoming

    private lateinit var adapterPopular: AdapterPopular

    private lateinit var adapterTopRated: AdapterTopRated

    override fun defineLayout() {

    }

    fun fetchData() {
        viewModelScope.launch {
            isLoading(true)
           try {
               supervisorScope {
                   try {
                       var dataPopular = async { baseUsecase?.getUpcomingMovies(1) }
                       var arrayPopular = dataPopular.await()
                       adapterPopular.addItems(arrayPopular?.blockingGet()?.movies as List<Movies>)
                   } catch (e: Exception) {
                       //Do Nothing becasue adapter still empty
                   }
                   try {
                       var dataTopRated = async { baseUsecase?.getTopRatedMovies(1) }
                       var arrayToprated = dataTopRated.await()
                       adapterTopRated.addItems(arrayToprated?.blockingGet()?.movies as List<Movies>)
                   } catch (e: Exception) {
                       //Do Nothing becasue adapter still empty
                   }
                   try {
                       var dataUpcoming = async { baseUsecase?.getUpcomingMovies(1) }
                       var arrayUpcoming = dataUpcoming.await()
                       adaperUpcoming.addItems(arrayUpcoming?.blockingGet()?.movies as List<Movies>)
                   } catch (e: Exception) {
                       //Do Nothing becasue adapter still empty
                   }
                   if (adaperUpcoming.listData.size > 0
                           && adapterPopular.listData.size >
                           0 && adapterTopRated.listData.size > 0)
                       Log.d("Scope", "fetchData: run after all data complete")
                   navigator?.hideShimmer()

               }
           }catch (e:Exception){

           }
        }
    }


    fun getAdapterUpcoming(): AdapterUpcoming {
        adaperUpcoming = AdapterUpcoming(ArrayList(), ::toDetailMovie)
        return adaperUpcoming
    }

    fun getAdapterTopRated(): AdapterTopRated {
        adapterTopRated = AdapterTopRated(ArrayList(), ::toDetailMovie)
        return adapterTopRated
    }

    fun getAdapterPopular(): AdapterPopular {
        adapterPopular = AdapterPopular(ArrayList(), ::toDetailMovie)
        return adapterPopular
    }

    fun seeMorePopular() {
        navigator?.seeMorePopular()
    }

    fun seeMoreTopRated() {
        navigator?.seeMoreTopRated()
    }

    fun seeMoreUpcoming() {
        navigator?.seeMoreUpcoming()
    }

    fun toDetailMovie(movies: Movies) {
        navigator?.toDetail(movies.id)
    }


}