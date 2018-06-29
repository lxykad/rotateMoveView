package com.lxy.test.entity

class Student(var name: String, var age: Int = 22) {

    private var address: String = ""

    constructor(name: String) : this(name, 18)

    init {

    }

}