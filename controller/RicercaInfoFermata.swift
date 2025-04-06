class RicercaInfoFermata {
    func infoFermata(input: String, completion: @escaping ([ApiFermata]) -> Void) {
        Task {
            do {
                let fermata = try await CallAtm.infoFermata(numero: input)
                let firstLine = fermata.lines?.first
                let waitingMessage = firstLine?.waitMessage ?? "Nessun messaggio disponibile"
                
                let detailsDescription: String
                if let details = fermata.details as? String, !details.isEmpty {
                    detailsDescription = details
                } else {
                    detailsDescription = "Dettagli non disponibili"
                }
                
                let siteUrl = fermata.siteUrl ?? "N/A"
                
                let result = [ApiFermata(
                    description: fermata.description ?? "Descrizione non disponibile",
                    bookInfo: siteUrl,
                    waitingMessage: waitingMessage,
                    y: fermata.location?.y ?? 0.0,
                    x: fermata.location?.x ?? 0.0,
                    code: fermata.code ?? "N/A"
                )]
                
                completion(result)
            } catch {
                completion([])
            }
        }
    }
}
