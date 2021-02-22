package uc.edu.klopfsea.groupscheduler.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.addevent_layout.*
import kotlinx.android.synthetic.main.main_activity.*
import uc.edu.klopfsea.groupscheduler.R
import uc.edu.klopfsea.groupscheduler.UserGroupsDTO
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.*



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
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.userGroups.observe(viewLifecycleOwner, Observer {
            userGroups -> txtDayValue.text
            //this needs test
        })


        btnAddEvent.setOnClickListener {
            saveUserGroups()
        }

        // TODO: Use the ViewModel
    }
    private fun saveUserGroups() {
        var userGroups = UserGroupsDTO().apply {
            groupName = ""  ; //Group name variable
            groupPicture = false ; // group pic variable
            dateTime = dateTime;//textView8.text.toString();
            groupDay = txtDayValue.text.toString()
        }
        viewModel.save(userGroups)
    }
}

