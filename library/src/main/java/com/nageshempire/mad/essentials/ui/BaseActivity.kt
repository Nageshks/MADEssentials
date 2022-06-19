package com.nageshempire.mad.essentials.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseActivity<B : ViewDataBinding>(private val layout: Int) :
    AppCompatActivity(), IBaseActivity {

    private var _binding: B? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layout)
        _binding?.lifecycleOwner = this
        onBindingInflated()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        onBindingDestroyed()
    }

    override fun onBindingDestroyed() {

    }

}