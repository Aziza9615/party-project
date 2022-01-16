package com.example.authactivity.ui.mycontacts

import android.app.AlertDialog
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityContactsBinding
import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.model.ListData
import com.example.authactivity.ui.category.CategoryBottomSheetFragment
import com.example.authactivity.ui.main.MainActivity
import com.example.authactivity.ui.mycontacts.bottomSheet.AddBottomSheetFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.item_fragment_contacts.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ContactsActivity : BaseActivity<ContactsViewModel, ActivityContactsBinding>(ContactsViewModel::class), ClickListener {

    private lateinit var adapter: ContactAdapter
    private lateinit var listData: ListData
    private lateinit var save_btn: Button
    private lateinit var txt_name: TextView
    private lateinit var txt_category: TextView

    override fun getViewBinding() = ActivityContactsBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = ContactsViewModel::class)
        PrefsHelper.instance = PrefsHelper(this)
        getIntentData()
        setupListeners()
        showEditTextDialogTwo()
        setupButton()
        //initViews()
        binding.amountTextView.text = PrefsHelper.instance.getAmount().toString()
    }

    private fun getIntentData() {
        val present = intent.getSerializableExtra(ContactsFragment.PRESENT_ITEM)
        val name = intent.getStringExtra("ITEM_KEY")
        binding.txtName.text = name
        val category = intent.getStringExtra("CATEGORY_KEY")
        binding.txtCategory.text = category
        binding.arrowBtn.setOnClickListener {
            startActivity(Intent(this@ContactsActivity, MainActivity::class.java))
        }
    }

    private fun setupButton() {
        txt_name = findViewById(R.id.txt_name)
        txt_category = findViewById(R.id.txt_category)
        save_btn = findViewById(R.id.save_btn)
        txt_name.addTextChangedListener(loginTextWatcher)
        txt_category.addTextChangedListener(loginTextWatcher)
        save_btn.setOnClickListener {
            saveEdits()
            onBackPressed()
        }
    }

    private val loginTextWatcher = object :TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val txt_name = txt_name.text.toString().trim()
            val txt_category = txt_category.text.toString().trim()
            save_btn.isEnabled = txt_name.isNotEmpty() && txt_category.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable?) {}
    }

//    private fun initViews() {
//        listData = intent.getSerializableExtra(ContactsFragment.CONTACT_DETAIL) as ListData
//        txt_name.setText(listData.name)
//        txt_category.setText(listData.category)
//        amount_textView.setText(listData.amount.toString())
//    }


    private fun saveEdits() {
        val name = txt_name.text.toString()
        val category = txt_category.toString()
        val amount = amount_textView.text.toString().toInt()
        listData.name = name
        listData = ListData(listData.id, name, category, amount)
        viewModel.updateList(listData)
    }

    private fun setupListeners() {
        binding.txtCategory.setOnClickListener {
            val bottomSheetDialogFragment: BottomSheetDialogFragment = CategoryBottomSheetFragment(this)
            bottomSheetDialogFragment.isCancelable = true
            bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
        }
        binding.txtName.setOnClickListener {
            val bottomSheetDialogFragment: BottomSheetDialogFragment = AddBottomSheetFragment(this)
            bottomSheetDialogFragment.isCancelable = true
            bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
        }
    }

    private fun showEditTextDialogTwo() {
        binding.amountTextView.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.item_currency, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.dialog_text)
            with(builder) {
                setTitle("Введите сумму (${PrefsHelper.instance.getAmount()})")
                setPositiveButton("Сохранить") { dialog, which ->
                    PrefsHelper.run { instance.saveAmount(editText.text.toString().toInt()) }
                    binding.amountTextView.text = editText.text.toString()
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
    override fun onItemClick(item: ListData) {}
    override fun onLongItemClick(item: ListData) {}
}
