package uc.edu.klopfsea.groupscheduler.Events

class UserEvent(
        var startDate: String,
        var startTime: String,
        var duration: String
) {
    var frequency: Map<String, ArrayList<String>>? = null

    /***
     * A secondary constructor for repeating events
     */
    constructor(frequency: Map<String, ArrayList<String>>, startDate: String, startTime: String, duration: String) : this(startDate, startTime, duration) {
        this.frequency = frequency
    }
}