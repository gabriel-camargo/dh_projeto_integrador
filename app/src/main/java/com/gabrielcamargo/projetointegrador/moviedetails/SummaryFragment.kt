package com.gabrielcamargo.projetointegrador.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gabrielcamargo.projetointegrador.R

class SummaryFragment : Fragment() {
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString("SUMMARY")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_summary, container, false)
        view.findViewById<TextView>(R.id.txtSummary).text = param1

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(summary: String) =
            SummaryFragment().apply {
                arguments = Bundle().apply {
                    putString("SUMMARY", summary)
                }
            }
    }
}