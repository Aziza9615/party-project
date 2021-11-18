package com.example.authactivity.ui.onBoard

import android.content.ClipDescription
import android.content.Intent
import android.icu.text.CaseMap
import android.widget.Button
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.databinding.ActivityOnboardBinding
import com.example.authactivity.ui.lang.LangActivity
import com.example.authactivity.ui.lang.LangViewModel
import kotlinx.android.synthetic.main.activity_onboard.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class OnBoardViewModel : BaseViewModel<BaseEvent>()
class OnBoardActivity : BaseActivity<OnBoardViewModel, ActivityOnboardBinding>(OnBoardViewModel::class) {

    override fun getViewBinding() = ActivityOnboardBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = OnBoardViewModel::class)
        setupListener()
    }

    private fun setupListener() {
        binding.arrow.setOnClickListener {
            startActivity(Intent(this@OnBoardActivity, LangActivity::class.java))
        }
            binding.goBtn.setOnClickListener {
                startActivity(Intent(this@OnBoardActivity,OnBoard2Activity::class.java))
        }
    }

    override fun subscribeToLiveData() {
    }

}