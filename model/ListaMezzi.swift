import Foundation

struct ListaMezzi: Codable {
    let id: String
    let code: String
    let direction: String
    let line: Line
    let stops: [Stop]?
    let geometry: CodableValue?
    let links: [Link]?

    enum CodingKeys: String, CodingKey {
        case id = "Id"
        case code = "Code"
        case direction = "Direction"
        case line = "Line"
        case stops = "Stops"
        case geometry = "Geometry"
        case links = "Links"
    }
}
