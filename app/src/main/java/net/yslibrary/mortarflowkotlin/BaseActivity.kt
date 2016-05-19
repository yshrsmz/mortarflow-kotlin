package net.yslibrary.mortarflowkotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import flow.Flow
import flow.KeyDispatcher
import net.yslibrary.mortarflowkotlin.flow.*

/**
 * Created by a12897 on 2016/05/19.
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract fun getDefaultKey(): Any

    override fun attachBaseContext(newBase: Context) {
        val appComponent = App.appComponent(newBase)
        val scope = App.scope(newBase)

        val keyChanger = CompositeKeyChanger()
        keyChanger.addDispatcher(SceneKeyChanger.WithLayout::class, SceneKeyChanger(this));

        val context = Flow.configure(newBase, this)
                .keyParceler(PaperKeyParceler())
                .dispatcher(KeyDispatcher.configure(this, keyChanger).build())
                .addServicesFactory(DaggerService(appComponent))
                .addServicesFactory(MortarService(scope))
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

    override fun onResume() {
        super.onResume()
        val appComponent = App.appComponent(this)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onBackPressed() {
        if (!Flow.get(this).goBack()) {
            super.onBackPressed()
        }
    }
}