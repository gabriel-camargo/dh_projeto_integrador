package com.gabrielcamargo.projetointegrador.moviedetails.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gabrielcamargo.projetointegrador.R

class SummaryFragment : Fragment() {
    private var summary: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            summary = it.getString("SUMMARY")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_summary, container, false)
        view.findViewById<TextView>(R.id.txtSummary).text = summary

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