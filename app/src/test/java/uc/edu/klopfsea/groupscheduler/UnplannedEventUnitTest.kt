package uc.edu.klopfsea.groupscheduler

import org.junit.Assert
import org.junit.Test
import uc.edu.klopfsea.groupscheduler.Events.UnplannedEvent

class UnplannedEventUnitTest {

    var name: String = "Dinner"
    var duration: String = "0200"
    var unplannedEvent: UnplannedEvent = UnplannedEvent(name, duration)
    lateinit var invalidUnplannedEvent: UnplannedEvent

    @Test
    fun unplannedEvent_maintainsState() {
        // Ensures object persists
        Assert.assertTrue(unplannedEvent.name == name)
        Assert.assertTrue(unplannedEvent.duration == duration)
    }

    @Test
    fun unplannedEvent_invalidDuration() {
        // Ensure events with invalid Durations cannot be made
        try {
            var duration: String = "2430"
            invalidUnplannedEvent = UnplannedEvent(name, duration)
        } catch (ex: Exception) {
            Assert.assertTrue(ex.message == "Invalid Event Duration")
        }
    }

    @Test
    fun unplannedEvent_invalidName() {
        // Ensure events with invalid Names cannot be made
        try {
            var name: String = ""
            invalidUnplannedEvent = UnplannedEvent(name, duration)
        } catch (ex: Exception) {
            Assert.assertTrue(ex.message == "Invalid Event Name")
        }
    }

}