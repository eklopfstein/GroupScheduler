package uc.edu.klopfsea.groupscheduler.service

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uc.edu.klopfsea.groupscheduler.RetroFitClientInstance
import uc.edu.klopfsea.groupscheduler.dao.IAddressDAO
import uc.edu.klopfsea.groupscheduler.dto.Address

class AddressService {

    fun fetchCityAndState(zipCode: String): MutableLiveData<Address> {
        val zipCode = zipCode.replace("\\s".toRegex(), "")
        var _addresses = MutableLiveData<Address>()
        val service = RetroFitClientInstance.retrofitInstace?.create(IAddressDAO::class.java)
        val call = service?.getLocation("https://api.zippopotam.us/us/$zipCode")
        call?.enqueue(object : Callback<Address> {
            override fun onFailure(call: Call<Address>, t: Throwable) {
                println("here")
                print("Could not retrieve service response")
            }

            override fun onResponse(call: Call<Address>, response: Response<Address>) {
                println("here")
                _addresses.value = response.body()
            }
        })
        return _addresses
    }
}