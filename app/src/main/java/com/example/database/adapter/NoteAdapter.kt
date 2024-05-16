package com.example.database.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.database.APP
import com.example.database.model.NoteModel
import com.example.database.R
import com.example.database.screens.start.StartFragment

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    var listNote = emptyList<NoteModel>()

    class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title: TextView = itemView.findViewById(R.id.item_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = listNote[position]

        holder.title.text = note.title
        holder.itemView.setOnClickListener {
            Toast.makeText(APP, "$position", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewAttachedToWindow(holder: NoteViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            StartFragment.clickNote(listNote[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: NoteViewHolder) {
        holder.itemView.setOnClickListener(null)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListMode(listNote: List<NoteModel>) {
        this.listNote = listNote
        notifyDataSetChanged()
    }
}