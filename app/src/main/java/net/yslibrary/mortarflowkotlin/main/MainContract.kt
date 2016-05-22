package net.yslibrary.mortarflowkotlin.main

import net.yslibrary.mortarflowkotlin.mortar.ContextHolder
import net.yslibrary.mortarflowkotlin.sub.SubScreen

/**
 * Created by yshrsmz on 2016/05/22.
 */
class MainContract {

  interface View : ContextHolder {
    fun toSubScreen(screen: SubScreen);
  }

  interface Presenter {
    fun onToNextClicked();
  }
}