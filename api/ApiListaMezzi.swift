import Foundation
import Foundation

struct ApiListaMezzi: Codable, CustomStringConvertible {
    var code: String
    var direction: String
    var lineDescription: String
    var tipologia: Int

    var description: String {
        return "ApiListaMezzi(code: \(code), direction: \(direction), lineDescription: \(lineDescription), tipologia: \(tipologia))"
    }
}
