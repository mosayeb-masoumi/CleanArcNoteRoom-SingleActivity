package com.example.cleanarcroomnote_xml.di

import android.app.Application
import androidx.room.Room
import com.example.cleanarcroomnote_xml.feature_note.data.data_source.NoteDatabase
import com.example.cleanarcroomnote_xml.feature_note.data.repository.NoteRepositoryImpl
import com.example.cleanarcroomnote_xml.feature_note.domain.repository.NoteRepository
import com.example.cleanarcroomnote_xml.feature_note.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app:Application): NoteDatabase{
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        )
//            .addMigrations(MIGRATION_1_2)
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
       return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository):NoteUseCases{
      return NoteUseCases(
          addNoteUseCase = AddNoteUseCase(repository),
          clearDbUseCase= ClearDbUseCase(),
          deleteNoteUseCase = DeleteNoteUseCase(repository),
          getNoteUseCase = GetNoteUseCase(repository),
          getNotesUseCase = GetNotesUseCase(repository),
      )
    }



}