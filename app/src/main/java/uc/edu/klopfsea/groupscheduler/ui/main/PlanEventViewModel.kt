package uc.edu.klopfsea.groupscheduler.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import uc.edu.klopfsea.groupscheduler.dto.Address
import uc.edu.klopfsea.groupscheduler.dto.PlannedEvent
import uc.edu.klopfsea.groupscheduler.service.AddressService

class PlanEventViewModel : ViewModel() {
    var addresses : MutableLiveData<ArrayList<Address>> = MutableLiveData<ArrayList<Address>>()
    var addressService : AddressService = AddressService()
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
    //    fetchZipCodes("e")
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

 /*   fun fetchZipCodes(zipCode : String) {
        addresses = addressService.fetchAddresses(zipCode)
    }*/

    fun save(plannedEvent: PlannedEvent) {
        val document = firestore.collection("plannedevents").document()
        plannedEvent.plannedEventId = document.id
        val set = document.set(plannedEvent)
           set.addOnSuccessListener {
                  Log.d("Firebase","Document Saved!")
                }
           set.addOnFailureListener {
                    Log.d("Firebase","Save failed")
                }
    }

}