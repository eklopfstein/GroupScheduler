package uc.edu.klopfsea.groupscheduler.ui.main

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.addevent_layout.*
import uc.edu.klopfsea.groupscheduler.R
import uc.edu.klopfsea.groupscheduler.model.Event
import java.text.SimpleDateFormat
import java.util.*

class AddEvent : Activity(), AdapterView.OnItemSelectedListener {
    lateinit var spinner: Spinner
    lateinit var btnAddEvent: Button
    lateinit var btnClose: ImageButton
    lateinit var etDate: EditText

    var period = arrayListOf<String>()
    var periodSelected = 0
    private val days = arrayListOf<String>()
    var timeSpecification = -1
    private val event = Event(isDateSet = false, isDaysSet = false, days = "", date = "", time = "", period = "AM", timeSpecification = "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.addevent_layout)


        spinner = findViewById(R.id.period)
        btnAddEvent = findViewById(R.id.btnAddEvent)
        etDate = findViewById(R.id.editTextDate)
        btnClose = findViewById(R.id.btnClose)
        period.add("AM")
        period.add("PM")

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, period)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this

        btnAddEvent.setOnClickListener {

            val time = findViewById<EditText>(R.id.editTextTime).text.toString()
            println(time)
            println(timeSpecification)
            Log.d("Time", time)
            Log.d("TimeSpecification", timeSpecification.toString())
            if ((time.isNotEmpty() && timeSpecification != -1) || timeSpecification == 2) {
                if (findViewById<RadioButton>(R.id.radio_days).isChecked) {
                    if (days.isEmpty()) {
                        Toast.makeText(this, "Days not selected", Toast.LENGTH_SHORT).show()
                    } else {
                        event.isDaysSet = true
                        event.days = setDays().toString()
                        System.out.println(event.days)
                        eventAddSuccess(time)
                    }
                } else if (findViewById<RadioButton>(R.id.radio_date).isChecked) {
                    val date = etDate.text.toString()
                    if (date.isNotEmpty() && isDateValid(date)) {
                        event.isDateSet = true
                        event.date = date
                        eventAddSuccess(time)
                    } else {
                        Toast.makeText(this, "Invalid Date Format", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Invalid Entry", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Invalid Entry 1", Toast.LENGTH_SHORT).show()
            }
        }

        btnClose.setOnClickListener {
            onBackPressed()
        }
    }

    fun eventAddSuccess(time: String) {
        event.time = time
        event.setTime(timeSpecification)
    }

    fun isDateValid(dateString: String): Boolean {
        try {
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val date = formatter.parse(dateString)
            System.out.println(date)
        } catch (e: Exception) {
            return false
        }
        return true
    }


    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        periodSelected = position
    }

    fun setDays(): StringBuilder {
        var dayString = StringBuilder("")
        if ("M" in days)
            dayString.append("M,")
        if ("T" in days)
            dayString.append("T,")
        if ("W" in days)
            dayString.append("W,")
        if ("R" in days)
            dayString.append("R,")
        if ("F" in days)
            dayString.append("F,")
        if ("S" in days)
            dayString.append("S,")
        if ("U" in days)
            dayString.append("U,")
        dayString.deleteCharAt(dayString.length - 1)
        return dayString
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            when (view.id) {
                R.id.radio_days -> {
                    findViewById<RadioButton>(R.id.radio_date).isChecked = false
                    findViewById<EditText>(R.id.editTextDate).isEnabled = false
                    findViewById<CheckBox>(R.id.monday).isEnabled = true
                    findViewById<CheckBox>(R.id.tuesday).isEnabled = true
                    findViewById<CheckBox>(R.id.wednesday).isEnabled = true
                    findViewById<CheckBox>(R.id.thursday).isEnabled = true
                    findViewById<CheckBox>(R.id.friday).isEnabled = true
                    findViewById<CheckBox>(R.id.saturday).isEnabled = true
                    findViewById<CheckBox>(R.id.sunday).isEnabled = true
                    event.isDaysSet = true
                    event.isDateSet = false
                }
                R.id.radio_date -> {
                    view.isChecked = true
                    findViewById<RadioButton>(R.id.radio_days).isChecked = false
                    findViewById<EditText>(R.id.editTextDate).isEnabled = true
                    findViewById<CheckBox>(R.id.monday).isEnabled = false
                    findViewById<CheckBox>(R.id.tuesday).isEnabled = false
                    findViewById<CheckBox>(R.id.wednesday).isEnabled = false
                    findViewById<CheckBox>(R.id.thursday).isEnabled = false
                    findViewById<CheckBox>(R.id.friday).isEnabled = false
                    findViewById<CheckBox>(R.id.saturday).isEnabled = false
                    findViewById<CheckBox>(R.id.sunday).isEnabled = false
                    event.isDaysSet = false
                    event.isDateSet = true
                }
                R.id.radio_after -> {
                    timeSpecification = 0
                    findViewById<EditText>(R.id.editTextTime).isEnabled = true
                    findViewById<Spinner>(R.id.period).isEnabled = true
                }
                R.id.radio_before -> {
                    timeSpecification = 1
                    findViewById<EditText>(R.id.editTextTime).isEnabled = true
                    findViewById<Spinner>(R.id.period).isEnabled = true
                }
                R.id.radio_allday -> {
                    timeSpecification = 2
                    findViewById<EditText>(R.id.editTextTime).isEnabled = false
                    findViewById<Spinner>(R.id.period).isEnabled = false
                }
            }
            view.isChecked = true
        }
    }

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked = view.isChecked
            when (view.id) {
                R.id.monday -> {
                    if (checked) {
                        if ("M" !in days)
                            days.add("M")
                    } else if (days.isNotEmpty()) {
                        if ("M" in days)
                            days.remove("M")
                    }
                }
                R.id.tuesday -> {
                    if (checked) {
                        if ("T" !in days)
                            days.add("T")
                    } else if (days.isNotEmpty()) {
                        if ("T" in days)
                            days.remove("T")
                    }
                }
                R.id.wednesday -> {
                    if (checked) {
                        if ("W" !in days)
                            days.add("W")
                    } else if (days.isNotEmpty()) {
                        if ("W" in days)
                            days.remove("W")
                    }
                }
                R.id.thursday -> {
                    if (checked) {
                        if ("R" !in days)
                            days.add("R")
                    } else if (days.isNotEmpty()) {
                        if ("R" in days)
                            days.remove("R")
                    }
                }
                R.id.friday -> {
                    if (checked) {
                        if ("F" !in days)
                            days.add("F")
                    } else if (days.isNotEmpty()) {
                        if ("F" in days)
                            days.remove("F")
                    }
                }
                R.id.saturday -> {
                    if (checked) {
                        if ("S" !in days)
                            days.add("S")
                    } else if (days.isNotEmpty()) {
                        if ("S" in days)
                            days.remove("S")
                    }
                }
                R.id.sunday -> {
                    if (checked) {
                        if ("U" !in days)
                            days.add("U")
                    } else if (days.isNotEmpty()) {
                        if ("U" in days)
                            days.remove("U")
                    }
                }
            }
        }
    }

}