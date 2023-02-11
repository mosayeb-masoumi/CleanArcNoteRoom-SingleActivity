package com.example.cleanarcroomnote_xml.feature_note.presentation.notes_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarcroomnote_xml.common.Resource
import com.example.cleanarcroomnote_xml.feature_note.domain.model.Note
import com.example.cleanarcroomnote_xml.feature_note.domain.use_cases.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(private val useCases: NoteUseCases) :ViewModel(){

    private val _getNotesResponse = MutableLiveData<List<Note?>>()
    val getNotesLiveData:LiveData<List<Note?>> get() = _getNotesResponse

    private val _deleteNoteResponse = MutableLiveData<DeleteNoteState>()
    val deleteNoteState:LiveData<DeleteNoteState> get() = _deleteNoteResponse




    fun getNoteList(){
        useCases.getNotesUseCase().onEach {
          _getNotesResponse.value = it
        }.launchIn(viewModelScope)
    }

    fun deleteNote(note: Note){
        useCases.deleteNoteUseCase(note).onEach { result ->
            when (result){
              is Resource.Loading -> {
                _deleteNoteResponse.value = DeleteNoteState(isLoading = true)
              }

                is Resource.Success -> {
                    _deleteNoteResponse.value = DeleteNoteState(data = result.data)
                }

                is Resource.Error -> {
                    _deleteNoteResponse.value = DeleteNoteState(error = "An unexpected error occured")
                }

            }

        }.launchIn(viewModelScope)
    }
}