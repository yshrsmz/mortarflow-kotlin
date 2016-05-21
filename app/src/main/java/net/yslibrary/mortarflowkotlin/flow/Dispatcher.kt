package net.yslibrary.mortarflowkotlin.flow

import android.app.Activity
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.ViewGroup
import flow.Traversal
import flow.TraversalCallback
import net.yslibrary.mortarflowkotlin.R

/**
 * Created by a12897 on 2016/05/20.
 * https://github.com/Kritarie/FlowSample/blob/master/app/src/main/java/net/seanamos/flowsample/ui/FlowSampleActivity.java
 */
class Dispatcher(private val activity: Activity) : flow.Dispatcher {

  override fun dispatch(traversal: Traversal, callback: TraversalCallback) {
    val frame = activity.findViewById(R.id.app_container) as ViewGroup
    val history = traversal.origin
    val destination = traversal.destination.top<Any>()

    if (destination !is WithLayout) {
      throw IllegalArgumentException("destination must implement WithLayout")
    }

    val incomingContext = traversal.createContext(destination, activity)
    val incomingView = LayoutInflater.from(incomingContext).inflate(destination.getLayoutId(), frame, false)
    traversal.getState(destination).restore(incomingView)

    history?.let { traversal.getState(it.top()).save(frame.getChildAt(0)) }

    frame.removeAllViews()
    frame.addView(incomingView)

    callback.onTraversalCompleted()
  }

  interface WithLayout {
    @LayoutRes fun getLayoutId(): Int
  }
}