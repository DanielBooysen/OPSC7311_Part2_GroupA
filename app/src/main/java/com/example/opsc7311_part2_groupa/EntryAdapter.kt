package com.example.opsc7311_part2_groupa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EntryAdapter (private val entries: List<TimeSheetEntry>) : RecyclerView.Adapter<EntryAdapter.EntryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_timesheet_entry, parent, false)
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
        fun bind(entry: TimeSheetEntry) {
            // Bind entry data to views in the item layout
            itemView.findViewById<TextView>(R.id.textStartTime).text = entry.startTime
            itemView.findViewById<TextView>(R.id.textEndTime).text = entry.endTime
            itemView.findViewById<TextView>(R.id.textCategory).text = entry.category
            itemView.findViewById<TextView>(R.id.textDescription).text = entry.description
        }
    }
}