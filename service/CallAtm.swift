import Foundation

struct CallAtm {
    private static let baseUrl = "https://giromilano.atm.it/proxy.tpportal/api/tpportal"

    private static func fetch<T: Decodable>(from urlString: String, as type: T.Type) async throws -> T {
        guard let url = URL(string: urlString) else {
            throw URLError(.badURL)
        }

        var request = URLRequest(url: url)
        request.setValue("gzip", forHTTPHeaderField: "Accept-Encoding")
        request.setValue("Mozilla/5.0", forHTTPHeaderField: "User-Agent")

        let (data, response) = try await URLSession.shared.data(for: request)

        guard let httpResponse = response as? HTTPURLResponse else {
            throw URLError(.badServerResponse)
        }

        guard 200..<300 ~= httpResponse.statusCode else {
            throw URLError(.badServerResponse)
        }

        return try JSONDecoder().decode(T.self, from: data)
    }

    static func infoMezzo(input: String, direzione: Int) async throws -> Mezzo {
        let url = "\(baseUrl)/tpl/journeyPatterns/\(input)%7C\(direzione)/?alternativeRoutesMode=false"
        return try await fetch(from: url, as: Mezzo.self)
    }

    static func infoFermata(numero: String) async throws -> Fermata {
        let url = "\(baseUrl)/geodata/pois/stops/\(numero)"
        return try await fetch(from: url, as: Fermata.self)
    }

    static func listaMezzi() async throws -> JourneyPatterns {
        let url = "\(baseUrl)/tpl/journeyPatterns"
        return try await fetch(from: url, as: JourneyPatterns.self)
    }

    static func news() async throws -> [News] {
        let url = "\(baseUrl)/tpl/atm/it"
        return try await fetch(from: url, as: [News].self)
    }

    static func infoAroundMe(y: Double, x: Double) async throws -> JourneyPatterns {
        let url = "\(baseUrl)/tpl/journeyPatterns/nearest?radius=200&Point.Y=\(y)&Point.X=\(x)"
        return try await fetch(from: url, as: JourneyPatterns.self)
    }
}
