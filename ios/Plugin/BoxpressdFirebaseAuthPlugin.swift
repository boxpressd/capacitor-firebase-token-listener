import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(BoxpressdFirebaseAuthPlugin)
public class BoxpressdFirebaseAuthPlugin: CAPPlugin {
    private let implementation = BoxpressdFirebaseAuth()

    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.echo(value)
        ])
    }
}
