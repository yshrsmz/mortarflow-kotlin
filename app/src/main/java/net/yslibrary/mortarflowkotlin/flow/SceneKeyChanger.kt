package net.yslibrary.mortarflowkotlin.flow

import android.app.Activity
import android.content.Context
import android.support.annotation.LayoutRes
import android.transition.AutoTransition
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.ViewGroup
import flow.Direction
import flow.KeyChanger
import flow.State
import flow.TraversalCallback
import net.yslibrary.mortarflowkotlin.R

class SceneKeyChanger(private val activity: Activity) : KeyChanger() {

    override fun changeKey(outgoingState: State?,
                           incomingState: State,
                           direction: Direction,
                           incomingContexts: MutableMap<Any, Context>,
                           callback: TraversalCallback) {

        val origin = outgoingState?.getKey<Any>()
        val destination = incomingState.getKey<WithLayout>()
        val layout = destination.getLayoutResId()
        val context = incomingContexts[destination]
        val frame = activity.findViewById(R.id.app_container) as ViewGroup
        val incomingView = LayoutInflater.from(context).inflate(layout, frame, false)

        outgoingState?.save(frame.getChildAt(0))

        val scene = Scene(frame, incomingView)
        val transition = if (destination is WithTransition) {
            destination.createTransition(outgoingState?.getKey(), incomingState.getKey(), direction)
        } else if (origin is WithTransition) {
            origin.createTransition(outgoingState?.getKey(), incomingState.getKey(), direction)
        } else if (outgoingState != null) {
            AutoTransition()
        } else {
            null
        }

        TransitionManager.go(scene, transition)
        incomingState.restore(incomingView)

        callback.onTraversalCompleted()
    }

    interface WithLayout {
        @LayoutRes fun getLayoutResId(): Int
    }

    interface WithTransition {
        fun createTransition(fromKey: Any?, toKey: Any, direction: Direction): Transition
    }
}