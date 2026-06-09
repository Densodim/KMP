import Foundation
import Shared

class NewListVM: ObservableObject, View {
    lazy var presenter: (any Presenter)? = {
        let presenter = NewsPresenter()
        presenter.attach(view: self)
        return presenter
    }()

    @Published var news: [NewsItem] = []

    func loadNews() {
        presenter?.attach(view: self)
    }
}