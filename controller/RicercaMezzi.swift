import Foundation

class RicercaMezzi {
    func listaMezzi(input: Int, completion: @escaping ([ApiListaMezzi]) -> Void) {
        Task {
            do {
                let response = try await CallAtm.listaMezzi()
                
                let result: [ApiListaMezzi] = response.journeyPatterns.compactMap { mezzo in
                    guard mezzo.line.transportMode == input else {
                        return nil
                    }
                    return ApiListaMezzi(
                        code: mezzo.code,
                        direction: mezzo.direction,
                        lineDescription: mezzo.line.lineDescription,
                        tipologia: mezzo.line.transportMode
                    )
                }
                
                completion(result)
            } catch {
                completion([])
            }
        }
    }
}
