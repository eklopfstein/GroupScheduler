package uc.edu.klopfsea.groupscheduler.Events

import java.util.*
import kotlin.collections.ArrayList

class UserEvent(
    var userEventName: Map<String, ArrayList<String>>,
    var frequency: Date,
    startTime: String,
    duration: String
) {
}