package uc.edu.klopfsea.groupscheduler

import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import uc.edu.klopfsea.groupscheduler.Events.UserEvent

/***
 * Test the UserEvent class for functionality
 */
class UserEventUnitTest {


    lateinit var userEventRecurring: UserEvent
    lateinit var userEvent: UserEvent
    lateinit var invalidUserEvent: UserEvent
    lateinit var frequency: Map<String, ArrayList<String>>

    @Before
    fun createBothUserEventTypes() {
        // Creates a user event that repeats on certain days each week and has a set start date
        var days: ArrayList<String> = ArrayList<String>()
        days.plus("M")
        days.plus("H")
        // Source: https://www.techiedelight.com/initialize-map-kotlin/
        frequency = mapOf(
                "weekly" to days
        )
        // Time are stored as Strings in military time
        var startTime: String = "1445"
        var duration: String = "0200"
        userEventRecurring = UserEvent(frequency, "2/13/2021", startTime, duration)

        // Creates a user event that only occurs on one day
        // Time are stored as Strings in military time
        startTime = "1445"
        duration = "0200"
        userEvent = UserEvent("2/13/2021", startTime, duration)
    }

    @Test
    fun userEventRecurring_maintainsState() {
        // Ensures object persists
        assertTrue(userEventRecurring.frequency!! == frequency)
        assertTrue(userEventRecurring.startDate == "2/13/2021")
        assertTrue(userEventRecurring.startTime == "1445")
        assertTrue(userEventRecurring.duration == "0200")
    }

    @Test
    fun userEventOneTime_maintainsState() {
        // Ensures object persists
        assertTrue(userEvent.startDate == "2/13/2021")
        assertTrue(userEvent.startTime == "1445")
        assertTrue(userEvent.duration == "0200")
    }

    @Test
    fun userEvent_invalidDate() {
        // Ensure events with invalid dates cannot be made
        try {
            var startTime: String = "1445"
            var duration: String = "0200"
            invalidUserEvent = UserEvent("13/13/2021", startTime, duration)
        } catch (ex: Exception) {
            print(ex)
        } finally {
            assertNull(invalidUserEvent)
        }
    }

    @Test
    fun userEvent_invalidStartTime() {
        // Ensure events with an invalid startTime cannot be made
        try {
            var startTime: String = "2500"
            var duration: String = "0200"
            invalidUserEvent = UserEvent("2/13/2021", startTime, duration)
        } catch (ex: Exception) {
            print(ex)
        } finally {
            assertNull(invalidUserEvent)
        }
    }

    @Test
    fun userEvent_invalidDuration() {
        // Ensure events with invalid Durations cannot be made
        try {
            var startTime: String = "1445"
            var duration: String = "2430"
            invalidUserEvent = UserEvent("2/13/2021", startTime, duration)
        } catch (ex: Exception) {
            print(ex)
        } finally {
            assertNull(invalidUserEvent)
        }
    }

    @Test
    fun userEventRecurring_invalidFrequency() {
        // Ensure events with invalid Durations cannot be made
        try {
            // Creates a user event that repeats on nonexistent days
            var days: ArrayList<String> = ArrayList<String>()
            days.plus("X")
            days.plus("Q")
            // Source: https://www.techiedelight.com/initialize-map-kotlin/
            frequency = mapOf(
                    "weekly" to days
            )
            // Time are stored as Strings in military time
            var startTime: String = "1445"
            var duration: String = "0200"
            invalidUserEvent = UserEvent(frequency, "2, 13 2021", startTime, duration)
        } catch (ex: Exception) {
            print(ex)
        } finally {
            assertNull(invalidUserEvent)
        }
    }

}