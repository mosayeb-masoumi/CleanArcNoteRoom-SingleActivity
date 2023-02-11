package com.example.cleanarcroomnote_xml.feature_note.presentation.edit_note_activity

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
class EditNoteViewModel @Inject constructor(private val useCases: NoteUseCases):ViewModel() {

    private val _saveNoteResponse = MutableLiveData<SaveNoteState>()
    val saveNoteResponse:LiveData<SaveNoteState> get() = _saveNoteResponse

    private  val _getNoteById = MutableLiveData<EditNoteState>()
    val getNoteById:LiveData<EditNoteState> get() = _getNoteById

    fun getItem(id:Int){

        useCases.getNoteUseCase(id).onEach {
            when (it) {

                is Resource.Loading -> {
                    _getNoteById.value = EditNoteState(isLoading = true)
                }

                is Resource.Success -> {
                    _getNoteById.value = EditNoteState(data = it.data)
                }

                is Resource.Error ->{
                    _saveNoteResponse.value = SaveNoteState(error = "An unexpected error occured")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun saveItem(note: Note){

        useCases.addNoteUseCase(note).onEach {

            when (it) {

                is Resource.Loading ->{
                  _saveNoteResponse.value = SaveNoteState(isLoading = true)
                }

                is Resource.Success ->{
                    _saveNoteResponse.value = SaveNoteState(data = it.data)
                }

                is Resource.Error ->{
                    _saveNoteResponse.value = SaveNoteState(error = "An unexpected error occured")
                }
            }

        }.launchIn(viewModelScope)
    }
}