class RicercaInfoFermata {
    
    func infoFermata(input: String, completion: @escaping ([ApiFermata]) -> Void) {
        print("🔍 [RicercaInfoFermata] Starting fetch for stop: \(input)")
        
        Task {
            do {
                let fermata = try await CallAtm.infoFermata(numero: input)
                print("✅ [RicercaInfoFermata] RAW API RESPONSE: \(fermata)")
                
                let firstLine = fermata.lines?.first
                let waitingMessage = firstLine?.waitMessage ?? "Nessun messaggio"
                
                let detailsDescription: String
                if let details = fermata.details as? String, !details.isEmpty {
                    detailsDescription = details
                } else {
                    detailsDescription = "Dettagli non disponibili"
                }
                
                let result = [ApiFermata(
                    description: fermata.description,
                    bookInfo: fermata.siteUrl ?? "N/A",
                    waitingMessage: waitingMessage,
                    y: fermata.location.y,
                    x: fermata.location.x
                )]
                
                print("📦 [RicercaInfoFermata] Mapped result: \(result.first?.debugDescription ?? "nil")")
                completion(result)
            } catch {
                print("❌ [RicercaInfoFermata] ERROR: \(error.localizedDescription)")
                completion([]) 
            }
        }
    }
}
