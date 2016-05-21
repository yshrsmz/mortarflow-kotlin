package net.yslibrary.mortarflowkotlin

import android.app.Application
import dagger.Component
import net.yslibrary.mortarflowkotlin.flow.InitialHistory
import net.yslibrary.mortarflowkotlin.flow.PaperKeyParceler

/**
 * Created by a12897 on 2016/05/19.
 */
@AppScope
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
  fun inject(app: App)

  fun initialHistory(): InitialHistory

  fun keyParceler(): PaperKeyParceler

  fun app(): Application
}