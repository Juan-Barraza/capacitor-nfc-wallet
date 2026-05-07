package  com.mydigitalwallet.nfc


object  NfcSessionManager {
    var lastFour: String = ""
    var expiry: String = ""
    var cardId: String = ""

    private var isSessionActive: Boolean = false

    fun setActiveCard(lastFour: String, expiry: String, cardId: String) {
        this.lastFour = lastFour
        this.expiry = expiry
        this.cardId = cardId
    }

    fun startSession() {
        isSessionActive = true
    }

    fun endSession() {
        isSessionActive = false
        lastFour = ""
        expiry = ""
        cardId = ""
    }

    fun isSessionActive(): Boolean {
        return  isSessionActive
    }
    fun hasActiveCard(): Boolean {
        return lastFour.isNotEmpty() && expiry.isNotEmpty()
    }

}