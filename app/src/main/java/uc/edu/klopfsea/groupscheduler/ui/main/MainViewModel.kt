package uc.edu.klopfsea.groupscheduler.ui.main

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import uc.edu.klopfsea.groupscheduler.UserGroupsDTO
import uc.edu.klopfsea.groupscheduler.service.userGroupsService

class MainViewModel : ViewModel() {
    var userGroupsDTO: MutableLiveData<ArrayList<UserGroupsDTO>> = MutableLiveData<ArrayList<UserGroupsDTO>>()
    var userGroupsService: userGroupsService = userGroupsService()
    private lateinit var firestore: FirebaseFirestore
    private var _userGroups: MutableLiveData<ArrayList<UserGroupsDTO>> = MutableLiveData<ArrayList<UserGroupsDTO>>()

    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        listenToUserGroupsDTO()
    }

    //this will hear updates from Fire store
    private fun listenToUserGroupsDTO() {
        firestore.collection("User Groups").addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Failed to Listen", e)
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val allUserGroups = ArrayList<UserGroupsDTO>()
                val documents = snapshot.documents
                documents.forEach {
                    val userGroup = it.toObject(UserGroupsDTO::class.java)
                    if (userGroup != null) {
                        allUserGroups.add(userGroup!!)
                    }
                }
                _userGroups.value = allUserGroups
            }
        }
    }

    fun save(userGroup: UserGroupsDTO) {
        val document = firestore.collection("User Groups").document()
        userGroup.userGroupID = document.id
        val set = document.set(userGroup)
        set.addOnSuccessListener {
            Log.d("Firebase", "document saved")
        }
        set.addOnFailureListener {
            Log.d("Firebase", "Save Failed")
        }
    }
    // TODO: Implement the ViewModel

    internal var userGroups: MutableLiveData<ArrayList<UserGroupsDTO>>
        get() {
            return _userGroups
        }
        set(value) {
            _userGroups = value
        }
}