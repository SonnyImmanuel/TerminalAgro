package com.example.terminalagro

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MessageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_message, container, false)
    }

    companion object{
        fun newInstance() : MessageFragment{
            val fragment = MessageFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}