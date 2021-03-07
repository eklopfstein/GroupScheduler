package uc.edu.klopfsea.groupscheduler.model

// declaring a data class
data class Event (
        var isDaysSet: Boolean = false,
        var isDateSet: Boolean = false,
        var days: String,
        var date: String,
        var time: String,
        var period: String,
        var timeSpecification: String
){
    // property declared in class body
    var id=0
    var month=0
    var year=0
    // Sets the time
    fun setTime(t: Int){
        timeSpecification = when (t) {
            0 -> "After $time $period"
            1 -> "Before $time $period"
            else -> "All Day"
        }
    }
    // formats output
    fun getFormatedDate():String{
        var s = date.split('/')
        var str = ""
        month = s[1].toInt()
        year = s[2].toInt()
        str += s[0]
        val d = s[0].toInt()
        if (d == 1)
            str += "st"
        else if (d == 2)
            str += "nd"
        else if (d == 3)
            str += "rd"
        else
            str += "th"
        return str
    }

}