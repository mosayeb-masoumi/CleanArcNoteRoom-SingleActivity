package com.example.cleanarcroomnote_xml.feature_note.data.repository

import com.example.cleanarcroomnote_xml.feature_note.data.data_source.NoteDao
import com.example.cleanarcroomnote_xml.feature_note.domain.model.Note
import com.example.cleanarcroomnote_xml.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val dio:NoteDao) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
       return dio.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dio.getNoteById(id)
    }


    override suspend fun addNote(note: Note) {
        return dio.addNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return dio.deleteNote(note)
    }

    override suspend fun clearDB() {
        return dio.clearDB()
    }





}