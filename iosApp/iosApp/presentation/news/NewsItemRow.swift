import SwiftUI
import Shared

struct NewsItemRow: View {
    let item: NewsItem

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            if let urlToImage = item.urlToImage, let url = URL(string: urlToImage) {
                AsyncImage(url: url) { image in
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                } placeholder: {
                    Color.gray.opacity(0.2)
                }
                .frame(height: 180)
                .clipped()
                .cornerRadius(8)
            }

            Text(item.title)
                .font(.headline)
                .lineLimit(2)

            Text(item.description_ as String)
                .font(.subheadline)
                .foregroundColor(.secondary)
                .lineLimit(3)

            HStack {
                Text(item.author)
                    .font(.caption)
                    .foregroundColor(.secondary)
                Spacer()
                if let publishedAt = item.publishedAt {
                    Text(publishedAt)
                        .font(.caption)
                        .foregroundColor(.secondary)
                }
            }
        }
        .padding(.vertical, 8)
    }
}
