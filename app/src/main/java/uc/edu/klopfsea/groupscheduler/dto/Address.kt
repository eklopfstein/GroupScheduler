package uc.edu.klopfsea.groupscheduler.dto

import com.google.gson.annotations.SerializedName
// {"post code": "45219", "country": "United States", "country abbreviation": "US", "places": [{"place name": "Cincinnati", "longitude": "-84.5131", "state": "Ohio", "state abbreviation": "OH", "latitude": "39.127"}]}
data class Address(
    @SerializedName("post code") private var postCode: String,
    @SerializedName("country") private var country: String,
    @SerializedName("country abbreviation") private var countryAbbreviation: String,
    @SerializedName("places") private var placeInformation: ArrayList<Location>
) {
    override fun toString(): String {
        return placeInformation[0].city.toString() + ", " + placeInformation[0].stateName.toString()
    }

    val location: ArrayList<Location>
        get() {
            return placeInformation
        }
}
