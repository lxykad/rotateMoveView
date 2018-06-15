package com.lxy.test.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lxy.test.R
import com.lxy.test.entity.Person
import com.lxy.test.entity.Student
import kotlinx.android.synthetic.main.activity_kt.*

class KtActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kt)

        var stu: Student = Student("lxy", 22)
        stu.show()

        tv_top.text = "kt1"

        var name: String? = null;
        var address: String = "haha";


        tv_top.setOnClickListener {

            name = name ?: "haha"

            // Toast.makeText(it.context,"bt",Toast.LENGTH_SHORT).show()
            //  println("length=======" + name!!.length) // 崩溃
            println("length=======" + name?.length) // null
        }


        stu.let {
            println("stu======")
        }

        var list = ArrayList<String>()
        list.add("a")
        list.add("b")

        with(list) {
            println("with====this====$this") // [a, b]
        }.let {
            println("with====it====$it") // kotlin.Unit
        }

        list.let {
            println("let=======$it")// [a, b]
        }

        list.apply {
            println("apply=====$this") // [a, b]
        }.let {
            println("apply=====$it") // [a, b]
        }

        list.run {
            println("run=====$this")// [a, b]
        }.let {
            println("run=====$it")// kotlin.Unit
        }

        var runF = run {
            "aaa"
        }

        println("run======$runF")

        list.also {
            println("also=====$it")// [a, b]
        }.let {
            println("also=====$it")// [a, b]
        }

        val takeIf = list.takeIf {

            list.size > 1
        }
        println("takeif======$takeIf") // [a, b]


        var lxh = Person("lxy", 10)
        var kad = Person("kad", 20)
        val b = lxh.equals(kad)


        println("person=======${lxh.hashCode()}")
        println("person=======${kad.hashCode()}")
        println("person=======$b")




    }
}
