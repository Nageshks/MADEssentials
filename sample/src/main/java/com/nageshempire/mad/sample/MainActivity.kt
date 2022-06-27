package com.nageshempire.mad.sample

import android.webkit.MimeTypeMap
import com.nageshempire.mad.essentials.ui.BaseActivity
import com.nageshempire.nextplayer.mad.sample.R
import com.nageshempire.nextplayer.mad.sample.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {
    override fun onBindingInflated() {
        MimeTypeMap.getSingleton().getMimeTypeFromExtension("apk")
    }
}