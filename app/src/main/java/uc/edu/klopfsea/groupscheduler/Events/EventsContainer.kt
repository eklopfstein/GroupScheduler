package uc.edu.klopfsea.groupscheduler.Events

class EventsContainer(var plannedEvents: ArrayList<PlannedEvent>, var unplannedEvents: ArrayList<UnplannedEvent>, var userEvents: ArrayList<UserEvent>) {

    fun addEvent(event: PlannedEvent) {
        plannedEvents.add(event)
    }

    fun addEvent(event: UnplannedEvent) {
        unplannedEvents.add(event)
    }

    fun addEvent(event: UserEvent) {
        userEvents.add(event)
    }

    fun deleteEvent(event: PlannedEvent) {
        if(plannedEvents.contains(event)) {
            plannedEvents.remove(event)
        } else (
                throw IllegalArgumentException("No records of given event in local system")
        )
    }

    fun deleteEvent(event: UnplannedEvent) {
        if(unplannedEvents.contains(event)) {
            unplannedEvents.remove(event)
        } else (
                throw IllegalArgumentException("No records of given event in local system")
                )
    }

    fun deleteEvent(event: UserEvent) {
        if(userEvents.contains(event)) {
            userEvents.remove(event)
        } else (
                throw IllegalArgumentException("No records of given event in local system")
                )
    }
}