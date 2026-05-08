'use strict';

var core = require('@capacitor/core');

const NfcWallet = core.registerPlugin('NfcWallet', {
    web: () => Promise.resolve().then(function () { return web; }).then((m) => new m.NfcWalletWeb()),
});

class NfcWalletWeb extends core.WebPlugin {
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

var web = /*#__PURE__*/Object.freeze({
    __proto__: null,
    NfcWalletWeb: NfcWalletWeb
});

exports.NfcWallet = NfcWallet;
//# sourceMappingURL=plugin.cjs.js.map
