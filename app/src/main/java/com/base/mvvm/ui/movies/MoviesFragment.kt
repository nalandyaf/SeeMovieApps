package com.base.mvvm.ui.movies

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.base.mvvm.BR
import com.base.mvvm.R
import com.base.mvvm.ViewModelProviderFactory
import com.base.mvvm.databinding.FragmentMoviesBinding
import com.base.mvvm.domain.models.Movies
import com.base.mvvm.ui.base.BaseFragment
import com.base.mvvm.ui.movies.detailMovie.DetailMovieActivity
import com.base.mvvm.ui.movies.seeMore.SeeMoreActivity
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import javax.inject.Inject

class MoviesFragment : BaseFragment<FragmentMoviesBinding, MoviesViewModel>(), MoviesNavigator {

    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null
    var mBinding: FragmentMoviesBinding? = null

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

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding
        viewModel.setNavigator(this)
        mBinding!!.popularMovieList.enableViewScaling(true)
        val snapHelper = GravitySnapHelper(Gravity.START)
        snapHelper.attachToRecyclerView(mBinding!!.topRatedList)
        snapHelper.attachToRecyclerView(mBinding!!.upcomingList)
        viewModel.apply {
            viewState.observe(
                    this@MoviesFragment,
                    Observer(this@MoviesFragment::handleState)
            )
            if (viewState.value?.data == null) viewModel.fetchData()
        }
    }

    private fun handleState(viewState: MoviesViewState?) {
        viewState?.let {
            viewModel.showProgressBar.set(it.loading)
            it.data?.let { data -> showData(data) }
            it.error?.let { error -> showError(error) }
        }
    }


    private fun showData(mvoies: List<Movies>) {
        viewModel.showEmpty.set(false)
    }

    private fun showError(e: Exception) {
        viewModel.showEmpty.set(true)
    }

    override fun hideShimmer() {
        dismissLoading()
    }

    override fun seeMore(type: Int) {
        val intent = Intent(context, SeeMoreActivity::class.java)
        intent.putExtra("data", type)
        context?.startActivity(intent)
    }

    override fun toDetail(id: Int?) {
        val intent = Intent(context, DetailMovieActivity::class.java)
        intent.putExtra("id", id)
        context?.startActivity(intent)
    }

    override fun handleError(throwable: Throwable?) {

    }


}