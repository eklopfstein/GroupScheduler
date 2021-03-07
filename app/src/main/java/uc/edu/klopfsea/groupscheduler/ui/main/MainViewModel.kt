package uc.edu.klopfsea.groupscheduler.ui.main

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

/***
 * connection and build the firebase firestore into our code
 */
class MainViewModel : ViewModel() {
    public var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

}