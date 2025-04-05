import Foundation

struct Category: Codable {
    var categoryId: String? 
    var categoryName: String?
    var hasTimeTables: Bool?
    var icons: [String]?

    enum CodingKeys: String, CodingKey {
        case categoryId = "CategoryId"
        case categoryName = "CategoryName"
        case hasTimeTables = "HasTimeTables"
        case icons = "Icons"
    }
}
