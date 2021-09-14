package com.hfad.android.storageapp.viewmodel

import android.app.Application
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.color.MaterialColors
import com.google.android.material.snackbar.Snackbar
import com.hfad.android.storageapp.HumanAdapter
import com.hfad.android.storageapp.R
import com.hfad.android.storageapp.database.Repository
import com.hfad.android.storageapp.databinding.FragmentHumanListBinding
import com.hfad.android.storageapp.model.Human
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.abs

class HumanListFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository = Repository.get()
    private val swipeBackgroundDelete: ColorDrawable =
        ColorDrawable(Color.parseColor("#FF0000")) //red
    private val swipeBackgroundUpdate: ColorDrawable =
        ColorDrawable(Color.parseColor("#FFA500")) //orange
    private val deleteIcon: Drawable =
        ContextCompat.getDrawable(
            application.applicationContext,
            R.drawable.ic_delete
        )!! //TODO delete !!
    private val editIcon: Drawable =
        ContextCompat.getDrawable(application.applicationContext, R.drawable.ic_edit)!!


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

    val itemTouchHelper =     //TODO: Add icons to swaps ( i know, it is hard but i might make it)
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.LEFT) {
                    val humanToDelete = (viewHolder as HumanAdapter.HumanViewHolder).human
                    delete(humanToDelete)


                } else {

                }


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
                val iconMargin = itemView.height - (editIcon.intrinsicHeight ?: 0) / 2

                if (dX > 0) {    //swipe right
                    swipeBackgroundUpdate.setBounds(
                        itemView.left,
                        itemView.top,
                        dX.toInt(),
                        itemView.bottom
                    )
                    editIcon.setBounds(
                        itemView.left + iconMargin,
                        itemView.top + iconMargin,
                        itemView.left + iconMargin + editIcon.intrinsicWidth,
                        itemView.bottom - iconMargin
                    )
                    swipeBackgroundUpdate.draw(c)
                    editIcon?.draw(c)
                } else {        //swipe left
                    swipeBackgroundDelete.setBounds(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                    deleteIcon.setBounds(
                        itemView.right - iconMargin - deleteIcon.intrinsicWidth,
                        itemView.top + iconMargin,
                        itemView.right - iconMargin + deleteIcon.intrinsicWidth,
                        itemView.bottom - iconMargin
                    )


                }

                swipeBackgroundUpdate.draw(c)
                editIcon.draw(c)


                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        })

    companion object {

    }
}
