package net.yslibrary.mortarflowkotlin

import android.app.Application
import mortar.MortarScope
import net.yslibrary.mortarflowkotlin.mortar.DaggerService

/**
 * Created by a12897 on 2016/05/18.
 */
class App : Application() {

    private val scope:MortarScope by lazy {
        MortarScope.buildRootScope()
        .withService(DaggerService.NAME, null)
        .build("Root")
    }
}