package com.base.mvvm.ui.movies

import com.base.mvvm.ui.base.BaseViewModel
import com.base.mvvm.utils.SchedulerProvider

class MoviesViewModel(baseUsecase: Any?, schedulerProvider: SchedulerProvider)
    : BaseViewModel<Any?, MoviesNavigator>(baseUsecase, schedulerProvider) {
    override fun defineLayout() {

    }
}