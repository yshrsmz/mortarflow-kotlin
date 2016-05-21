package net.yslibrary.mortarflowkotlin.main

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * Created by a12897 on 2016/05/19.
 */
class MainView : FrameLayout {

  @JvmOverloads
  constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : super(context, attrs, defStyle) {

  }


  interface Component {
    fun inject(view: MainView)
  }
}