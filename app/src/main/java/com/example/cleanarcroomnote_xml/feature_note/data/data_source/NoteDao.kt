package com.example.cleanarcroomnote_xml.feature_note.data.data_source

import androidx.room.*
import com.example.cleanarcroomnote_xml.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE id= :id")
    suspend fun getNoteById(id: Int): Note?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)


    @Query("DELETE FROM note")    // to clear all db
    suspend fun clearDB()
}