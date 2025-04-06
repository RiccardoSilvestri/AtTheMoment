import Foundation

struct Stop: Codable {
    var operatorCode: String
    var code: String
    var description: String
    var location: Location
    var pointType: Int
    var stopType: String

    enum CodingKeys: String, CodingKey {
        case operatorCode = "OperatorCode"
        case code = "Code"
        case description = "Description"
        case location = "Location"
        case pointType = "PointType"
        case stopType = "StopType"
    }
}
