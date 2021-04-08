package uc.edu.klopfsea.groupscheduler.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import uc.edu.klopfsea.groupscheduler.dto.PlannedEvent

class PlanEventViewModel : ViewModel() {
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
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

}