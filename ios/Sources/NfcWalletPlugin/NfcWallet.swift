import Foundation

@objc public class NfcWallet: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
