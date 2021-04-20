package uc.edu.klopfsea.groupscheduler.dto

/***
 * class for planning event, it has name, adddress, time and notes constructors and uses the address dto to fill in zipcode, city info
 */

data class PlannedEvent(var eventName: String? = "", var address: String = "", var plannedTime: String = "", var notes: String = "", var eventDate: String = "", var city: String = "", var state: String? = "", var plannedEventId: String = "")