package uc.edu.klopfsea.groupscheduler.model

// declaring a data class
data class Event(
        var isDaysSet: Boolean = false,
        var isDateSet: Boolean = false,
        var days: String,
        var date: String,
        var time: String,
        var period: String,
        var timeSpecification: String
) {
    // property declared in class body
    var id = 0
    var month = 0
    var year = 0

    // Sets the time
    fun setTime(timeID: Int) {
        timeSpecification = when (timeID) {
            0 -> "After $time $period"
            1 -> "Before $time $period"
            else -> "All Day"
        }
    }

    // formats output
    fun getFormattedDate(): String {
        val dateParts = date.split('-')
        var formattedDate = ""
        month = dateParts[1].toInt()
        year = dateParts[0].toInt()
        formattedDate += dateParts[2]
        val datePart = dateParts[2].toInt()
        formattedDate += when (datePart) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }
        return formattedDate
    }

}