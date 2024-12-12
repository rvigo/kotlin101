package com.rvigo.kotlin101.delegation.byClass

import com.rvigo.kotlin101.delegation.byClass.Teacher.Subject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class DelegationByClassTest {

    companion object {
        const val DEFAULT_NAME = "Joseph"
    }

    @Test
    fun `should return NOOP action`() {
        val subject = Subject.MATH
        val teacher = TenuredTeacher(DEFAULT_NAME, subject)

        val result = teacher.interact()

        assertEquals(Person.Action.NOOP, result)
    }

    @Test
    fun `should return TALK action`() {
        val subject = Subject.MATH
        val teacher = TenuredTeacher(DEFAULT_NAME, subject)
        val anotherTeacher = TenuredTeacher(DEFAULT_NAME, Subject.SCIENCE)

        val result = teacher.interact(anotherTeacher)

        assertEquals(Person.Action.TALK, result)
    }

    @Test
    fun `should return TEACH action`() {
        val subject = Subject.MATH
        val teacher = TenuredTeacher(DEFAULT_NAME, subject)
        val student = Student(DEFAULT_NAME)

        val result = teacher.interact(student)

        assertEquals(Teacher.TEACH, result)
    }

    @Test
    fun `should do something`() {
        val subject = Subject.MATH
        val teacher = TenuredTeacher(DEFAULT_NAME, subject)
        val substituteTeacherName = "Mary"
        val substituteTeacher = SubstituteTeacher(substituteTeacherName, teacher)
        val student = Student(DEFAULT_NAME)

        substituteTeacher.interact(student)

        assertEquals(substituteTeacherName, substituteTeacher.name)
        assertEquals(teacher.subject, substituteTeacher.subject)
        assertNotEquals(teacher.name, substituteTeacher.name)
    }
}