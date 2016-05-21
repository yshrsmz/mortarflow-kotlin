package net.yslibrary.mortarflowkotlin.main

import dagger.Provides
import flow.ClassKey

import net.yslibrary.mortarflowkotlin.AppComponent
import net.yslibrary.mortarflowkotlin.R
import net.yslibrary.mortarflowkotlin.ScreenScope
import net.yslibrary.mortarflowkotlin.flow.Dispatcher
import net.yslibrary.mortarflowkotlin.flow.MortarService
import nz.bradcampbell.paperparcel.PaperParcel
import nz.bradcampbell.paperparcel.PaperParcelable


/**
 * Created by a12897 on 2016/05/19.
 */
@PaperParcel
class MainScreen : ClassKey(), MortarService.ComponentFactory<AppComponent>, Dispatcher.WithLayout, PaperParcelable {

  override fun getLayoutId() = R.layout.view_main

  override fun buildComponent(parent: AppComponent): Any {
    return DaggerMainScreen_Component.builder()
        .appComponent(parent)
        .build()
  }

  @ScreenScope
  @dagger.Component(
      dependencies = arrayOf(AppComponent::class),
      modules = arrayOf(Module::class)
  )
  interface Component {
    fun inject(view: MainView)
    fun presenter(): MainPresenter
  }

  @dagger.Module
  class Module {
    @Provides
    @ScreenScope
    fun provideMainPresenter() = MainPresenter()
  }
}