package uc.edu.klopfsea.groupscheduler

import org.junit.Assert
import org.junit.Test
import uc.edu.klopfsea.groupscheduler.Events.PlannedEvent

class PlannedEventUnitTest {

    private var NAME: String = "Dinner"
    private var DATE: String = "2021-04-26"
    private var START_TIME: String = "1700"
    private var plannedEvent: PlannedEvent = PlannedEvent(NAME, DATE, START_TIME)
    private lateinit var invalidPlannedEven: PlannedEvent

    @Test
    fun `planned event maintains state`() {
        // Ensures object persists
        Assert.assertTrue(plannedEvent.name == NAME)
        Assert.assertTrue(plannedEvent.date == DATE)
        Assert.assertTrue(plannedEvent.startTime == START_TIME)
    }

    @Test
    fun `planned event invalid name`() {
        // Ensure events with invalid Durations cannot be made
        try {
            var name: String = " "
            invalidPlannedEven = PlannedEvent(name, DATE, START_TIME)
        } catch (ex: Exception) {
            Assert.assertTrue(ex.message == "Invalid Event Name")
        }
    }

    @Test
    fun `planned event invalid date`() {
        // Ensure events with invalid dates cannot be made
        try {
            var date: String = "2021-13-26"
            invalidPlannedEven = PlannedEvent(NAME, date, START_TIME)
        } catch (ex: Exception) {
            Assert.assertTrue(ex.message == "Invalid Event Date")
        }
    }

    @Test
    fun `planned event invalid start time`() {
        // Ensure events with an invalid startTime cannot be made
        try {
            var startTime: String = "2500"
            invalidPlannedEven = PlannedEvent(NAME, DATE, startTime)
        } catch (ex: Exception) {
            Assert.assertTrue(ex.message == "Invalid Event Time")
        }
    }
}