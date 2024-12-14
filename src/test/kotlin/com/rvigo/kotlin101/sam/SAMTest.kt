package com.rvigo.kotlin101.sam

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class SAMTest {

    @Test
    fun `should run a callback`() {
        val payload = Payload("Hello")

        val callBack = CallBack {
            it.copy(data = it.data + " World")
        }

        val resultPayload = callBack.onResult(payload)

        assertEquals("Hello World", resultPayload.data)
        assertNotEquals(payload, resultPayload)
    }

    @Test
    fun `should run a specialized callback`() {
        val payload = Payload("Hello")

        val callBack = UppercaseCallback()

        val resultPayload = callBack.onResult(payload)

        assertEquals("HELLO", resultPayload.data)
        assertNotEquals(payload, resultPayload)
    }
}