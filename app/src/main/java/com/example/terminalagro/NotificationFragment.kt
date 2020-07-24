package com.example.terminalagro

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class NotificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    companion object{
        fun newInstance() : NotificationFragment{
            val fragment = NotificationFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}