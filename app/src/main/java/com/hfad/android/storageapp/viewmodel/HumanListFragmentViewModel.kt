package com.hfad.android.storageapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.android.storageapp.database.Repository
import com.hfad.android.storageapp.model.Human
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HumanListFragmentViewModel : ViewModel() {
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