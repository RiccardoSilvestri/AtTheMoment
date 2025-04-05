import Foundation
import SwiftUI

struct HeaderView: View {
    @State private var newsTitle: String = "Caricamento notizie..."
    @State private var offsetX: CGFloat = UIScreen.main.bounds.width

    var body: some View {
        VStack {
            Text(newsTitle)
                .offset(x: offsetX)
                .onAppear {
                    withAnimation(Animation.linear(duration: 8).repeatForever(autoreverses: false)) {
                        offsetX = -UIScreen.main.bounds.width
                    }
                    fetchNews()
                }
                .padding()
        }
        .contentShape(Rectangle())
        .onTapGesture {
            print("Vai alla HomeView")
        }
    }

    func fetchNews() {
        DispatchQueue.global(qos: .background).async {
            let fetchedTitle = "⚡ Ultime notizie ATM disponibili ora!"
            DispatchQueue.main.async {
                newsTitle = fetchedTitle
            }
        }
    }
}
