import Foundation
import SwiftUI
import MapKit

struct StopActivity: View {
    @State private var region = MKCoordinateRegion(
        center: CLLocationCoordinate2D(latitude: 43.0, longitude: 12.0),
        span: MKCoordinateSpan(latitudeDelta: 0.05, longitudeDelta: 0.05)
    )

    var body: some View {
        VStack {
            Map(coordinateRegion: $region, interactionModes: .all, showsUserLocation: true)
                .edgesIgnoringSafeArea(.all)
            
            Text("Stop Description")
            Text("Book Info")
            Text("Waiting Message")
        }
        .onAppear {
            updateStopInfo()
        }
    }

    func updateStopInfo() {
    }
}
