package com.example.mymetals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mymetals.network.MetalViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HoldingsFragment : Fragment() {

    private val metalViewModel: MetalViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_holdings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        metalViewModel.basicCoroutineFetch()

    }

}