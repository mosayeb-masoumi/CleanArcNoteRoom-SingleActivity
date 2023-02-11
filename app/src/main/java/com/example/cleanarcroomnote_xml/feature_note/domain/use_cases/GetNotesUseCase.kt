package com.example.cleanarcroomnote_xml.feature_note.domain.use_cases

import com.example.cleanarcroomnote_xml.feature_note.domain.model.Note
import com.example.cleanarcroomnote_xml.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase (private val repository: NoteRepository){

    // because we used flow in getlist in dao then suspend is redundant
    operator fun invoke(): Flow<List<Note>> {
        return repository.getNotes()
    }
}