package com.hfad.android.storageapp.viewmodel

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.color.MaterialColors
import com.google.android.material.snackbar.Snackbar
import com.hfad.android.storageapp.R
import com.hfad.android.storageapp.database.Repository
import com.hfad.android.storageapp.databinding.FragmentHumanListBinding
import com.hfad.android.storageapp.model.Human
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.abs

class HumanListFragmentViewModel : ViewModel() {
    private val repository: Repository = Repository.get()
    private val swipeBackgroundDelete: ColorDrawable = ColorDrawable(Color.parseColor("#FF0000")) //red
    private val swipeBackgroundUpdate: ColorDrawable = ColorDrawable(Color.parseColor("#FFA500")) //orange
    private val deleteIcon:Drawable = ContextCompat.getDrawable(context)


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
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                var startPosition = viewHolder.adapterPosition
                var endPosition = target.adapterPosition

//                Collections.swap(humansLiveData.value,startPosition,endPosition)
                recyclerView.adapter?.notifyItemMoved(startPosition, endPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Snackbar.make(viewHolder.itemView, "${viewHolder.itemView}", Snackbar.LENGTH_LONG)
                    .show()
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
                if (dX > 0) {    //swipe right
                    swipeBackgroundUpdate.setBounds(
                        itemView.left,
                        itemView.top,
                        dX.toInt(),
                        itemView.bottom
                    )
                    swipeBackgroundUpdate.draw(c)
                } else {        //swipe left
                    swipeBackgroundDelete.setBounds(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                    swipeBackgroundDelete.draw(c)
                }


                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }

//            override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
//
//            }
        })

    companion object {

    }
}
