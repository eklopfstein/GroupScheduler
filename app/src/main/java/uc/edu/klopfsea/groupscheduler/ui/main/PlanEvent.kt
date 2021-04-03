package uc.edu.klopfsea.groupscheduler.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.plan_event_fragment.*
import uc.edu.klopfsea.groupscheduler.R
import uc.edu.klopfsea.groupscheduler.dto.PlannedEvent

class PlanEvent : Fragment() {

    companion object {
        fun newInstance() = PlanEvent()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.plan_event_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let {
            viewModel = ViewModelProviders.of(it!!).get(MainViewModel::class.java)
        }
        viewModel.addresses.observe(viewLifecycleOwner, Observer { addresses ->
            editTextTextPostalAddress.setAdapter(ArrayAdapter(context!!, R.layout.support_simple_spinner_dropdown_item, addresses))
        })
        btnAddEvent.setOnClickListener {
            savePlannedEvent()
        }
    }

    private fun savePlannedEvent() {
        var plannedEvent = PlannedEvent()
        with(plannedEvent) {
            eventName = planeventname.toString()
            eventDate = editTextDate.text.toString()
            plannedTime = editTextTime.text.toString()
            notes = edtnotes.text.toString()
            address = edtAddress.text.toString()
            city = edtCity.text.toString()
            state = edtState.toString()
        }
        viewModel.savePlanned(plannedEvent)
    }

}