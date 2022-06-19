package com.nageshempire.mad.essentials.ui

import android.view.Menu

interface IBaseFragment {
    fun onLayoutInflated()
    fun setupView()
    fun setupObservables()
    fun onMenuInflated(menu: Menu)
}