package com.rvigo.kotlin101.delegation.builtIns

import kotlin.properties.Delegates

class Person(private val name: String, age: Int) {
    private var age: Int by Delegates.observable(age) { _, oldAge, newAge ->
        println("$name is celebrating their birthday! (From $oldAge to $newAge)")
        lastAge = oldAge
    }

    var lastAge: Int = age

    fun changeAge(newAge: Int) {
        age = newAge
    }
}