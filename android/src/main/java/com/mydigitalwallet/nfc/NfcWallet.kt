package com.mydigitalwallet.nfc;

import android.content.Context
import android.nfc.NfcAdapter


class NfcWallet(private val context: Context) {

    fun isNfcSupported(): Boolean {
       return NfcAdapter.getDefaultAdapter(context) != null
    }

    fun setActiveCard(): Boolean {
        val adapter = NfcAdapter.getDefaultAdapter(context)
        return adapter.isEnabled
    }



    fun validateCardData(lastFour: String, expiry: String, cardId: String): String? {

        if (lastFour.length != 4) {
            return "lastFour should have 4 digits"
        }

        if (!lastFour.all { it.isDigit() }) {
            return "lastFour should only contain digits"
        }

        if (!expiry.matches(Regex("^(0[1-9]|1[0-2])/([0-9]{2})$"))) {
            return "expiry should be in the format MM/YY"
        }

        if (cardId.isEmpty()) {
            return "cardId cannot be empty"
        }

        return null
    }

}
