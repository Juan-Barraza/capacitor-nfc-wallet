export interface NfcWalletPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
