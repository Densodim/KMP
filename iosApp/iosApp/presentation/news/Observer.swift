import Foundation
import Shared

// Псевдоним для интерфейса FlowCollector из Kotlin Coroutines
typealias Collector = Kotlinx_coroutines_coreFlowCollector

class Observer: NSObject, Collector {
    let callback: (Any?) -> Void

    init(callback: @escaping (Any?) -> Void) {
        self.callback = callback
        super.init()
    }

    // Метод emit вызывается из Kotlin при каждом новом значении в Flow
    func emit(value: Any?, completionHandler: @escaping (Error?) -> Void) {
        callback(value)
        completionHandler(nil)
    }
}
