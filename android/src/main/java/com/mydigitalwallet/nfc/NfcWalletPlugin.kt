package com.mydigitalwallet.nfc

import com.getcapacitor.JSObject
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.CapacitorPlugin

@CapacitorPlugin(name = "NfcWallet")
class NfcWalletPlugin: Plugin() {

    private lateinit var nfcWallet: NfcWallet

    override fun load() {
        nfcWallet = NfcWallet(context)
    }

    @PluginMethod
    fun isNfcSupported(call: PluginCall) {
        val ret = JSObject()
        ret.put("isSupported", nfcWallet.isNfcSupported())
        ret.put("enabled", nfcWallet.setActiveCard())
        call.resolve(ret)
    }

    @PluginMethod
    fun setActiveCard(call: PluginCall) {
        val lastFour = call.getString("lastFour")
        val expiry = call.getString("expiry")
        val cardId = call.getString("cardId")

        if (lastFour == null || expiry == null || cardId == null) {
            call.reject("lastFour, expiry and cardId are required")
            return
        }

        val validationError = nfcWallet.validateCardData(lastFour, expiry, cardId)
        if (validationError != null) {
            call.reject(validationError)
            return
        }
        NfcSessionManager.setActiveCard(lastFour, expiry, cardId)
        call.resolve()
    }

    @PluginMethod
    fun startSession(call: PluginCall) {
        if (!NfcSessionManager.hasActiveCard()) {
            call.reject("Set an active card first")
            return
        }
        NfcSessionManager.startSession()
        call.resolve()
    }

    @PluginMethod
    fun endSession(call: PluginCall) {
        NfcSessionManager.endSession()
        call.resolve()
    }

    @PluginMethod
    fun getSessionStatus(call: PluginCall) {
        val ret = JSObject()
        ret.put("isActive", NfcSessionManager.isSessionActive())
        ret.put("hasCard", NfcSessionManager.hasActiveCard())
        call.resolve(ret)
    }

}