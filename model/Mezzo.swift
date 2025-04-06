import Foundation

struct Mezzo: Codable {
    var id: String
    var code: String
    var direction: String
    var line: Line
    var stops: [Stop]

    enum CodingKeys: String, CodingKey {
        case id = "Id"
        case code = "Code"
        case direction = "Direction"
        case line = "Line"
        case stops = "Stops"
    }
}
