package net.yslibrary.mortarflowkotlin.main

import android.os.Bundle
import mortar.MortarScope
import net.yslibrary.mortarflowkotlin.mortar.ViewPresenter
import net.yslibrary.mortarflowkotlin.sub.SubScreen

/**
 * Created by yshrsmz on 2016/05/21.
 */
class MainPresenter : ViewPresenter<MainContract.View>(), MainContract.Presenter {

  override fun onEnterScope(scope: MortarScope?) {
    super.onEnterScope(scope)
  }

  override fun onLoad(savedInstanceState: Bundle?) {
    super.onLoad(savedInstanceState)
  }

  override fun onSave(outState: Bundle?) {
    super.onSave(outState)
  }

  override fun onExitScope() {
    super.onExitScope()
  }

  override fun onToNextClicked() {
    view?.let { it.toSubScreen(SubScreen()) }
  }
}