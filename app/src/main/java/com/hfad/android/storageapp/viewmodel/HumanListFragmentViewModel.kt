package com.hfad.android.storageapp.viewmodel

import android.app.Application
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.hfad.android.storageapp.HumanAdapter
import com.hfad.android.storageapp.R
import com.hfad.android.storageapp.database.Repository
import com.hfad.android.storageapp.model.Human
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HumanListFragmentViewModel(application: Application) : AndroidViewModel(application) {



    private val repository: Repository = Repository.get()

    val humansLiveData = repository.getAll()

    fun add(human: Human) = viewModelScope.launch(Dispatchers.IO) {
        repository.add(human)
    }

    fun delete(human: Human) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(human)
    }

    fun getById(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.getById(id)
    }

    fun update(human: Human) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(human)
    }

    fun getInAlphabetOrder() = repository.getInAlphabetOrder()

}
