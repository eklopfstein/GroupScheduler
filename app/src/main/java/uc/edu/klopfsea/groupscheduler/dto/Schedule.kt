package uc.edu.klopfsea.groupscheduler.dto

data class Schedule (var day : String? = "", var date : String? = "", var time : String? = "", var scheduleId : String = "", var planEvent : ArrayList<PlannedEvent> = ArrayList<PlannedEvent>()){

}