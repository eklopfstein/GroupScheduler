package uc.edu.klopfsea.groupscheduler.dto

/***
 * class for creating events, it has name and time (hour, minute) constructors
 */
data class NewEventDto(var newEventName: String = "", var hour: Int = 0, var minute: Int = 0, var newEventId: String = "")