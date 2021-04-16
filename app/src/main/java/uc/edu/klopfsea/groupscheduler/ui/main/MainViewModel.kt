package uc.edu.klopfsea.groupscheduler.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import uc.edu.klopfsea.groupscheduler.Events.EventsContainer
import uc.edu.klopfsea.groupscheduler.Events.UnplannedEvent
import uc.edu.klopfsea.groupscheduler.Events.UserEvent
import uc.edu.klopfsea.groupscheduler.dto.Address
import uc.edu.klopfsea.groupscheduler.dto.NewEventDto
import uc.edu.klopfsea.groupscheduler.dto.PlannedEvent
import uc.edu.klopfsea.groupscheduler.dto.Schedule
import uc.edu.klopfsea.groupscheduler.service.AddressService

/***
 * connection and build the firebase firestore into our code
 */
class MainViewModel : ViewModel() {
    var addresses: MutableLiveData<Address> = MutableLiveData<Address>()
    var addressService: AddressService = AddressService()
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var plannedEvents: ArrayList<uc.edu.klopfsea.groupscheduler.Events.PlannedEvent> = ArrayList<uc.edu.klopfsea.groupscheduler.Events.PlannedEvent>()
    private var unplannedEvents: ArrayList<UnplannedEvent> = ArrayList<UnplannedEvent>()
    private var userEvents: ArrayList<UserEvent> = ArrayList<UserEvent>()
    private var events: EventsContainer = EventsContainer(plannedEvents, unplannedEvents, userEvents)

    fun fetchCityAndState(zipCode: String) {
        addresses = addressService.fetchCityAndState(zipCode)
    }

    init {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun save(schedule: Schedule) {
        val document = firestore.collection("schedule").document()
        schedule.scheduleId = document.id
        val set = document.set(schedule)
        set.addOnSuccessListener {
            Log.d("Firebase", "Document Save Successful")
        }
        set.addOnFailureListener {
            Log.d("Firebase", "Save failure")
        }
    }

    fun savePlanned(plannedEvent: PlannedEvent) {
        val document = firestore.collection("plannedevents").document()
        plannedEvent.plannedEventId = document.id
        val set = document.set(plannedEvent)
        set.addOnSuccessListener {
            Log.d("Firebase", "Document Saved!")
        }
        set.addOnFailureListener {
            Log.d("Firebase", "Save failed")
        }
    }

    fun saveNew(newEvents: NewEventDto) {
        val document = firestore.collection("newevent").document()
        newEvents.newEventId = document.id
        val set = document.set(newEvents)
        set.addOnSuccessListener {
            Log.d("firebase", "document saved")
        }
        set.addOnFailureListener {
            Log.d("firebase", "save failed")
        }
    }
}