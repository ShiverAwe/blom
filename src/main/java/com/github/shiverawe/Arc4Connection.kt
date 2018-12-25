package com.github.shiverawe

import java.math.BigInteger
import kotlin.experimental.and
import kotlin.experimental.xor

class Arc4Connection(key: BigInteger) {
    private val state = ByteArray(256)

    init {
        val seed: ByteArray = key.toByteArray()
        assert(seed.isNotEmpty() && seed.size <= 256) {"Invalid ceed size: ${seed.size}"}
        for (i in 0..255) state[i] = (i and 0xFF).toByte()
        var j = 0
        for (i in 0..255) {
            j = j + state[i].toInt() + seed[i % seed.size].toInt() and 0xFF
            // swap
            state[i] = state[j].also { state[j] = state[i] }
        }
    }

    fun encrypt(plaintext: ByteArray): ByteArray {
        val cipherText = ByteArray(plaintext.size)
        var i = 0
        var j = 0
        var t: Int
        val stateCopy = state
        for (counter in plaintext.indices) {
            i = (i + 1) % 256 and 0xFF
            j = (j + stateCopy[i]) % 256 and 0xFF
            // swap
            stateCopy[i] = stateCopy[j].also { stateCopy[j] = stateCopy[i] }
            t = (stateCopy[i] + stateCopy[j]) % 256 and 0xFF
            cipherText[counter] = plaintext[counter].xor(stateCopy[t]).and(0xFF.toByte())
        }
        return cipherText
    }

    /**
     * Symmetric encryption
     */
    fun decrypt(cipherText: ByteArray): ByteArray {
        return encrypt(cipherText)
    }

}