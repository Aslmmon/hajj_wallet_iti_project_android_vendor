package com.example.finalproject.Fragments.CashOut

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

import com.example.finalproject.R

class CashOutFragment : Fragment() {

    companion object {
        fun newInstance() = CashOutFragment()
    }

    private lateinit var viewModel: CashOutViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.cash_out)
        return inflater.inflate(R.layout.cash_out_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CashOutViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
