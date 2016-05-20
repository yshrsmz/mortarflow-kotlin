package net.yslibrary.mortarflowkotlin

import android.app.Application
import dagger.Module
import dagger.Provides
import flow.History
import net.yslibrary.mortarflowkotlin.flow.InitialHistory
import net.yslibrary.mortarflowkotlin.flow.PaperKeyParceler
import net.yslibrary.mortarflowkotlin.main.MainScreen

/**
 * Created by a12897 on 2016/05/19.
 */
@Module
class AppModule(private val app: Application) {

    @AppScope
    @Provides
    fun provideApplication(): Application = app

    @AppScope
    @Provides
    fun provideInitialHistory() = InitialHistory(History.single(MainScreen()))

    @AppScope
    @Provides
    fun provideKeyParceler() = PaperKeyParceler()
}