package uc.edu.klopfsea.groupscheduler.dao

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import uc.edu.klopfsea.groupscheduler.dto.Address

interface IAddressDAO {

    @GET("/us/oh/cincinnati")
    fun getAllAddress(): Call<ArrayList<Address>>

    @GET("/us/oh/cincinnati")
    fun getZipCodes(@Query("Combined_Name") zipCode: String): Call<ArrayList<Address>>

    @GET
    fun getLocation(@Url zipCodeUrl: String): Call<Address>
}