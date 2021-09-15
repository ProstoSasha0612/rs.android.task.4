package com.hfad.android.storageapp

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import java.nio.file.Files.delete

class SwipableItemTouchHelper(val swipeCallbacks: SwipeCallbacks) {
    private val swipeBackgroundDelete: ColorDrawable =
        ColorDrawable(Color.parseColor("#FF0000")) //red
    private val swipeBackgroundUpdate: ColorDrawable =
        ColorDrawable(Color.parseColor("#FFA500")) //orange

    //TODO: Add icons to swaps ( i know, it is hard but i might make it)
    val touchHelper =
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
                    swipeCallbacks.leftSwipe(humanToDelete)
                } else {
                    swipeCallbacks.rightSwipe()
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
//            val iconMargin = itemView.height - (editIcon.intrinsicHeight ?: 0) / 2

                if (dX > 0) {    //swipe right
                    swipeBackgroundUpdate.setBounds(
                        itemView.left,
                        itemView.top,
                        dX.toInt(),
                        itemView.bottom
                    )
//                editIcon.setBounds(
//                        itemView.left + iconMargin,
//                        itemView.top + iconMargin,
//                        itemView.left + iconMargin + editIcon.intrinsicWidth,
//                        itemView.bottom - iconMargin
//                )
                    swipeBackgroundUpdate.draw(c)
//                editIcon?.draw(c)
                } else {        //swipe left
                    swipeBackgroundDelete.setBounds(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
//                deleteIcon.setBounds(
//                        itemView.right - iconMargin - deleteIcon.intrinsicWidth,
//                        itemView.top + iconMargin,
//                        itemView.right - iconMargin + deleteIcon.intrinsicWidth,
//                        itemView.bottom - iconMargin
//                )
                    swipeBackgroundDelete.draw(c)
                }


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
}