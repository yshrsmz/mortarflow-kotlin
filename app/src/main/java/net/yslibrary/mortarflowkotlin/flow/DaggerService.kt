package net.yslibrary.mortarflowkotlin.flow

import android.content.Context
import flow.Flow

/**
 * Created by a12897 on 2016/05/18.
 */
class DaggerService {

  companion object {
    @JvmStatic val NAME = DaggerService::class.simpleName

    @JvmStatic fun <T> getDaggerComponent(context: Context) = context.getSystemService(NAME) as T

    @JvmStatic fun <T> getFlowComponent(context: Context) = Flow.getService<T>(NAME, context)

    @JvmStatic fun <T> createComponent(context: Context): T {
      if (context !is WithComponent) {
        throw IllegalArgumentException("context should implement WithComponent interface")
      }
      return context.getComponent()
    }
  }

  interface WithComponent {
    fun <T> getComponent(): T
  }
}