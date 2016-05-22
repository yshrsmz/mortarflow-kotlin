package net.yslibrary.mortarflowkotlin.mortar

import mortar.Presenter
import mortar.bundler.BundleService

/**
 * Created by yshrsmz on 2016/05/22.
 */
open class ViewPresenter<V : ContextHolder> : Presenter<V>() {
  override fun extractBundleService(view: V): BundleService? {
    return BundleService.getBundleService(view.getContext())
  }
}