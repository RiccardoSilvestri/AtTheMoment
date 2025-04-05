import SwiftUI

struct FermataDetailView: View {
    var fermata: Stop
    @State private var detailFermata: ApiFermata?
    @State private var errorMessage: String?

    private let ricercaFermata = RicercaInfoFermata()

    var body: some View {
        VStack {
            if let error = errorMessage {
                Text("Error: \(error)")
                    .foregroundColor(.red)
            } else if let detail = detailFermata {
                Text(detail.description)
                    .font(.title)
                    .padding()

                Text("Book Info: \(detail.bookInfo)")
                    .padding()

                Text("Waiting Message: \(detail.waitingMessage)")
                    .padding()

                Text("Location: (\(detail.y), \(detail.x))")
                    .padding()
            } else {
                ProgressView("Loading...")
                    .padding()
            }
        }
        .onAppear {
            fetchFermataDetails()
        }
        .navigationTitle(fermata.description)
        .padding()
    }

    func fetchFermataDetails() {
        print("📍 [FermataDetailView] Fetching details for stop: \(fermata.code)")
        
        ricercaFermata.infoFermata(input: fermata.code) { details in
            DispatchQueue.main.async {
                // Verifica se ci sono linee disponibili
                if let firstDetail = details.first, !firstDetail.bookInfo.isEmpty {
                    detailFermata = firstDetail
                    print("🎉 [FermataDetailView] Successfully loaded details")
                } else {
                    errorMessage = "No details found"
                    print("⚠️ [FermataDetailView] Empty details array")
                }
            }
        }
    }


}
