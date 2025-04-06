import Foundation

struct LineInfo: Codable {
    var line: Line
    var direction: String?
    var bookletUrl: String?
    var bookletUrl2: String?
    var waitMessage: String?
    var journeyPatternId: String
    var trafficBulletins: [TrafficBulletin]?
    var links: [Link]?

    enum CodingKeys: String, CodingKey {
        case line = "Line"
        case direction = "Direction"
        case bookletUrl = "BookletUrl"
        case bookletUrl2 = "BookletUrl2"
        case waitMessage = "WaitMessage"
        case journeyPatternId = "JourneyPatternId"
        case trafficBulletins = "TrafficBulletins"
        case links = "Links"
    }
}
