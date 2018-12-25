package com.github.shiverawe

import java.math.BigInteger
import java.util.*

/**
 * @param size Size of matrix (size X size)
 * @param length Crypto key length
 */
class BlomKeyGenerator(
        private val size: Int,
        private val length: Int,
        private val module: BigInteger,
        private val random: Random
) {

    private var secretMatrix: Array<Array<BigInteger>>

    init {
        assert(size < 1 || length < 1) { "Invalid arguments!" }
        assert(module > BigInteger.ZERO) { "Module should be positive" }
        secretMatrix = Array(size) { Array(size) { BigInteger.ZERO } }
        genSecretMatrix()
    }

    fun generateKeys(): Pair<Array<BigInteger>, Array<BigInteger>> {
        val openKey = Array(size) { BigInteger.ZERO }
        for (i in openKey.indices) {
            openKey[i] = BigInteger(length, random).remainder(module)
        }
        val secretKey = Array(size) { BigInteger.ZERO }
        for (i in secretKey.indices) {
            secretKey[i] = BigInteger.ZERO
            for (j in secretKey.indices) {
                val buffer = secretMatrix[i][j].multiply(openKey[j])
                secretKey[i] = secretKey[i].add(buffer).remainder(module).remainder(module)
            }
        }
        return Pair(openKey, secretKey)
    }

    private fun genSecretMatrix() {
        for (i in 0 until size) {
            for (j in i until size) {
                secretMatrix[i][j] = BigInteger(length, random).remainder(module)
                secretMatrix[j][i] = secretMatrix[i][j]
            }
        }
    }
}