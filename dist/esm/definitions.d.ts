export interface NfcWalletPlugin {
    isNfcSupport(): Promise<{
        supported: boolean;
        enabled: boolean;
    }>;
    setActiveCard(options: {
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
