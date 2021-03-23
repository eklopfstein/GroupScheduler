package uc.edu.klopfsea.groupscheduler.service

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import uc.edu.klopfsea.groupscheduler.RetroFitClientInstance
import uc.edu.klopfsea.groupscheduler.dao.IAddressDAO
import uc.edu.klopfsea.groupscheduler.dto.Address
import retrofit2.Callback
import retrofit2.Response

class AddressService {

    fun fetchAddresses(zipCode : String) : MutableLiveData<ArrayList<Address>>{
        var _addresses = MutableLiveData<ArrayList<Address>>()
        val service = RetroFitClientInstance.retrofitInstace?.create(IAddressDAO::class.java)
        val call = service?.getAllAddress()
        call?.enqueue(object: Callback<ArrayList<Address>> {
            override fun onFailure(call: Call<ArrayList<Address>>, t: Throwable) {
                print("Could not retrieve service response")
            }

            override fun onResponse(call: Call<ArrayList<Address>>, response: Response<ArrayList<Address>>) {
                _addresses.value = response.body()
            }
        })
        return _addresses
    }
}