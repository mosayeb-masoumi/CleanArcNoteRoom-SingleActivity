package com.example.cleanarcroomnote_xml.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarcroomnote_xml.feature_note.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
//    , autoMigrations = [AutoMigration(from = 1 , to = 2)]   // to migrate automatically
)
abstract class NoteDatabase : RoomDatabase(){

    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"


        // to migrate manually
//        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                // The following query will add a new column called lastUpdate to the notes database
//                database.execSQL("ALTER TABLE notes ADD COLUMN lastUpdate INTEGER NOT NULL DEFAULT 0")
//            }
//        }
    }
}