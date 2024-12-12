package com.rvigo.kotlin101.delegation.byProperty

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class DelegationByPropertyTest {

    @Test
    fun `should throw an exception when the quantity validation fails`() {
        assertThrows<IllegalArgumentException> {
            Item(1, "item1")
        }
    }

    @Test
    fun `should create the item if the validation passes`() {
        assertDoesNotThrow { Item(10, "item1") }
    }
}
