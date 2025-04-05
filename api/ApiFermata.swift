import Foundation

struct ApiFermata: Codable {
    var description: String
    var bookInfo: String
    var waitingMessage: String
    var y: Double
    var x: Double
    var address: String?
    var municipality: String?
    


    var debugDescription: String {
        return """
        ApiFermata:
        - Description: \(description)
        - BookInfo: \(bookInfo)
        - WaitingMessage: \(waitingMessage)
        - Location: (\(y), \(x))
        """
    }
}
