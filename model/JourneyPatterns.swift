import Foundation
struct JourneyPatterns: Codable {
    var journeyPatterns: [ListaMezzi]

    enum CodingKeys: String, CodingKey {
        case journeyPatterns = "JourneyPatterns"
    }
}
