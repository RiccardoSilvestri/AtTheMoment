import Foundation
import CoreLocation

class RicercaAroundMe {
    func listaMezziAroundMe(y: Double, x: Double, completion: @escaping ([ApiListaMezzi]) -> Void) {
        Task {
            do {
                let response = try await CallAtm.infoAroundMe(y: y, x: x)
                
                // MODIFICATO: Rimossa la gestione optional
                let listaMezzi = response.journeyPatterns
                
                let result = listaMezzi.map {
                    ApiListaMezzi(
                        code: $0.code,
                        direction: $0.direction,
                        lineDescription: $0.line.lineDescription,
                        tipologia: $0.line.transportMode ?? 0
                    )
                }
                completion(result)
            } catch {
                completion([])
            }
        }
    }
}
