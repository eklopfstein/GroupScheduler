package uc.edu.klopfsea.groupscheduler.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.schedule_fragment.*
import uc.edu.klopfsea.groupscheduler.R
import uc.edu.klopfsea.groupscheduler.dto.Schedule

class ScheduleFragment : Fragment() {

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    private lateinit var viewModel: ScheduleViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.schedule_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
        btnAddEvent.setOnClickListener {
            saveSchedule()
        }
    }
    private fun saveSchedule() {
        var schedule = Schedule().apply {
            if (radio_days.isChecked) {
                if (monday.isChecked) {
                    day = monday.text.toString()
                }
                else if(tuesday.isChecked) {
                    day = tuesday.text.toString()
                }
                else if(wednesday.isChecked) {
                    day = wednesday.text.toString()
                }
                else if(thursday.isChecked) {
                    day = thursday.text.toString()
                }
                else if(friday.isChecked) {
                    day = friday.text.toString()
                }
                else if(saturday.isChecked) {
                    day = saturday.text.toString()
                }
                else if (sunday.isChecked) {
                    day = sunday.text.toString()
                }
            }
            if (radio_date.isChecked) {
                date = editTextDate.text.toString()
            }
            time = editTextTime.text.toString()
        }
        viewModel.save(schedule)
    }
}