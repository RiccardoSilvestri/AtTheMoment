import Foundation

struct TrafficBulletin: Codable {
    var title: String?
    var body: String?
    var publicationDate: String?
    var expirationDate: String? 

    enum CodingKeys: String, CodingKey {
        case title = "Title"
        case body = "Body"
        case publicationDate = "PublicationDate"
        case expirationDate = "ExpirationDate"
    }
}
