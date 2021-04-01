package uc.edu.klopfsea.groupscheduler.Events

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class EventVerifier {

    /***
     * Overloaded function for verifying all event types
     */
    fun verifyEvent(event: PlannedEvent): Boolean {
        if (!dateVerification(event.date)) {
            return false
        }
        if (!timeVerification(event.startTime)) {
            return false
        }
        if (!nameVerification(event.name)) {
            return false
        }
        return true
    }

    /***
     * Overloaded function for verifying all event types
     */
    fun verifyEvent(event: UnplannedEvent): Boolean {
        if (!timeVerification(event.duration)) {
            return false
        }
        if (!nameVerification(event.name)) {
            return false
        }
        return true
    }

    /***
     * Overloaded function for verifying all event types
     */
    fun verifyEvent(event: UserEvent): Boolean {
        if (!dateVerification(event.startDate)) {
            return false
        }
        if (!timeVerification(event.startTime)) {
            return false
        }
        if (!timeVerification(event.duration)) {
            return false
        }
        if (event.frequency != null) {
            if (!event.frequency?.let { frequencyVerification(it) }!!) {
                return false
            }
        }

        return true
    }

    /**
     * Verify user given date
     */
    private fun dateVerification(date: String): Boolean {
        // try converting string to Date
        try {
            val formatter = DateTimeFormatter.ofPattern("u-MM-d")
            val date = LocalDate.parse(date, formatter)
            val text = date.format(formatter)
            val parsedDate = LocalDate.parse(text, formatter)
        } catch (ex: Exception) {
            // If it fails throw the exception and fail the test
            throw IllegalArgumentException("Invalid Event Date")
            return false
        }
        // return true if it passes
        return true

    }

    private fun timeVerification(time: String): Boolean {
        try {
            // convert user given time to float
            time.toFloat()
        } catch (ex: Exception) {
            // If it fails throw exception and fail the test
            throw IllegalArgumentException("Invalid Event Time")
            return false
        } finally {
            // If succeeds verify float is within 2400 and positive
            if (time.toFloat() < 0 || time.toFloat() > 2400) {
                // if not fail test and throw exception
                throw IllegalArgumentException("Invalid Event Time")
                return false
            }
        }
        // if it is within frame pass the test
        return true
    }

    private fun nameVerification(name: String): Boolean {
        // check if name is empty
        if (name.isEmpty()) {
            // if it is throw the exception and fail the test
            throw IllegalArgumentException("Invalid Event Name")
            return false
        }
        // if not pass the test
        return true

    }

    private fun frequencyVerification(frequency: Map<String, ArrayList<String>>): Boolean {
        // loop through frequency map
        for ((frequency, days) in frequency) {
            // loop through days list
            for (day in days) {
                // check if all days are valid days
                if (day == "M" || day == "T" || day == "W" || day == "H" || day == "F" || day == "S" || day == "U") {
                } else {
                    // if not throw exception and fail test
                    throw IllegalArgumentException("Invalid Event Frequency")
                    return false
                }
            }
        }
        // if all days are valid pass the test
        return true
    }
}