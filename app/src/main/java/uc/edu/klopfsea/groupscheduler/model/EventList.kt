package uc.edu.klopfsea.groupscheduler.model

object EventList {
    private val eventList = arrayListOf<Event>()

    fun getData(): ArrayList<Event> {
        eventList.add(Event(isDaysSet = true, isDateSet = false, date = "", days = "M,T,W,R", time = "", timeSpecification = "All Day", period = ""))
        eventList.add(Event(isDaysSet = false, isDateSet = true, date = "12th", days = "", time = "4:30", timeSpecification = "Before 4:30 PM", period = "PM"))
        return eventList
    }

    fun putData(event: Event) {
        eventList.add(event)
    }

}