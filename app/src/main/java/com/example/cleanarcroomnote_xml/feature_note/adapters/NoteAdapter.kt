package com.example.cleanarcroomnote_xml.feature_note.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.cleanarcroomnote_xml.databinding.RowBinding
import com.example.cleanarcroomnote_xml.feature_note.domain.model.Note
import javax.inject.Inject

class NoteAdapter  @Inject constructor(): ListAdapter<Note, NoteViewHolder>(MyDiffUtil()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = RowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
      val model = getItem(position)
        holder.bind(model)
        holder.setOnClickListener(listener , model)
    }

    class MyDiffUtil: DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private var listener: NoteItemInteraction? = null
    fun setListener(listener: NoteItemInteraction) {
        this.listener = listener
    }

}