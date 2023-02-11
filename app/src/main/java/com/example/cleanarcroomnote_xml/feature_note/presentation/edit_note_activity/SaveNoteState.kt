package com.example.cleanarcroomnote_xml.feature_note.presentation.edit_note_activity

import com.example.cleanarcroomnote_xml.feature_note.domain.model.Note

data class SaveNoteState(
    val isLoading: Boolean = false,
    val data: Note? = null,
    val error: String = ""
)
