package uc.edu.klopfsea.groupscheduler.dto

import com.google.type.DateTime


data class PlannedEvent(var eventName: String? ="", var address: String ="", var plannedTime: String ="", var notes: String ="", var eventDate: String ="", var addresses : ArrayList<Address> = ArrayList<Address>(), var city : String = "", var state : String? = "", var plannedEventId : String ="") {

}
