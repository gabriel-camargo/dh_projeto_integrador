package com.gabrielcamargo.projetointegrador.moviedetails.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gabrielcamargo.projetointegrador.R
import com.gabrielcamargo.projetointegrador.moviedetails.repository.SummaryRepository
import com.gabrielcamargo.projetointegrador.moviedetails.viewModel.SummaryViewModel

private const val ARG_PARAM1 = "OVERVIEW"

class SummaryFragment : Fragment() {
    lateinit var _view: View
    private lateinit var _viewModel: SummaryViewModel

    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_summary, container, false)

        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        _viewModel = ViewModelProvider(
//                this,
//                SummaryViewModel.SummaryViewModelFactory(SummaryRepository(_view.context))
//        ).get(SummaryViewModel::class.java)
//
//        _viewModel.summary.observe(viewLifecycleOwner, Observer {
//            val summary = _view.findViewById<TextView>(R.id.txtSummary)
//            summary.text = it
//        })

//        _viewModel.getSummary()

            val summary = _view.findViewById<TextView>(R.id.txtSummary)
            summary.text = param1
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            SummaryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }


}