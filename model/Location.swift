import Foundation

struct Location: Codable {
    var x: Double
    var y: Double

    enum CodingKeys: String, CodingKey {
        case x = "X"
        case y = "Y"
    }
}
