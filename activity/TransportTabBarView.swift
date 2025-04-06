import Foundation
import SwiftUI

struct TransportTabBarView: View {
    var onSelectTransport: (Int) -> Void

    var body: some View {
        HStack(spacing: 16) {
            Button("🚋 Tram") {
                onSelectTransport(1)
            }
            Button("🚌 Bus") {
                onSelectTransport(3)
            }
            Button("🚆 Treno") {
                onSelectTransport(2)
            }
        }
        .padding()
        .buttonStyle(.borderedProminent)
    }
}
