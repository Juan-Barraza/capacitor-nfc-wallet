package com.mydigitalwallet.nfc

import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import android.util.Log

class WalletApduService : HostApduService() {

    companion object {
        private const val TAG = "WalletApduService"


        private val WALLET_AID = byteArrayOf(
            0xF0.toByte(), 0x01, 0x02, 0x03, 0x04, 0x05, 0x06
        )

        private val SELECT_APDU_HEADER = byteArrayOf(
            0x00, 0xA4.toByte(), 0x04, 0x00
        )

        // Standar response ISO 7816
        private val SW_OK                       = byteArrayOf(0x90.toByte(), 0x00)          // 200 OK
        private val SW_NOT_FOUND                = byteArrayOf(0x6A, 0x82.toByte())          // 404
        private val SW_ERROR                    = byteArrayOf(0x6F, 0x00)                   // 500
        private val SW_CONDITIONS_NOT_SATISFIED = byteArrayOf(0x69, 0x85.toByte())          // 403
    }


    override fun processCommandApdu(
        commandApdu: ByteArray,
        extras: Bundle?
    ): ByteArray {

        Log.d(TAG, "APDU: ${commandApdu.toHex()}")


        if (!NfcSessionManager.isSessionActive()) {
            Log.w(TAG, "not active session")
            return SW_CONDITIONS_NOT_SATISFIED
        }

        if (!NfcSessionManager.hasActiveCard()) {
            Log.w(TAG, "no active card")
            return SW_CONDITIONS_NOT_SATISFIED
        }


        return when {
            commandApdu.startsWith(SELECT_APDU_HEADER) -> handleSelect(commandApdu)
            commandApdu[1] == 0xB0.toByte()            -> handleReadRecord()
            else -> {
                Log.w(TAG, "no supported command")
                SW_ERROR
            }
        }
    }


    private fun handleSelect(apdu: ByteArray): ByteArray {
        return try {
            val aidLength   = apdu[4].toInt()
            val aidRequested = apdu.copyOfRange(5, 5 + aidLength)

            if (aidRequested.contentEquals(WALLET_AID)) {
                Log.d(TAG, "AID correct:  OK")
                buildSelectResponse() + SW_OK
            } else {
                Log.w(TAG, "AID is not equal: ${aidRequested.toHex()}")
                SW_NOT_FOUND
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error in handleSelect: ${e.message}")
            SW_ERROR
        }
    }

    private fun handleReadRecord(): ByteArray {
        return try {
            val last4Bytes  = NfcSessionManager.lastFour.toByteArray()
            val expiryBytes = NfcSessionManager.expiry.toByteArray()

            byteArrayOf(
                0x70,                                        // Tag: Record Template
                (last4Bytes.size + expiryBytes.size + 4).toByte(), // Length total
                0x57,                                        // Tag: last 4
                last4Bytes.size.toByte(),                    // Length
                *last4Bytes,                                 // Value: "1234"
                0x5F, 0x24.toByte(),                         // Tag: expiry
                expiryBytes.size.toByte(),                   // Length
                *expiryBytes                                 // Value: "12/27"
            ) + SW_OK
        } catch (e: Exception) {
            Log.e(TAG, "Error en handleReadRecord: ${e.message}")
            SW_ERROR
        }
    }


    private fun buildSelectResponse(): ByteArray {
        val appLabel = "MYDIGWALLET".toByteArray()
        return byteArrayOf(
            0x6F,                       // Tag: FCI Template
            (appLabel.size + 4).toByte(),
            0x50,                       // Tag: Application Label
            appLabel.size.toByte(),
            *appLabel
        )
    }

    override fun onDeactivated(reason: Int) {
        val motivo = when (reason) {
            DEACTIVATION_LINK_LOSS  -> "phone is out of range"
            DEACTIVATION_DESELECTED -> "terminal deselected"
            else                    -> "unknown reason ($reason)"
        }
        Log.d(TAG, "NFC desactivated: $motivo")

        NfcSessionManager.endSession()
    }

    // ByteArray to String hexadecimal to debug
    // Example: [0x90, 0x00] → "9000"
    private fun ByteArray.toHex() =
        joinToString("") { "%02X".format(it) }
    private fun ByteArray.startsWith(prefix: ByteArray): Boolean {
        if (size < prefix.size) return false
        return prefix.indices.all { this[it] == prefix[it] }
    }
}