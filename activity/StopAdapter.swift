import Foundation
import SwiftUI

struct StopAdapter: View {
    var stops: [String]
    
    var body: some View {
        List(stops, id: \.self) { stop in
            Text(stop)
        }
    }
}
