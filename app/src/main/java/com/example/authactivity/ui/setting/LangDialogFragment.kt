package com.example.authactivity.ui.setting

import android.app.AlertDialog
import android.app.Dialog
import android.content.res.Configuration
import android.os.Bundle
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.DialogFragment
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.example.authactivity.R
import java.util.*

class LangDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            AlertDialog.Builder(requireContext())
                    .setMessage(getString(R.string.choose))
                    .setPositiveButton((R.string.ok)) { dialog, which ->
                        if (which == 0) {
                            setLocate("en")
                            recreate()
                        } else if (which == 1) {
                            setLocate("kg")
                            recreate()
                        } else if (which == 2) {
                            setLocate("ru")
                            recreate()
                        }
                        dialog.dismiss()
                    }

                    .setNegativeButton(getString(R.string.no)) { _,_ ->
                        dismiss()
                    }
                    .create()

    private fun recreate() {

    }

    private fun setLocate(Lang: String?) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        context?.resources?.updateConfiguration(config, requireContext().resources.displayMetrics)
        val editor = getDefaultSharedPreferences(requireContext()).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
        loadLocate()
    }

    private fun loadLocate() {
        val sharedPreferences = getDefaultSharedPreferences(requireContext())
        val language = sharedPreferences.getString("My_Lang", "")
        setLocate(language)
    }

companion object {
        const val TAG = "LangDialogFragment"
    }
}