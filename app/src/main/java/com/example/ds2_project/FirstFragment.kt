package com.example.ds2_project

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FirstFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView =inflater.inflate(R.layout.fragment_first, container, false)

        // Inflate the layout for this fragment
        return rootView
    }

    fun start(view: View) {
        var intent1= Intent(getActivity(),MainActivity::class.java)
        startActivity(intent1)

    }
    fun about(view: View) {
        var intent2= Intent(getActivity(),AboutActivity::class.java)
        startActivity(intent2)
    }
    fun exit(view: View) {
        getActivity()!!.finish()
    }
}