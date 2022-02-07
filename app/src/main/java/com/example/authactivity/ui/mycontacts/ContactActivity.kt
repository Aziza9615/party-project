package com.example.authactivity.ui.mycontacts

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityContactsBinding
import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.local.showToast
import com.example.authactivity.model.ContactData
import com.example.authactivity.ui.mycontacts.category.CategoryBottomSheetFragment
import com.example.authactivity.ui.mycontacts.bottomSheet.AddBottomSheetFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

interface KowumchaTextWatcher : TextWatcher { //TODO Смотрите создал отдельный интерфейс и унаследовал от TextWatch, теперь в `val fieldsTextWatcher` не обязательно реализовать все методы
    override fun afterTextChanged(s: Editable?) {}
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}

class ContactActivity : BaseActivity<ContactViewModel, ActivityContactsBinding>(ContactViewModel::class), ClickListener {

    override fun getViewBinding() = ActivityContactsBinding.inflate(layoutInflater)

    private lateinit var contactViewModel: ContactViewModel
    private lateinit var contact: ContactData

    override fun setupViews() {
        viewModel = getViewModel(clazz = ContactViewModel::class)
        PrefsHelper.instance = PrefsHelper(this)
        getIntentData()
        setupListeners()
        showEditTextDialogTwo()
//        binding.txtAmount.text = PrefsHelper.instance.getAmount().toString()//TODO удалил символ доллара он лишний, в разных валютах нужно проставлять нужный символ, если раскомментировать то код будет ломаться, если что править в строке 68
    }

    private fun getIntentData() {
//        val present = intent.getSerializableExtra(ContactsFragment.PRESENT_ITEM)
        binding.txtCategory.text = PrefsHelper.instance.getCategory()
        binding.txtName.text = PrefsHelper.instance.getName()
        binding.arrowBtn.setOnClickListener {
            PrefsHelper.instance.saveCategory("") //TODO зачем эта строка?
            PrefsHelper.instance.saveName("")//TODO зачем эта строка?
            finish()
        }
    }

    private val fieldsTextWatcher = object : KowumchaTextWatcher {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val name = binding.txtName.text.toString().trim()
            val category = binding.txtCategory.text.toString().trim()
            val amount = binding.txtAmount.text.toString().trim()
            val isConfirmToContinue = name.isNotEmpty() && category.isNotEmpty() && amount.isNotEmpty()
            binding.nextBtn.isEnabled = isConfirmToContinue
            if (!isConfirmToContinue) {
                showToast(applicationContext, "Заполните все поля!")
                insertSoldProduct(amount.toInt(), name, category)
            }
        }
    }

    private fun insertSoldProduct(amount: Int, name: String, category: String) {
        contact = ContactData(
            0,
            name,
            category,
            amount
        )
    }

    private fun setupListeners() { //TODO здесь у нас все листнеры так что объединил
        binding.txtCategory.setOnClickListener {
            val bottomSheetDialogFragment: BottomSheetDialogFragment = CategoryBottomSheetFragment() //TODO эту переменную лучше объявить внутри класса
            bottomSheetDialogFragment.isCancelable = true
            bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
        }
        binding.txtName.setOnClickListener {
            val bottomSheetDialogFragment: BottomSheetDialogFragment = AddBottomSheetFragment(this) //TODO эту переменную лучше объявить внутри класса
            bottomSheetDialogFragment.isCancelable = true
            bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
        }

        binding.txtName.addTextChangedListener(fieldsTextWatcher)
        binding.txtCategory.addTextChangedListener(fieldsTextWatcher)
        binding.txtAmount.addTextChangedListener(fieldsTextWatcher)
        binding.nextBtn.setOnClickListener {
            //contactViewModel.insertContact(contact)//TODO из-за этого КРЕШИТ!!! ответ в ContactRepositoryImpl
        }
    }

    private fun showEditTextDialogTwo() {
        binding.txtAmount.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.item_currency, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.dialog_text)
            with(builder) {
                setTitle("Введите сумму (${PrefsHelper.instance.getAmount()})") //TODO все строки нужно добавлять в ресурсы
                setPositiveButton("Сохранить") { dialog, which ->
                    PrefsHelper.run { instance.saveAmount(editText.text.toString().toInt()) }
                    binding.txtAmount.text = editText.text.toString()
                }
                setNegativeButton("Отмена") { dialog, which ->
                    Log.d("Main", "Negative Button Click")
                }
                setView(dialogLayout)
                show()
            }
        }
    }

    override fun subscribeToLiveData() {}
    override fun onItemClick(item: ContactData) {}
    override fun onLongItemClick(item: ContactData) {}
}

