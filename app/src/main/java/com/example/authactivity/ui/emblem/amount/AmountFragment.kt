package com.example.authactivity.ui.emblem.amount

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.authactivity.R
import com.example.authactivity.model.CurrencyModel
import com.example.authactivity.ui.emblem.AmountAdapter
import com.example.authactivity.ui.emblem.AmountClickListener
import com.example.authactivity.ui.emblem.EmblemFragment
import com.example.authactivity.ui.emblem.viewmodel.CurrencyViewModel
import com.example.authactivity.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_amount.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class AmountFragment : Fragment(), AmountClickListener {

    lateinit var viewModel: ViewModel
    lateinit var adapter: AmountAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_amount, container, false)
        val back_btn = view.findViewById<View>(R.id.back_btn) as Button
        back_btn.setOnClickListener {
            val fragment = EmblemFragment()
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
        activity?.title = "AmountFragment"
        setupViews()
    }

    private fun setupViews() {
        viewModel = getViewModel(clazz = CurrencyViewModel::class)
        setupListener()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = AmountAdapter(this)
        amount_list.adapter = adapter
    }

    private fun setupListener() {
        btn_next_amount.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.putExtra(NEXT_ITEM, Bundle())
            startActivity(intent)
        }
    }

    companion object {
        const val NEXT_ITEM = " NEXT_ITEM"
    }

    override fun onAmountClick(item: CurrencyModel) {}

}
