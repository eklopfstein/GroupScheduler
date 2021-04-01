package uc.edu.klopfsea.groupscheduler.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.schedule_fragment.*
import uc.edu.klopfsea.groupscheduler.R
import uc.edu.klopfsea.groupscheduler.dto.Schedule

class ScheduleFragment : Fragment() {

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.schedule_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let {
            viewModel = ViewModelProviders.of(it!!).get(MainViewModel::class.java)
        }
        btnAddEvent.setOnClickListener {
            saveSchedule()
        }
    }
    private fun saveSchedule() {
        var schedule = Schedule().apply {
            radio_group.setOnCheckedChangeListener { group, checkedId ->
                if (checkedId == R.id.radio_days) {
                    when {
                        monday.isChecked -> {
                            day = monday.text.toString()
                        }
                        tuesday.isChecked -> {
                            day = tuesday.text.toString()
                        }
                        wednesday.isChecked -> {
                            day = wednesday.text.toString()
                        }
                        thursday.isChecked -> {
                            day = thursday.text.toString()
                        }
                        friday.isChecked -> {
                            day = friday.text.toString()
                        }
                        saturday.isChecked -> {
                            day = saturday.text.toString()
                        }
                        sunday.isChecked -> {
                            day = sunday.text.toString()
                        }
                    }
                }
                if (checkedId == R.id.radio_date) {
                    date = editTextDate.text.toString()
                }
            }
            time = editTextTime.text.toString()
        }
        viewModel.save(schedule)
    }
}