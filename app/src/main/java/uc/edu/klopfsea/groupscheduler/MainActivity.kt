package uc.edu.klopfsea.groupscheduler

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uc.edu.klopfsea.groupscheduler.model.Event
import android.view.GestureDetector
import android.view.MotionEvent
import uc.edu.klopfsea.groupscheduler.ui.main.AddEvent
import uc.edu.klopfsea.groupscheduler.ui.main.MainFragment
import uc.edu.klopfsea.groupscheduler.ui.main.ScheduleFragment


class MainActivity : AppCompatActivity() {

    private lateinit var detector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
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
        TODO("Not yet implemented")
    }

    private fun onSwipeTop() {
        TODO("Not yet implemented")
    }

    private fun onSwipeLeft() {
        TODO("Not yet implemented")
    }

    private fun onSwipeRight() {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ScheduleFragment.newInstance())
                    .commitNow()
    }
}
