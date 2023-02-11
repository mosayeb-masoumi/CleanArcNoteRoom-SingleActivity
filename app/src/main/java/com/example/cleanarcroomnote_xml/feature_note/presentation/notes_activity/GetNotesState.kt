package com.example.cleanarcroomnote_xml.feature_note.presentation.notes_activity

import com.example.cleanarcroomnote_xml.feature_note.domain.model.Note

data class GetNotesState(
 val isLoading:Boolean = false,
 val noteList:List<Note?> = emptyList(),
 val error:String =""
)
