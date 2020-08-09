package com.base.mvvm.di.builder

import com.base.mvvm.ui.movies.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = [])
    abstract fun bindMovies(): MoviesFragment?

}