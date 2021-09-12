package com.hfad.android.storageapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.hfad.android.storageapp.database.Repository
import com.hfad.android.storageapp.databinding.FragmentHumanListBinding
import com.hfad.android.storageapp.model.Human
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

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

    val itemTouchHelper =
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.UP) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                var startPosition = viewHolder.adapterPosition
                var endPosition = target.adapterPosition

//                Collections.swap(humansLiveData.value,startPosition,endPosition)
                recyclerView.adapter?.notifyItemMoved(startPosition,endPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Snackbar.make(viewHolder.itemView, "${viewHolder.itemView}", Snackbar.LENGTH_LONG).show()
            }
        })

    companion object {

    }
}
