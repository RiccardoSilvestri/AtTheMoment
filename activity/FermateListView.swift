import SwiftUI

struct FermateListView: View {
    var mezzo: ApiListaMezzi
    @State private var fermateList: [Stop] = []

    private let ricercaFermate = RicercaInfoMezzo() // Una classe per ottenere le fermate

    var body: some View {
        VStack {
            Text("Fermate per \(mezzo.lineDescription)")
                .font(.title)
                .padding()

            if !fermateList.isEmpty {
                List(fermateList, id: \.code) { fermata in
                    NavigationLink(destination: FermataDetailView(fermata: fermata)) {
                        Text(fermata.description)
                    }
                }
            } else {
                Text("Nessuna fermata trovata").padding()
            }
        }
        .onAppear {
            fetchFermateForMezzo()
        }
        .navigationTitle(mezzo.lineDescription)
    }

    func fetchFermateForMezzo() {
        print("🔎 Fetching stops for \(mezzo.code)")
        
        ricercaFermate.infoMezzo(input: mezzo.code, direzione: 0) { fermate in
            DispatchQueue.main.async {
                fermateList = fermate
                print("📥 Received \(fermate.count) fermate")
            }
        }
    }
}
