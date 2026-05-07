import { WebPlugin } from '@capacitor/core';

import type { NfcWalletPlugin } from './definitions';

export class NfcWalletWeb extends WebPlugin implements NfcWalletPlugin {
  async isNfcSupport(): Promise<{ supported: boolean; enabled: boolean; }> {
    return { supported: false, enabled: false };
  }

  async setActiveCard(_options: { lastFour: string; expiry: string; cardId: string; }): Promise<void> {
    throw this.unavailable('NfcWallet is not support on web');
  }

  async startSession(): Promise<void> {
    throw this.unavailable('NfcWallet is not support on web');
  }

  async endSession(): Promise<void> {
    throw this.unavailable('NfcWallet is not support on web');
  }

  async getSessionStatus(): Promise<{ isActive: boolean; hasCard: boolean; }> {
    throw this.unavailable('NfcWallet is not support on web');
  }
}
