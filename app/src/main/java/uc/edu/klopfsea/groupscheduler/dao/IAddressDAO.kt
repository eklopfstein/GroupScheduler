package uc.edu.klopfsea.groupscheduler.dao

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url
import uc.edu.klopfsea.groupscheduler.dto.Address

interface IAddressDAO {

    @GET
    fun getLocation(@Url zipCodeUrl: String): Call<Address>
}