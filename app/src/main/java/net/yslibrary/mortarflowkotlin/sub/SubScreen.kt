package net.yslibrary.mortarflowkotlin.sub

import flow.ClassKey
import net.yslibrary.mortarflowkotlin.AppComponent
import net.yslibrary.mortarflowkotlin.ScreenScope
import net.yslibrary.mortarflowkotlin.flow.Dispatcher
import net.yslibrary.mortarflowkotlin.flow.MortarService
import nz.bradcampbell.paperparcel.PaperParcelable

/**
 * Created by yshrsmz on 2016/05/22.
 */
class SubScreen : ClassKey(), MortarService.ComponentFactory<AppComponent>, Dispatcher.WithLayout, PaperParcelable {
  override fun buildComponent(parent: AppComponent): Any {
    throw UnsupportedOperationException()
  }

  override fun getLayoutId(): Int {
    throw UnsupportedOperationException()
  }

  @ScreenScope
  @dagger.Component
  interface Component {

  }
}