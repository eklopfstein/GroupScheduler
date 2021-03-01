package uc.edu.klopfsea.groupscheduler

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import uc.edu.klopfsea.groupscheduler.Events.UserEvent
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.Map

/***
 * Test the UserEvent class for functionality
 */
class UserEventUnitTest {

    @get:Rule
    lateinit var userEvent: UserEvent
    lateinit var frequency : Map<String, ArrayList<String>>

    @Before
    fun createUserEventRecurring() {
        // Creates a user event that repeats on certain days each week and has a set start date
        var days : ArrayList<String> = ArrayList<String>()
        days.plus("M")
        days.plus("H")
        var pair = Pair("weekly", days)
        frequency.plus(pair)
        // Time are stored as Strings in military time
        var startTime : String  = "1445"
        var duration : String = "0200"
        var userEvent = UserEvent(frequency, Date(2021, 2, 13), startTime, duration)
    }

    @Before
    fun createUserEventOneTime() {
        // Creates a user event that only occurs on one day
        // Time are stored as Strings in military time
        var startTime : String  = "1445"
        var duration : String = "0200"
        var userEvent = UserEvent(Date(2021, 2, 13), startTime, duration)
    }

    @Test
    fun userEventRecurring_maintainsState() {
        // Ensures object persists
        assertTrue(userEvent.frequency.equals(frequency))
        assertTrue(userEvent.startDate.equals(Date(2021, 2, 13)))
        assertTrue(userEvent.startTime.equals("1445"))
        assertTrue(userEvent.duration.equals("0200"))
    }

    @Test
    fun userEventOneTime_maintainsState() {
        // Ensures object persists
        assertTrue(userEvent.date.equals(Date(2021, 2, 13)))
        assertTrue(userEvent.startTime.equals("1445"))
        assertTrue(userEvent.duration.equals("0200"))
    }

}