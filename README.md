# capacitor-nfc-wallet

NFC HCE payment plugin for MyDigitalWallet

> ⚠️ **Educational purposes only:** This plugin was developed for academic purposes to demonstrate the HCE (Host Card Emulation) protocol and APDU communication in NFC payments. It is not certified by EMVCo and does not have agreements with payment networks (Visa/Mastercard). It should not be used in real production environments without the corresponding agreements and certifications.

## Install

To use npm

```bash
npm install capacitor-nfc-wallet
````

To use yarn

```bash
yarn add capacitor-nfc-wallet
```

Sync native files

```bash
npx cap sync
```

## API

<docgen-index>

* [`isNfcSupport()`](#isnfcsupport)
* [`setActiveCard(...)`](#setactivecard)
* [`startSession()`](#startsession)
* [`endSession()`](#endsession)
* [`getSessionStatus()`](#getsessionstatus)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### isNfcSupport()

```typescript
isNfcSupport() => Promise<{ supported: boolean; enabled: boolean; }>
```

**Returns:** <code>Promise&lt;{ supported: boolean; enabled: boolean; }&gt;</code>

--------------------


### setActiveCard(...)

```typescript
setActiveCard(options: { lastFour: string; expiry: string; cardId: string; }) => Promise<void>
```

| Param         | Type                                                               |
| ------------- | ------------------------------------------------------------------ |
| **`options`** | <code>{ lastFour: string; expiry: string; cardId: string; }</code> |

--------------------


### startSession()

```typescript
startSession() => Promise<void>
```

--------------------


### endSession()

```typescript
endSession() => Promise<void>
```

--------------------


### getSessionStatus()

```typescript
getSessionStatus() => Promise<{ isActive: boolean; hasCard: boolean; }>
```

**Returns:** <code>Promise&lt;{ isActive: boolean; hasCard: boolean; }&gt;</code>

--------------------

</docgen-api>
