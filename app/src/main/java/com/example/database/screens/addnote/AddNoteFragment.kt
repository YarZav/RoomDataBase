package com.example.database.screens.addnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.database.APP
import com.example.database.R
import com.example.database.databinding.FragmentAddNoteBinding
import com.example.database.model.NoteModel

class AddNoteFragment : Fragment() {
    private lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(AddNoteViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            val title = binding.etAddTitle.text.toString()
            val description = binding.etAddDescription.text.toString()
            val noteModel = NoteModel(title = title, description = description)
            viewModel.insert(noteModel) {
                // Success completion
            }

            // Save and come back
            APP.navController.navigate(R.id.action_addNoteFragment_to_startFragment)
        }

        binding.btnBack.setOnClickListener {
            APP.navController.navigate(R.id.action_addNoteFragment_to_startFragment)
        }
    }
}