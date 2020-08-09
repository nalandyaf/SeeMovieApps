package com.base.mvvm.ui.movies.detailMovie

import com.base.mvvm.databinding.ActivityDetailMovieBinding
import com.base.mvvm.ui.base.BaseActivity

class DetailMovieActivity : BaseActivity<ActivityDetailMovieBinding,DetailMovieViewModel>() , DetailMovieNavigator {

    override val bindingVariable: Int
        get() = TODO("Not yet implemented")
    override val layoutId: Int
        get() = TODO("Not yet implemented")
    override val viewModel: DetailMovieViewModel
        get() = TODO("Not yet implemented")

    override fun handleError(throwable: Throwable?) {
        TODO("Not yet implemented")
    }
}