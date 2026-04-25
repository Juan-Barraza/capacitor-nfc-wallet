import { WebPlugin } from '@capacitor/core';

import type { NfcWalletPlugin } from './definitions';

export class NfcWalletWeb extends WebPlugin implements NfcWalletPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
