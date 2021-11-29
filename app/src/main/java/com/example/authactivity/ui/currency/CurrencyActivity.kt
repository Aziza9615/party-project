package com.example.authactivity.ui.currency

import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityCurrencyBinding
import com.example.authactivity.model.LangData
import com.example.authactivity.ui.main.MainActivity
import com.example.authactivity.ui.onBoard.OnBoardActivity
import kotlinx.android.synthetic.main.activity_currency.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class CurrencyActivity : BaseActivity<CurrencyViewModel, ActivityCurrencyBinding>(CurrencyViewModel::class) {

    override fun getViewBinding() = ActivityCurrencyBinding.inflate(layoutInflater)

    private lateinit var lang: LangData

    override fun setupViews() {
        viewModel = getViewModel(clazz = CurrencyViewModel::class)
        setupRadio()
        onClick()
        showEditTextDialog()
        showEditTextDialogTwo()
    }

    private fun setupRadio() {
        binding.radio.setOnCheckedChangeListener { group, checkedId ->
            var rb = findViewById<RadioButton>(checkedId)
            Toast.makeText(
                applicationContext, " You have chosen a currency :" + " ${rb.text}",
                Toast.LENGTH_SHORT
            ).show()
        }
        fun radio_button_click(view: View){
            val radio: RadioButton = findViewById(radio.checkedRadioButtonId)
            Toast.makeText(applicationContext,"On click : ${radio.text}",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun onClick() {
        binding.arrowBtn.setOnClickListener {
            val intent = Intent(this, OnBoardActivity::class.java)
            startActivity(intent)
        }
        binding.nextBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showEditTextDialog() {
        binding.amountTxtview.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.item_currency, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.dialog_text)

            with(builder) {
                setTitle("Введите вашу заплату(\$)")
                setPositiveButton("Сохранить"){dialog, which ->
                    binding.amountTxtview.text = editText.text.toString()
                }
                setNegativeButton("Отмена"){dialog, which ->
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
                setTitle("Введите вашу заплату(\$)")
                setPositiveButton("Сохранить"){dialog, which ->
                    binding.incomeTextView.text = editText.text.toString()
                }
                setNegativeButton("Отмена"){dialog, which ->
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