package com.example.authactivity.ui.settings.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.authactivity.R
import kotlinx.android.synthetic.main.activity_bottom_sheet.*
import kotlinx.android.synthetic.main.activity_bottom_sheet.view.*

class CustomDialogFragment: DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.activity_bottom_sheet, container, false)

        rootView.cancel.setOnClickListener {
            dismiss()
        }
        rootView.no_btn.setOnClickListener {
            val selectedID = radioGroup.checkedRadioButtonId
            val radio = rootView.findViewById<RadioButton>(selectedID)
            var ratingResult = radio.text.toString()
            Toast.makeText(context, "You rated: $ratingResult", Toast.LENGTH_LONG).show()
            dismiss()
        }
        return rootView
    }
}