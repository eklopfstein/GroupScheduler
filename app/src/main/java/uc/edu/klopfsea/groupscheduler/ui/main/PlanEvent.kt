package uc.edu.klopfsea.groupscheduler.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        btnAddEvent.setOnClickListener {
            savePlannedEvent()
        }


        edtZipCode.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (edtZipCode.text.length == 5) {
                    viewModel.fetchCityAndState(edtZipCode.text.toString())
                    updateLocation()
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (edtZipCode.text.length == 5) {
                    viewModel.fetchCityAndState(edtZipCode.text.toString())
                    updateLocation()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if (edtZipCode.text.length == 5) {
                    viewModel.fetchCityAndState(edtZipCode.text.toString())
                    updateLocation()
                }
            }

            fun updateLocation() {
                viewModel.addresses.observe(viewLifecycleOwner, Observer {

                        addresses ->
                    edtCity.equals(addresses.location[0].city)

                })
                viewModel.addresses.observe(viewLifecycleOwner, Observer {

                        addresses ->
                    edtState.equals(addresses.location[0].stateName)
                })
            }
        })
    }

    private fun savePlannedEvent() {
        var plannedEvent = PlannedEvent()
        with(plannedEvent) {
            eventName = planeventname.toString()
            eventDate = editTextDate.text.toString()
            plannedTime = editTextTime.text.toString()
            notes = edtnotes.text.toString()
            address = edtAddress.text.toString()
            city = edtCity.toString()
            state = edtState.toString()
        }
        viewModel.savePlanned(plannedEvent)
    }

}