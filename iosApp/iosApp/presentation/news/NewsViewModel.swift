import Foundation
import Shared

@MainActor
final class NewsViewModel: ObservableObject {
    @Published private(set) var state: NewsListState = .idle

    private let newsService: NewsService

    init(newsService: NewsService = DI().getNewsService()) {
        self.newsService = newsService
    }

    func fetchData() {
        state = .loading

        Task {
            do {
                let result = try await newsService.loadNews()
                if let items = result as? NewsItemsList {
                    state = .loaded(items.articles as! [NewsItem])
                } else {
                    state = .failed("Unexpected response type")
                }
            } catch {
                state = .failed(error.localizedDescription)
            }
        }
    }
}

enum NewsListState {
    case idle
    case loading
    case loaded([NewsItem])
    case failed(String)
}
