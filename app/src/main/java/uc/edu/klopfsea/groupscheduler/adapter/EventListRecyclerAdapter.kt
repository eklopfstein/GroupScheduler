package uc.edu.klopfsea.groupscheduler.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uc.edu.klopfsea.groupscheduler.R
import uc.edu.klopfsea.groupscheduler.model.Event

class EventListRecyclerAdapter(var context: Context, var eventList: MutableList<Event>): RecyclerView.Adapter<EventListRecyclerAdapter.EventListViewHolder>() {
    class EventListViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val txtDate: TextView = view.findViewById(R.id.textView1)
        val txtTime: TextView = view.findViewById(R.id.textView2)
        val btnDelete: ImageButton = view.findViewById(R.id.delete)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_schedule_event_layout, parent, false)
        return EventListViewHolder(view)
    }


    override fun getItemCount(): Int {
        return eventList.size
    }

    override fun onBindViewHolder(holder: EventListViewHolder, position: Int) {
        val event = eventList[position]
        if (event.isDateSet){
//            holder.txtDate.text = event.getFormatedDate() toget only the date without month and year, this can be used
            holder.txtDate.text = event.date
        }else{
            holder.txtDate.text = event.days
        }
        holder.txtTime.text = event.timeSpecification
        holder.btnDelete.setOnClickListener{
            eventList.removeAt(position)
            (context as Activity).recreate()
            notifyDataSetChanged()
        }
    }

}