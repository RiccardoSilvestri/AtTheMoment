import Foundation
import SwiftUI

struct StopInfoView: View {
    var descriptionText: String
    var bookInfoText: String
    var waitingMessageText: String

    var body: some View {
        VStack(spacing: 16) {
            Text(descriptionText)
                .font(.title2)
            Text(bookInfoText)
                .font(.body)
            Text(waitingMessageText)
                .font(.footnote)
                .foregroundColor(.gray)
        }
        .padding()
    }
}
