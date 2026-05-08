import { WebPlugin } from '@capacitor/core';
import type { NfcWalletPlugin } from './definitions';
export declare class NfcWalletWeb extends WebPlugin implements NfcWalletPlugin {
    isNfcSupport(): Promise<{
        supported: boolean;
        enabled: boolean;
    }>;
    setActiveCard(_options: {
        lastFour: string;
        expiry: string;
        cardId: string;
    }): Promise<void>;
    startSession(): Promise<void>;
    endSession(): Promise<void>;
    getSessionStatus(): Promise<{
        isActive: boolean;
        hasCard: boolean;
    }>;
}
