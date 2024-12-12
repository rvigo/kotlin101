package com.rvigo.kotlin101.delegation.byClass

import com.rvigo.kotlin101.delegation.byClass.Person.Action
import com.rvigo.kotlin101.delegation.byClass.Teacher.Subject

interface Person {
    val name: String

    fun interact(other: Person? = null): Action = other?.let { Action.TALK } ?: Action.NOOP

    interface Action {
        object NOOP : Action
        object TALK : Action
    }
}

interface Teacher : Person {
    val subject: Subject

    // custom action
    object TEACH : Action

    override fun interact(other: Person?): Action = when (other) {
        is Teacher -> Action.TALK
        is Student -> TEACH.also { println("$name is teaching $subject to student ${other.name}") }
        else -> Action.NOOP
    }

    enum class Subject {
        MATH, SCIENCE
    }
}

class DefaultTeacher(override val name: String, override val subject: Subject) : Teacher

class TenuredTeacher(override val name: String, override val subject: Subject) :
    Teacher by DefaultTeacher(name, subject)

class SubstituteTeacher(override val name: String, teacher: Teacher) : Teacher by teacher

class RegularPerson(override val name: String) : Person

class Student(override val name: String) : Person by RegularPerson(name)
