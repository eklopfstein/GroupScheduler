package uc.edu.klopfsea.groupscheduler.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.new_event_fragment.*
import uc.edu.klopfsea.groupscheduler.R

class NewEvent : Fragment() {

    companion object {
        fun newInstance() = NewEvent()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.new_event_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let {
            viewModel = ViewModelProvider(it!!).get(MainViewModel::class.java)
        }

        btnAddEvent.setOnClickListener {
            saveNewEvent()
        }
    }

    private fun saveNewEvent() {
        var newevent = uc.edu.klopfsea.groupscheduler.dto.NewEventDto().apply {
            newEventName = eventNametxtbox.text.toString()
            hour = hours.text.toString().toInt()
            minute = minutes.text.toString().toInt()
        }
        viewModel.saveNew(newevent)
    }

}