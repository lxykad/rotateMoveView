package com.lxy.test.entity

class Student(name: String) {

    private var address: String = ""

    constructor(name: String, age: Int) : this(name)

    init {
      //  println("=============student====$name")
    }

    fun show() {
      //  println("11111=======$address")
    }

}