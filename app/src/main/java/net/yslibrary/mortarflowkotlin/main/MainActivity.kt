package net.yslibrary.mortarflowkotlin.main

import flow.ClassKey
import net.yslibrary.mortarflowkotlin.BaseActivity

/**
 * Created by a12897 on 2016/05/19.
 */
class MainActivity : BaseActivity() {

    override fun getScopeName(): String {
        return BaseActivity::class.simpleName!!
    }

    override fun getDefaultKey(): ClassKey {
        return MainScreen()
    }
}