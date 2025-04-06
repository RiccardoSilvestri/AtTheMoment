import SwiftUI

struct FermateListView: View {
    var mezzo: ApiListaMezzi
    @State private var fermateList: [Stop] = []
    @State private var direzioneSelezionata: Int = 0
    @State private var searchText = ""

    private let ricercaFermate = RicercaInfoMezzo()

    var body: some View {
        VStack {
            Text("Fermate per Mezzo \(mezzo.code)")
                .font(.title)
                .padding()

            Picker("Direzione", selection: $direzioneSelezionata) {
                Text("Andata").tag(0)
                Text("Ritorno").tag(1)
            }
            .pickerStyle(SegmentedPickerStyle())
            .padding()

            TextField("Cerca fermata...", text: $searchText)
                .padding()
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .onChange(of: searchText) { _ in
                    filterFermateList()
                }

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
        .onChange(of: direzioneSelezionata) { _ in
            fetchFermateForMezzo()
        }
        .navigationTitle(mezzo.lineDescription)
    }

    func fetchFermateForMezzo() {
        ricercaFermate.infoMezzo(input: mezzo.code, direzione: direzioneSelezionata) { fermate in
            DispatchQueue.main.async {
                fermateList = fermate
                filterFermateList()
            }
        }
    }

    func filterFermateList() {
        if searchText.isEmpty {
            fetchFermateForMezzo()
        } else {
            fermateList = fermateList.filter { fermata in
                fermata.description.lowercased().contains(searchText.lowercased()) ||
                fermata.code.lowercased().contains(searchText.lowercased())
            }
        }
    }
}
