package uc.edu.klopfsea.groupscheduler

import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uc.edu.klopfsea.groupscheduler.Events.UserEvent
import java.util.*
import kotlin.collections.ArrayList

/***
 * Test the UserEvent class for functionality
 */
class UserEventUnitTest {

    @get:Rule
    lateinit var userEventRecurring: UserEvent
    lateinit var userEvent: UserEvent
    lateinit var frequency: Map<String, ArrayList<String>>

    @Before
    fun createUserEventRecurring() {
        // Creates a user event that repeats on certain days each week and has a set start date
        var days: ArrayList<String> = ArrayList<String>()
        days.plus("M")
        days.plus("H")
        var pair = Pair("weekly", days)
        frequency.plus(pair)
        // Time are stored as Strings in military time
        var startTime: String = "1445"
        var duration: String = "0200"
        userEventRecurring = UserEvent(frequency, "2, 13 2021", startTime, duration)
    }

    @Before
    fun createUserEventOneTime() {
        // Creates a user event that only occurs on one day
        // Time are stored as Strings in military time
        var startTime: String = "1445"
        var duration: String = "0200"
        userEvent = UserEvent("2, 13 2021", startTime, duration)
    }

    @Test
    fun userEventRecurring_maintainsState() {
        // Ensures object persists
        assertTrue(userEventRecurring.frequency!!.equals(frequency))
        assertTrue(userEventRecurring.startDate.equals("2, 13 2021"))
        assertTrue(userEventRecurring.startTime.equals("1445"))
        assertTrue(userEventRecurring.duration.equals("0200"))
    }

    @Test
    fun userEventOneTime_maintainsState() {
        // Ensures object persists
        assertTrue(userEvent.startDate.equals(Date(2021, 2, 13)))
        assertTrue(userEvent.startTime.equals("1445"))
        assertTrue(userEvent.duration.equals("0200"))
    }

}