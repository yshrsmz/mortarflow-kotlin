package net.yslibrary.mortarflowkotlin.flow

import flow.Services
import flow.ServicesFactory
import mortar.MortarScope
import net.yslibrary.mortarflowkotlin.AppComponent

/**
 * Created by a12897 on 2016/05/20.
 */
class MortarService(private val root: MortarScope) : ServicesFactory() {
    override fun bindServices(services: Services.Binder) {
        val key = services.getKey<Any>()
        if (!root.hasService(key.toString())) {
            return
        }

        if (key !is WithComponent) {
            throw IllegalArgumentException("services must implement WithComponent")
        }
        val component: Any = key.getComponent(root.getService<AppComponent>(DaggerService.NAME))
        val childScope = root.findChild(key.toString())
        if (childScope == null) {
            root.buildChild()
                    .withService(DaggerService.NAME, component)
                    .build(key.toString())
        }
        services.bind(DaggerService.NAME, component)
    }

    interface WithComponent {
        fun <T> getComponent(parent: T)
    }
}