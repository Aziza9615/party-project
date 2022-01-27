package com.example.authactivity.ui.mycontacts

import android.app.AlertDialog
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayoutStates
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityContactsBinding
import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.model.ContactData
import com.example.authactivity.ui.category.CategoryBottomSheetFragment
import com.example.authactivity.ui.category.CategoryBottomSheetFragment.Companion.CATEGORY_KEY
import com.example.authactivity.ui.main.MainActivity
import com.example.authactivity.ui.mycontacts.bottomSheet.AddBottomSheetFragment
import com.example.authactivity.ui.mycontacts.bottomSheet.AddBottomSheetFragment.Companion.ITEM_KEY
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.item_fragment_contacts.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ContactsActivity : BaseActivity<ContactViewModel, ActivityContactsBinding>(ContactViewModel::class), ClickListener {

    private lateinit var contact: ContactData
    private lateinit var save_btn: Button
    private lateinit var txt_name: TextView
    private lateinit var txt_category: TextView
    private lateinit var txt_amount: TextView

    override fun getViewBinding() = ActivityContactsBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = ContactViewModel::class)
        PrefsHelper.instance = PrefsHelper(this)
        getIntentData()
        setupListeners()
        showEditTextDialogTwo()
        setupButton()
        binding.txtAmount.text = PrefsHelper.instance.getAmount().toString()
        binding.txtName.text =  PrefsHelper.instance.getName().toString()
    }

    private fun getIntentData() {
        val present = intent.getSerializableExtra(ContactsFragment.PRESENT_ITEM)
        val category = intent.getStringExtra(CATEGORY_KEY)
        binding.txtCategory.text = category
        val name = intent.getStringExtra(ITEM_KEY)
        binding.txtName.text = name
        val textView = findViewById<TextView>(R.id.txt_name)
        PrefsHelper.run { instance.saveName(textView.text.toString()) }
        binding.txtName.text = textView.text.toString()
        binding.arrowBtn.setOnClickListener {
            startActivity(Intent(this@ContactsActivity, MainActivity::class.java))
        }
    }

    private fun setupButton() {
        txt_name = findViewById(R.id.txt_name)
        txt_amount = findViewById(R.id.txt_amount)
        save_btn = findViewById(R.id.save_btn)
        txt_name.addTextChangedListener(loginTextWatcher)
        txt_amount.addTextChangedListener(loginTextWatcher)
        save_btn.setOnClickListener {
            saveEdits()
        }
    }

    private fun saveEdits() {
        val newName = txt_name.text.toString()
        val amount = txt_amount.text.toString().toInt()
        contact.name = newName
        contact = ContactData(contact.id, newName, amount)
        viewModel.updateContact(contact)
    }

        private val loginTextWatcher = object :TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val txt_name = txt_name.text.toString().trim()
            val txt_amount = txt_amount.text.toString().trim()
            save_btn.isEnabled = txt_name.isNotEmpty() && txt_amount.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    private fun setupListeners() {
        binding.txtCategory.setOnClickListener {
            val bottomSheetDialogFragment: BottomSheetDialogFragment = CategoryBottomSheetFragment(
                this)
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
        binding.txtAmount.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.item_currency, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.dialog_text)
            with(builder) {
                setTitle("Введите сумму (${PrefsHelper.instance.getAmount()})")
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

