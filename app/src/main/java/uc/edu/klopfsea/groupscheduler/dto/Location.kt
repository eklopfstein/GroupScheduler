package uc.edu.klopfsea.groupscheduler.dto

import com.google.gson.annotations.SerializedName

class Location(
        @SerializedName("place name") private var name: String,
        @SerializedName("longitude") private var longitude: String,
        @SerializedName("state") private var state: String,
        @SerializedName("state abbreviation") private var stateAbbreviation: String,
        @SerializedName("latitude") private var latitude: String
) {

    /***
     * Create access point of city name
     */
    val city: String
        get() {
            return name
        }

    /***
     * Create access point of state name
     */
    val stateName: String
        get() {
            return state
        }

    /***
     * Format string to be the city and state
     */
    override fun toString(): String {
        return "$city, $stateName"
    }


}