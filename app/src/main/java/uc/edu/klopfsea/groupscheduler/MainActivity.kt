package uc.edu.klopfsea.groupscheduler

import android.content.Intent
import android.os.Bundle
import android.widget.CalendarView
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uc.edu.klopfsea.groupscheduler.model.Event

import uc.edu.klopfsea.groupscheduler.ui.main.AddEvent
import uc.edu.klopfsea.groupscheduler.ui.main.MainFragment


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var calendarView: CalendarView

    val eventList = arrayListOf<Event>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val addEventPageButton = findViewById<ImageButton>(R.id.addEventPageButton)

        calendarView = findViewById(R.id.calendarView)
        recyclerView = findViewById(R.id.recyclerView)
        layoutManager = GridLayoutManager(this,2)
        recyclerView.layoutManager = layoutManager

        addEventPageButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEvent::class.java)
            startActivity(intent)
        }

            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
            }
        }
    }
