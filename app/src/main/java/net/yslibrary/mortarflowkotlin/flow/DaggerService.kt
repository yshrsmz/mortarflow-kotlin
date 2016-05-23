package net.yslibrary.mortarflowkotlin.flow

import android.content.Context
import android.view.View
import flow.Flow

/**
 * Created by a12897 on 2016/05/18.
 */
class DaggerService {

  companion object {
    @JvmStatic val NAME = DaggerService::class.simpleName

    @JvmStatic fun <T> getComponentForContext(context: Context) = context.getSystemService(NAME) as T

    @JvmStatic fun <T> getComponentForFlow(context: Context) = Flow.getService<T>(NAME, context) as T

    @JvmStatic inline fun <reified T> getComponentForFlow(view: View) {
      return getComponentForFlow(view.context)
    }
  }

  interface WithComponent {
    fun <T> getComponent(): T
  }
}