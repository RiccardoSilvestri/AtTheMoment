import Foundation
import Foundation

struct ApiMezzo: Codable {
    var code: String
    var lineDescription: String
    var direction: Int
    var stops: [Stop]
}
