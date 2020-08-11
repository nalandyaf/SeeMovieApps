package com.base.mvvm.ui.movies

import com.base.mvvm.ui.base.BaseNavigator

interface MoviesNavigator : BaseNavigator {
    fun hideShimmer()
    fun seeMore(type:Int)
    fun toDetail(id: Int?)
}