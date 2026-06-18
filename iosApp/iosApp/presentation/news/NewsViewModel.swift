import Foundation
import Shared

@MainActor
final class NewsViewModel: ObservableObject {
    @Published private(set) var state: NewsListState = .idle

    private let sharedVM: NewsViewModels = KoinDIFactory.shared.di.newsViewModels
    private var isObserving = false

    private lazy var collector: Observer = Observer { [weak self] value in
        if let list = value as? NewsItemsList {
            DispatchQueue.main.async {
                self?.state = .loaded(list.articles as! [NewsItem])
            }
        }
    }

    func fetchData() {
        if !isObserving {
            isObserving = true
            Task {
                do {
                    try await sharedVM.newFlow.collect(collector: collector)
                } catch {
                    state = .failed(error.localizedDescription)
                }
            }
        }
        state = .loading
        sharedVM.loadNews()
    }
}

enum NewsListState {
    case idle
    case loading
    case loaded([NewsItem])
    case failed(String)
}
