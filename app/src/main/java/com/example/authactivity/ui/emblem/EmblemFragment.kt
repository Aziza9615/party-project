package com.example.authactivity.ui.emblem
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModel
import com.example.authactivity.R
import com.example.authactivity.databinding.FragmentAmountBinding
import com.example.authactivity.databinding.FragmentEmblemBinding
import com.example.authactivity.ui.emblem.viewmodel.CurrencyViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class EmblemFragment : Fragment() {

    lateinit var mBtn: Button
    lateinit var viewModel: ViewModel
    lateinit var binding: FragmentEmblemBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
//        binding = FragmentEmblemBinding.inflate(inflater)
//        return binding.root
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
        }
    }

    companion object {
        const val NEXT_ITEM = "RESUME_ITEM"
    }
}