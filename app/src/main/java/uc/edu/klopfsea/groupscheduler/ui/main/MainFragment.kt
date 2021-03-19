package uc.edu.klopfsea.groupscheduler.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uc.edu.klopfsea.groupscheduler.R
import uc.edu.klopfsea.groupscheduler.dto.Schedule
import uc.edu.klopfsea.groupscheduler.model.Event


class MainFragment : Fragment() {

   // lateinit var eventsRecyclerView: RecyclerView
   // lateinit var layoutManager: RecyclerView.LayoutManager
   // lateinit var calendarView: CalendarView

    val eventList = arrayListOf<Event>()


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

       /* val addEventPageButton = findViewById<ImageButton>(R.id.addEventPageButton)

        // By ID we can use each component which id is assign in xml file use findViewById()

        eventsRecyclerView = findViewById(R.id.eventsRecyclerView)
        layoutManager = GridLayoutManager(this, 2)
        eventsRecyclerView.layoutManager = layoutManager


        addEventPageButton.setOnClickListener {
            val intent = Intent(this@MainFragment, AddEvent::class.java)
            startActivity(intent)
        }*/

        return inflater.inflate(R.layout.main_fragment, container, false)
    }


    private fun Intent(mainFragment: MainFragment, java: Class<AddEvent>): Intent? {
        TODO("Not yet implemented")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        //the button should be btnAddEvent
        //btnAddEvent.setOnClickListener {
        //    saveSchedule()
        //}
    }

    /*private fun saveSchedule() {
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
    }*/

}

