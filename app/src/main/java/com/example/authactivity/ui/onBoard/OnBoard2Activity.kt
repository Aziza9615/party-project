package com.example.authactivity.ui.onBoard

import android.app.Activity
import android.content.Intent
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.databinding.ActivityOnboard2Binding
import com.example.authactivity.databinding.ActivityOnboardBinding
import com.example.authactivity.ui.lang.LangActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class OnBoard2ViewModel : BaseViewModel<BaseEvent>()
class OnBoard2Activity : BaseActivity<OnBoard2ViewModel, ActivityOnboard2Binding>(OnBoard2ViewModel::class) {

    override fun getViewBinding() = ActivityOnboard2Binding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = OnBoard2ViewModel::class)
        setupListener()
    }

    private fun setupListener() {
        binding.arrow.setOnClickListener {
            startActivity(Intent(this@OnBoard2Activity, OnBoardActivity::class.java))
        }
            binding.furtherBtn2.setOnClickListener {
                startActivity(Intent(this@OnBoard2Activity,OnBoard3Activity::class.java))
        }
    }

    override fun subscribeToLiveData() {

    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, OnBoard2Activity::class.java)
            activity.startActivity(intent)
        }
    }
}