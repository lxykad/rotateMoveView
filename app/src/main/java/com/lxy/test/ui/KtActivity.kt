package com.lxy.test.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.lxy.test.R
import com.lxy.test.entity.Student
import kotlinx.android.synthetic.main.activity_kt.*

class KtActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kt)

        var stu: Student = Student("lxy", 22)
        stu.show()

        tv_top.text = "kt1"

        //  var ktView: KtView = KtView(this)

        var name: String? = null;
        var address: String = "haha";




        tv_top.setOnClickListener {

            name = name ?: "haha"

            // Toast.makeText(it.context,"bt",Toast.LENGTH_SHORT).show()
            //  println("length=======" + name!!.length) // 崩溃
            println("length=======" + name?.length) // null
        }
    }
}
