package uc.edu.klopfsea.groupscheduler.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import uc.edu.klopfsea.groupscheduler.dto.Schedule

class ScheduleViewModel : ViewModel() {
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

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
}