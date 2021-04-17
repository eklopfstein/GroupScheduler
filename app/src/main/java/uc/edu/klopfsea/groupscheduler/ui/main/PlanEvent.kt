package uc.edu.klopfsea.groupscheduler.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.plan_event_fragment.*
import uc.edu.klopfsea.groupscheduler.MainActivity
import uc.edu.klopfsea.groupscheduler.R
import uc.edu.klopfsea.groupscheduler.dto.Address
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


        editTextPostalAddress.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (editTextPostalAddress.text.length == 5) {
                    viewModel.fetchCityAndState(editTextPostalAddress.text.toString())
                    updateLocation()
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (editTextPostalAddress.text.length == 5) {
                    viewModel.fetchCityAndState(editTextPostalAddress.text.toString())
                    updateLocation()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                if (editTextPostalAddress.text.length == 5) {
                    viewModel.fetchCityAndState(editTextPostalAddress.text.toString())
                    updateLocation()
                }
            }

            fun updateLocation() {
                viewModel.addresses.observe(viewLifecycleOwner, Observer {
                        addresses -> edtCityAndState.setAdapter(ArrayAdapter(context!!, R.layout.support_simple_spinner_dropdown_item, addresses.location))
                })
                viewModel.fetchCityAndState(editTextPostalAddress.text.toString())
            }
        })
    }

    private fun savePlannedEvent() {
        var cityAndState: List<String> = edtCityAndState.text.toString().split(",")
        var plannedEvent = PlannedEvent()
        with(plannedEvent) {
            eventName = planeventname.toString()
            eventDate = editTextDate.text.toString()
            plannedTime = editTextTime.text.toString()
            address = edtAddress.text.toString()
            city = cityAndState[0]
            state = cityAndState[1]
            //addresses = editTextTextPostalAddress.setAdapter(context!!, ArrayAdapter<Address>())
            notes = edtnotes.text.toString()
        }
        viewModel.savePlanned(plannedEvent)
    }

}