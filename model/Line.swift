import Foundation

struct Line: Codable {
    let operatorCode: String
    let lineCode: String
    let lineDescription: String
    let suburban: Bool
    let transportMode: Int
    let otherRoutesAvailable: Bool
    let links: [Link]?

    enum CodingKeys: String, CodingKey {
        case operatorCode = "OperatorCode"
        case lineCode = "LineCode"
        case lineDescription = "LineDescription"
        case suburban = "Suburban"
        case transportMode = "TransportMode"
        case otherRoutesAvailable = "OtherRoutesAvailable"
        case links = "Links"
    }
}
