package net.yslibrary.mortarflowkotlin.sub

import android.content.Context
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.widget.FrameLayout
import net.yslibrary.mortarflowkotlin.databinding.ViewSubBinding
import net.yslibrary.mortarflowkotlin.flow.DaggerService
import javax.inject.Inject

/**
 * Created by yshrsmz on 2016/05/22.
 */
class SubView : FrameLayout, SubContract.View {

  @field:Inject
  lateinit var presenter: SubPresenter

  val binding: ViewSubBinding by lazy { DataBindingUtil.bind<ViewSubBinding>(this) }

  @JvmOverloads
  constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
    DaggerService.getComponentForFlow<SubScreen.Component>(context)!!.inject(this)
  }

  override fun onFinishInflate() {
    super.onFinishInflate()


  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    presenter.takeView(this)
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    presenter.dropView(this)
  }
}