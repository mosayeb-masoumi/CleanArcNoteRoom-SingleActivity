package com.example.cleanarcroomnote_xml.feature_note.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "content") var content: String,
    @ColumnInfo(name = "timestamp") var timestamp: String,
    @ColumnInfo(name = "age") var age: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null  // important to equal null  for working increment
}

class InvalidNoteException(message: String) : Exception(message)