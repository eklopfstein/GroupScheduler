package uc.edu.klopfsea.groupscheduler.dto

import com.google.gson.annotations.SerializedName

data class Address(
        @SerializedName("post code") var zipCode : String = "",
        @SerializedName("place name") var city : String = ""){
    override fun toString(): String {
        return "$zipCode $city"
    }
}
