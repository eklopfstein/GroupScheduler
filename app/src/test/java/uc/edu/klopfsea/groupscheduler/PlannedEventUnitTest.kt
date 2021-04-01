package uc.edu.klopfsea.groupscheduler

import org.junit.Assert
import org.junit.Test
import uc.edu.klopfsea.groupscheduler.Events.PlannedEvent

class PlannedEventUnitTest {

    var name: String = "Dinner"
    var date: String = "2021-04-26"
    var startTime: String = "1700"
    var plannedEvent: PlannedEvent = PlannedEvent(name, date, startTime)
    lateinit var invalidPlannedEven: PlannedEvent

    @Test
    fun plannedEvent_maintainsState() {
        // Ensures object persists
        Assert.assertTrue(plannedEvent.name == name)
        Assert.assertTrue(plannedEvent.date == date)
        Assert.assertTrue(plannedEvent.startTime == startTime)
    }

    @Test
    fun plannedEvent_invalidName() {
        // Ensure events with invalid Durations cannot be made
        try {
            var name: String = " "
            invalidPlannedEven = PlannedEvent(name, date, startTime)
        } catch (ex: Exception) {
            Assert.assertTrue(ex.message == "Invalid Event Name")
        }
    }

    @Test
    fun plannedEvent_invalidDate() {
        // Ensure events with invalid dates cannot be made
        try {
            var date: String = "2021-13-26"
            invalidPlannedEven = PlannedEvent(name, date, startTime)
        } catch (ex: Exception) {
            Assert.assertTrue(ex.message == "Invalid Event Date")
        }
    }

    @Test
    fun plannedEvent_invalidStartTime() {
        // Ensure events with an invalid startTime cannot be made
        try {
            var startTime: String = "2500"
            invalidPlannedEven = PlannedEvent(name, date, startTime)
        } catch (ex: Exception) {
            Assert.assertTrue(ex.message == "Invalid Event Time")
        }
    }
}