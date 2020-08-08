package com.base.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.base.mvvm.data.remote.MovieRepository
import com.base.mvvm.data.remote.UserRepository
import com.base.mvvm.domain.mappers.MovieMapper
import com.base.mvvm.domain.mappers.UserMapper
import com.base.mvvm.domain.usecases.movies.IMoviesUsecases
import com.base.mvvm.domain.usecases.movies.MovieUsecases
import com.base.mvvm.domain.usecases.user.IUserUsecases
import com.base.mvvm.domain.usecases.user.UserUsecases
import com.base.mvvm.ui.login.LoginViewModel
import com.base.mvvm.ui.registration.RegistrationViewModel
import com.base.mvvm.utils.SchedulerProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelProviderFactory @Inject constructor(private val schedulerProvider: SchedulerProvider) : NewInstanceFactory() {
    private val userUsecases: IUserUsecases
    private val movieUsecases: IMoviesUsecases
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(movieUsecases, schedulerProvider) as T
        } else if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            return RegistrationViewModel(userUsecases, schedulerProvider) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    init {
        userUsecases = UserUsecases(UserMapper(), UserRepository.instance!!)
        movieUsecases = MovieUsecases(MovieMapper(), MovieRepository())
    }
}