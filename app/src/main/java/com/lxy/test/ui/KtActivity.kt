package com.lxy.test.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lxy.test.R
import kotlinx.android.synthetic.main.activity_kt.*

class KtActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kt)

        tv_top.text = "hhh"
    }
}
