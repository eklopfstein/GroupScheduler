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
    fun setTime(t: Int) {
        timeSpecification = if (t == 0)
            "After $time $period"
        else if (t == 1)
            "Before $time $period"
        else
            "All Day"
    }

    // formats output
    fun getFormatedDate(): String {
        var s = date.split('-')
        var str = ""
        month = s[1].toInt()
        year = s[0].toInt()
        str += s[2]
        val d = s[2].toInt()
        str += if (d == 1)
            "st"
        else if (d == 2)
            "nd"
        else if (d == 3)
            "rd"
        else
            "th"
        return str
    }

}