package net.yslibrary.mortarflowkotlin.sub

import dagger.Provides
import flow.ClassKey
import net.yslibrary.mortarflowkotlin.AppComponent
import net.yslibrary.mortarflowkotlin.R
import net.yslibrary.mortarflowkotlin.ScreenScope
import net.yslibrary.mortarflowkotlin.flow.Dispatcher
import net.yslibrary.mortarflowkotlin.flow.MortarService
import nz.bradcampbell.paperparcel.PaperParcelable

/**
 * Created by yshrsmz on 2016/05/22.
 */
class SubScreen : ClassKey(), MortarService.ComponentFactory<AppComponent>, Dispatcher.WithLayout, PaperParcelable {
  override fun buildComponent(parent: AppComponent): Any {
    return DaggerSubScreen_Component.builder()
        .appComponent(parent)
        .module(Module())
        .build()
  }

  override fun getLayoutId(): Int {
    return R.layout.view_sub
  }

  @ScreenScope
  @dagger.Component(
      dependencies = arrayOf(AppComponent::class),
      modules = arrayOf(Module::class)
  )
  interface Component {
    fun inject(view: SubView)
    fun presenter(): SubPresenter
  }

  @dagger.Module
  class Module {

    @ScreenScope
    @Provides
    fun providePresenter(): SubPresenter {
      return SubPresenter()
    }
  }
}