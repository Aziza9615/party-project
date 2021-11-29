package com.example.authactivity.ui.onBoard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.authactivity.R
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.ui.currency.CurrencyActivity
import com.example.authactivity.ui.lang.LangActivity
import com.example.authactivity.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_onboard.*

class OnBoardViewModel : BaseViewModel<BaseEvent>()
class OnBoardActivity : AppCompatActivity() {

    private lateinit var sliderAdapter: SliderAdapter
    private var dots: Array<TextView?>? = null
    private lateinit var layouts: Array<Int>
    private val sliderChangeListener = object : ViewPager.OnPageChangeListener {

        override fun onPageSelected(position: Int) {
            addBottomDots(position)
            if (position == layouts.size.minus(1)) {
                nextBtn.show()
            } else {
                nextBtn.show()
            }
        }

        override fun onPageScrollStateChanged(state: Int) {
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard)
        init()
        dataSet()
        interactions()
        setupListener()
    }

    private fun setupListener() {
        arrow_btn.setOnClickListener {
            val intent = Intent(this, LangActivity::class.java)
            startActivity(intent)
        }
    }

    private fun init() {
        layouts = arrayOf(
                R.layout.onboarding_slide1,
                R.layout.onboarding_slide2,
                R.layout.onboarding_slide3
        )
        sliderAdapter = SliderAdapter(this, layouts)
    }

    private fun dataSet() {
        addBottomDots(0)
        slider.apply {
            adapter = sliderAdapter
            addOnPageChangeListener(sliderChangeListener)
        }
    }

    private fun interactions() {
        nextBtn.setOnClickListener {
            val current = getCurrentScreen(+1)
            if (current < layouts.size) {
                slider.currentItem = current
            } else {
                onClick()
            }
        }
    }

    private fun onClick() {
        AppPrefs(this).setFirstTimeLaunch(false)
        startActivity(Intent(this, CurrencyActivity::class.java))
        finish()
    }

    private fun addBottomDots(currentPage: Int) {
        dots = arrayOfNulls(layouts.size)
        dotsLayout.removeAllViews()
        for (i in 0 until dots!!.size) {
            dots!![i] = TextView(this)
            dots!![i]?.text = Html.fromHtml("&#8226;")
            dots!![i]?.textSize = 35f
            dots!![i]?.setTextColor(resources.getColor(R.color.brand_back))
            dotsLayout.addView(dots!![i])
        }

        if (dots!!.isNotEmpty()) {
            dots!![currentPage]?.setTextColor(resources.getColor(R.color.brand))
        }
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, OnBoardActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private fun getCurrentScreen(i: Int): Int = slider.currentItem.plus(i)
}
