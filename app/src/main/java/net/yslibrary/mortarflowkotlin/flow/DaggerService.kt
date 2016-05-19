package net.yslibrary.mortarflowkotlin.flow

import android.content.Context
import flow.Flow
import flow.Services
import flow.ServicesFactory

/**
 * Created by a12897 on 2016/05/18.
 */
class DaggerService(private val rootComponent: Any) : ServicesFactory() {

    override fun bindServices(services: Services.Binder) {
        val key = services.getKey<Any>()
        if (key !is WithComponent) {
            return
        }

        val parent = services.getService<Any>(NAME) ?: rootComponent
        val component = key.createComponent(parent)

        services.bind(NAME, component)
    }

    interface WithComponent {
        fun createComponent(parent: Any): Any
    }

    companion object {
        const val NAME = "DaggerService"

        fun <T> get(context: Context) = Flow.getService<T>(NAME, context)
    }
}