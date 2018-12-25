package com.github.shiverawe
import java.math.BigInteger
import java.util.*

fun main(args: Array<String>) {
    val context = EncryptionContext(power = 1024)

    val alice = context.createClient("Alice")
    val bob = context.createClient("Bob")
    val charlie = context.createClient("Charlie")

    alice.sendMessage("Hi bob, it's my first message", bob)
    bob.sendMessage("Hi Alice, nice to meet you", alice)
    bob.sendMessage("Hi, Charlie, where are you?", charlie)
}

fun randomPrimeNumber(length: Int, random: Random): BigInteger {
    while (true) {
        val primeNumber = BigInteger(length, random)
        if (primeNumber.isProbablePrime(100)) {
            return primeNumber
        }
    }
}
