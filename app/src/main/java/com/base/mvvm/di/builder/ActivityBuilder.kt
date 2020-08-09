package com.base.mvvm.di.builder

import com.base.mvvm.ui.home.HomeActivity
import com.base.mvvm.ui.login.LoginActivity
import com.base.mvvm.ui.registration.RegistrationActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [])
    abstract fun bindLoginActivity(): LoginActivity?

    @ContributesAndroidInjector(modules = [])
    abstract fun bindRegistrationActivity(): RegistrationActivity?

    @ContributesAndroidInjector(modules =  [])
    abstract fun bindingHomeActivity(): HomeActivity?
}