import SwiftUI

struct MainActivity: View {
    @State private var selectedTransport = -1
    @State private var mezziList: [ApiListaMezzi] = []
    @State private var originalMezziList: [ApiListaMezzi] = []
    @State private var searchText = ""
    private let ricercaMezzi = RicercaMezzi()
    private let ricercaAroundMe = RicercaAroundMe()

    var body: some View {
        NavigationStack {
            VStack {
                Text("Selected Transport: \(selectedTransport == -1 ? "None" : "\(selectedTransport)")")
                    .padding()

                TransportTabBarView { transportIndex in
                    selectedTransport = transportIndex
                    handleTransportSelection(transportIndex)
                }

                if selectedTransport != -1 {
                    TextField("Cerca mezzo...", text: $searchText)
                        .padding()
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                        .onChange(of: searchText) { newValue in
                            filterMezziList()
                        }
                }

                if !mezziList.isEmpty {
                    List(mezziList, id: \.code) { mezzo in
                        NavigationLink(destination: FermateListView(mezzo: mezzo)) {
                            if selectedTransport == 3 {
                                Text("Bus \(mezzo.code)")
                            } else if selectedTransport == 2 {
                                Text("Tram \(mezzo.code)")
                            } else if selectedTransport == 1 {
                                Text("Treno \(mezzo.code)")
                            } else {
                                Text("Mezzo \(mezzo.code)")
                            }
                        }
                    }
                } else {
                    Text("Nessun mezzo trovato").padding()
                }
            }
            .navigationTitle("Transport Selector")
        }
    }

    func handleTransportSelection(_ transportIndex: Int) {
        if transportIndex == 4 {
            ricercaAroundMe.listaMezziAroundMe(y: 45.0, x: 9.0) { mezzi in
                DispatchQueue.main.async {
                    let uniqueMezzi = removeDuplicateMezzi(mezzi)
                    mezziList = uniqueMezzi
                    originalMezziList = uniqueMezzi
                    filterMezziList()
                }
            }
        } else {
            ricercaMezzi.listaMezzi(input: transportIndex) { mezzi in
                DispatchQueue.main.async {
                    let uniqueMezzi = removeDuplicateMezzi(mezzi)
                    mezziList = uniqueMezzi
                    originalMezziList = uniqueMezzi
                    filterMezziList()
                }
            }
        }
    }

    func removeDuplicateMezzi(_ mezzi: [ApiListaMezzi]) -> [ApiListaMezzi] {
        var seenCodes = Set<String>()
        return mezzi.filter { mezzo in
            if seenCodes.contains(mezzo.code) {
                return false
            } else {
                seenCodes.insert(mezzo.code)
                return true
            }
        }
    }

    func filterMezziList() {
        if searchText.isEmpty {
            mezziList = originalMezziList
        } else {
            mezziList = originalMezziList.filter { mezzo in
                mezzo.lineDescription.lowercased().contains(searchText.lowercased()) ||
                mezzo.code.lowercased().contains(searchText.lowercased())
            }
        }
    }
}
