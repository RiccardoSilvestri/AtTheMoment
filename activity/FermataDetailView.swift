import SwiftUI
import MapKit

struct FermataDetailView: View {
    var fermata: Stop
    @State private var detailFermata: ApiFermata?
    @State private var errorMessage: String?
    @State private var region: MKCoordinateRegion
    @State private var timer: Timer?

    private let ricercaFermata = RicercaInfoFermata()

    init(fermata: Stop) {
        self.fermata = fermata
        _region = State(initialValue: MKCoordinateRegion(
            center: CLLocationCoordinate2D(latitude: 0.0, longitude: 0.0),
            span: MKCoordinateSpan(latitudeDelta: 0.002, longitudeDelta: 0.002)
        ))
    }

    var body: some View {
        VStack {
            if let error = errorMessage {
                Text("Error: \(error)")
                    .foregroundColor(.red)
                    .padding()
            } else if let detail = detailFermata {
                VStack {
                    Text(detail.description)
                        .font(.title)
                        .padding()

                    Text("Waiting Message: \(detail.waitingMessage)")
                        .padding()

                    Map(coordinateRegion: $region, showsUserLocation: false, annotationItems: [detail]) { fermataDetail in
                        MapPin(coordinate: CLLocationCoordinate2D(latitude: fermataDetail.y, longitude: fermataDetail.x), tint: .blue)
                    }
                    .frame(height: 300)
                    .padding()
                }
            } else {
                ProgressView("Loading...")
                    .padding()
            }
        }
        .onAppear {
            fetchFermataDetails()
            startTimer()
        }
        .onDisappear {
            timer?.invalidate()
        }
        .navigationTitle(fermata.description)
        .padding()
    }

    func fetchFermataDetails() {
        ricercaFermata.infoFermata(input: fermata.code) { details in
            DispatchQueue.main.async {
                if let firstDetail = details.first {
                    let bookInfo = firstDetail.bookInfo.isEmpty ? "N/A" : firstDetail.bookInfo
                    detailFermata = ApiFermata(
                        description: firstDetail.description,
                        bookInfo: bookInfo,
                        waitingMessage: firstDetail.waitingMessage,
                        y: firstDetail.y,
                        x: firstDetail.x,
                        code: firstDetail.code
                    )
                    region.center = CLLocationCoordinate2D(latitude: firstDetail.y, longitude: firstDetail.x)
                } else {
                    errorMessage = "No details found for stop \(fermata.code)"
                }
            }
        }
    }

    func startTimer() {
        timer = Timer.scheduledTimer(withTimeInterval: 10, repeats: true) { _ in
            fetchFermataDetails()
        }
    }
}
