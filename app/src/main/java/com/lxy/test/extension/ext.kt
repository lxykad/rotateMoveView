package com.lxy.test.extension

import android.content.Context
import android.widget.Toast

/**
 * 扩展函数
 */
fun Context.toast(msg: String, type: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, type).show()
}