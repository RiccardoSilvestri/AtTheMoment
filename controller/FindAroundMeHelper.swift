import Foundation
import CoreLocation
class FindAroundMeHelper: NSObject, CLLocationManagerDelegate {
    private let locationManager = CLLocationManager()
    var callback: ((Double, Double) -> Void)?
    var errorCallback: ((String) -> Void)?

    override init() {
        super.init()
        locationManager.delegate = self
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
    }

    func startLocationUpdates(callback: @escaping (Double, Double) -> Void, onError: @escaping (String) -> Void) {
        self.callback = callback
        self.errorCallback = onError

        let status = locationManager.authorizationStatus
        if status == .denied || status == .restricted {
            onError("Location permission not granted")
            return
        }

        locationManager.requestWhenInUseAuthorization()
        locationManager.startUpdatingLocation()
    }

    func stopLocationUpdates() {
        locationManager.stopUpdatingLocation()
    }

    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        guard let location = locations.first else { return }
        callback?(location.coordinate.latitude, location.coordinate.longitude)
    }

    func locationManager(_ manager: CLLocationManager, didFailWithError error: Error) {
        errorCallback?("Location error: \(error.localizedDescription)")
    }
}
