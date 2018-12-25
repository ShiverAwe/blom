package com.github.shiverawe

import java.util.*

class EncryptionContext(power: Int) {
    private val random = Random()
    private val module = randomPrimeNumber(power, random)
    private val blom = BlomKeyGenerator(size = 8, length = power, module = module, random = random)

    fun createClient(name: String): Client {
        val keys = blom.generateKeys()
        return Client(name = name, openKey = keys.first, secretKey = keys.second, module = module)
    }
}