package net.yslibrary.mortarflowkotlin.main

import flow.ClassKey
import net.yslibrary.mortarflowkotlin.AppComponent
import net.yslibrary.mortarflowkotlin.flow.DaggerService
import nz.bradcampbell.paperparcel.PaperParcel
import nz.bradcampbell.paperparcel.PaperParcelable

/**
 * Created by a12897 on 2016/05/19.
 */
@PaperParcel
class MainScreen : ClassKey(), DaggerService.WithComponent, PaperParcelable {
    override fun <T> getComponent(): T {
        throw UnsupportedOperationException()
    }


    interface Component {
        fun inject(view: MainView)
    }
}