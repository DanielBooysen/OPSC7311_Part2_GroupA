package com.example.opsc7311_part2_groupa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EntryAdapter (private val entries: List<TimeSheetEntry>) : RecyclerView.Adapter<EntryAdapter.EntryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_timesheet_entry, parent, false)
        return EntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        val entry = entries[position]
        holder.bind(entry)
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    inner class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val startTimeTextView: TextView = itemView.findViewById(R.id.textStartTime)
        private val endTimeTextView: TextView = itemView.findViewById(R.id.textEndTime)
        private val categoryTextView: TextView = itemView.findViewById(R.id.textCategory)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.textDescription)

        fun bind(entry: TimeSheetEntry) {
            // Bind entry data to views in the item layout
            startTimeTextView.text = entry.startTime
            endTimeTextView.text = entry.endTime
            categoryTextView.text = entry.category
            descriptionTextView.text = entry.description
        }
    }
}



