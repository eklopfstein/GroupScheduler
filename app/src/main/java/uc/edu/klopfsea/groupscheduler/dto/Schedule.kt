package uc.edu.klopfsea.groupscheduler.dto

/***
 * class for schedule events, it has day, date, and time constructors
 */

data class Schedule(var day: String? = "", var date: String? = "", var time: String? = "", var scheduleId: String = "")