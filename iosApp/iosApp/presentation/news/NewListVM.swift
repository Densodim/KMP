import Foundation
import Shared

@MainActor
class NewListVM: ObservableObject {
    @Published var news: [NewsItem] = []
    @Published var isLoading: Bool = false

    // Экземпляр общей ViewModel из Shared модуля
    private let sharedVM = NewsViewModels()

    // Флаг, чтобы не подписываться на Flow дважды
    private var isObserving = false

    // Наш Observer (FlowCollector)
    private lazy var itemsCollector: Observer = {
        Observer { [weak self] value in
            if let newsList = value as? NewsItemsList {
                DispatchQueue.main.async {
                    self?.news = newsList.articles
                    self?.isLoading = false
                }
            }
        }
    }()

    func loadNews() {
        if !isObserving {
            isObserving = true
            startObserving()
        }

        isLoading = true
        sharedVM.loadNews()
    }

    private func startObserving() {
        Task {
            do {
                // Подписываемся на Flow из Kotlin
                try await sharedVM.newFlow.collect(collector: itemsCollector)
            } catch {
                print("Flow collection failed: \(error)")
                isObserving = false
            }
        }
    }
}
