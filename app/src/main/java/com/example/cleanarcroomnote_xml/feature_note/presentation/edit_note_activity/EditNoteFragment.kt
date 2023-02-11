package com.example.cleanarcroomnote_xml.feature_note.presentation.edit_note_activity

import android.content.Intent.getIntent
import android.content.Intent.getIntentOld
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowId
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cleanarcroomnote_xml.R
import com.example.cleanarcroomnote_xml.databinding.FragmentEditNoteBinding
import com.example.cleanarcroomnote_xml.feature_note.domain.model.Note
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class EditNoteFragment : Fragment() {

    lateinit var binding: FragmentEditNoteBinding
    private val viewModel by activityViewModels<EditNoteViewModel>()

    var itemId: Int = -1
    var itemJson = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemId = it.getInt("id", -1)
            itemJson = it.getString("json", "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditNoteBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if (itemId == -1) {

            //update
//             val type = object : TypeToken<Note?>() {}.type
//             val updateNote : Note
//             updateNote = Gson().fromJson<Note?>(itemJson, type)
//            binding.edtTitle.setText(updateNote.title)
//            binding.edtDesc.setText(updateNote.content)
//            binding.edtAge.setText(updateNote.age.toString())




        } else {
            //getItem from db
            getItemById()
        }

        binding.fabAdd.setOnClickListener {

            val title = binding.edtTitle.text?.trim().toString()
            val content = binding.edtDesc.text?.trim().toString()
            val age = binding.edtAge.text?.trim().toString()


            if (title.isNotBlank() && content.isNotBlank() && age.isNotBlank()) {

                if (itemId == -1) { // add new Item
                    val note = Note(title, content, "time", age.toInt())
                    addEditItemToDB(note)

                } else {  //update Existed Item

//                 addEditItemToDB(note)
                }


            } else {
                Toast.makeText(context, "please fill required fields", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun getItemById() {
        viewModel.getItem(itemId)
        val getItemById = viewModel.getNoteById
        getItemById.observe(viewLifecycleOwner) {
            if (it != null) {
                when {
                    it.isLoading -> {
                        Log.i("loading", "loading")
                    }
                    it.error != "" -> {
                        Log.i("error", "error")
                    }

                    it.data != null -> {
                        binding.edtTitle.setText(it.data.title)
                        binding.edtDesc.setText(it.data.content)
                        binding.edtAge.setText(it.data.age.toString())

                    }
                }
            }
        }
    }

    private fun addEditItemToDB(note: Note) {

        viewModel.saveItem(note)
        // wait for observing
        val saveItemLivedata = viewModel.saveNoteResponse
        saveItemLivedata.observe(viewLifecycleOwner) {

            if (it != null) {
                when {
                    it.isLoading -> {
                        Log.i("loading", "loading")
                    }
                    it.error != "" -> {
                        Log.i("error", "error")
                    }
                    it.data != null -> {
                        Toast.makeText(context, "saved susccessfully", Toast.LENGTH_LONG).show()
                        findNavController().navigate(
                            resId = R.id.action_editNoteFragment_to_notesFragment,
                            args = Bundle().also {}
                        )
                    }
                }
            }
        }


    }


}