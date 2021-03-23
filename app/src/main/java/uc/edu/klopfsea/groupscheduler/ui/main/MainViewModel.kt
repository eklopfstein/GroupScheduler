package uc.edu.klopfsea.groupscheduler.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import uc.edu.klopfsea.groupscheduler.dto.Address
import uc.edu.klopfsea.groupscheduler.service.AddressService

/***
 * connection and build the firebase firestore into our code
 */
class MainViewModel : ViewModel() {
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    /*var addresses : MutableLiveData<ArrayList<Address>> = MutableLiveData<ArrayList<Address>>()
    var addressService : AddressService = AddressService()

    fun fetchZipCodes(zipCode : String) {
        addresses = addressService.fetchAddresses(zipCode)
    }*/

    init {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

}