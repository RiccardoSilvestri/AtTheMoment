import Foundation

struct Link: Codable {
    let rel: String?
    let href: String
    let title: String?

    enum CodingKeys: String, CodingKey {
        case rel = "Rel"
        case href = "Href"
        case title = "Title"
    }
}
