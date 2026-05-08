import { registerPlugin } from '@capacitor/core';
const NfcWallet = registerPlugin('NfcWallet', {
    web: () => import('./web').then((m) => new m.NfcWalletWeb()),
});
export * from './definitions';
export { NfcWallet };
//# sourceMappingURL=index.js.map