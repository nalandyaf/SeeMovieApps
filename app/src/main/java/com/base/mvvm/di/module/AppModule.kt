package com.base.mvvm.di.module

import android.app.Application
import android.content.Context
import com.base.mvvm.R
import com.base.mvvm.di.main.MainScope
import com.base.mvvm.utils.AndroidUtils
import com.base.mvvm.utils.AppSchedulerProvider
import com.base.mvvm.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @MainScope
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    @MainScope
    fun provideCalligraphyDefaultConfig(): CalligraphyConfig {
        return CalligraphyConfig.Builder()
                .setDefaultFontPath(AndroidUtils.getString(R.string.font_path_poppins))
                .setFontAttrId(R.attr.fontPath)
                .build()
    }
}