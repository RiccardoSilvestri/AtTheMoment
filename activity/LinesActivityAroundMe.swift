import SwiftUI
import CoreLocation

class LocationManagerWrapper: NSObject, ObservableObject, CLLocationManagerDelegate {
    private let manager = CLLocationManager()
    @Published var lastLocation: CLLocation?
    
    override init() {
        super.init()
        manager.delegate = self
    }
    
    func start() {
        manager.requestWhenInUseAuthorization()
        manager.startUpdatingLocation()
    }
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        lastLocation = locations.last
    }
}

struct LinesActivityAroundMe: View {
    @StateObject private var locationWrapper = LocationManagerWrapper()
    @State private var mezziArrayNomi = [String]()
    @State private var searchText = ""
    
    var body: some View {
        VStack {
            List(mezziArrayNomi.filter {
                searchText.isEmpty ? true : $0.contains(searchText)
            }, id: \.self) { mezzo in
                Text(mezzo)
            }
            
            TextField("Search", text: $searchText)
                .padding()
        }
        .onAppear {
            locationWrapper.start()
        }
    }
}
