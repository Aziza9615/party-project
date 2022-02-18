package com.example.authactivity.ui.setting

import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityCurrencyBinding
import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.ui.main.MainActivity
import com.example.authactivity.ui.onBoard.OnBoardViewModel
import kotlinx.android.synthetic.main.activity_currency.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class CurrencySettingsActivity : BaseActivity<OnBoardViewModel, ActivityCurrencyBinding>(OnBoardViewModel::class) {

    override fun getViewBinding() = ActivityCurrencyBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = OnBoardViewModel::class)
        PrefsHelper.instance = PrefsHelper(this)
        setupRadio()
        onClick()
        showEditTextDialog()
        showEditTextDialogTwo()
        binding.amountTextView.text = PrefsHelper.instance.getSalary().toString()
        binding.incomeTextView.text = PrefsHelper.instance.getIncome().toString()
    }

    private fun setupRadio() {
        binding.radio.setOnCheckedChangeListener { group, checkedId ->
            var rb = findViewById<RadioButton>(checkedId)
            PrefsHelper.instance.saveCurrency(getCurrencyFromRadioButton(rb.id.toString()))
            Toast.makeText(
                applicationContext, "You have chosen a currency : ${PrefsHelper.instance.getCurrency()}",
                Toast.LENGTH_SHORT
            ).show()
        }
        fun radio_button_click(view: View) {
            val radio: RadioButton = findViewById(radio.checkedRadioButtonId)
            Toast.makeText(applicationContext, "On click : ${radio.text}",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun getCurrencyFromRadioButton(id: String): String {
        return when (id) {
            "2131296358" -> "$"
            "2131296359" -> "c"
            "2131296360" -> "₽"
            "2131296361" -> "€"
            else -> "$"
        }
    }

    private fun onClick() {
        binding.arrowBtn.setOnClickListener {
            onBackPressed()
        }
        binding.nextBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showEditTextDialog() {
        binding.amountTextView.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.item_currency, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.dialog_text)

            with(builder) {
                setTitle("Введите вашу заплату(${PrefsHelper.instance.getCurrency()})")
                setPositiveButton("Сохранить") { dialog, which ->
                    PrefsHelper.instance.saveSalary(editText.text.toString().toInt())
                    binding.amountTextView.text = PrefsHelper.instance.getSalary().toString()
                }
                setNegativeButton("Отмена") { dialog, which ->
                    Log.d("Main", "Negative Button Click")
                }
                setView(dialogLayout)
                show()
            }
        }
    }

    private fun showEditTextDialogTwo() {
        binding.incomeTextView.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.item_currency, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.dialog_text)
            with(builder) {
                setTitle("Другие источники дохода (${PrefsHelper.instance.getCurrency()})")
                setPositiveButton("Сохранить") { dialog, which ->
                    PrefsHelper.instance.saveIncome(editText.text.toString().toInt())
                    binding.incomeTextView.text = editText.text.toString()
                }
                setNegativeButton("Отмена") { dialog, which ->
                    Log.d("Main", "Negative Button Click")
                }
                setView(dialogLayout)
                show()
            }
        }
    }

    override fun subscribeToLiveData() {
    }
}