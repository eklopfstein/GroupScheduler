package uc.edu.klopfsea.groupscheduler.dto

import com.google.gson.annotations.SerializedName

data class Address(
        @SerializedName("post code") private var postCode: String,
        @SerializedName("country") private var country: String,
        @SerializedName("country abbreviation") private var countryAbbreviation: String,
        @SerializedName("places") private var placeInformation: ArrayList<Location>
) {
    /***
     * Format toString show the city and state
     */
    override fun toString(): String {
        return placeInformation[0].city + ", " + placeInformation[0].stateName
    }

    /***
     * Get the city and state of the zip code
     */
    val location: ArrayList<Location>
        get() {
            return placeInformation
        }
}
