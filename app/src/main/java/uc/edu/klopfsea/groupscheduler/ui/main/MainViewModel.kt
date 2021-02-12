package uc.edu.klopfsea.groupscheduler.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import uc.edu.klopfsea.groupscheduler.UserGroupsDTO

class MainViewModel : ViewModel() {
    private lateinit var firestore : FirebaseFirestore

    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun save(userGroups: UserGroupsDTO) {
        val document = firestore.collection("User Groups").document()
        userGroups.userGroupID = document.id
        val set = document.set(userGroups)
            set.addOnSuccessListener {
                Log.d("Firebase", "document saved")
            }
            set.addOnFailureListener {
                Log.d("Firebase", "Save Failed")
            }
    }
    // TODO: Implement the ViewModel
}