import { registerPlugin } from '@capacitor/core';

import type { NfcWalletPlugin } from './definitions';

const NfcWallet = registerPlugin<NfcWalletPlugin>('NfcWallet', {
  web: () => import('./web').then((m) => new m.NfcWalletWeb()),
});

export * from './definitions';
export { NfcWallet };
