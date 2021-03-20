package uc.edu.klopfsea.groupscheduler.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uc.edu.klopfsea.groupscheduler.R

class NewEvent : Fragment() {

    companion object {
        fun newInstance() = NewEvent()
    }

    private lateinit var viewModel: NewEventViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.new_event_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewEventViewModel::class.java)
        // TODO: Use the ViewModel
    }

}