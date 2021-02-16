package uc.edu.klopfsea.groupscheduler.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uc.edu.klopfsea.groupscheduler.R
import uc.edu.klopfsea.groupscheduler.UserGroupsDTO
import java.sql.Date
import java.sql.Timestamp

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }
    private fun saveUserGroups() {
        var userGroups = UserGroupsDTO(groupName = "",groupDay = "", groupPicture = false,dateTime = java.util.Date()).apply {
            groupName = ""  ; //Group name variable
            groupPicture = false ; // group pic variable
            dateTime = dateTime;//textView3.text.toString();
            groupDay = "";
        }
        viewModel.save(userGroups)
    }
}