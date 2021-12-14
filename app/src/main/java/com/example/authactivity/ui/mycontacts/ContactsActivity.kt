package com.example.authactivity.ui.mycontacts

import android.R
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityContactsBinding
import com.example.authactivity.ui.onBoard.OnBoardViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_contacts.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ContactsActivity : BaseActivity<OnBoardViewModel, ActivityContactsBinding>(OnBoardViewModel::class) {

    private var nameList = mutableListOf<String>("Выберите")
    override fun getViewBinding() = ActivityContactsBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = OnBoardViewModel::class)
        getIntentData()
        setupListener()
        setupListCategory()
    }

    private fun getIntentData() {
        val present = intent.getSerializableExtra(ContactsFragment.PRESENT_ITEM)
        binding.arrowBtn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupListener() {
        val adapter = ArrayAdapter(this@ContactsActivity, R.layout.simple_spinner_item, nameList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
        val bottomSheetDialogFragment: BottomSheetDialogFragment =
            AddBottomSheetFragment()
        bottomSheetDialogFragment.isCancelable = true
        bottomSheetDialogFragment.show(
            supportFragmentManager,
            bottomSheetDialogFragment.tag
        )
    }

    private fun setupListCategory() {
        val adapter = ArrayAdapter(this@ContactsActivity, R.layout.simple_spinner_item, nameList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                    val bottomSheetDialogFragment: BottomSheetDialogFragment =
                        AddBottomSheetFragment()
                    bottomSheetDialogFragment.isCancelable = true
                    bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
                }

                override fun onNothingSelected(parent: AdapterView<out Adapter>?) {
                }
            }
    }

    override fun subscribeToLiveData() {
    }
}