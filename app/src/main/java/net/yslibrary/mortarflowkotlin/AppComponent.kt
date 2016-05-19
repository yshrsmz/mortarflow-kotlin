package net.yslibrary.mortarflowkotlin

import dagger.Component

/**
 * Created by a12897 on 2016/05/19.
 */
@AppScope
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: App)
}