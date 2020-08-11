package com.base.mvvm.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.base.mvvm.data.remote.MovieRepository
import com.base.mvvm.domain.mappers.MovieMapper
import com.base.mvvm.domain.usecases.movies.IMoviesUsecases
import com.base.mvvm.domain.usecases.movies.MovieUsecases
import com.base.mvvm.ui.movies.MoviesViewModel
import com.base.mvvm.utils.AppSchedulerProvider
import com.base.mvvm.utils.SchedulerProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MoviesViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @InjectMocks
    var movieMapper: MovieMapper? = null

    @InjectMocks
    var movieRepository: MovieRepository? = null

    @Mock
    var iMoviesUsecase: IMoviesUsecases? = null

    @Mock
    var movieUsecases: MovieUsecases? = null

    var moviesViewModel: MoviesViewModel? = null
    var schedulerProvider: SchedulerProvider = AppSchedulerProvider()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        moviesViewModel = MoviesViewModel(iMoviesUsecase!!, schedulerProvider)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun shouldLoadingWhenFirstInitialized() {
        val state = moviesViewModel!!.viewState.value!!
        Assert.assertTrue(state.loading)
        Assert.assertNull(state.error)
        Assert.assertNull(state.data)
    }

    @Test
    fun shouldThrowErrorWhenRepositoryIsThrowingError() {
        runBlocking {
            Mockito.`when`(movieUsecases?.getPopularMovies(Mockito.anyInt())).thenAnswer { throw Exception() }
            moviesViewModel?.fetchData()
            val state = moviesViewModel!!.viewState.value!!
            Assert.assertFalse(state.loading)
            Assert.assertNotNull(state.error)
            Assert.assertNull(state.data)
        }
    }


}