package net.yslibrary.mortarflowkotlin.main

import android.content.Context
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.widget.FrameLayout
import flow.Flow
import net.yslibrary.mortarflowkotlin.databinding.ViewMainBinding
import net.yslibrary.mortarflowkotlin.flow.DaggerService
import net.yslibrary.mortarflowkotlin.sub.SubScreen
import javax.inject.Inject

/**
 * Created by a12897 on 2016/05/19.
 */
class MainView : FrameLayout, MainContract.View {

  @field:Inject
  lateinit var presenter: MainPresenter

  private val binding: ViewMainBinding by lazy { DataBindingUtil.bind<ViewMainBinding>(this) }

  @JvmOverloads
  constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : super(context, attrs, defStyle) {
    DaggerService.getComponentForFlow<MainScreen.Component>(context)!!.inject(this)
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    presenter.takeView(this)
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    presenter.dropView(this)
  }

  override fun onFinishInflate() {
    super.onFinishInflate()
    binding.toNext.setOnClickListener { view -> presenter.onToNextClicked() }
  }

  override fun toSubScreen(screen: SubScreen) {
    Flow.get(this).set(screen)
  }
}