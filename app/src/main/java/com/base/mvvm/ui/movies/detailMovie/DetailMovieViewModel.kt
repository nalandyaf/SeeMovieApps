package com.base.mvvm.ui.movies.detailMovie

import com.base.mvvm.domain.usecases.movies.IDetailMovieUsecases
import com.base.mvvm.ui.base.BaseViewModel
import com.base.mvvm.utils.SchedulerProvider

class DetailMovieViewModel(baseUsecases: IDetailMovieUsecases, schedulerProvider: SchedulerProvider)
    : BaseViewModel<IDetailMovieUsecases, DetailMovieNavigator>(baseUsecases, schedulerProvider) {

    override fun defineLayout() {

    }
}