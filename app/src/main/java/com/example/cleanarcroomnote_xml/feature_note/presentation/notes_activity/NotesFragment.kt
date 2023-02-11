package com.example.cleanarcroomnote_xml.feature_note.presentation.notes_activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarcroomnote_xml.R
import com.example.cleanarcroomnote_xml.databinding.FragmentNotesBinding
import com.example.cleanarcroomnote_xml.feature_note.adapters.NoteAdapter
import com.example.cleanarcroomnote_xml.feature_note.adapters.NoteItemInteraction
import com.example.cleanarcroomnote_xml.feature_note.domain.model.Note
import com.example.cleanarcroomnote_xml.feature_note.presentation.edit_note_activity.EditNoteFragment
import javax.inject.Inject

import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"


@AndroidEntryPoint
class NotesFragment : Fragment() ,NoteItemInteraction{


    lateinit var binding: FragmentNotesBinding
    private val viewModel by activityViewModels<NoteListViewModel>()

    @Inject
    lateinit var adapter :NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
                   }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(
                resId = R.id.action_notesFragment_to_editNoteFragment,
                args = Bundle().also {
                    it.putInt("id" , -1)
                })
        }



    }

    override fun onResume() {
        super.onResume()

        getNotesFomDb()
    }

    private fun getNotesFomDb() {

        viewModel.getNoteList()
        val getNotesLiveData = viewModel.getNotesLiveData
        getNotesLiveData.observe(viewLifecycleOwner){
          if(it!=null){
              if(it.isEmpty()){
                  binding.txtNoResult.visibility = View.VISIBLE
              }else{
                  binding.txtNoResult.visibility = View.GONE

              }
              setRecyClerView(it)
          }
        }
    }

    private fun setRecyClerView(it: List<Note?>) {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerview.layoutManager = layoutManager
        adapter.setListener(this)
        adapter.submitList(it)
        binding.recyclerview.adapter = adapter
    }

    override fun noteItemOnclick(note: Note) {

        val gson = Gson()
        val json = gson.toJson(note)

        findNavController().navigate(
            resId = R.id.action_notesFragment_to_editNoteFragment,
            args = Bundle().also {
                it.putInt("id" , note.id!!)
                it.putString("json",json)
            })


    }

    override fun deleteItemOnclick(note: Note) {
        deleteItem(note)
    }

    private fun deleteItem(note: Note) {
     viewModel.deleteNote(note)
        viewModel.deleteNoteState.observe(viewLifecycleOwner){
           if(it !=null){
             when {
                 it.isLoading ->{
                     Log.i("loading", "loading")
                 }

                 it.error !="" ->{
                     Log.e("error", "an error occured")
                 }

                 it.data !=null ->{
                     Toast.makeText(context, "delete susccessfully", Toast.LENGTH_LONG)
                         .show()


                     getNotesFomDb()
                 }
             }
           }
        }
    }

}