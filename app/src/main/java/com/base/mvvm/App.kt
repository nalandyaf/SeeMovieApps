package com.base.mvvm

import android.app.Activity
import android.app.Application
import android.content.Context
import com.base.mvvm.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class App : Application(), HasActivityInjector {
    @kotlin.jvm.JvmField
    @Inject
    var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>? = null
    @kotlin.jvm.JvmField
    @Inject
    var mCalligraphyConfig: CalligraphyConfig? = null

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appContext = applicationContext
        DaggerAppComponent.builder()
                .application(this)
                ?.build()
                ?.inject(this)
        CalligraphyConfig.initDefault(mCalligraphyConfig)
    }

    companion object {
        var appContext: Context? = null
            private set
        var instance: App? = null
            private set

    }
}