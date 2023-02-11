package com.example.cleanarcroomnote_xml.feature_note.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarcroomnote_xml.databinding.RowBinding
import com.example.cleanarcroomnote_xml.feature_note.domain.model.Note

class NoteViewHolder(private val binding: RowBinding): RecyclerView.ViewHolder(binding.root)  {
    fun bind(model: Note?) {

        binding.txtTitle.text= model?.title
        binding.txtDescription.text = model?.content
    }

    fun setOnClickListener(listener: NoteItemInteraction?, model: Note) {

        binding.root.setOnClickListener {
          model.let {
            listener?.noteItemOnclick(it)
          }
        }


        binding.imgDelete.setOnClickListener {
            model.let {
                listener?.deleteItemOnclick(it)
            }
        }
    }


}