package com.rvigo.kotlin101.delegation.byClass

import com.rvigo.kotlin101.delegation.byClass.Teacher.Subject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class DelegationByClassTest {

    @ParameterizedTest
    @MethodSource("subjectProvider")
    fun `should return NOOP action`(person: Person?, action: Person.Action) {
        val teacher = TenuredTeacher(DEFAULT_NAME, Subject.MATH)

        val result = teacher.interact(person)

        assertEquals(action, result)
    }

    @Test
    fun `should delegate tenured teacher methods to the substitute teacher`() {
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

    companion object {
        private const val DEFAULT_NAME = "Joseph"

        @JvmStatic
        private fun subjectProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(
                null, Person.Action.NOOP
            ),
            Arguments.of(
                TenuredTeacher(DEFAULT_NAME, Subject.MATH), Person.Action.TALK
            ),
            Arguments.of(
                Student(DEFAULT_NAME), Teacher.TEACH
            )
        )
    }
}