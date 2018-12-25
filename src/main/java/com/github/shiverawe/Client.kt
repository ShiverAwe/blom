package com.github.shiverawe

import java.math.BigInteger

class Client(
        val name: String,
        val openKey: Array<BigInteger>,
        private val secretKey: Array<BigInteger>,
        private val module: BigInteger
) {

    private val connections: MutableMap<Client, Arc4Connection> = HashMap()

    /**
     * Used to handshake clients
     * Calculates key and sets up the connection between clients
     */
    private fun greet(partner: Client, module: BigInteger) {
        assert(this.secretKey.size == partner.openKey.size) { "Size of keys are not equal" }
        var sessionKey = BigInteger.ZERO
        for (i in secretKey.indices) {
            val element = secretKey[i].multiply(partner.openKey[i]).remainder(module)
            sessionKey = sessionKey.add(element).remainder(module)
        }
        connections[partner] = Arc4Connection(sessionKey)
    }

    /**
     * Sends message
     * If receiver is unknown, performs handshake
     */
    fun sendMessage(message: String, receiver: Client) {
        if (connections[receiver] == null) {
            println("$name : Sending message : Unknown receiver : ${receiver.name} : Creating encryption key ...")
            greet(receiver, module)
        }
        val messageEncrypted = connections[receiver]!!.encrypt(message.toByteArray())
        println("$name : Sending message : Text : $message")
        println("$name : Sending message : Encrypted message : ${messageEncrypted.asList()}")
        receiver.receiveMessage(messageEncrypted, this)
    }

    /**
     * Receives message
     * If sender is unknown, performs handshake
     */
    private fun receiveMessage(messageEncrypted: ByteArray, author: Client) {
        if (connections[author] == null) {
            println("$name : Receiving message : Unknown author : ${author.name} : Creating encryption key ...")
            greet(author, module)
        }
        val messageDecrypted = String(connections[author]!!.decrypt(messageEncrypted))
        println("$name : Receiving message : Data : ${messageEncrypted.toList()}")
        println("$name : Receiving message : Decrypted text : $messageDecrypted")
    }

}
