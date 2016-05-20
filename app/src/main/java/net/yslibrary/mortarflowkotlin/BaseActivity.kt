package net.yslibrary.mortarflowkotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import flow.Flow
import mortar.MortarScope
import mortar.bundler.BundleServiceRunner
import net.yslibrary.mortarflowkotlin.flow.DaggerService
import net.yslibrary.mortarflowkotlin.flow.MortarService


/**
 * Created by a12897 on 2016/05/19.
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract fun getDefaultKey(): Any

    abstract fun getScopeName(): String

    override fun attachBaseContext(newBase: Context) {
        val appContext = newBase.applicationContext
        val appComponent = DaggerService.getDaggerComponent<AppComponent>(appContext)
        val scope = getScope(appContext)

        val context = Flow.configure(newBase, this)
                .addServicesFactory(MortarService(scope))
                .keyParceler(appComponent.keyParceler())
                .defaultKey(getDefaultKey())
                .install()

        super.attachBaseContext(context)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Flow.onNewIntent(intent, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
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