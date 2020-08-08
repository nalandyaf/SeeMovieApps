package com.base.mvvm.ui.login

import android.text.Editable
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import com.base.mvvm.R
import com.base.mvvm.domain.entities.response.MoviesList
import com.base.mvvm.domain.exceptions.MapperException
import com.base.mvvm.domain.usecases.movies.IMoviesUsecases
import com.base.mvvm.domain.usecases.user.IUserUsecases
import com.base.mvvm.ui.base.BaseViewModel
import com.base.mvvm.utils.AndroidUtils
import com.base.mvvm.utils.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel(baseUsecase: IMoviesUsecases, schedulerProvider: SchedulerProvider) : BaseViewModel<IMoviesUsecases?, LoginNavigator?>(baseUsecase, schedulerProvider) {
    @kotlin.jvm.JvmField
    var username = ObservableField<String>()
    @kotlin.jvm.JvmField
    var password = ObservableField<String>()

    override fun onPositiveButtonClick(view: View?) {
        login()
    }

    override fun onNegativeButtonClick(view: View?) {}
    override fun defineLayout() {
        positiveButton.set(AndroidUtils.getString(R.string.login_page_positive_button))
        appBarTitle.set(AndroidUtils.getString(R.string.login_page_app_bar_title))
    }

    fun afterUsernameChanged(s: Editable) {
        username.set(s.toString())
    }

    fun afterPasswordChanged(s: Editable) {
        password.set(s.toString())
    }

    fun login() {
        isLoading(true)
        try {
            compositeDisposable.add(baseUsecase!!.getDiscoverMovies(1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onSuccess))
        } catch (e: MapperException) {
            e.printStackTrace()
            onError(e)
        }
    }

    fun onSuccess(moviesList: MoviesList){
        val movies = moviesList.movies
    }


}