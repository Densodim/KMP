import SwiftUI
import Shared

struct NewsListView: View {
    // Используем нашу новую ViewModel с Observer
    @StateObject private var viewModel = NewListVM()

    var body: some View {
        NavigationView {
            ZStack {
                if viewModel.isLoading && viewModel.news.isEmpty {
                    ProgressView("Загрузка...")
                } else if viewModel.news.isEmpty {
                    VStack {
                        Text("Нет новостей")
                        Button("Загрузить") {
                            viewModel.loadNews()
                        }
                        .padding()
                    }
                } else {
                    List(viewModel.news, id: \.title) { item in
                        NewsItemRow(item: item)
                    }
                    .listStyle(.plain)
                    .refreshable {
                        viewModel.loadNews()
                    }
                }
            }
            .navigationTitle("Новости")
            .onAppear {
                viewModel.loadNews()
            }
        }
    }
}
