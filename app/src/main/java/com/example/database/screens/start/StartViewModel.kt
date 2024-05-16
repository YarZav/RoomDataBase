package com.example.database.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.database.REPOSITORY
import com.example.database.db.NoteDatabase
import com.example.database.db.repository.NoteRealisation
import com.example.database.model.NoteModel

class StartViewModel(application: Application): AndroidViewModel(application) {
    val context = application

    fun initDatabase() {
        val daoNote = NoteDatabase.getInstance(context).getNoteDao()
        REPOSITORY = NoteRealisation(daoNote)
    }

    fun getAllNotes(): LiveData<List<NoteModel>> {
        return REPOSITORY.allNotes
    }
}