package com.example.database.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.database.APP
import com.example.database.R
import com.example.database.databinding.FragmentDetailBinding
import com.example.database.model.NoteModel

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    lateinit var currentNote: NoteModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        currentNote = arguments?.getSerializable("note") as NoteModel

        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        binding.tvTitleDetail.text = currentNote.title
        binding.tvTitleDesc.text = currentNote.description

        binding.btnDelete.setOnClickListener {
            viewModel.delete(currentNote) {
                // Success completion
            }

            // Delete and come back
            APP.navController.navigate(R.id.action_detailFragment_to_startFragment)
        }

        binding.btnBack.setOnClickListener {
            APP.navController.navigate(R.id.action_detailFragment_to_startFragment)
        }
    }
}