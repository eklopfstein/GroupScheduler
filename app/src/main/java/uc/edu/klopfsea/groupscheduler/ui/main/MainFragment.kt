package uc.edu.klopfsea.groupscheduler.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.main_fragment.*
import uc.edu.klopfsea.groupscheduler.MainActivity
import uc.edu.klopfsea.groupscheduler.R
import uc.edu.klopfsea.groupscheduler.model.Event


class MainFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var calendarView: CalendarView

    val eventList = arrayListOf<Event>()


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
        // TODO: 3/26/2021  have to create for new button to go back to main
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
