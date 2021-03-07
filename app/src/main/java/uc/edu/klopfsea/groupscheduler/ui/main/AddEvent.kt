package uc.edu.klopfsea.groupscheduler.ui.main

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.*
import uc.edu.klopfsea.groupscheduler.R
import uc.edu.klopfsea.groupscheduler.model.Event
import java.text.SimpleDateFormat
import java.util.*

class AddEvent : Activity(), AdapterView.OnItemSelectedListener {
    // late initialization
    lateinit var spinner: Spinner
    lateinit var btnAddEvent: Button
    lateinit var btnClose: ImageButton
    lateinit var etDate: EditText

    // creating empty ArrayList and setting default values
    var period = arrayListOf<String>()
    var periodSelected = 0
    val days = arrayListOf<String>()
    var timeSpecification = -1
    val event = Event(isDateSet = false, isDaysSet = false, days = "", date = "", time = "", period = "AM", timeSpecification = "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.addevent_layout)

        // By ID we can use each component which id is assign in xml file use findViewById()
        spinner = findViewById(R.id.period)
        btnAddEvent = findViewById(R.id.btnAddEvent)
        etDate = findViewById(R.id.editTextDate)
        btnClose = findViewById(R.id.btnClose)
        period.add("AM")
        period.add("PM")

        //Set Dropdown list and access the spinner
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,period)
        adapter.setDropDownViewResource(R.layout.spinner_dropdown)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this

        // operations to be performed when user tap on the btnAddEvent button
        btnAddEvent.setOnClickListener {

            // converts the entered time to text and prints
            val time = findViewById<EditText>(R.id.editTextTime).text.toString()
            System.out.println(time)
            System.out.println(timeSpecification)
            if ((time.isNotEmpty() && timeSpecification != -1) || timeSpecification == 2) {
                if (findViewById<RadioButton>(R.id.radio_days).isChecked) {
                    // If no radio button checked in this radio group
                    if (days.isEmpty()) {
                        Toast.makeText(this, "Days not selected", Toast.LENGTH_SHORT).show()
                    }
                    // Get the instance of radio button using id
                    else {
                        event.isDaysSet = true
                        event.days = setDays().toString()
                        System.out.println(event.days)
                        eventAddSuccess(time)
                    }
                } else if (findViewById<RadioButton>(R.id.radio_date).isChecked) {
                    val date = etDate.text.toString()
                    // Get the instance of radio button using id
                    if (date.isNotEmpty() && isDateValid(date)) {
                        event.isDateSet = true
                        event.date = date
                        eventAddSuccess(time)
                    }
                    // If invalid date entered
                    else {
                        Toast.makeText(this, "Invalid Date Format", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Invalid Entry", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Invalid Entry 1", Toast.LENGTH_SHORT).show()
            }
        }

        // it will exit the application when user tap on the btnClose button
        btnClose.setOnClickListener {
            onBackPressed()
        }
    }
  
    // sets the time
    fun eventAddSuccess(time: String){
        event.time = time
        event.setTime(timeSpecification)
    }
    // formats and prints date
    fun isDateValid(dateString: String): Boolean{
        try{
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

    // retrieves the object from adapter
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        periodSelected = position
    }

    // create a StringBuilder object to setDays for checkboxes
    fun setDays(): StringBuilder{
        var dayString  = StringBuilder("")
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
          
    // Get radio group selected status and text using button click event
    fun onRadioButtonClicked(view: View){
        if(view is RadioButton){
            when(view.id){
                R.id.radio_days->{
                    findViewById<RadioButton>(R.id.radio_date).isChecked=false
                    findViewById<EditText>(R.id.editTextDate).isEnabled=false
                    findViewById<CheckBox>(R.id.monday).isEnabled=true
                    findViewById<CheckBox>(R.id.tuesday).isEnabled=true
                    findViewById<CheckBox>(R.id.wednesday).isEnabled=true
                    findViewById<CheckBox>(R.id.thursday).isEnabled=true
                    findViewById<CheckBox>(R.id.friday).isEnabled=true
                    findViewById<CheckBox>(R.id.saturday).isEnabled=true
                    findViewById<CheckBox>(R.id.sunday).isEnabled=true
                    event.isDaysSet=true
                    event.isDateSet=false
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

    // Get checkbox selected status and text using button click event
    fun onCheckboxClicked(view: View){
        if (view is CheckBox){
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