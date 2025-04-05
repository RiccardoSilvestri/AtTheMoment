import Foundation

class RicercaNews {
    func listaNews(completion: @escaping ([News]) -> Void) {
        Task {
            do {
                let newsList: [News] = try await CallAtm.news()
                completion(newsList)
            } catch {
                completion([])
            }
        }
    }
}
