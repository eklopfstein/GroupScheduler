package uc.edu.klopfsea.groupscheduler.Events

class UserEvent(
        var startDate: String,
        var startTime: String,
        var duration: String
) {
    var frequency: Map<String, ArrayList<String>>? = null

    /***
     * A secondary constructor for repeating events
     * Source: https://www.bing.com/videos/search?q=kotlin+secondary+constructor&view=detail&mid=3CFFA7FE44C241D863E33CFFA7FE44C241D863E3&FORM=VIRE
     */
    constructor(frequency: Map<String, ArrayList<String>>, startDate: String, startTime: String, duration: String) : this(startDate, startTime, duration) {
        this.frequency = frequency
    }

    init {
        // verify events on creation
        val eventVerifier: EventVerifier = EventVerifier()
        eventVerifier.verifyEvent(this)
    }
}