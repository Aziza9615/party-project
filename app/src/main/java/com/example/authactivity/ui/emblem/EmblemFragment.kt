package com.example.authactivity.ui.emblem
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat.recreate
import androidx.lifecycle.ViewModel
import com.example.authactivity.R
import com.example.authactivity.ui.emblem.amount.AmountFragment
import com.example.authactivity.ui.emblem.viewmodel.CurrencyViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*

class EmblemFragment : Fragment() {

    lateinit var mBtn: Button
    lateinit var viewModel: ViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_emblem, container, false)
        val btn = view.findViewById<View>(R.id.btn_next) as Button

        btn.setOnClickListener {
            val fragment = AmountFragment()
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, fragment)
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "EmblemFragment"
        setupViews()
    }

    private fun setupViews() {
        viewModel = getViewModel(clazz = CurrencyViewModel::class)
        mBtn = requireView().findViewById(R.id.language)
        mBtn.setOnClickListener {
            //showChangeLang()
        }
    }
}
//
//    private fun showChangeLang() {
//        val listItems = arrayOf("English", "Кыргызский", "Русский")
//
//        val mBuilder = AlertDialog.Builder(requireContext(), this@EmblemFragment)
//        mBuilder.setTitle("Choose Language")
//        mBuilder.setSingleChoiceItems(listItems, -1) { dialog, which ->
//            if (which == 0) {
//                setLocate("en")
//                recreate()
//
//            } else if (which == 1) {
//                setLocate("ky")
//                recreate()
//
//            } else if (which == 2) {
//                setLocate("ru")
//                recreate()
//            }
//
//            dialog.dismiss()
//        }
//
//        val mDialog = mBuilder.create()
//        mDialog.show()
//    }
//
//    private fun setLocate(Lang: String?) {
//        val locale = Locale(Lang)
//        Locale.setDefault(locale)
//        val config = Configuration()
//        config.locale = locale
//        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
//
//        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
//        editor.putString("My_Lang", Lang)
//        editor.apply()
//    }
//
//    private fun loadLocate() {
//        val SharedPreferences =  getSharedPreferences("Settings", Activity.MODE_PRIVATE)
//        val language = SharedPreferences.getString("My_Lang", "")
//        setLocate(language)
//    }
//
//    companion object {
//        const val NEXT_ITEM = "RESUME_ITEM"
//    }
//}