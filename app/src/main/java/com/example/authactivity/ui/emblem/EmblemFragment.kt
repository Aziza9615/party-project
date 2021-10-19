package com.example.authactivity.ui.emblem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.lifecycle.ViewModel
import com.example.authactivity.R
import com.example.authactivity.databinding.FragmentEmblemBinding
import com.example.authactivity.ui.emblem.amount.AmountFragment
import com.example.authactivity.ui.emblem.viewmodel.CurrencyViewModel
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.getViewModel

class EmblemFragment : Fragment() {

    lateinit var viewModel: ViewModel
    //private var binding: FragmentEmblemBinding? = null

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_emblem, container, false)
        val btn = view.findViewById<View>(R.id.btn_next) as Button
//        val language = resources.getStringArray(R.array.language)
//        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.dropdrown_item, language)

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
        }
    }
