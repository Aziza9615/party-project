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
import com.example.authactivity.model.ContactData
import com.example.authactivity.model.ListData
import com.example.authactivity.ui.mycontacts.category.CategoryBottomSheetFragment
import com.example.authactivity.ui.mycontacts.category.CategoryBottomSheetFragment.Companion.CATEGORY_KEY
import com.example.authactivity.ui.main.MainActivity
import com.example.authactivity.ui.mycontacts.bottomSheet.AddBottomSheetFragment
import com.example.authactivity.ui.mycontacts.bottomSheet.AddBottomSheetFragment.Companion.ITEM_KEY
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.activity_contacts.view.*
import kotlinx.android.synthetic.main.item_bottom_sheet.*
import kotlinx.android.synthetic.main.item_fragment_contacts.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ContactActivity : BaseActivity<ContactViewModel, ActivityContactsBinding>(ContactViewModel::class), ClickListener {

    override fun getViewBinding() = ActivityContactsBinding.inflate(layoutInflater)

    private lateinit var contact: ContactData

    lateinit var textView: TextView
    lateinit var button: Button


    override fun setupViews() {
        viewModel = getViewModel(clazz = ContactViewModel::class)
        PrefsHelper.instance = PrefsHelper(this)
        getIntentData()
        setupListeners()
        showEditTextDialogTwo()
        setupButton()
        //initViews()
        binding.txtAmount.text = PrefsHelper.instance.getAmount().toString()
    }

    private fun getIntentData() {
        val present = intent.getSerializableExtra(ContactsFragment.PRESENT_ITEM)
        PrefsHelper.instance.saveCategory((txt_category.text.toString()))
        val category = intent.getStringExtra(CATEGORY_KEY)
        binding.txtCategory.text = category
        PrefsHelper.instance.saveName((binding.txtName.text.toString()))
        val name = intent.getStringExtra(ITEM_KEY)
        binding.txtName.text = name
        binding.arrowBtn.setOnClickListener {
            startActivity(Intent(this@ContactActivity, MainActivity::class.java))
        }
    }

    private val loginTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val name = binding.txtName.text.toString().trim()
            val amount = binding.txtAmount.text.toString().trim()
            binding.saveBtn.isEnabled = name.isNotEmpty() && amount.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    private fun setupButton() {
        binding.txtName.addTextChangedListener(loginTextWatcher)
        binding.txtAmount.addTextChangedListener(loginTextWatcher)
        binding.saveBtn.setOnClickListener {
            startActivity(Intent(this@ContactActivity, MainActivity::class.java))
            //saveContacts()
        }
    }

//    private fun saveContacts() {
//        textView = binding.txtName
//        button = binding.saveBtn
//        button.setOnClickListener {
//            val intent = Intent(this, ContactsFragment::class.java)
//            val message = Message()
//            message.setMessage(textView.text.toString())
//            intent.putExtra("message", message)
//            startActivity(intent)
//        }
//    }

//    class Message : Serializable {
//        private var message: String? = null
//
//        override fun toString(): String {
//            return "$message"
//        }
//        fun setMessage(message: String?) {
//            this.message = message
//        }
//        companion object {
//            const val serialVersionUID = 1L
//        }
//    }


//    private fun initViews() {
//        contact = intent.getSerializableExtra(ContactsFragment.ITEM_KEY) as ContactData
//        binding.txtName.setText(contact.name)
//        //binding.txtCategory.setText(contact.category)
//        binding.txtAmount.setText(contact.amount.toString())
//    }


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
    override fun onItemClick(item: ListData) {}
    override fun onLongItemClick(item: ListData) {}
}

