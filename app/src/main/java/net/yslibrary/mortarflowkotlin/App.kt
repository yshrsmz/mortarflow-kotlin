package net.yslibrary.mortarflowkotlin

import android.app.Application
import android.content.Context
import mortar.MortarScope
import net.yslibrary.mortarflowkotlin.flow.DaggerService

/**
 * Created by a12897 on 2016/05/18.
 */
class App : Application() {

    companion object {
        @JvmStatic fun get(context: Context) = context.applicationContext as App
        @JvmStatic fun appComponent(context: Context) = get(context).appComponent
        @JvmStatic fun scope(context: Context) = get(context).scope
    }

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    private val scope: MortarScope by lazy {
        MortarScope.buildRootScope()
                .withService(DaggerService.NAME, appComponent)
                .build("Root")
    }

    override fun getSystemService(name: String?): Any? {
        if (scope.hasService(name)) {
            return scope.getService(name)
        }

        return super.getSystemService(name)
    }
}