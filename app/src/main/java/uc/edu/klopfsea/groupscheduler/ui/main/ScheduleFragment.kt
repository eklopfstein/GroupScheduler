package uc.edu.klopfsea.groupscheduler.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.schedule_fragment.*
import uc.edu.klopfsea.groupscheduler.MainActivity
import uc.edu.klopfsea.groupscheduler.R
import uc.edu.klopfsea.groupscheduler.dto.Schedule
import java.lang.StringBuilder

class ScheduleFragment : Fragment() {
    private val checkedRadioButton1 = radio_group?.checkedRadioButtonId


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
        btnClose.setOnClickListener {
            (activity as MainActivity).onSwipeRight()
        }
        radio_days.setOnCheckedChangeListener { buttonView, isChecked ->
            monday.isEnabled = false
            tuesday.isEnabled = false
            wednesday.isEnabled = false
            thursday.isEnabled = false
            friday.isEnabled = false
            saturday.isEnabled = false
            sunday.isEnabled = false
            editTextDate.isEnabled = true
        }
        radio_date.setOnCheckedChangeListener { buttonView, isChecked ->
            editTextDate.isEnabled = false
            monday.isEnabled = true
            tuesday.isEnabled = true
            wednesday.isEnabled = true
            thursday.isEnabled = true
            friday.isEnabled = true
            saturday.isEnabled = true
            sunday.isEnabled = true
        }
        btnAddEvent.setOnClickListener {
            saveSchedule()
        }
    }

    internal fun saveSchedule() {
        var schedule = Schedule().apply {

            val checkedRadioButton1 = radio_group.checkedRadioButtonId
            val after = radio_after.text.toString()
            val before = radio_before.text.toString()
            val allDay = radio_allday.text.toString()
            val timeString = editTextTime.text.toString()
            //val newTime = timeString.toString()
            val afterString = "$after $timeString"
            val beforeString = "$before $timeString"
            val allString = "$allDay"


            if (checkedRadioButton1.equals(radio_days))
            else if (monday.isChecked)
                day += monday.text.toString()

            if (tuesday.isChecked)
                day += tuesday.text.toString()

            if (wednesday.isChecked)
                day += wednesday.text.toString()

            if (thursday.isChecked)
                day += thursday.text.toString()

            if (friday.isChecked)
                day += friday.text.toString()

            if (saturday.isChecked)
                day += saturday.text.toString()

            if (sunday.isChecked)
                day += sunday.text.toString()

            date = editTextDate.text.toString()

            val checkedRadioButton2 = radio_group2.checkedRadioButtonId
            when (checkedRadioButton2) {
                R.id.radio_after -> time = afterString
                R.id.radio_before -> time = beforeString
                R.id.radio_allday -> time = allString
            }

        }
        viewModel.save(schedule)
    }
}

