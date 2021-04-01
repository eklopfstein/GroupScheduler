package uc.edu.klopfsea.groupscheduler.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import uc.edu.klopfsea.groupscheduler.dto.NewEvent

class NewEventViewModel : ViewModel() {

    private lateinit var firestore: FirebaseFirestore

    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun saveNew(newEvents: NewEvent) {
        val document = firestore.collection("newevent").document()
        newEvents.newEventId = document.id
        val set = document.set(newEvents)
                set.addOnSuccessListener {
                    Log.d("firebase", "document saved")
                }
                set.addOnFailureListener {
                    Log.d("firebase","save failed")
                }
    }

}