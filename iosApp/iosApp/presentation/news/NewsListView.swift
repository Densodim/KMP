import SwiftUI
import Shared

struct NewsListView: View {
    @StateObject private var viewModel = NewsViewModel()

    var body: some View {
        NavigationView {
            Group {
                switch viewModel.state {
                case .idle:
                    Color.clear.onAppear { viewModel.fetchData() }

                case .loading:
                    ProgressView("Загрузка...")

                case .loaded(let articles):
                    List(articles, id: \.title) { item in
                        NewsItemRow(item: item)
                    }
                    .listStyle(.plain)

                case .failed(let message):
                    VStack(spacing: 16) {
                        Text("Ошибка: \(message)")
                            .multilineTextAlignment(.center)
                            .foregroundColor(.secondary)
                        Button("Повторить") {
                            viewModel.fetchData()
                        }
                    }
                    .padding()
                }
            }
            .navigationTitle("Новости")
        }
    }
}
