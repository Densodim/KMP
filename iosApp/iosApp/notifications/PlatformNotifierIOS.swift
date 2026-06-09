import Foundation
import Shared
import UIKit
import UserNotifications

public final class PlatformNotifierIOS: PlatformNotifier {
    public static let shared = PlatformNotifierIOS()

    private var token: String = ""
    private var pendingRegistrationCompletion: ((String?, Error?) -> Void)?

    public init() {}

    public func register(completionHandler: @escaping (String?, Error?) -> Void) {
        UNUserNotificationCenter.current().requestAuthorization(options: [.alert, .badge, .sound]) { granted, error in
            if let error {
                completionHandler(nil, error)
                return
            }

            guard granted else {
                completionHandler(nil, nil)
                return
            }

            DispatchQueue.main.async {
                self.pendingRegistrationCompletion = completionHandler
                UIApplication.shared.registerForRemoteNotifications()
            }
        }
    }

    public func unregister() {
        UIApplication.shared.unregisterForRemoteNotifications()
        token = ""
    }

    public func getToken() -> String {
        token
    }

    public func updateToken(_ token: String) {
        self.token = token
        pendingRegistrationCompletion?(token, nil)
        pendingRegistrationCompletion = nil
    }

    public func failRegistration(_ error: Error) {
        pendingRegistrationCompletion?(nil, error)
        pendingRegistrationCompletion = nil
    }
}
