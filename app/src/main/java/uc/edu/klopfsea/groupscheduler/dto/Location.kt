package uc.edu.klopfsea.groupscheduler.dto

import com.google.gson.annotations.SerializedName

// {"post code": "45219", "country": "United States", "country abbreviation": "US", "places": [{"place name": "Cincinnati", "longitude": "-84.5131", "state": "Ohio", "state abbreviation": "OH", "latitude": "39.127"}]}
class Location(
    @SerializedName("place name") private var name: String,
    @SerializedName("longitude") private var longitude: String,
    @SerializedName("state") private var state: String,
    @SerializedName("state abbreviation") private var stateAbbreviation: String,
    @SerializedName("latitude") private var latitude: String
) {

    val city: String
        get() {
            return name
        }
    val stateName: String
        get() {
            return state
        }
}