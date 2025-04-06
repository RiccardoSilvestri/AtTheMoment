struct Fermata: Codable {
    var code: String?
    var description: String?
    var location: Location?
    var customerCode: String?
    var municipality: String?
    var address: String?
    var telephone: String?
    var fax: String?
    var siteUrl: String?
    var email: String?
    var category: Category?
    var details: CodableValue? // Optional
    var dynamicFirstLevel: CodableValue? // Optional
    var lines: [LineInfo]? // Optional
    var pointAccessible: Bool?
    var pointStopPath: CodableValue?
    var pointStopStatus: CodableValue?
    var pointStopInfo: CodableValue?
    var links: [Link]?

    enum CodingKeys: String, CodingKey {
        case code = "Code"
        case description = "Description"
        case location = "Location"
        case customerCode = "CustomerCode"
        case municipality = "Municipality"
        case address = "Address"
        case telephone = "Telephone"
        case fax = "Fax"
        case siteUrl = "SiteUrl"
        case email = "Email"
        case category = "Category"
        case details = "Details" // Optional
        case dynamicFirstLevel = "Dynamic_First_Level" // Optional
        case lines = "Lines" // Optional
        case pointAccessible
        case pointStopPath
        case pointStopStatus
        case pointStopInfo
        case links
    }
}
