package uc.edu.klopfsea.groupscheduler.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.main_fragment.*
import uc.edu.klopfsea.groupscheduler.MainActivity
import uc.edu.klopfsea.groupscheduler.R
import uc.edu.klopfsea.groupscheduler.dto.NewEventDto
import uc.edu.klopfsea.groupscheduler.dto.PlannedEvent
import uc.edu.klopfsea.groupscheduler.dto.Schedule
import uc.edu.klopfsea.groupscheduler.model.Event

class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
class PlannedEventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
class NewEventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class MainFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var calendarView: CalendarView
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    val eventList = arrayListOf<Event>()

    val db = Firebase.firestore

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity.let {
            viewModel = ViewModelProviders.of(it!!).get(MainViewModel::class.java)
        }
        scheduleEventButton.setOnClickListener {
            (activity as MainActivity).onSwipeTop()
        }
        planEventButton.setOnClickListener {
            (activity as MainActivity).onSwipeLeft()
        }
        addEventPageButton.setOnClickListener {
            (activity as MainActivity).onSwipeBottom()
        }

        val scheduleQuery = db.collection("schedule")
        val plannedQuery = db.collection("plannedevents")
        val newEventQuery = db.collection("newevent")

        val scheduleOptions = FirestoreRecyclerOptions.Builder<Schedule>().setQuery(scheduleQuery, Schedule::class.java)
            .setLifecycleOwner(this).build()
        val scheduleAdapter = object: FirestoreRecyclerAdapter<Schedule, ScheduleViewHolder>(scheduleOptions) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
               val view = LayoutInflater.from(context).inflate(R.layout.single_plan_event_layout, parent, false)
                return ScheduleViewHolder(view)
            }

            override fun onBindViewHolder(
                holder: ScheduleViewHolder,
                position: Int,
                model: Schedule
            ) {
                val dayTextView: TextView = holder.itemView.findViewById(R.id.Days)
                val timeTextView: TextView = holder.itemView.findViewById(R.id.Time)
                val deleteBtn: ImageButton = holder.itemView.findViewById(R.id.delete)

                dayTextView.text = model.day
                timeTextView.text = model.time
            }
        }

        val plannedEventOptions = FirestoreRecyclerOptions.Builder<PlannedEvent>().setQuery(plannedQuery, PlannedEvent::class.java)
                .setLifecycleOwner(this).build()
        val plannedEventAdapter = object: FirestoreRecyclerAdapter<PlannedEvent, PlannedEventViewHolder>(plannedEventOptions) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlannedEventViewHolder {
                val view = LayoutInflater.from(context).inflate(R.layout.single_schedule_event_layout, parent, false)
                return PlannedEventViewHolder(view)
            }

            override fun onBindViewHolder(holder: PlannedEventViewHolder, position: Int, model: PlannedEvent) {
                val plannedNameTV: TextView = holder.itemView.findViewById(R.id.lblName)
                val plannedDateTV: TextView = holder.itemView.findViewById(R.id.lblDate)

                plannedNameTV.text = model.eventName
                plannedDateTV.text = model.eventDate
            }

        }

        val newEventOptions = FirestoreRecyclerOptions.Builder<NewEventDto>().setQuery(newEventQuery, NewEventDto::class.java)
                .setLifecycleOwner(this).build()
        val newEventAdapter = object: FirestoreRecyclerAdapter<NewEventDto, NewEventViewHolder>(newEventOptions) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewEventViewHolder {
                val view = LayoutInflater.from(context).inflate(R.layout.single_event_layout, parent, false)
                return NewEventViewHolder(view)
            }

            override fun onBindViewHolder(holder: NewEventViewHolder, position: Int, model: NewEventDto) {
                val newEventNameTV: TextView = holder.itemView.findViewById(R.id.lblNewEventName)
                val timeNeededTV: TextView = holder.itemView.findViewById(R.id.lblTimeNeeded)

                val timeNeeded = model.hour.toString() + " " + model.minute.toString()
                newEventNameTV.text = model.newEventName
                timeNeededTV.text = timeNeeded
            }

        }

        scheduleEventsRecyclerView.adapter = scheduleAdapter
        scheduleEventsRecyclerView.layoutManager = LinearLayoutManager(context)
        plannedEventsRecyclerView.adapter = plannedEventAdapter
        plannedEventsRecyclerView.layoutManager = LinearLayoutManager(context)
        eventsRecyclerView.adapter = newEventAdapter
        eventsRecyclerView.layoutManager = LinearLayoutManager(context)

    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
