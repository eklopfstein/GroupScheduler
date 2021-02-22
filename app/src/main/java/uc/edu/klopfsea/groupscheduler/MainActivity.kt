package uc.edu.klopfsea.groupscheduler

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton

import androidx.appcompat.app.AppCompatActivity
import uc.edu.klopfsea.groupscheduler.ui.main.AddEvent
import uc.edu.klopfsea.groupscheduler.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val addEventPageButton = findViewById<ImageButton>(R.id.addEventPageButton) as ImageButton

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
