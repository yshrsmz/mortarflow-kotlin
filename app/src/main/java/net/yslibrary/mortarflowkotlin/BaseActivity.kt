package net.yslibrary.mortarflowkotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import flow.Flow
import mortar.MortarScope
import mortar.bundler.BundleServiceRunner
import net.yslibrary.mortarflowkotlin.flow.DaggerService
import net.yslibrary.mortarflowkotlin.flow.Dispatcher
import net.yslibrary.mortarflowkotlin.flow.MortarService


/**
 * Created by a12897 on 2016/05/19.
 */
abstract class BaseActivity : AppCompatActivity() {

  lateinit protected var scope: MortarScope

  abstract fun getDefaultKey(): Any

  abstract fun getScopeName(): String


  override fun getSystemService(name: String?): Any? {
    if (scope.hasService(name)) {
      return scope.getService(name)
    }
    return super.getSystemService(name)
  }

  override fun attachBaseContext(newBase: Context) {
    val appContext = newBase.applicationContext
    val appComponent = DaggerService.getComponentForContext<AppComponent>(appContext)
    scope = getScope(appContext)

    val context = Flow.configure(newBase, this)
        .addServicesFactory(MortarService(scope))
        .keyParceler(appComponent.keyParceler())
        .defaultKey(getDefaultKey())
        .dispatcher(Dispatcher(this))
        .install()

    super.attachBaseContext(context)
  }

  override fun onNewIntent(intent: Intent?) {
    super.onNewIntent(intent)
    Flow.onNewIntent(intent, this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    BundleServiceRunner.getBundleServiceRunner(this).onCreate(savedInstanceState)
    setContentView(R.layout.activity_base)
  }

  override fun onSaveInstanceState(outState: Bundle?) {
    super.onSaveInstanceState(outState)
    BundleServiceRunner.getBundleServiceRunner(this).onSaveInstanceState(outState)
  }

  override fun onDestroy() {
    if (isFinishing) {
      val activityScope = MortarScope.findChild(applicationContext, getScopeName())
      activityScope?.let { it.destroy() }
    }
    super.onDestroy()
  }

  private fun getScope(context: Context): MortarScope {
    val parent = MortarScope.getScope(context)
    var child = MortarScope.findChild(context, getScopeName())
    if (child == null) {
      child = parent.buildChild()
          .withService(BundleServiceRunner.SERVICE_NAME, BundleServiceRunner())
          .build(getScopeName())
    }
    return child
  }

  override fun onBackPressed() {
    if (!Flow.get(this).goBack()) {
      super.onBackPressed()
    }
  }
}