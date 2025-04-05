import Foundation


class RicercaInfoMezzo {
    func infoMezzo(input: String, direzione: Int, completion: @escaping ([Stop]) -> Void) {
        Task {
            do {
                let mezzo: Mezzo = try await CallAtm.infoMezzo(input: input, direzione: direzione)
                
                completion(mezzo.stops ?? [])
            } catch {
                print("❌ Error fetching stops for \(input): \(error)")
                completion([])  
            }
        }
    }
}
