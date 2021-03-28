package uc.edu.klopfsea.groupscheduler

import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uc.edu.klopfsea.groupscheduler.model.Event
import uc.edu.klopfsea.groupscheduler.ui.main.*


class MainActivity : AppCompatActivity() {

    private lateinit var detector: GestureDetectorCompat
    private lateinit var planEvent: PlanEvent
    private lateinit var mainFragment: MainFragment
    private lateinit var scheduleFragment: ScheduleFragment
    private lateinit var activeFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        planEvent = PlanEvent.newInstance()
        mainFragment = MainFragment.newInstance()
        scheduleFragment = ScheduleFragment.newInstance()

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

            return if (Math.abs(diffX) > Math.abs(diffY)) {
                // either left or right swipe
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
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
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
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

    private fun onSwipeBottom() {
        //TODO("Not yet implemented")
    }

    internal fun onSwipeTop() {
        if (activeFragment == mainFragment || activeFragment == planEvent) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, scheduleFragment)
                    .commitNow()
            activeFragment = scheduleFragment
        }
    }

    internal fun onSwipeLeft() {
        if (activeFragment == mainFragment || activeFragment == scheduleFragment) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, planEvent)
                    .commitNow()
            activeFragment = planEvent
        }
    }

    internal fun onSwipeRight() {
        if (activeFragment == planEvent || activeFragment == scheduleFragment) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, mainFragment)
                    .commitNow()
            activeFragment = mainFragment
        }
    }

}