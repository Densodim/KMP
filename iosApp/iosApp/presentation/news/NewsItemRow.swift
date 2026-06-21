import SwiftUI
import Shared

struct NewsItemRow: View {
    let item: NewsItem
    let onFavoriteClick: () -> Void

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            // Изображение
            if let urlString = item.urlToImage, let url = URL(string: urlString) {
                AsyncImage(url: url) { image in
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                } placeholder: {
                    ProgressView()
                }
                .frame(height: 180)
                .clipped()
                .cornerRadius(8)
            }

            // Заголовок
            Text(item.title ?? "Без заголовка")
                .font(.headline)
                .lineLimit(2)

            // Описание (в Swift 'description' обычно переименовывается в 'description_')
            Text(item.description_ ?? "")
                .font(.subheadline)
                .foregroundColor(.secondary)
                .lineLimit(3)

            HStack {
                // Автор
                Text(item.author ?? "Неизвестный автор")
                    .font(.caption)
                    .foregroundColor(.secondary)

                Spacer()

                // Дата публикации
                if let publishedAt = item.publishedAt {
                    Text(publishedAt)
                        .font(.caption)
                        .foregroundColor(.secondary)
                }

                // Кнопка "избранное"
                Button(action: onFavoriteClick) {
                    Image(systemName: item.isFavorite ? "heart.fill" : "heart")
                        .foregroundColor(item.isFavorite ? .red : .secondary)
                }
                .buttonStyle(.borderless)
            }
        }
        .padding(.vertical, 8)
    }
}
