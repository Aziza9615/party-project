package com.example.authactivity.ui.activity

import android.app.Activity
import android.content.Intent
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityAmountBinding
import com.example.authactivity.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class AmountActivity : BaseActivity<AmountViewModel, ActivityAmountBinding>(AmountViewModel::class) {

    override fun getViewBinding() = ActivityAmountBinding.inflate(layoutInflater)

    override fun setupViews() {
        setupListener()
        CreateAdd()
    }

    private fun setupListener() {
        binding.btnNextAmount.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.backBtn.setOnClickListener {
            val intent = Intent(this, EmblemActivity::class.java)
            startActivity(intent)
        }
    }

    private fun CreateAdd() {
        binding.addBtn.setOnClickListener {
            val amount = binding.amountEv.text.toString()
            val price = binding.currency.text.toString()
            if (amount.isNotEmpty() && price.isNotEmpty()) {
                viewModel.CreateAdd(
                        amount = amount, price = price.toDouble()
                )
                }
            }
        }

    override fun subscribeToLiveData() {

    }

    companion object {
        fun intent(activity: Activity) {
            val intent = Intent(activity, AmountActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
