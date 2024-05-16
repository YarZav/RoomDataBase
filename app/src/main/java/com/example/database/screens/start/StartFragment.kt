package com.example.database.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.database.APP
import com.example.database.R
import com.example.database.adapter.NoteAdapter
import com.example.database.databinding.FragmentStartBinding
import com.example.database.model.NoteModel

class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    private fun init() {
        adapter = NoteAdapter()

        recyclerView = binding.rvNotes
        recyclerView.adapter = adapter

        val viewModel = ViewModelProvider(this).get(StartViewModel::class.java)
        viewModel.initDatabase()
        viewModel.getAllNotes().observe(
            viewLifecycleOwner,
            { listNotes ->
                adapter.setListMode(listNotes.asReversed())
            }
        )

        binding.btnNext.setOnClickListener {
            APP.navController.navigate(R.id.action_startFragment_to_addNoteFragment)
        }
    }

    companion object {
        fun clickNote(noteModel: NoteModel) {
            val bundle = Bundle()
            bundle.putSerializable("note", noteModel)
            APP.navController.navigate(R.id.action_startFragment_to_detailFragment, bundle)
        }
    }
}