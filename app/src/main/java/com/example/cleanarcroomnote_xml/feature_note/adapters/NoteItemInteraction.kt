package com.example.cleanarcroomnote_xml.feature_note.adapters

import com.example.cleanarcroomnote_xml.feature_note.domain.model.Note

interface NoteItemInteraction {

    fun noteItemOnclick(note: Note)
    fun deleteItemOnclick(note: Note)
}