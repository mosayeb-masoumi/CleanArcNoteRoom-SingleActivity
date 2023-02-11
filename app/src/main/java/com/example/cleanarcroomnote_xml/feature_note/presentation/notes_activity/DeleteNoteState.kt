package com.example.cleanarcroomnote_xml.feature_note.presentation.notes_activity

import com.example.cleanarcroomnote_xml.feature_note.domain.model.Note

data class DeleteNoteState(
    val isLoading:Boolean = false,
    val data: Note?=null,
    var error:String = ""
)
