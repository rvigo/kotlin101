package com.rvigo.kotlin101.sam

data class Payload(val data: String)

fun interface CallBack {
    fun onResult(result: Payload): Payload
}

// SAM interfaces can also be implemented
class UppercaseCallback : CallBack {
    override fun onResult(result: Payload): Payload {
        return result.copy(data = result.data.uppercase())
    }
}