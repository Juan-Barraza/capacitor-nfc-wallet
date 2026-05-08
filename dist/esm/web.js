import { WebPlugin } from '@capacitor/core';
export class NfcWalletWeb extends WebPlugin {
    async isNfcSupport() {
        return { supported: false, enabled: false };
    }
    async setActiveCard(_options) {
        throw this.unavailable('NfcWallet is not support on web');
    }
    async startSession() {
        throw this.unavailable('NfcWallet is not support on web');
    }
    async endSession() {
        throw this.unavailable('NfcWallet is not support on web');
    }
    async getSessionStatus() {
        throw this.unavailable('NfcWallet is not support on web');
    }
}
//# sourceMappingURL=web.js.map