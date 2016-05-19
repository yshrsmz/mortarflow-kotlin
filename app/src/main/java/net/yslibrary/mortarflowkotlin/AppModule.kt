package net.yslibrary.mortarflowkotlin

import android.app.Application
import dagger.Module
import dagger.Provides

/**
 * Created by a12897 on 2016/05/19.
 */
@Module
class AppModule(private val app: Application) {

    @AppScope
    @Provides
    fun provideApplication(): Application = app
}