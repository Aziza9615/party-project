package com.example.authactivity.ui.mycontacts

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityContactsBinding
import com.example.authactivity.ui.AddBottomSheetFragment
import com.example.authactivity.ui.onBoard.OnBoardViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_contacts.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ContactsActivity : BaseActivity<OnBoardViewModel, ActivityContactsBinding>(OnBoardViewModel::class) {

    override fun getViewBinding() = ActivityContactsBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = OnBoardViewModel::class)
        getIntentData()
        setupListener()
    }

    private fun getIntentData() {
        val present = intent.getSerializableExtra(ContactsFragment.PRESENT_ITEM)
        binding.arrowBtn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupListener() {
        binding.spinner.onItemClickListener
        val bottomSheetDialogFragment: BottomSheetDialogFragment =
            AddBottomSheetFragment()
        bottomSheetDialogFragment.isCancelable = true
        bottomSheetDialogFragment.show(
            supportFragmentManager,
            bottomSheetDialogFragment.tag)
    }

    override fun subscribeToLiveData() {
    }
}