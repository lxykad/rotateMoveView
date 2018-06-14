package com.lxy.test.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lxy.test.MainActivity
import com.lxy.test.R
import kotlinx.android.synthetic.main.activity_kt2.*

class Kt2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kt2)

        bt.text = "kt2"

        bt.setOnClickListener(View.OnClickListener {
            val intent = Intent(it.context, MainActivity::class.java)
            startActivity(intent)
        })

    }
}
