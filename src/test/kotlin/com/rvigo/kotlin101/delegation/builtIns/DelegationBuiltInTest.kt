package com.rvigo.kotlin101.delegation.builtIns

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DelegationBuiltInTest {

    @Test
    fun `should notify birthday event when age changes`() {
        val oldAge = 29
        val newAge = 30

        val person = Person("Joseph", oldAge)

        person.changeAge(newAge)

        assertEquals(oldAge, person.lastAge)
    }
}