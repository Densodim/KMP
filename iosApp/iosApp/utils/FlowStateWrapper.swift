import Foundation
import Shared

@propertyWrapper
struct Injected<T: AnyObject> {
    private var service: T?

    public init() {}

    public var wrappedValue: T? {
        mutating get {
            if service == nil {
                service = KoinDIFactory.shared.di as? T
            }
            return service
        }
        set { service = newValue }
    }

    public var projectedValue: Injected<T> {
        get { self }
        mutating set { self = newValue }
    }
}
