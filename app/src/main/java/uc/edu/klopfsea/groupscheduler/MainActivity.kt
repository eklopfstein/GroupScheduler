package uc.edu.klopfsea.groupscheduler

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.schedule_fragment.*
import uc.edu.klopfsea.groupscheduler.ui.main.*
import kotlin.math.abs


class MainActivity : AppCompatActivity() {

    private lateinit var detector: GestureDetectorCompat
    private lateinit var planEvent: PlanEvent
    private lateinit var mainFragment: MainFragment
    private lateinit var scheduleFragment: ScheduleFragment
    private lateinit var newEvent: NewEvent
    private lateinit var activeFragment: Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        planEvent = PlanEvent.newInstance()
        mainFragment = MainFragment.newInstance()
        scheduleFragment = ScheduleFragment.newInstance()
        newEvent = NewEvent.newInstance()

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, mainFragment)
                    .commitNow()
            activeFragment = mainFragment
        }
        detector = GestureDetectorCompat(this, DiaryGestureListener())
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return if (detector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }

    inner class DiaryGestureListener : GestureDetector.SimpleOnGestureListener() {
        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onFling(downEvent: MotionEvent?, moveEvent: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            var diffX = moveEvent?.x?.minus(downEvent!!.x) ?: 0.0F
            var diffY = moveEvent?.y?.minus(downEvent!!.y) ?: 0.0F

            return if (abs(diffX) > abs(diffY)) {
                // either left or right swipe
                if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        //right swipe
                        this@MainActivity.onSwipeRight()
                    } else {
                        //left swipe
                        this@MainActivity.onSwipeLeft()
                    }
                    true
                } else {
                    super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            } else {
                //either top or bottom swipe
                if (abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        this@MainActivity.onSwipeTop()
                    } else {
                        this@MainActivity.onSwipeBottom()
                    }
                    true
                } else {
                    super.onFling(downEvent, moveEvent, velocityX, velocityY)
                }
            }
        }
    }

    internal fun onSwipeBottom() {
        if (activeFragment == mainFragment || activeFragment == planEvent || activeFragment == scheduleFragment)
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, newEvent)
                    .commitNow()
        activeFragment = newEvent
    }

    internal fun onSwipeTop() {
        if (activeFragment == mainFragment || activeFragment == planEvent || activeFragment == newEvent) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, scheduleFragment)
                    .commitNow()
            activeFragment = scheduleFragment
        }
    }

    internal fun onSwipeLeft() {
        if (activeFragment == mainFragment || activeFragment == scheduleFragment || activeFragment == newEvent) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, planEvent)
                    .commitNow()
            activeFragment = planEvent
        }
    }

    internal fun onSwipeRight() {
        if (activeFragment == planEvent || activeFragment == scheduleFragment || activeFragment == newEvent) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, mainFragment)
                    .commitNow()
            activeFragment = mainFragment
        }
    }

    internal fun onRadioButtonClicked(scheduleFragment: ScheduleFragment) {

    }

}