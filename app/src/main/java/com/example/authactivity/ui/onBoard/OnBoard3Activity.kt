package com.example.authactivity.ui.onBoard

import android.app.Activity
import android.content.Intent
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.databinding.ActivivtyOnboard3Binding
import com.example.authactivity.ui.lang.LangActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class OnBoard3ViewModel : BaseViewModel<BaseEvent>()
class OnBoard3Activity : BaseActivity<OnBoard3ViewModel, ActivivtyOnboard3Binding>(OnBoard3ViewModel::class) {

    override fun getViewBinding() = ActivivtyOnboard3Binding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = OnBoard3ViewModel::class)
        setupListener()
    }

    private fun setupListener() {
        binding.arrow.setOnClickListener {
            startActivity(Intent(this@OnBoard3Activity, OnBoard2Activity::class.java))
        }
            binding.furtherBtn3.setOnClickListener {
                startActivity(Intent(this@OnBoard3Activity,OnBoard3Activity::class.java))
        }
    }

    override fun subscribeToLiveData() {

    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, OnBoard3Activity::class.java)
            activity.startActivity(intent)
        }
    }
}