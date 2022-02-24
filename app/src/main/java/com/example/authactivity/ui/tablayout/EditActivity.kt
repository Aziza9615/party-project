package com.example.authactivity.ui.tablayout

import android.app.AlertDialog
import android.util.Log
import android.widget.EditText
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityEditBinding
import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.model.AcceptData
import com.example.authactivity.model.ContactData
import com.example.authactivity.ui.tablayout.TabActivity.Companion.edit_detail
import com.example.authactivity.ui.tablayout.fragment.AcceptViewModel
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_edit.txt_name
import org.koin.androidx.viewmodel.ext.android.getViewModel

class EditActivity : BaseActivity<AcceptViewModel, ActivityEditBinding>(AcceptViewModel::class) {

    override fun getViewBinding() = ActivityEditBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = AcceptViewModel::class)
        PrefsHelper.instance = PrefsHelper(this)
        getIntentData()
        setupListeners()
        showEditTextDialogTwo()
        initViews()
        binding.txtAmount.text = PrefsHelper.instance.getAmount().toString()
        binding.txtName.text = PrefsHelper.instance.getName().toString()
        binding.txtCategory.text = PrefsHelper.instance.getCategory().toString()
    }

    private fun initViews() {
//        val contact = intent.getSerializableExtra(edit_detail) as ContactData
//        binding.txtName.text = contact.name
    }

    private fun getIntentData() {
        binding.txtCategory.text = PrefsHelper.instance.getCategory()
        binding.txtName.text = PrefsHelper.instance.getName()
        binding.txtAmount.text = PrefsHelper.instance.getAmount().toString()
        binding.arrowBtn.setOnClickListener {
            PrefsHelper.instance.saveCategory("")
            PrefsHelper.instance.saveName("")
            PrefsHelper.instance.saveAmount(0)
                onBackPressed()
            }
        }

    private fun setupListeners() {
        PrefsHelper.run { instance.saveName(txt_name.text.toString()) }
        binding.txtName.text = PrefsHelper.instance.getName().toString()
        binding.nextBtn.setOnClickListener {
            saveContacts()
            onBackPressed()
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

    private fun saveContacts() {
        val id = PrefsHelper.instance.getNameId()
        val name = PrefsHelper.instance.getName()
        val category = PrefsHelper.instance.getCategory()
        val amount = PrefsHelper.instance.getAmount().toString().toInt()

        val accept = AcceptData(id!!, name!!, category!!, amount)

        viewModel.updateAccept(accept)

        PrefsHelper.instance.saveCategory(category)
        PrefsHelper.instance.saveName(name!!)
        PrefsHelper.instance.saveAmount(0)
        PrefsHelper.instance.saveNameId(0)
    }

    override fun subscribeToLiveData() {}
}