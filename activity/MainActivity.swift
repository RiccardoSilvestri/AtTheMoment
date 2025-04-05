import SwiftUI

struct MainActivity: View {
    @State private var selectedTransport = -1
    @State private var mezziList: [ApiListaMezzi] = []

    private let ricercaMezzi = RicercaMezzi()
    private let ricercaAroundMe = RicercaAroundMe()

    var body: some View {
        NavigationStack {
            VStack {
                NavigationLink("Go to News") {
                    NewsActivity()
                }

                NavigationLink("Find Around Me") {
                    LinesActivityAroundMe()
                }

                Text("Selected Transport: \(selectedTransport == -1 ? "None" : "\(selectedTransport)")")
                    .padding()

                TransportTabBarView { transportIndex in
                    selectedTransport = transportIndex
                    handleTransportSelection(transportIndex)
                }

                if !mezziList.isEmpty {
                    List(mezziList, id: \.code) { mezzo in
                        NavigationLink(destination: FermateListView(mezzo: mezzo)) {
                            Text(mezzo.lineDescription)
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
        print("🚏 Transport selected: \(transportIndex)")

        if transportIndex == 4 {
            print("📍 Fetching nearby transport")
            ricercaAroundMe.listaMezziAroundMe(y: 45.0, x: 9.0) { mezzi in
                DispatchQueue.main.async {
                    print("📥 Received \(mezzi.count) mezzi from AroundMe")
                    mezziList = mezzi
                }
            }
        } else {
            print("🔎 Fetching transport for index \(transportIndex)")
            ricercaMezzi.listaMezzi(input: transportIndex) { mezzi in
                DispatchQueue.main.async {
                    print("📥 Received \(mezzi.count) mezzi from RicercaMezzi")
                    mezziList = mezzi
                }
            }
        }
    }
}
