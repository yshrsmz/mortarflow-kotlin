package net.yslibrary.mortarflowkotlin.mortar

import android.os.Bundle
import mortar.MortarScope
import mortar.Presenter
import mortar.bundler.BundleService
import timber.log.Timber

/**
 * Created by yshrsmz on 2016/05/22.
 */
open class ViewPresenter<V : ContextHolder> : Presenter<V>() {
  override fun extractBundleService(view: V): BundleService? {
    return BundleService.getBundleService(view.getContext())
  }

  override fun onEnterScope(scope: MortarScope?) {
    super.onEnterScope(scope)
    Timber.d("onEnterScope - $this")
  }

  override fun onExitScope() {
    super.onExitScope()
    Timber.d("onExitScope - $this")
  }

  override fun onSave(outState: Bundle?) {
    super.onSave(outState)
    Timber.d("onSave - $this")
  }

  override fun onLoad(savedInstanceState: Bundle?) {
    super.onLoad(savedInstanceState)
    Timber.d("onLoad - $this")
  }
}