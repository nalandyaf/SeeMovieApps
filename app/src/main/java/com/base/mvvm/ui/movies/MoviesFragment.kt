package com.base.mvvm.ui.movies

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.base.mvvm.BR
import com.base.mvvm.R
import com.base.mvvm.ViewModelProviderFactory
import com.base.mvvm.databinding.FragmentMoviesBinding
import com.base.mvvm.ui.base.BaseFragment
import javax.inject.Inject

class MoviesFragment : BaseFragment<FragmentMoviesBinding, MoviesViewModel>(), MoviesNavigator {

    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_movies
    override val viewModel: MoviesViewModel
        get() = ViewModelProvider(this, factory!!).get(MoviesViewModel::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override fun handleError(throwable: Throwable?) {

    }


}