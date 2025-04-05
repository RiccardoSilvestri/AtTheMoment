import Foundation
import SwiftUI

struct NewsActivity: View {
    @State private var newsTitles = [String]()

    var body: some View {
        List(newsTitles, id: \.self) { title in
            Text(title)
        }
        .onAppear {
            fetchNews()
        }
    }
    
    func fetchNews() {
    }
}
