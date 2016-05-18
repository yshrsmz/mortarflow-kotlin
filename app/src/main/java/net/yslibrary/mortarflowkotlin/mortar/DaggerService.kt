package net.yslibrary.mortarflowkotlin.mortar

import android.content.Context
import flow.Flow

/**
 * Created by a12897 on 2016/05/18.
 */
class DaggerService {

    companion object {
        val NAME = DaggerService::class.simpleName

        @Suppress("UNCHECKED_CAST")
        fun <T> getComponentForContext(context: Context): T {
            return context.getSystemService(NAME) as T
        }

        fun <T> getComponentForFlow(context: Context): T {
            return Flow.getService<T>(NAME, context)!!
        }
    }
}