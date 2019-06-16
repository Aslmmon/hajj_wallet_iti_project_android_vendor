package com.example.finalproject.Fragments.CashOut.StepperFragments

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.example.finalproject.Fragments.CashOut.StepperFragments.FirstFragment
import com.example.finalproject.Fragments.CashOut.StepperFragments.SecondFragment
import com.stepstone.stepper.Step
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter

class MyStepperAdapter(fm: FragmentManager, context: Context) :
        AbstractFragmentStepAdapter(fm, context) {
    override fun getCount(): Int {
        return 2
    }

    override fun createStep(position: Int): Step? {
        when (position) {
            0 -> {
                return FirstFragment()
            }
            1 -> {
                return SecondFragment()
            }
        }
        return null
    }
}