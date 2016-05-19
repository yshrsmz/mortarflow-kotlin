package net.yslibrary.mortarflowkotlin.flow

import android.content.Context
import flow.Direction
import flow.KeyChanger
import flow.State
import flow.TraversalCallback
import kotlin.reflect.KClass


/**
 * Created by a12897 on 2016/05/19.
 */
class CompositeKeyChanger : KeyChanger() {

    private val dispatchers = mutableMapOf<KClass<out Any>, KeyChanger>()

    fun addDispatcher(cls: KClass<out Any>, dispatcher: KeyChanger) {
        dispatchers.put(cls, dispatcher)
    }

    override fun changeKey(outgoingState: State?,
                           incomingState: State,
                           direction: Direction,
                           incomingContexts: MutableMap<Any, Context>,
                           callback: TraversalCallback) {

        val destination = incomingState.getKey<Any>()

        dispatchers.forEach {
            if (it.key.java.isAssignableFrom(destination.javaClass)) {
                it.value.changeKey(outgoingState, incomingState, direction, incomingContexts, callback)
            }
        }
    }
}