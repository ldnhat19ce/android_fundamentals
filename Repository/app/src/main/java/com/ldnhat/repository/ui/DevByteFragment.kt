package com.ldnhat.repository.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ldnhat.repository.R
import com.ldnhat.repository.databinding.FragmentDevByteBinding

class DevByteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding:FragmentDevByteBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dev_byte, container, false)

        return binding.root
    }
}