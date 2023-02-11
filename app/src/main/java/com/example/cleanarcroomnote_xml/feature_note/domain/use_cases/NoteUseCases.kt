package com.example.cleanarcroomnote_xml.feature_note.domain.use_cases

data class NoteUseCases(
    val addNoteUseCase: AddNoteUseCase,
    val clearDbUseCase: ClearDbUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val getNotesUseCase: GetNotesUseCase,
    val getNoteUseCase: GetNoteUseCase
)