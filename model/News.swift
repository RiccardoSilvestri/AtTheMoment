import Foundation
struct News: Codable {
    var publication: String
    var expiration: String
    var title: String
    var body: String
    var lines: [String]
    var guid: String

    enum CodingKeys: String, CodingKey {
        case publication = "Publication"
        case expiration = "Expiration"
        case title = "Title"
        case body = "Body"
        case lines = "Lines"
        case guid = "Guid"
    }
}
