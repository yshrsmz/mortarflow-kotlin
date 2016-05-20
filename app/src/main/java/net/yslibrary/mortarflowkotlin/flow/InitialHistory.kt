package net.yslibrary.mortarflowkotlin.flow

import flow.History
import java.util.concurrent.atomic.AtomicReference

/**
 * Created by a12897 on 2016/05/20.
 */
class InitialHistory(history: History) {

    lateinit private var initialHistory: AtomicReference<History>

    init {
        initialHistory = AtomicReference(history)
    }

    fun get() = initialHistory.get()

    fun set(history: History) {
        initialHistory = AtomicReference(history)
    }
}